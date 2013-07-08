package br.com.kamaleon.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import br.com.kamaleon.business.projeto.Atividade;
import br.com.kamaleon.business.projeto.atividade.AtividadeHistorico;
import br.com.kamaleon.business.projeto.atividade.AtividadeService;
import br.com.kamaleon.business.projeto.atividade.StatusAtividade;
import br.com.kamaleon.business.projeto.versao.Versao;
import br.com.kamaleon.business.projeto.versao.VersaoService;
import br.com.kamaleon.generic.persistence.Filtro;
import br.com.kamaleon.generic.persistence.FiltroHibernate;
import br.com.kamaleon.generic.persistence.GenericDAO;
import br.com.kamaleon.generic.persistence.RepositorioException;
import br.com.kamaleon.util.FuncoesUsuario;

@RequestMapping("/versaos")
@Controller
@RooWebScaffold(path = "versaos", formBackingObject = Versao.class)
public class VersaoController {

    @Autowired
    private VersaoService versaoService;

    @Autowired
    private AtividadeService atividadeService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Versao versao, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, versao);
            return "versaos/create";
        }  	
        
        uiModel.asMap().clear();
        
        versaoService.saveVersao(versao);
        
        for(Atividade atividade: versao.getAtividades()){
        	
        	atividade.setVersao(versao);        	
        	AtividadeHistorico atividadeHistorico = new AtividadeHistorico();
    		atividadeHistorico.setAtividade(atividade);
    		atividadeHistorico.setAcao("Versão "+versao.getDescricao()+", Adicionada");
    		atividadeHistorico.setData(new Date());
    		atividadeHistorico.setUsuario(FuncoesUsuario.getUsuarioLogged());
    		atividadeHistorico.persist();
    		
        }        
        
        return "redirect:/versaos/" + encodeUrlPathSegment(versao.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	Versao versao = new Versao();
    	versao.setData(new Date());
        populateEditForm(uiModel, versao);
        return "versaos/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) throws RepositorioException {
    	
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("versao", versaoService.findVersao(id));
        uiModel.addAttribute("itemId", id);
        Filtro filtro = new FiltroHibernate();
        filtro.equals("versao.id",id);        
        uiModel.addAttribute("atividades", atividadeService.findAllAtividades());
        
        return "versaos/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("versaos", versaoService.findVersaoEntries(firstResult, sizeNo));
            float nrOfPages = (float) versaoService.countAllVersaos() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("versaos", versaoService.findAllVersaos());
        }
        addDateTimeFormatPatterns(uiModel);
        return "versaos/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Versao versao, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, versao);
            return "versaos/update";
        }
    	
        uiModel.asMap().clear();     
        
        
        versaoService.updateVersao(versao);
        return "redirect:/versaos/" + encodeUrlPathSegment(versao.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, versaoService.findVersao(id));
        
        return "versaos/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Versao versao = versaoService.findVersao(id);
        versaoService.deleteVersao(versao);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/versaos";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("versao_data_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, Versao versao) {

    	uiModel.addAttribute("versao", versao);
        addDateTimeFormatPatterns(uiModel);
                
		uiModel.addAttribute("atividades", atividadeService.findAllAtividades());

    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    @RequestMapping("atividades/{id}")
    @ResponseBody
    public List<br.com.kamaleon.business.projeto.Atividade> filtroAtividade(@PathVariable("id") Long idProjeto) throws RepositorioException {
        
    	Filtro filtro = new FiltroHibernate();
        if (idProjeto > 0) {
            filtro.equals("projeto.id", idProjeto);
        }
        
        filtro.equals("versao.id", 1);
        filtro.equals("statusAtividade", StatusAtividade.FECHADO);
        
        return GenericDAO.getInstance().list(Atividade.class, filtro);
        
    }
}
