package services;

import java.util.ArrayList;
import java.util.List;

import controler.bean.AbstractBean;
import model.ItemSell;
import model.ProductModel;
import model.SellModel;
import model.dao.ProductDAO;
import model.dao.SalesDAO;

public class SellServices extends AbstractBean{

	private List<ItemSell> itens = new ArrayList<ItemSell>();
	private String warningSell = "", warningSold = "";
	private float sum = 0;
	
	public void addItem(String cod, float qtd) {

		if(cod!=null && !cod.equals("")) {
			ProductDAO pd = new ProductDAO(null);
			ProductModel p = pd.getProduct(cod);
			ItemSell item = new ItemSell();		
			
			warningSell = "";
			
			if(p != null) {
				if(p.getQuantity() <= 0) {
					warningSell = "Produto não apresenta quantidade em estoque. Favor atualizar os dados do item.";					
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
				warningSell = "Codigo: " + cod + " não cadastrado!";
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

	public boolean finish(String cpf, float value) {
		SellModel sell = new SellModel();
		
		sell.setItens(itens);
		sell.setPrice(sum);
		sell.setCpfUsuario(cpf);
		
		SalesDAO sd = new SalesDAO(sell);

		if(sd.save(itens)) {
			reportarMensagemDeSucesso("Venda finalizada com sucesso!");
			warningSell = "Codigo da venda: " + sell.getId();
			if(value > 0 && value > sum) {
				warningSell += String.format("<br />Troco: R$ %.2f",  (value - sum)); 
			}
			return true;
		}
		else
			reportarMensagemDeErro("A venda não foi registrada no banco de dados!");
		return false;
	}

	public boolean hasItens() {
		return !itens.isEmpty();
	}

	public String getWarningSell() {
		return warningSell;
	}

	public List<ItemSell> getItens() {
		return itens;
	}
	
	private SalesDAO sd = new SalesDAO(null);
	public String getSellInfo(long codVenda) {
		
		SellModel sm = sd.getSell(codVenda);
		if(sm == null )
			return "";
		return String.format("<h3>Codigo: " + sm.getId() + "</h3><br />Valor: R$ %.2f"  + 
				"<br />Data: " + sm.getDate() + "<br />CPF do vendedor: " + sm.getCpfUsuario(), sm.getPrice());
		
	}

	public String getWarningSold() {
		return warningSold;
	}

	public String getInfoSold() {
		// TODO Auto-generated method stub
		return null;
	}
}
