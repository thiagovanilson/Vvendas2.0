package model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="vendas")  

public class SellModel {

	@Id  
	private long id;
	
	private float price;	
	private String cpfUsuario;
	private Date date;
	
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
}
