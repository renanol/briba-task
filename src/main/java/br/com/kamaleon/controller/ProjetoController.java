package br.com.kamaleon.controller;

import br.com.kamaleon.business.projeto.Projeto;
import java.util.List;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/projetoes")
@Controller
@RooWebScaffold(path = "projetoes", formBackingObject = Projeto.class)
public class ProjetoController {

    @RequestMapping("/json")
    @ResponseBody
    public List<br.com.kamaleon.business.projeto.Projeto> getProjetosJson() {
        return Projeto.findAllProjetoes();
    }
}
