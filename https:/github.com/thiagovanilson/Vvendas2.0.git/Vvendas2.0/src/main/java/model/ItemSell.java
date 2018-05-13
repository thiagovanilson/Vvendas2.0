package model;

import javax.persistence.*;

@Entity
public class ItemSell {
	
	@Id  	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long idSell;
	private String barCode;
	private float price;
	private String name;
	private String description;
	private int quantity;
	
	public long getIdSell() {
		return idSell;
	}
	public void setIdSell(long idSell) {
		this.idSell = idSell;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubTotal() {
		return quantity * price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
