package br.com.kamaleon.business.pessoa.funcionario;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { br.com.kamaleon.business.pessoa.funcionario.Funcionario.class })
public interface FuncionarioService {
	public Funcionario buscarFuncionarioPorId(Long id);
}
