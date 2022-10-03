package com.example.Tan2x.entities;

import java.awt.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userName;
	private long productId;
	private String productTitle;
	private int quantity;
	private int price;
	private String image;
	
	public Cart(long id, String userName, long productId, String productTitle, int quantity, int price, String image) {
		super();
		this.id = id;
		this.userName = userName;
		this.productId = productId;
		this.productTitle = productTitle;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
	}

	public Cart(String userName, long productId, String productTitle, int quantity, int price, String image) {
		super();
		this.userName = userName;
		this.productId = productId;
		this.productTitle = productTitle;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}
	
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userName=" + userName + ", productId=" + productId + ", productTitle=" + productTitle + ", quantity=" + quantity
				+ ", price=" + price + ", image=" + image +"]";
	}
	
	
}
