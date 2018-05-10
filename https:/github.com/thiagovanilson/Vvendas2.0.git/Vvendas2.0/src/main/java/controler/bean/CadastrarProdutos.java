package controler.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Facade;
import model.Persist;
import model.ProductDAO;
import model.ProductModel;

@RequestScoped
@ManagedBean
public class CadastrarProdutos extends AbstractBean{	
	
	private String name, cod, description;
	private float price;
	private int quantity;
	
	public String filtrar() {
		
		return null;
	}
	
	public CadastrarProdutos() {
		
	}

	public void save() {
		try {
			ProductModel p = new ProductModel();;

			p.setId(cod);
			p.setName(name);
			p.setPrice(price);
			p.setDescricao(description);
			p.setQuantity(quantity);
			
			if (new ProductDAO(p).save()) {
				clean();
				reportarMensagemDeSucesso("Produto salvo!");
			}
			else
				reportarMensagemDeErro("Erro ao salvar. Revise os campos!");

		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	public void clean() {
		description = "";
		name        = "";
		cod         = "";
		price       = 0;
		quantity    = 0;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}