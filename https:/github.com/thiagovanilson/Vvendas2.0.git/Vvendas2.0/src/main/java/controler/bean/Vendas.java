package controler.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.ItemSell;
import model.ProductModel;
import services.SellServices;

@SuppressWarnings("serial")
@Named
@ApplicationScoped

public class Vendas extends AbstractBean{	
	

	private String cod;
	private float qtd = 1;
	private boolean showPayment = false;
	private float value;
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public boolean isShowPayment() {
		return showPayment;
	}
	public void setShowPayment(boolean showPayment) {
		this.showPayment = showPayment;
	}

	@Inject
	private BuscarProdutos bp;
	@Inject
	private SellServices ss;
		
	public void addCart() {
		ss.addItem(cod, qtd);
		cod = "";
		qtd = 1;
	}
	public String getInfo() {
		return ss.getInfo();
	}
	
	public void finish(String cpf) {
		ss.finish(cpf, value);
		setShowPayment(false);
	}
	public String previewInfo() {
		if(cod == null || cod.equals("null")) {
			return "";
		}
		if(cod.equals("") ) {			
			return "";
		}
		bp.setCod(cod);
		bp.buscar();
		List<ProductModel> temp = bp.getProdutos();
		
		if(temp != null && temp.size() > 0) {
			ProductModel pm = temp.get(0);
			return String.format("<h3>%s,  Preço: R$ %.2f</h3>",pm.getName(), pm.getPrice());
		}else
			return "Produto não cadastrado no sistema!";
	
		
	}
	public void clean() {
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

	public float getQtd() {
		return qtd;
	}

	public void setQtd(float qtd) {
		this.qtd = qtd;
	}

	public List<ItemSell> getItens() {
		return ss.getItens();
	}
	
}