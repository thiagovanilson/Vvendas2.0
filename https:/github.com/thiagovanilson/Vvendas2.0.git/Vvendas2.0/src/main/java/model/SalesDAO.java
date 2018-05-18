package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SalesDAO extends Persist{

	private SellModel sm;
	private long cod;
	private List<ItemSell> itens;
	private boolean hasItens = false;
	
	public SalesDAO(SellModel s){
		sm = s;
	}
	public SalesDAO(long cod){
		getSell(cod);
	}
	public SellModel getSell(long cod) {
		this.cod = cod;
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
		EntityManager manager = factory.createEntityManager();
		
		sm = manager.find(SellModel.class, cod);
		if(sm != null)
			itens = sm.getItens();
		
		return sm;
		
	}
	public void search() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	sm = manager.find(SellModel.class, cod);

    	itens = sm.getItens();
    	hasItens = itens.size() > 0;
	}
	public List<ItemSell> getItens() {
		search();
    	return itens;
	}	
	public boolean save(List<ItemSell> itens) {
		sm.setItens(itens);
//		for(ItemSell is: itens) {
//			is.setIdSell(sm.getId());
//			save(is);
//		}
		return save(sm);
	}
	
	public String getWarnings() {
		if(sm == null)
			return "Não foram encontrado venda com codigo " + cod;
		return "";
	}	
	public String getSellInfo() {
		if(sm == null)
			return "";
		return String.format("<h3>Codigo: " + sm.getId() + "</h3><br />Valor: R$ %.2f"  + 
				"<br />Data: " + sm.getDate() + "<br />CPF do vendedor: " + sm.getCpfUsuario(), sm.getPrice());
	}
	public boolean hasItens() {
		
		return hasItens;
	}
}
