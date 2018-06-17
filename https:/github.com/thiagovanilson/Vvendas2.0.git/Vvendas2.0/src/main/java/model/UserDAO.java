package model;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO extends Persist{
	
	private UserModel um;
	
//	@Inject
//	private EntityManager manager;
//	
	public UserDAO(UserModel u){
		um = u;
	}
	
	
	public boolean delete(String id) {
		
		um = getUser(id);

		return delete(um);
	}
	
		
//	public boolean save() {
//		return new Persist().save(um);
//	}
	
	public UserModel getUser(String key) {
	    //Ler
		if(key == null || key.equals(""))
			return new UserModel();
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();

	    UserModel um = manager.find(UserModel.class, key);
	    manager.close();
	    return um;

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
		    u.setUsergroup(o.getUsergroup());
		    if(o.getPass() != null && !o.getPass().equals(""))
		    	u.setPass(o.getPass());
		    
		    manager.persist(u);
		    manager.getTransaction().commit();    
		    manager.close();

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    	return false;
	    }
	}
	@SuppressWarnings("unchecked")
	public ArrayList<UserModel> getUsers(String key) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager2 = factory.createEntityManager();
    	
    	return (ArrayList<UserModel>) manager2.createQuery(
    			"SELECT u FROM UserModel u WHERE u.name LIKE :uname")
    			.setParameter("uname", "%"+key+"%")
    			.setMaxResults(10)
    			.getResultList();


	}


	public int qtdUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
		return Integer.parseInt( manager.createQuery(
    			"select count(u.cpf) from UserModel u where 1=1")
    			.getSingleResult().toString());
	}	
}
