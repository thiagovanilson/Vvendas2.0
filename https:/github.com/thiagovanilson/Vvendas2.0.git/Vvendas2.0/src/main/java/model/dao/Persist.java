package model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ProductModel;


public class Persist implements Serializable{

//	public static void main(String[] args) {
//		new Percistence().save("");
//	}
//	@Inject
//	private EntityManagerFactory emf;
//	@Inject
//	protected EntityManager manager;
	
	public boolean save(Object o) {
	    try {
	   
		    EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		    EntityManager manager = factory.createEntityManager();
		    
		    manager.getTransaction().begin();        
		    manager.persist(o);
		    manager.getTransaction().commit();    
		    manager.close();

		    return true;
	    } catch (Exception e) {
	    	System.out.println("------"+e.getMessage());	
	    	
	    }
		return false;
	}
	
	//Se der erro receber o tipo do objeto certo
	public boolean delete(Object o) {
	    try {
		    EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		    EntityManager manager = factory.createEntityManager();
		    
		    manager.getTransaction().begin();
		    o = manager.merge(o);
		    
		    manager.remove(o);
		    manager.getTransaction().commit();    
		    manager.close();

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    }
		return false;
	}
	
	public boolean edit(ProductModel o) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		EntityManager manager = factory.createEntityManager();
	    try {
		    
		    manager.getTransaction().begin();
		    ProductModel p = getProduct(o.getId());
		    
	    	if(p == null)
	    		return false;	
	    	
		    p = manager.merge(p);
		    
		    p.setName(o.getName());
		    p.setPrice(o.getPrice());
		    p.setQuantity(o.getQuantity());
		    p.setDescricao(o.getDescricao());
		    p.setMedida(o.getMedida());
		    p.setTipoMedida(o.getTipoMedida());
		    
		    manager.persist(p);
		    manager.getTransaction().commit();    

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    	return false;
	    }finally {
	    	manager.close();
	    	
	    }
	}
	
	public ProductModel getProduct(String key) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();

	    return manager.find(ProductModel.class, key);

	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ProductModel> getProducts(String key) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	return (ArrayList<ProductModel>) manager.createQuery(
    			"SELECT p FROM ProductModel p WHERE p.name LIKE :pname")
    			.setParameter("pname", "%"+key+"%")
    			.setMaxResults(100)
    			.getResultList();


	}	
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> find(String sqlCode){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
	    
			   return manager
			        .createQuery(sqlCode)
			        .getResultList();
	}
}
