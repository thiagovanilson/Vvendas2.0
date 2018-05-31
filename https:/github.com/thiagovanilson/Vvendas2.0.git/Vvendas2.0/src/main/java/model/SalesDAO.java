package model;

import java.util.ArrayList;
import java.util.Date;
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
    	if(sm == null) {
    		itens = null;
    		hasItens = false;
    		return;
    	}
    	itens = sm.getItens();
    	hasItens = itens.size() > 0;
	}
	public List<ItemSell> getItens() {
		search();
    	return itens;
	}	
	public boolean save(List<ItemSell> itens) {
		sm.setItens(itens);

		return save(sm);
	}
	
	public String getWarnings() {
		if(sm == null)
			return "Não foi encontrado venda com codigo " + cod;
		return "";
	}	
	public String getSellInfo() {
		if(sm == null || !hasItens)
			return "";
		return String.format("<h3>Codigo: " + sm.getId() + "</h3><br />Valor: R$ %.2f"  + 
				"<br />Data: " + sm.getDate() + "<br />CPF do vendedor: " + sm.getCpfUsuario(), sm.getPrice());
	}
	public ArrayList<SellModel> getSales(int days) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	ArrayList<SellModel> resultList = (ArrayList<SellModel>) manager.createQuery(
    			"SELECT s FROM SellModel s WHERE s.date >= :inicio")
    			.setParameter("inicio", new Date(new Date().getTime() - (1000L*60*60*24*days)))
    			.setMaxResults(100)
    			.getResultList();
    	
    	manager.close();
		return resultList;


	}
	public ArrayList<SellModel> getSales(int days, String cpf) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	ArrayList<SellModel> resultList = (ArrayList<SellModel>) manager.createQuery(
    			"SELECT s FROM SellModel s WHERE s.date >= :inicio AND s.cpfUsuario like :cpf ")
    			.setParameter("inicio", new Date(new Date().getTime() - (1000L*60*60*24*days)))
    			.setParameter("cpf", cpf)
    			.setMaxResults(100)
    			.getResultList();

    	manager.close();
		return resultList;
	}
	public boolean hasItens() {
		
		return hasItens;
	}
	public ArrayList<UserModel> getUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	return (ArrayList<UserModel>) manager.createQuery(
    			"SELECT u FROM UserModel u ")
    			.setMaxResults(100)
    			.getResultList();
	}
}
