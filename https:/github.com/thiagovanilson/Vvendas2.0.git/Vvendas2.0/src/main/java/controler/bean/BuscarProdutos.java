package controler.bean;

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
	
	
	public String filtrar() {
		
		return null;
	}
	
	public BuscarProdutos() {
		
	}

	public void buscar() {
		aviso = "";
		clean();
		
		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				name = p.getName();
			}
			else
				aviso = "Não foram encontrados resultados. :(";
		}
	}
	public void clean() {
		name        = "";
		cod         = "";		
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

}