package controler.bean;

import java.util.List;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import model.ItemSell;
import services.SellServices;

@SuppressWarnings("serial")
@Named
@ViewScoped

public class Vendas extends AbstractBean{	
	
//	@Inject
//	private Services services;
	private String cod;
	private int qtd = 1;
	
	private SellServices ss = new SellServices();
		
	public void addCart() {
		ss.addItem(cod, qtd);
		cod = "";
	}
	public String getInfo() {
		return ss.getInfo();
	}
	
	public void finish(String cpf) {
		ss.finish(cpf);
			
	}
	
	private void clean() {
		ss = new SellServices();
	}
	public String getWarning() {
		return ss.getWarnings();
	}
	public boolean hasItens() {
		return ss.hasItens();
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

	public List<ItemSell> getItens() {
		return ss.getItens();
	}
	
}