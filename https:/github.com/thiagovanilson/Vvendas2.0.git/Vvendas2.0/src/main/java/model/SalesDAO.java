package model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SalesDAO extends Persist{

	private SellModel sm;
	private long cod;
	private ArrayList<ItemSell> itens;
	
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
		
		return sm;
		
	}
	@SuppressWarnings("unchecked")
	public void search() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	itens = (ArrayList<ItemSell>) manager.createQuery(
    			"SELECT i FROM ItemSell i WHERE i.idSell = :id")
    			.setParameter("id", cod )
    			.setMaxResults(100)
    			.getResultList();
	}
	public ArrayList<ItemSell> getItens() {
		search();
    	return itens;
	}	
	public boolean save() {
		return save(sm);
	}
	
	public boolean save(ArrayList<ItemSell> itens) {
		
		
		for(ItemSell i: itens) {
			i.setIdSell(sm.getId());
			
			save(i);
		}
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
		return String.format("Codigo: " + sm.getId() + "<br />Valor: R$ %.2f"  + 
				"<br />Data: " + sm.getDate() + "<br />CPF do vendedor: " + sm.getCpfUsuario(), sm.getPrice());
	}
}
