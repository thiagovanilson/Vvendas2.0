package controler.bean;

import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.ItemSell;
import model.ProductModel;
import services.SellServices;

@SuppressWarnings("serial")
@Named
@ViewScoped

public class Vendas extends AbstractBean{	
	
//	@Inject
//	private Services services;
	private String cod;
	private int qtd = 1;
	
	@Inject
	private BuscarProdutos bp;
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
	public String previewInfo() {
		
		bp.setCod(cod);
		bp.buscar();
		List<ProductModel> temp = bp.getProdutos();
	
		if(temp == null) {			
			if(cod != null && cod.length() > 0)
				return "Produto não cadastrado!";
			return "";
		}
		
		ProductModel pm = temp.get(0);
		return String.format("<h3>%s,  Preço: R$ %.2f</h3>",pm.getName(), pm.getPrice());
		
	}
	private void clean() {
		ss = new SellServices();
	}
	public String getWarning() {
		return ss.getWarningSell();
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