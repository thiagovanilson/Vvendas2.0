package model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ProductModel;


public class Persist implements Serializable{

//	public static void main(String[] args) {
//		new Percistence().save("");
//	}
	
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");;
//	@Inject
//	protected EntityManager manager;
	
//	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	
	public boolean save(Object o) {
		EntityManager manager = factory.createEntityManager();
	   
		try {	   
		    
		    manager.getTransaction().begin();        
		    manager.persist(o);
		    manager.getTransaction().commit();   
		   
		    return true;
	    } catch (Exception e) {
	    	System.out.println("------"+e.getMessage());	
	    	
	    }finally {
	    	 manager.close();
//	    	 factory.close();
	    }
		return false;
	}
	
	//Se der erro receber o tipo do objeto certo
	public boolean delete(Object o) {
		EntityManager manager = factory.createEntityManager();
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
//	    	factory.close();
	    }
		return false;
	}
	
		
	@SuppressWarnings("unchecked")
	public List<String> find(String sqlCode){
		
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
	    
	    List<String> result = manager.createQuery(sqlCode).getResultList();
	    
	    manager.close();
	    return result;
	}
}
