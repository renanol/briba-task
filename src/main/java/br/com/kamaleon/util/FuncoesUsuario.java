package br.com.kamaleon.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.kamaleon.business.pessoa.usuario.Usuario;
import br.com.kamaleon.generic.persistence.Filtro;
import br.com.kamaleon.generic.persistence.FiltroHibernate;
import br.com.kamaleon.generic.persistence.GenericDAO;
import br.com.kamaleon.generic.persistence.RepositorioException;
import de.bripkens.gravatar.DefaultImage;
import de.bripkens.gravatar.Gravatar;
import de.bripkens.gravatar.Rating;

public class FuncoesUsuario {
	
	
	public static Usuario getUsuarioLogged(){
		
		Usuario usuario = null;
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		if(usuarioLogado instanceof User){
			
			User user = (User) usuarioLogado;
			
			System.out.println(user.getUsername());
			
			Filtro filtro = new FiltroHibernate();
			
			filtro.equals("login", user.getUsername());			
			
			try {
				usuario = GenericDAO.getInstance().getObjeto(Usuario.class, filtro);
			} catch (RepositorioException e) {
				System.out.println(e.getMessage());;
			}
			
		}
		
		return usuario;
	}
	
	public static String getGravatarUsuario(String email){
		
		String gravatarImageURL = new Gravatar()
	    .setSize(25)
	    .setHttps(true)
	    .setRating(Rating.PARENTAL_GUIDANCE_SUGGESTED)
	    .setStandardDefaultImage(DefaultImage.MONSTER)
	    .getUrl(email);
		
		return gravatarImageURL;
	}

}
