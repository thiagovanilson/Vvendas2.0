package services;

import java.util.ArrayList;

import model.Persist;
import model.ProductDAO;
import model.ProductModel;

public class SearchProductsServices {
	private String warning = "";
	private boolean empty = true;
	
	public ArrayList<ProductModel> buscar(String cod, String name) {
		warning = "";
		ArrayList<ProductModel> produtos = new ArrayList<ProductModel>();
		
		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				produtos.add(p);
				empty = false;
			}
			else {
				empty = true;
				warning = "Não foram encontrados resultados. :(";
			}
		}else { 
			if(name!=null && !name.equals("")){
				for(ProductModel p: new Persist().getProducts(name)){
					produtos.add(p);
					empty = false;	
				}
			}else {
				warning = "Nenhum campo preenchido!";
				return null;
			}
		}
		return produtos;
	}

	public String getWarnings() {
		return warning;
	}

	public boolean hasItens() {
		return !empty;
	}
}
