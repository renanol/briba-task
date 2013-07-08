package br.com.kamaleon.controller;

import br.com.kamaleon.business.projeto.categoria.Categoria;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/categorias")
@Controller
@RooWebScaffold(path = "categorias", formBackingObject = Categoria.class)
public class CategoriaController {
}
