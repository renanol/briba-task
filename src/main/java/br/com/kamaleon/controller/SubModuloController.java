package br.com.kamaleon.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.kamaleon.business.projeto.Projeto;
import br.com.kamaleon.business.projeto.modulo.Modulo;
import br.com.kamaleon.business.projeto.modulo.SubModulo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/submoduloes")
@Controller
@RooWebScaffold(path = "submoduloes", formBackingObject = SubModulo.class)
public class SubModuloController {
			
	void populateEditForm(Model uiModel, SubModulo subModulo) {
        uiModel.addAttribute("subModulo", subModulo);
        uiModel.addAttribute("projetos", Projeto.findAllProjetoes());
        uiModel.addAttribute("moduloes", Modulo.findAllModuloes());
        
    }
	
	 @RequestMapping(params = "form", produces = "text/html")
	    public String createForm(Model uiModel) {
	        populateEditForm(uiModel, new SubModulo());
	        List<String[]> dependencies = new ArrayList<String[]>();
	        if (Projeto.findAllProjetoes().size() == 0) {
	            dependencies.add(new String[] { "projeto", "projetos" });
	        }
	        uiModel.addAttribute("dependencies", dependencies);
	        return "submoduloes/create";
	    }
}
