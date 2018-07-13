package model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ItemSell;
import model.SellModel;
import model.UserModel;

public class SalesDAO extends Persist implements Serializable{

	private SellModel sm;
	private long cod;
	private List<ItemSell> itens;
	private boolean hasItens = false;
	private List<SellModel> resultList;
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("comercio");
	
	public SalesDAO(SellModel s){
		sm = s;
	}
	public SalesDAO(long cod){
		getSell(cod);
	}
	public SellModel getSell(long cod) {
		this.cod = cod;
		
		EntityManager manager = factory.createEntityManager();
		
		sm = manager.find(SellModel.class, cod);
		if(sm != null)
			itens = sm.getItens();
		
		return sm;
		
	}
	public void search() {
    	EntityManager manager = factory.createEntityManager();
    	
    	sm = manager.find(SellModel.class, cod);
    	if(sm == null) {
    		itens = null;
    		hasItens = false;
    		return;
    	}
    	itens = sm.getItens();
    	hasItens = itens.size() > 0;
    	
    	manager.close();
	}
	public void search100() {
    	EntityManager manager = factory.createEntityManager();
    	sm = null;
		hasItens = false;
    	
    	resultList = (List<SellModel>) manager.createQuery(
    			"SELECT s FROM SellModel s WHERE  1=1 order by s.date desc")
    			.setMaxResults(100)
    			.getResultList();

    	manager.close();
		
	}
	public List<ItemSell> getItens() {
		search();
    	return itens;
	}	
	public boolean save(List<ItemSell> itens) {
		sm.setItens(itens);
		return save(sm);
	}
	
	
	public SellModel getSellModel() {
		return sm;
	}
	public ArrayList<SellModel> getSales(int days) {
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
    	EntityManager manager = factory.createEntityManager();
    	
    	
    	return (ArrayList<UserModel>) manager.createQuery(
    			"SELECT u FROM UserModel u ")
    			.setMaxResults(100)
    			.getResultList();
	}
	public List<SellModel> get100() {
		return resultList;
	}
}
