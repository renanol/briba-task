package br.com.kamaleon.controller;

import br.com.kamaleon.business.projeto.prioridade.Prioridade;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/prioridades")
@Controller
@RooWebScaffold(path = "prioridades", formBackingObject = Prioridade.class)
public class PrioridadeController {
}
