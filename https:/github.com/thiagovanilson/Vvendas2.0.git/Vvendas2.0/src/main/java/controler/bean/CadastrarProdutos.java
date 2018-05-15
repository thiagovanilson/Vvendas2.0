package controler.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import model.ProductDAO;
import model.ProductModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CadastrarProdutos extends AbstractBean{	
	
	private String name, cod, description;
	private float price;
	private int quantity;
	private boolean isEdition = false;
	
	public String filtrar() {
		
		return null;
	}
	
	public boolean isEdition() {
		return isEdition;
	}

	public void save() {
		try {
			ProductModel p = new ProductModel();
						
			p.setId(cod);
			p.setName(name);
			p.setPrice(price);
			p.setQuantity(quantity);
			p.setDescricao(description);
			
			ProductDAO pd = new ProductDAO(p);
			
			if(isEdition) {
				if (pd.edit(p)) {
					clean();
					reportarMensagemDeSucesso("Edições salvas!");
				}
				else
					reportarMensagemDeErro("Erro ao alterar dados. Revise os campos!");	
			}else {
				if (pd.save()) {
					clean();
					reportarMensagemDeSucesso("Produto salvo!");
				}
				else
					reportarMensagemDeErro("Erro ao salvar. Revise os campos!");			
			}
			isEdition = false;
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	public void search() {
		ProductDAO pd  = new ProductDAO(null);
		ProductModel p = pd.getProduct(cod);
		
		if(p != null) {
			name       = p.getName();
			price      = p.getPrice();
			quantity   = p.getQuantity();
			description= p.getDescricao();
			
			isEdition = true;
		}
		else {
			clean();
			
			isEdition = false;
		}
	}
	public void clean() {
		description = "";
		name        = "";
		price       = 0;
		quantity    = 0;
	}
	public void delete() {
		ProductDAO pd = new ProductDAO(null);	
		
		if (pd.delete(cod)) {
			clean();
			reportarMensagemDeSucesso("Excluido com sucesso!");
		}
		else
			reportarMensagemDeErro("Falha ao excluir.");	
		
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