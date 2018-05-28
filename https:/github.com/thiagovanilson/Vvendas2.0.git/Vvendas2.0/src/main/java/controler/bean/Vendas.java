package controler.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;

import model.ItemSell;
import model.ProductDAO;
import model.ProductModel;
import model.SalesDAO;
import model.SellModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class Vendas extends AbstractBean{	
	
	private String cod, warning;
	private int qtd = 1;
	private Services services = new Services();
	private float sum = 0;
	
	private List<ItemSell> itens = new ArrayList<ItemSell>();
	
	public void addCart() {
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
	
	public void finish() {
		SellModel sell = new SellModel();
		
		sell.setItens(itens);
		sell.setPrice(sum);
		sell.setCpfUsuario(services.getUser().getCpf());
		
		SalesDAO sd = new SalesDAO(sell);

		if(sd.save(itens)) {
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

	public List<ItemSell> getItens() {
		return itens;
	}
	
}