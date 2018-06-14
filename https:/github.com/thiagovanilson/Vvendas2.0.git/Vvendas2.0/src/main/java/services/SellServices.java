package services;

import java.util.ArrayList;
import java.util.List;

import controler.bean.AbstractBean;
import model.ItemSell;
import model.ProductDAO;
import model.ProductModel;
import model.SalesDAO;
import model.SellModel;

public class SellServices extends AbstractBean{

	private List<ItemSell> itens = new ArrayList<ItemSell>();
	private String warning = "";
	private float sum = 0;
	
	public void addItem(String cod, int qtd) {
		ItemSell item = new ItemSell();		

		if(cod!=null && !cod.equals("")) {
			ProductDAO pd = new ProductDAO(null);
			ProductModel p = pd.getProduct(cod);
			
			warning = "";
			
			if(p != null) {
				if(p.getQuantity() <= 0) {
					warning = "Produto não apresenta quantidade em estoque. Favor atualizar os dados do item.";					
				}else
					if(p.getQuantity() >= qtd)
						p.setQuantity(p.getQuantity() - qtd);
				
				item.setBarCode(cod);
				item.setPrice(p.getPrice());
				item.setQuantity(qtd);
				item.setName(p.getName());
				item.setDescription(p.getDescricao());
				
				//Update itens quantity on system.
				
				pd.edit(p);
				
				itens.add(item);
				sum += item.getSubTotal();
				
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

	public boolean finish(String cpf) {
		SellModel sell = new SellModel();
		
		sell.setItens(itens);
		sell.setPrice(sum);
		sell.setCpfUsuario(cpf);
		
		SalesDAO sd = new SalesDAO(sell);

		if(sd.save(itens)) {
			reportarMensagemDeSucesso("Venda finalizada com sucesso!");
			warning = "Codigo da venda: " + sell.getId();
			return true;
		}
		else
			reportarMensagemDeErro("A venda não foi registrada no banco de dados!");
		return false;
	}

	public boolean hasItens() {
		return !itens.isEmpty();
	}

	public String getWarnings() {
		return warning;
	}

	public List<ItemSell> getItens() {
		return itens;
	}
}
