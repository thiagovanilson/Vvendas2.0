package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ProductModel;

public class ProductDAO extends Persist {

	
	private ProductModel pm;
		
//	@Inject
//	private EntityManager manager;
	public ProductDAO(ProductModel p){
		pm = p;
	}
	public ProductDAO(){
		pm = null;
	}
	
	public boolean save() {
		return super.save(pm);
	}
	public boolean delete(String id) {
		Persist p = new Persist();
		pm = p.getProduct(id);

		return delete();
	}
	public boolean delete() {
		if (pm != null)
			return new Persist().delete(pm);
		return false;
	}
	public boolean edit(ProductModel pm) {
	
		return super.edit(pm);
	}
	public int qtdProducts() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
		    
    	return Integer.parseInt( manager.createQuery(
    			"select count(p.id) from ProductModel p where 1=1")
    			.getSingleResult().toString());


	}	
	public ProductModel getProduct(String id) {
		return new Persist().getProduct(id);
	}
	
}
