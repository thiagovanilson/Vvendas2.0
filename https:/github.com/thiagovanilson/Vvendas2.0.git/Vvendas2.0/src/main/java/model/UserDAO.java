package model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {

	private UserModel um;
	
	public UserDAO(UserModel u){
		um = u;
	}
	
	public boolean save() {
		return new Persist().save(um);
	}
	public boolean delete(String id) {
		Persist p = new Persist();
		um = p.getUser(id);

		return delete();
	}
	public boolean delete() {
		if (um != null)
			return new Persist().delete(um);
		return false;
	}
		
	
	public boolean edit(UserModel um) {
			
		return new Persist().edit(um);
	}
	public UserModel getUser(String cpf) {
		return new Persist().getUser(cpf);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserModel> getUsers(String key) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	return (ArrayList<UserModel>) manager.createQuery(
    			"SELECT u FROM UserModel u WHERE u.name LIKE :uname")
    			.setParameter("uname", "%"+key+"%")
    			.setMaxResults(10)
    			.getResultList();


	}	
}
