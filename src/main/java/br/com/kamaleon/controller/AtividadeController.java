package br.com.kamaleon.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import br.com.kamaleon.business.pessoa.cliente.ClienteService;
import br.com.kamaleon.business.pessoa.usuario.UsuarioService;
import br.com.kamaleon.business.projeto.Atividade;
import br.com.kamaleon.business.projeto.Projeto;
import br.com.kamaleon.business.projeto.atividade.AtividadeHistorico;
import br.com.kamaleon.business.projeto.atividade.AtividadeService;
import br.com.kamaleon.business.projeto.atividade.StatusAtividade;
import br.com.kamaleon.business.projeto.categoria.CategoriaService;
import br.com.kamaleon.business.projeto.modulo.Modulo;
import br.com.kamaleon.business.projeto.modulo.ModuloService;
import br.com.kamaleon.business.projeto.modulo.SubModulo;
import br.com.kamaleon.business.projeto.modulo.SubModuloService;
import br.com.kamaleon.business.projeto.prioridade.PrioridadeService;
import br.com.kamaleon.business.projeto.situacao.SituacaoService;
import br.com.kamaleon.business.projeto.versao.Versao;
import br.com.kamaleon.business.projeto.versao.VersaoService;
import br.com.kamaleon.generic.persistence.Filtro;
import br.com.kamaleon.generic.persistence.FiltroHibernate;
import br.com.kamaleon.generic.persistence.GenericDAO;
import br.com.kamaleon.generic.persistence.RepositorioException;
import br.com.kamaleon.util.Constantes;
import br.com.kamaleon.util.FuncoesData;
import br.com.kamaleon.util.FuncoesUsuario;
import br.com.kamaleon.util.ValidadorUniversal;

