package controler.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.ItemSell;
import model.SalesDAO;
import model.SellModel;
import services.SellServices;


@Named
@ViewScoped

public class BuscarVendas implements Serializable  {		
	
	@Inject
	private SalesDAO sd ;//= new SalesDAO(new SellModel());
	
	private SellServices ss = new SellServices();
	
	private long codVenda;
	private Date data;
	
	public void search() {
//		if(sd == null)
//			sd = new SalesDAO(null);
		sd.search();
	}
	
	public long getCodVenda() {
		return codVenda;
	}
	public void setCodVenda(long cod) {
		codVenda = cod;
		sd.getSell(cod);
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}	
	public SellModel getVenda(){
//		if(sd == null)
//			sd = new SalesDAO(null);
		return sd.getSell(codVenda);
	}
	public List<ItemSell> getItens() {
		if(sd == null)
			sd = new SalesDAO(codVenda);
		return sd.getItens();
	}
	public String getWarnings() {
		return ss.getWarningSold();
	}
	public String getSellInfo() {
		
		return ss.getSellInfo(codVenda);
	}
	public boolean hasItens() {
		if(sd == null)
			return false;
		
		return sd.hasItens();
	}
}