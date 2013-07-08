package br.com.kamaleon.generic.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.springframework.roo.addon.configurable.RooConfigurable;

@RooConfigurable
public class GenericDAO {

	private static GenericDAO genericDAO;

	@PersistenceContext
	private EntityManager entityManager;

	private GenericDAO(){}

	public static GenericDAO getInstance(){
		if(genericDAO == null){
			synchronized (GenericDAO.class) {
				genericDAO = new GenericDAO();			
			}
		}
		return genericDAO;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> clazz, Filtro filtro) throws RepositorioException {
		try {
			FiltroHibernate filtroH = null;

			if (filtro instanceof FiltroHibernate) {
				filtroH = (FiltroHibernate) filtro;
			} 

			Criteria cri = filtroH.getCriteria(clazz);
			return cri.list();

		} catch (Exception e) {
			throw new RepositorioException(e);
		}
	}
	
	 /**
	  * @param <T>
	  * @param clazz
	  * @param filtro
	  * @return
	  * @throws RepositorioException
	  */
	 @SuppressWarnings("unchecked")
	 public <T> T getObjeto(Class<T> clazz, Filtro filtro) throws RepositorioException {
		 try {
			 FiltroHibernate filtroH = null;
			 if (filtro instanceof FiltroHibernate) {
				 filtroH = (FiltroHibernate) filtro;
			 } 
			 
			 Criteria cri = filtroH.getCriteria(clazz);

			 return (T)cri.uniqueResult();
		 } catch (Exception e) {
			 throw new RepositorioException(e);
		 }
	 }

}
