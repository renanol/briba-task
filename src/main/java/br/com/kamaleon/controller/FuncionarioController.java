package br.com.kamaleon.controller;

import br.com.kamaleon.business.endereco.EnderecoService;
import br.com.kamaleon.business.pessoa.funcionario.CargoService;
import br.com.kamaleon.business.pessoa.funcionario.Funcionario;
import br.com.kamaleon.business.pessoa.usuario.GrupoService;
import br.com.kamaleon.business.pessoa.usuario.UsuarioService;
import br.com.kamaleon.generic.Status;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/funcionarios")
@Controller
@RooWebScaffold(path = "funcionarios", formBackingObject = Funcionario.class)
public class FuncionarioController {

    @Autowired
    CargoService cargoService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EnderecoService enderecoService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Funcionario funcionario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, funcionario);
            return "funcionarios/create";
        }
        uiModel.asMap().clear();
        cargoService.saveCargo(funcionario.getCargo());
        usuarioService.saveUsuario(funcionario.getUsuario());
        funcionarioService.saveFuncionario(funcionario);
        return "redirect:/funcionarios/" + encodeUrlPathSegment(funcionario.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Funcionario funcionario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, funcionario);
            return "funcionarios/update";
        }
        uiModel.asMap().clear();
        System.out.println(funcionario.getEndereco().getId());
        System.out.println(funcionario.getUsuario().getId());
        usuarioService.updateUsuario(funcionario.getUsuario());
        funcionarioService.updateFuncionario(funcionario);
        return "redirect:/funcionarios/" + encodeUrlPathSegment(funcionario.getId().toString(), httpServletRequest);
    }

    private void populateEditForm(Model uiModel, Funcionario funcionario) {
        uiModel.addAttribute("funcionario", funcionario);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("enderecoes", enderecoService.findAllEnderecoes());
        uiModel.addAttribute("cargoes", cargoService.findAllCargoes());
        uiModel.addAttribute("usuarios", usuarioService.findAllUsuarios());
        uiModel.addAttribute("statuses", Arrays.asList(Status.values()));
        uiModel.addAttribute("grupoes", grupoService.findAllGrupoes());
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("funcionario", funcionarioService.findFuncionario(id));
        uiModel.addAttribute("itemId", id);
        return "funcionarios/show";
    }
}
