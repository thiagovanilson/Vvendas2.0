package controler.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Facade;
import model.Persist;
import model.ProductDAO;
import model.ProductModel;

@RequestScoped
@ManagedBean
public class BuscarProdutos extends AbstractBean{	
	
	private String name, cod, aviso;
	ArrayList<ProductModel> produtos = new ArrayList();
	
	public String filtrar() {
		
		return null;
	}
	
	public BuscarProdutos() {
		
	}

	private boolean empty = true;
	
	public void buscar() {
		aviso = "";
		
		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				produtos.add(p);
				empty = false;
			}
			else {
				empty = true;
				clean();
				aviso = "Não foram encontrados resultados. :(";
			}
		}else {
			for(ProductModel p: new Persist().getProducts(name)){
				produtos.add(p);
				empty = false;

			}
		}
	}
	public void clean() {
		name        = "";
		cod         = "";
		produtos = new ArrayList();
		
	}
	public boolean hasItens() {
		return !empty;
	}
	public String getAviso() {
		return aviso;
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

	public ArrayList<ProductModel> getProdutos() {
		return produtos;
	}

}