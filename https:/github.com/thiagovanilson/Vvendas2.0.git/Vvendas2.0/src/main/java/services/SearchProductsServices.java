package services;

import java.util.ArrayList;


import model.ProductModel;
import model.dao.ProductDAO;

public class SearchProductsServices {
	private String warning = "";
	private boolean empty = true;
	private ProductDAO pd = new ProductDAO();
	
	public ArrayList<ProductModel> buscar(String cod, String name) {
		warning = "";
		ArrayList<ProductModel> produtos = new ArrayList<ProductModel>();
		
		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				produtos.add(p);
			}
		}else { 
			if(name!=null && !name.equals("")){
				for(ProductModel p: pd.getProducts(name)){
					produtos.add(p);
				}
			}else {
				warning = "Nenhum campo preenchido!";		
				empty = true;
				return null;
			}
		}
		empty = produtos.isEmpty();
		
		if(empty)
			warning = "Não foram encontrados resultados. :(";
		
		return produtos;
	}

	public String getWarnings() {
		return warning;
	}

	public boolean hasItens() {
		return !empty;
	}
}
