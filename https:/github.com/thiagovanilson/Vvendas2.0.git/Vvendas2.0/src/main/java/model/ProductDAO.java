package model;

import javax.inject.Inject;

public class ProductDAO {

	@Inject
	private ProductModel pm;
	
	public ProductDAO(ProductModel p){
		pm = p;
	}
	public ProductDAO(){
		pm = null;
	}
	
	public boolean save() {
		return new Persist().save(pm);
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
	
		return new Persist().edit(pm);
	}
	public ProductModel getProduct(String id) {
		return new Persist().getProduct(id);
	}	
}
