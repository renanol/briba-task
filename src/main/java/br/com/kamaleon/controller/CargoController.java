package br.com.kamaleon.controller;

import br.com.kamaleon.business.pessoa.funcionario.Cargo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cargoes")
@Controller
@RooWebScaffold(path = "cargoes", formBackingObject = Cargo.class)
public class CargoController {
}
