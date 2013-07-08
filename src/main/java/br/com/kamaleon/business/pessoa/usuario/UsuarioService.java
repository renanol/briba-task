package br.com.kamaleon.business.pessoa.usuario;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { br.com.kamaleon.business.pessoa.usuario.Usuario.class })
public interface UsuarioService {
	
	public Usuario findUsuarioLogin(String login);
	
}
