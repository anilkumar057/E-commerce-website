package com.example.Tan2x.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long orderId;
	private String proTitle;
	private int proQuantity;
	private int price;
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(long id, long orderId, String proTitle, int proQuantity, int price) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.proTitle = proTitle;
		this.proQuantity = proQuantity;
		this.price = price;
	}

	public OrderItem(long orderId, String proTitle, int proQuantity, int price) {
		super();
		this.orderId = orderId;
		this.proTitle = proTitle;
		this.proQuantity = proQuantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}

	public int getProQuantity() {
		return proQuantity;
	}

	public void setProQuantity(int proQuantity) {
		this.proQuantity = proQuantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", proTitle=" + proTitle + ", proQuantity="
				+ proQuantity + ", price=" + price + "]";
	}
	
	
}
