package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import model.dao.ProductDAO;

@Entity
@Table(name="vendas")  

public class SellModel implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id  
	private long id;
	
	private float price;	
	private String cpfUsuario;
	private Date date;
	
	@OneToMany (fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	private List<ItemSell> itens;
	
	public SellModel(){
		date = new Date();
		id = generateId();
	}
	
	private long generateId() {
		long id = (long) (Math.random() * 1000000000);			
			
		ProductModel p = new ProductDAO(null).getProduct(id + "");
		
		if(p != null) {
			return generateId();
		}		
		return id;
	}
	
	public long getId() {
		return id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public Date getDate() {
		return date;
	}
	//@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.ALL})
	public List<ItemSell> getItens() {
		return itens;
	}

	
	//@JoinColumn(name="item")
	//@OneToMany(fetch=FetchType.EAGER, mappedBy = "sm")
	public void setItens(List<ItemSell> itens) {
		this.itens = itens;
	}
}
