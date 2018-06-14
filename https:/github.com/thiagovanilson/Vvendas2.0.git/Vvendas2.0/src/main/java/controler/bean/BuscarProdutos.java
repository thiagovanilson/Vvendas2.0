package controler.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.*;

import model.Persist;
import model.ProductDAO;
import model.ProductModel;
import services.SearchProductsServices;

@Named
@ViewScoped

public class BuscarProdutos implements Serializable{	
	
	private String name, cod;
	private ArrayList<ProductModel> produtos = new ArrayList<ProductModel>();
	private SearchProductsServices sps = new SearchProductsServices();
			
	public void buscar() {
		
		produtos = sps.buscar(cod, name);
	}
	public void clean() {
		name        = "";
		cod         = "";		
	}
	public boolean hasItens() {
		return sps.hasItens();
	}
	public String getAviso() {
		return sps.getWarnings();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public List<ProductModel> getProdutos() {
		if(produtos == null)
			buscar();
		return produtos;
	}
}