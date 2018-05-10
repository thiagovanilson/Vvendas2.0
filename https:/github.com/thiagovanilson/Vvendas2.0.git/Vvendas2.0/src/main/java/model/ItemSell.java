package model;

import javax.persistence.*;

@Entity
public class ItemSell {
	
	@Id
	private long id;
	private long barCode;
	private float price;
	private int quantity;
	
	public long getId() {
		return id;
	}
	public long getBarCode() {
		return barCode;
	}
	public void setBarCode(long barCode) {
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
}
