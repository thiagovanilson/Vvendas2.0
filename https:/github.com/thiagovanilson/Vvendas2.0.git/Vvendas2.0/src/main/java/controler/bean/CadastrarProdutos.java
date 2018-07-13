package controler.bean;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.ProductModel;
import services.RegisterProductsServices;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class CadastrarProdutos extends AbstractBean{	
	
	private String[] medidas = new String[] {"Kg","Gramas","Litros","ML","Unidade","Desconhecido"};
	private String name, cod, description, tipoMedida;
	private float quantity, price;
	private int medida;
	
	private ProductModel p = new ProductModel();
	private RegisterProductsServices rps = new RegisterProductsServices();
	
	public boolean isEdition() {
		return rps.isEdition();
	}
	public boolean hasCode() {
		if(cod == null)
			return false;
		return cod.length() > 0;
	}
	public void save() {
		if(p == null)
			p = new ProductModel();
		
		p.setId(cod);
		p.setDescricao(description);
		p.setMedida(medida);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setTipoMedida(tipoMedida);
		p.setName(name);
		
		if(rps.save(p))
			clean();
	}
	public void search() {
		
		p = rps.search(cod);
		
		if(p != null) {
			name       = p.getName();
			price      = p.getPrice();
			medida     = p.getMedida();
			quantity   = p.getQuantity();
			description= p.getDescricao();
			tipoMedida = p.getTipoMedida();
		}
		else {
			clean();
		}
	}
	
	public void clean() {
		description = "";
		name        = "";
		tipoMedida  = "";
		price       = 0;
		quantity    = 0;
		medida      = 0;
	}
	public void delete() {
		if (rps.delete(p))
			clean();
		
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

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String[] getMedidas() {
		return medidas;
	}

	public int getMedida() {
		return medida;
	}

	public void setMedida(int medida) {
		this.medida = medida;
	}

	public String getTipoMedida() {
		return tipoMedida;
	}

	public void setTipoMedida(String tipoMedida) {
		this.tipoMedida = tipoMedida;
	}

	
}