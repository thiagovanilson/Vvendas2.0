package model.dao;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.UserModel;

public class UserDAO extends Persist{
	
	private UserModel um;
	
	public UserDAO(UserModel u){
		um = u;
	}	
	
	public boolean delete(String id) {
		
		um = getUser(id);

		return delete(um);
	}
	
	public UserModel getUser(String key) {

		if(key == null || key.equals(""))
			return new UserModel();
    	EntityManager manager = factory.createEntityManager();

	    UserModel um = manager.find(UserModel.class, key);
	    manager.close();
	    return um;

	}
	public boolean edit(UserModel o) {
	    try {
		    EntityManager manager = factory.createEntityManager();
		    
		    manager.getTransaction().begin();
		    UserModel u = getUser(o.getCpf());
		    	if(u == null)
		    		return false;	
		    u = manager.merge(u);
		    
		    u.setName(o.getName());
		    u.setUsergroup(o.getUsergroup());
		    u.setEmail(o.getEmail());
		    
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
	    EntityManager manager = factory.createEntityManager();
    	
	    ArrayList<UserModel> usrs = (ArrayList<UserModel>) manager.createQuery(
    			"SELECT u FROM UserModel u WHERE u.name LIKE :uname")
    			.setParameter("uname", "%"+key+"%")
    			.setMaxResults(100)
    			.getResultList();
	    manager.close();
	    return usrs;
	    
	}


	public int qtdUsers() {
	    EntityManager manager = factory.createEntityManager();
		int qtd = Integer.parseInt( manager.createQuery(
    			"select count(u.cpf) from UserModel u where 1=1")
    			.getSingleResult().toString());
		
		manager.close();
		return qtd;
	}	
}
