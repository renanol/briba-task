package br.com.kamaleon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.business.pessoa.usuario.UsuarioService;

@RequestMapping("/relatorio/")
@Controller
public class RelatorioController {
	
		@Autowired
		private UsuarioService usuarioService;
	
	    @RequestMapping(method = RequestMethod.GET , value = "pdf")
	    public ModelAndView generatePdfReport(ModelAndView modelAndView){

	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	        
	        List<Usuario> usuarios = usuarioService.findAllUsuarios();

	        JRDataSource JRdataSource = new JRBeanCollectionDataSource(usuarios);

	        parameterMap.put("dataSource", JRdataSource);

	        //pdfReport bean has ben declared in the jasper-views.xml file
	        modelAndView = new ModelAndView("jasperPdfView", parameterMap);

	        return modelAndView;

	    }

}
