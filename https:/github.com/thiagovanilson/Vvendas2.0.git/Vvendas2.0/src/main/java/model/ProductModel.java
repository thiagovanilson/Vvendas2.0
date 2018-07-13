package model;

import javax.persistence.*;

@Entity
@Table(name="produtos")

public class ProductModel {

	@Id
	private String id;
	private String name;
	private float quantity, price;
	private String descricao;
	private int medida;
	private String tipoMedida;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public String getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(String tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
	public int getMedida() {
		return medida;
	}
	public void setMedida(int medida) {
		this.medida = medida;
	}	
}
