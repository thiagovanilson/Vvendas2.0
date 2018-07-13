package model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Persist implements Serializable{

	private static final long serialVersionUID = 5364024655861926954L;
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");;
	
	@Inject
	protected EntityManager manager;
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	public boolean save(Object o) {
		manager = factory.createEntityManager();
	   
		try {	   
		    
		    manager.getTransaction().begin();        
		    manager.persist(o);
		    manager.getTransaction().commit();   
		   
		    return true;
	    } catch (Exception e) {
	    	
	    }finally {
	    	 manager.close();
	    }
		return false;
	}
	
	public boolean delete(Object o) {
		manager = factory.createEntityManager();
	    try {
		    
		    manager.getTransaction().begin();
		    o = manager.merge(o);
		    
		    manager.remove(o);
		    manager.getTransaction().commit();    

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    }finally {
	    	manager.close();
	    }
		return false;
	}
	
		
	@SuppressWarnings("unchecked")
	public List<String> find(String sqlCode){
		
	    manager = factory.createEntityManager();
	    
	    List<String> result = manager.createQuery(sqlCode).getResultList();
	    
	    manager.close();
	    return result;
	}
}
