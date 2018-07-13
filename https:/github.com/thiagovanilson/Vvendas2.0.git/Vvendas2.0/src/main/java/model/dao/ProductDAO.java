package model.dao;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ProductModel;
@RequestScoped
public class ProductDAO extends Persist {

	
	private static final long serialVersionUID = 8892975801587678289L;
	
//	@Inject
//	private EntityManager manager;
	private ProductModel pm;
//	@Inject
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	
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
		pm = getProduct(id);

		return delete();
	}
	public boolean delete() {
		if (pm != null)
			return delete(pm);
		return false;
	}
	
	public int qtdProducts() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	    EntityManager manager = factory.createEntityManager();
		    
    	return Integer.parseInt( manager.createQuery(
    			"select count(p.id) from ProductModel p where 1=1")
    			.getSingleResult().toString());


	}	
	
	public ProductModel getProduct(String key) {
//    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();

	    return manager.find(ProductModel.class, key);

	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ProductModel> getProducts(String key) {
//    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	ArrayList<ProductModel> result = (ArrayList<ProductModel>) manager.createQuery(
    			"SELECT p FROM ProductModel p WHERE p.name LIKE :pname")
    			.setParameter("pname", "%"+key+"%")
    			.setMaxResults(100)
    			.getResultList();
    	
    	manager.close();
//    	factory.close();
    	
    	return result;

	}
	public boolean edit(ProductModel o) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		EntityManager manager = factory.createEntityManager();
	   
		try {
		    
		    manager.getTransaction().begin();
		    ProductModel p = getProduct(o.getId());
		    
	    	if(p == null)
	    		return false;	
	    	
		    p = manager.merge(p);
		    
		    p.setName(o.getName());
		    p.setPrice(o.getPrice());
		    p.setMedida(o.getMedida());
		    p.setQuantity(o.getQuantity());
		    p.setDescricao(o.getDescricao());
		    p.setTipoMedida(o.getTipoMedida());
		    
		    manager.persist(p);
		    manager.getTransaction().commit();    

		    return true;
	    } catch (Exception e) {
	    	e.getMessage();	    	
	    	return false;
	    }finally {
	    	manager.close();
//	    	factory.close();
	    }
	}
}
