package controler.bean;

import java.util.ArrayList;

import javax.faces.bean.*;

import model.ItemSell;
import model.Persist;
import model.ProductDAO;
import model.ProductModel;
import model.SellModel;

@ViewScoped
@ManagedBean
public class Vendas extends AbstractBean{	
	
	private String cod, warning;
	private int qtd = 1;
	
	private float sum = 0;
	
	private ArrayList<ItemSell> itens = new ArrayList();
	
	public void addCart() {
		ItemSell item = new ItemSell();		

		if(cod!=null && !cod.equals("")) {
			ProductModel p = new ProductDAO(null).getProduct(cod);
			
			if(p != null) {
				if(p.getQuantity() < qtd) {
					warning = "Quantidade indisponivel";
					return;
				}
				item.setBarCode(cod);
				item.setPrice(p.getPrice());
				item.setQuantity(qtd);
				item.setName(p.getName());
				item.setDescription(p.getDescricao());
				
				p.setQuantity(p.getQuantity() - qtd);
				
				itens.add(item);
				warning = "";
				sum += item.getSubTotal();
				
				//TO DO. On implements "Close sell", don't forget to decrease qtd of itens
			}
			else {
				warning = "Codigo: " + cod + " não cadastrado!";
			}
			cod = "";
			qtd = 1 ;
		}		
	}
	public String getInfo() {
		if(itens.size() > 0)
			return String.format("Quantidade de produtos: " + itens.size() + ".    Valor total: R$ %.2f",  sum);
		return "";
	}
	
	public void finish() {
		SellModel sell = new SellModel();
		Persist p      = new Persist();
		
		sell.setPrice(sum);
		
		for(ItemSell i: itens) {
			i.setIdSell(sell.getId());
			
			p.save(i);
		}
		
		if(p.save(sell)) {
			reportarMensagemDeSucesso("Venda finalizada com sucesso!");
			warning = "Codigo da venda: " + sell.getId();
			clean();
		}
		else
			reportarMensagemDeErro("A venda não foi registrada no banco de dados!");
	}
	
	private void clean() {
		itens.removeAll(itens);
		sum = 0;
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