@RequestMapping("/atividades")
@Controller
@RooWebScaffold(path = "atividades", formBackingObject = Atividade.class)
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private SubModuloService subModuloService;

    @Autowired
    private PrioridadeService prioridadeService;

    @Autowired
    private SituacaoService situacaoService;

    @Autowired
    private VersaoService versaoService;
    

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Atividade atividade, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {

    	if (bindingResult.hasErrors()) {
    		populateEditForm(uiModel, atividade);
    		return "atividades/create";
    	}

    	uiModel.asMap().clear();
    	
    	atividadeService.saveAtividade(atividade);

    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
    	MultipartFile multipartFile = multipartRequest.getFile("arquivo");

    	try {
    		MultipartFile file = multipartFile;
    		InputStream inputStream = null;
    		FileOutputStream outputStream = null;

    		if (file.getSize() > 0) {

    			inputStream = file.getInputStream();

    			/*
    			if (file.getSize() > 10000) {
    				System.out.println("File Size:::" + file.getSize());    				
    				return "atividades/create";
    			}*/

    			File fileName = new File(Constantes.PATH_URL_UPLOAD + atividade.getId()+file.getOriginalFilename());

    			outputStream = new FileOutputStream(fileName);

    			/*int readBytes = 0;
    			byte[] buffer = new byte[10000];    			
    			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
    				outputStream.write(buffer, 0, readBytes);    				
    			}*/
    			
    			int readBytes = 0;    			
    			while ((readBytes = inputStream.read()) != -1) {
					outputStream.write(readBytes);
				}
    			
    			outputStream.flush();
    			outputStream.close();
    			inputStream.close();
    			atividade.setCaminhoArquivo(Constantes.URL_DOMINIO_UPLOAD + atividade.getId()+file.getOriginalFilename());
    			
    	    	atividadeService.updateAtividade(atividade);

    		}

    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}  	

    	return "redirect:/atividades/" + encodeUrlPathSegment(atividade.getId().toString(), httpServletRequest);
    	
    }

    public void populateEditForm(Model uiModel, Atividade atividade) {
    	
        uiModel.addAttribute("atividade", atividade);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("clientes", clienteService.findAllClientes());
        uiModel.addAttribute("usuarios", usuarioService.findAllUsuarios());
        uiModel.addAttribute("projetoes", Projeto.findAllProjetoes());
        uiModel.addAttribute("categorias", categoriaService.findAllCategorias());
        uiModel.addAttribute("moduloes", moduloService.findAllModuloes());
        uiModel.addAttribute("submoduloes", subModuloService.findAllSubModuloes());
        uiModel.addAttribute("prioridades", prioridadeService.findAllPrioridades());
        uiModel.addAttribute("situacaos", situacaoService.findAllSituacaos());
        uiModel.addAttribute("versaos", versaoService.findAllVersaos());
        uiModel.addAttribute("statusatividades", Arrays.asList(StatusAtividade.values()));
        
    }

    public void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("atividade_datainicio_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("atividade_dataprevista_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        Atividade atividadeNew = new Atividade();
        atividadeNew.setDataInicio(new Date());
        populateEditForm(uiModel, atividadeNew);
        return "atividades/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
    	Filtro filtro = new FiltroHibernate();

        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("atividade", atividadeService.findAtividade(id));
        uiModel.addAttribute("itemId", id);
                
        if(id != null){
        	
        	filtro.equals("atividade.id", id);
        	filtro.addOrder("data", false);
        	
        	try {      		
        		        		
				uiModel.addAttribute("atividadesHistorico", GenericDAO.getInstance().list(AtividadeHistorico.class, filtro));
				
			} catch (RepositorioException e) {
				
				System.out.println(e.getMessage());
				
			}
        }
        return "atividades/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("atividades", atividadeService.findAtividadeEntries(firstResult, sizeNo));
            float nrOfPages = (float) atividadeService.countAllAtividades() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("atividades", atividadeService.findAllAtividades());
        }
        
        uiModel.addAttribute("prioridades", prioridadeService.findAllPrioridades());
        uiModel.addAttribute("usuarios", usuarioService.findAllUsuarios());
        uiModel.addAttribute("clientes", clienteService.findAllClientes());
        uiModel.addAttribute("versaos", versaoService.findAllVersaos());
        uiModel.addAttribute("statusatividades", Arrays.asList(StatusAtividade.values()));
        addDateTimeFormatPatterns(uiModel);
        
        return "atividades/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Atividade atividade, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, atividade);
            return "atividades/update";
        }
        uiModel.asMap().clear();
        
        Atividade atividadeTmp = atividadeService.findAtividade(atividade.getId());
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Edição ");
        
        if( !!! atividadeTmp.getStatusAtividade().equals(atividade.getStatusAtividade()) ){
        	stringBuilder.append("Alterou Status de "+atividadeTmp.getStatusAtividade()+" para "+atividade.getStatusAtividade());
        }
        
        AtividadeHistorico atividadeHistorico = new AtividadeHistorico();
        atividadeHistorico.setAcao(stringBuilder.toString());
        atividadeHistorico.setDescricao(atividade.getComentario());
        atividadeHistorico.setData(new Date());
        atividadeHistorico.setHorasTrabalhada(String.valueOf(atividade.getTempoGasto()));
        atividadeHistorico.setProgresso(atividade.getProgresso());
        atividadeHistorico.setAtividade(atividade);
        atividadeHistorico.setUsuario(FuncoesUsuario.getUsuarioLogged());
        atividadeHistorico.persist();
        
        atividade.setComentario(null);
        atividade.setTempoGasto(0);        
        
        atividadeService.updateAtividade(atividade);
        
        return "redirect:/atividades/" + encodeUrlPathSegment(atividade.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        
    	populateEditForm(uiModel, atividadeService.findAtividade(id));
        
        return "atividades/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Atividade atividade = atividadeService.findAtividade(id);
        atividadeService.deleteAtividade(atividade);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/atividades";
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

    @RequestMapping("/json/{idProjeto}")
    @ResponseBody
    public List<Modulo> getModulos(@PathVariable String idProjeto) throws RepositorioException {
        
        Filtro filtro = new FiltroHibernate();
        
        filtro.equals("projeto.id", Long.parseLong(idProjeto));
        
        return GenericDAO.getInstance().list(Modulo.class, filtro);
    }

    @RequestMapping("/json/sub/{idModulo}")
    @ResponseBody
    public List<SubModulo> getSubModulos(@PathVariable String idModulo) throws RepositorioException {
        Filtro filtro = new FiltroHibernate();
        filtro.equals("modulo.id", Long.parseLong(idModulo));
        return GenericDAO.getInstance().list(SubModulo.class, filtro);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filtro")
    @ResponseBody
    public List<Atividade> filtroAtividade(HttpServletRequest httpServletRequest) throws RepositorioException, ParseException {
        
    	String titulo = httpServletRequest.getParameter("titulo");
        String codigo = httpServletRequest.getParameter("codigo");
        String projeto = httpServletRequest.getParameter("projeto");
        String modulo = httpServletRequest.getParameter("modulo");
        String subModulo = httpServletRequest.getParameter("submodulo");
        String versao = httpServletRequest.getParameter("versao");
        String cliente = httpServletRequest.getParameter("cliente");
        String usuario = httpServletRequest.getParameter("usuario");
        String prioridade = httpServletRequest.getParameter("prioridade");
        String status = httpServletRequest.getParameter("status");
        String dataInicio = httpServletRequest.getParameter("dataInicio");
        String dataFinal = httpServletRequest.getParameter("dataFinal");
        String commitado = httpServletRequest.getParameter("commitado");


        Filtro filtro = new FiltroHibernate();
                                
        if (ValidadorUniversal.check(titulo)) {
            filtro.ilike("titulo", "%" + titulo + "%");
        }
        
        if (ValidadorUniversal.check(codigo)) {
        	Long idCodigo = Long.parseLong(codigo);
        	if(idCodigo > 0){
        		filtro.equals("id", idCodigo);
        	}
        }
        
       if(ValidadorUniversal.check(projeto)){
        	Long idProjeto = Long.parseLong(projeto);
        	if(idProjeto > 0){
        		filtro.equals("projeto.id", idProjeto);
        	}
        }
        
        if(ValidadorUniversal.check(modulo)){
        	Long idModulo = Long.parseLong(modulo);
        	if(idModulo > 0){
        		filtro.equals("modulo.id", idModulo);
        	}
        	
        }
        
        if(ValidadorUniversal.check(subModulo)){
        	Long idSubModulo = Long.parseLong(subModulo);
        	if(idSubModulo > 0){
        		filtro.equals("subModulo.id", idSubModulo);
        	}
        }             
        
        if(ValidadorUniversal.check(versao)){
        	Long idVersao = Long.parseLong(versao);
        	if(idVersao > 0){
        		filtro.equals("versao.id", idVersao);
        	}
        }  
        
        if(ValidadorUniversal.check(cliente)){
        	Long idCliente = Long.parseLong(cliente);
        	if(idCliente > 0){
        		filtro.equals("cliente.id", idCliente);
        	}
        } 

        if(ValidadorUniversal.check(usuario)){
        	Long idUsuario = Long.parseLong(usuario);
        	if(idUsuario > 0){
        		filtro.equals("usuarioAlocado.id", idUsuario);
        	}
        } 
        
        if(ValidadorUniversal.check(prioridade)){
        	Long idPrioridade = Long.parseLong(prioridade);
        	if(idPrioridade > 0){
        		filtro.equals("prioridade.id", idPrioridade);
        	}
        }
        
        if(ValidadorUniversal.check(status)){
        	if(!status.equals("0")){
        		filtro.equals("statusAtividade", StatusAtividade.valueOf(status));
        	}
        }       
             
        if(ValidadorUniversal.check(dataInicio) && ValidadorUniversal.check(dataFinal)){
        	filtro.between("dataInicio", FuncoesData.getData(dataInicio), FuncoesData.getData(dataFinal));
        }
        
        if(ValidadorUniversal.check(commitado)){
        	filtro.equals("commitado", Boolean.parseBoolean(commitado));
        }
        
        
        filtro.addOrder("dataInicio", false);
               
        return GenericDAO.getInstance().list(Atividade.class, filtro);
    }
    
    @RequestMapping("/finalizarAtividade/{idAtividade}")    
    public String finalizarAtividae(@PathVariable String idAtividade, Model uiModel) throws RepositorioException {

    	Atividade atividade = Atividade.findAtividade(Long.parseLong(idAtividade));
    	
    	uiModel.addAttribute("atividade", atividade);
        
        return "atividades/finish";
    }
    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public void finalizar(){
    	
    }
    
    public void salvarHistorico(Versao versao){
    	versao.persist();
    }
}

