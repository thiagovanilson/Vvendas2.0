package model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class SellModel {

	@Id  
	private long id;
	
	private float price;	
	private String cpfUsuario;
	private Date date;
	
	
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
	public void setDate(Date date) {
		this.date = date;
	}	
}
