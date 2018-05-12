package controler.bean;

import java.util.ArrayList;

import javax.faces.bean.*;

import model.Facade;
import model.ItemSell;
import model.Persist;
import model.ProductDAO;
import model.ProductModel;

@ViewScoped
@ManagedBean
public class Vendas extends AbstractBean{	
	
	private String cod, warning;
	private int qtd = 1;
	
	private ArrayList<ItemSell> itens = new ArrayList();
	
	public void addCart() {
		ItemSell item = new ItemSell();		

		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				item.setBarCode(cod);
				item.setPrice(p.getPrice());
				item.setQuantity(qtd);
				item.setName(p.getName());
				item.setDescription(p.getDescricao());
				
				itens.add(item);
				warning = "";
			}
			else {
				warning = "Codigo: " + cod + " não cadastrado!";
			}
			cod = "";
			qtd = 1 ;
		}		
	}
	public String getWarning() {
		return warning;
	}
	public boolean hasItens() {
		return !itens.isEmpty();
	}
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public ArrayList<ItemSell> getItens() {
		return itens;
	}
	
}