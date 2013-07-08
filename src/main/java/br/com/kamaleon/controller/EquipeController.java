package br.com.kamaleon.controller;

import br.com.kamaleon.business.pessoa.usuario.Equipe;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/equipes")
@Controller
@RooWebScaffold(path = "equipes", formBackingObject = Equipe.class)
public class EquipeController {
}
