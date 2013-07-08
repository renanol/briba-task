package br.com.kamaleon.controller;

import br.com.kamaleon.business.empesa.setor.Setor;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/setors")
@Controller
@RooWebScaffold(path = "setors", formBackingObject = Setor.class)
public class SetorController {
}
