package br.com.kamaleon.controller;

import br.com.kamaleon.business.projeto.modulo.Modulo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/moduloes")
@Controller
@RooWebScaffold(path = "moduloes", formBackingObject = Modulo.class)
public class ModuloController {
}
