package br.com.kamaleon.controller;

import br.com.kamaleon.business.projeto.situacao.Situacao;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/situacaos")
@Controller
@RooWebScaffold(path = "situacaos", formBackingObject = Situacao.class)
public class SituacaoController {
}
