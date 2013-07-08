package br.com.kamaleon.generic.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;
import org.springframework.roo.addon.configurable.RooConfigurable;

@RooConfigurable
public class HibernateUtil {

	private final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();

	private final ThreadLocal<Boolean> threadServlet = new ThreadLocal<Boolean>();
	
	private final ThreadLocal<Boolean> threadRollBack = new ThreadLocal<Boolean>();

    private final String ERRO_CONECTION_LOST = "error.perca.conexao.bd";

    @PersistenceContext()
    protected EntityManager entityManager;
    
    private UserTransaction userTransaction;
    
    private DataSource ds = null;
    
    private boolean passouServlet = false;
    
    private boolean fazerRollBack = false;
    
	public Session getSession() {
		Session emi = (Session) entityManager.getDelegate();
		threadSession.set(emi);
		Session s = (Session) threadSession.get();
		return s;
	}
	

	public void beginTransaction() {
		if (userTransaction != null) {
			try {
				if (userTransaction.getStatus() != Status.STATUS_ACTIVE) {
					userTransaction.begin();
					System.out.println("open transaction");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ERRO_CONECTION_LOST);
			}
		}
	}

	public void commitTransaction() {
		if (userTransaction != null) {
			try {
				if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
					userTransaction.commit();
					System.out.println("commit transaction");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ERRO_CONECTION_LOST);
			}
		}
	}

	public void rollbackTransaction() {
		if (userTransaction != null) {
			try {
				userTransaction.rollback();
				System.out.println("************** fez rollback *************");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ERRO_CONECTION_LOST);
			}
		}
	}
	
   public void setEntityManager(EntityManager em) {
	   System.out.println("Entity > "+em);
	   if (em != null) {
			entityManager = em;
			EntityManagerImpl emi = (EntityManagerImpl) em.getDelegate();
			threadSession.set(emi.getSession());
		} else {
			threadSession.set(null);
		}
   }

   public EntityManager getEntityManager() {
	   return entityManager;
   }
   
   public void flush() throws RepositorioException {
	   try{
		   getEntityManager().flush();
		   getSession().flush();
	   }catch(HibernateException e)
	   {
		   e.printStackTrace();
		   
           throw new RepositorioException(e);
	   }
   }
   
   public void clear() {
	   getEntityManager().clear();
	   getSession().clear();
   }
   
   public void evict(Object objeto) {
	   getSession().evict(objeto);
   }
   
   public void removeSession() {
	   threadSession.remove();
   }
   
   public boolean getPassouServlet() {
	   return (threadServlet.get() == null?false:threadServlet.get());
   }
   
   public void setPassouServlet(boolean passou) {
	   threadServlet.set(passou);
	   passouServlet = passou;
   }

	public boolean isFazerRollBack() {
		return threadRollBack.get() == null ? false: threadRollBack.get();
	}

	public void setFazerRollBack(boolean rollBack) {
		threadRollBack.set(rollBack);
		fazerRollBack = rollBack;
	}

	public DataSource getDataSource() {
		return ds;
	}

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public UserTransaction getUserTransaction() {
		return userTransaction;
	}

	public void setUserTransaction(UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

}
