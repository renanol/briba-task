package br.com.kamaleon.business.projeto.modulo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class SubModuloServiceImpl implements SubModuloService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<SubModulo> listaSubModulosPorProjeto(String idModulo){
		return entityManager.createQuery("select s from SubModulo s where s.modulo ='"+idModulo+"'", SubModulo.class).getResultList();
	}
}
