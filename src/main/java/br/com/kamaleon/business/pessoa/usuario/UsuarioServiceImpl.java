package br.com.kamaleon.business.pessoa.usuario;

import br.com.kamaleon.generic.persistence.Filtro;
import br.com.kamaleon.generic.persistence.FiltroHibernate;
import br.com.kamaleon.generic.persistence.GenericDAO;
import br.com.kamaleon.generic.persistence.RepositorioException;


public class UsuarioServiceImpl implements UsuarioService {
	
	@Override
	public Usuario findUsuarioLogin(String login) {
		
		Filtro filtro = new FiltroHibernate();
		
		filtro.equals("login", login);
		
		Usuario usuario = null;
		
		try {
			usuario = GenericDAO.getInstance().getObjeto(Usuario.class, filtro);
		} catch (RepositorioException e) {
			System.out.println(e.getMessage());;
		}

		return usuario;
		
	}
}
