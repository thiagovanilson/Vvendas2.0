package model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Persist {

//	public static void main(String[] args) {
//		new Percistence().save("");
//	}
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
	    	e.getMessage();	    	
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
	public boolean edit(UserModel o) {
	    try {
		    EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		    EntityManager manager = factory.createEntityManager();
		    
		    manager.getTransaction().begin();
		    UserModel u = getUser(o.getCpf());
		    	if(u == null)
		    		return false;	
		    u = manager.merge(u);
		    
		    u.setName(o.getName());
		    u.setPass(o.getPass());
		    u.setUsergroup(o.getUsergroup());
		    
		    manager.persist(u);
		    manager.getTransaction().commit();    
		    manager.close();

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    	return false;
	    }
	}
	public boolean edit(ProductModel o) {
	    try {
		    EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		    EntityManager manager = factory.createEntityManager();
		    
		    manager.getTransaction().begin();
		    ProductModel p = getProduct(o.getId());
		    
	    	if(p == null)
	    		return false;	
	    	
		    p = manager.merge(p);
		    
		    p.setName(o.getName());
		    p.setPrice(o.getPrice());
		    p.setQuantity(o.getQuantity());
		    p.setDescricao(o.getDescricao());
		    
		    manager.persist(p);
		    manager.getTransaction().commit();    
		    manager.close();

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    	return false;
	    }
	}
	public UserModel getUser(String key) {
	    //Ler
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();

	    return manager.find(UserModel.class, key);

	}
	public ProductModel getProduct(String key) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();

	    return manager.find(ProductModel.class, key);

	}	
	public ArrayList<ProductModel> getProducts(String key) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	return (ArrayList<ProductModel>) manager.createQuery(
    			"SELECT p FROM ProductModel p WHERE p.name LIKE :pname")
    			.setParameter("pname", "%"+key+"%")
    			.setMaxResults(10)
    			.getResultList();


	}	
	
	public List<String> find(String sqlCode){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
	    
			   return manager
			        .createQuery(sqlCode)
			        .getResultList();
	}
}
