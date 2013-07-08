package br.com.kamaleon.controller;

import br.com.kamaleon.business.pessoa.usuario.Grupo;
import br.com.kamaleon.generic.Status;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/grupoes")
@Controller
@RooWebScaffold(path = "grupoes", formBackingObject = Grupo.class)
public class GrupoController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Grupo grupo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, grupo);
            return "grupoes/create";
        }
        uiModel.asMap().clear();
        grupo.setDescricao(grupo.getDescricao().toUpperCase());
        grupo.setStatus(Status.ATIVO);
        grupoService.saveGrupo(grupo);
        return "redirect:/grupoes/" + encodeUrlPathSegment(grupo.getId().toString(), httpServletRequest);
    }
}
