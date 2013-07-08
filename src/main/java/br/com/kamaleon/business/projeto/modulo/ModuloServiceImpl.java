package br.com.kamaleon.business.projeto.modulo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ModuloServiceImpl implements ModuloService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Modulo> listaModulosPorProjeto(String idProjeto){
		
		
		return entityManager.createQuery("select m from Modulo m where m.projeto = "+idProjeto, Modulo.class).getResultList();
		
	}
	
}
