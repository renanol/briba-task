package br.com.kamaleon.controller;

import br.com.kamaleon.generic.CodigoKamaleon;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/codigokamaleons")
@Controller
@RooWebScaffold(path = "codigokamaleons", formBackingObject = CodigoKamaleon.class)
public class CodigoKamaleonController {
}
