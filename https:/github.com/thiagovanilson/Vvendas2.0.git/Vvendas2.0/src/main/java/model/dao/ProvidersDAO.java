package model.dao;

import java.util.List;

import javax.persistence.*;

import model.ProviderModel;

public class ProvidersDAO extends Persist {

	
	public ProviderModel getProvider(String cnpj) {
		if(cnpj == null || cnpj.contains("_"))
			return null;
		
    	EntityManager manager = factory.createEntityManager();

	    ProviderModel um = manager.find(ProviderModel.class, cnpj);
	    manager.close();

	    return um;
	}
	public boolean edit(ProviderModel pm) {
		EntityManager manager = factory.createEntityManager();
	    
		try {
		    
		    manager.getTransaction().begin();
		    ProviderModel p = getProvider(pm.getCnpj());
		    
	    	if(p == null)
	    		return false;	
	    	
		    p = manager.merge(p);
		  
		    p.setEmail(pm.getEmail());
		    p.setName (pm.getName ());
		    p.setTel  (pm.getTel()  );
		    
		    manager.persist(p);
		    manager.getTransaction().commit();    

		    return true;
	    } catch (Exception e) {
	    	manager.getTransaction().rollback();
	    	e.getMessage();	    	
	    	return false;
	    }finally {
	    	manager.close();
	    }
	}
	public List<ProviderModel> getByName(String name) {
    	EntityManager manager = factory.createEntityManager();    	
    	
    	List<ProviderModel> temp =  manager.createQuery(
    			"SELECT p FROM ProviderModel p WHERE p.name LIKE :name")
    			.setParameter("name", "%" + name + "%")
    			.setMaxResults(100)
    			.getResultList();
    	manager.close();
    	
    	return temp;
	}
}
