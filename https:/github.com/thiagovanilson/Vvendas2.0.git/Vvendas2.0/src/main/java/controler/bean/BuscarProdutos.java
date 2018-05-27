package controler.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Persist;
import model.ProductDAO;
import model.ProductModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class BuscarProdutos extends AbstractBean{	
	
	private String name, cod, aviso;
	ArrayList<ProductModel> produtos = new ArrayList<ProductModel>();
	
	public String filtrar() {
		
		return null;
	}
	
	public BuscarProdutos() {
		
	}

	private boolean empty = true;
	
	public void buscar() {
		aviso = "";
		produtos = new ArrayList<ProductModel>();
		
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

	public List<ProductModel> getProdutos() {
		if(produtos == null)
			buscar();
		return produtos;
	}

}