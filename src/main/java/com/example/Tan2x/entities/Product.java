package com.example.Tan2x.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private int price;
	private boolean offer;
	private boolean view;
	private float weight;
	private String description;
	private String image1;
	private String image2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;


	public Product(long id,String title, int price, boolean offer, boolean view,float weight, String description,
			String image1, String image2, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.offer = offer;
		this.view = view;
		this.weight= weight;
		this.description = description;
		this.image1 = image1;
		this.image2 = image2;
		this.category = category;
	}

	public Product(String title, int price, boolean offer, boolean view, float weight,String description, String image1,
			String image2, Category category) {
		super();
		this.title = title;
		this.price = price;
		this.offer = offer;
		this.view = view;
		this.weight=weight;
		this.description = description;
		this.image1 = image1;
		this.image2 = image2;
		this.category = category;
	}

	public Product() {
		super();
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isOffer() {
		return offer;
	}


	public void setOffer(boolean offer) {
		this.offer = offer;
	}


	public boolean isView() {
		return view;
	}


	public void setView(boolean view) {
		this.view = view;
	}
	
	
	public float getWeight() {
		return weight;
	}


	public void setWeight(float weight) {
		this.weight = weight;
	}
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage1() {
		return image1;
	}


	public void setImage1(String image1) {
		this.image1 = image1;
	}


	public String getImage2() {
		return image2;
	}


	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", offer=" + offer + ", view=" + view
				+ ", weight=" + weight + ", description=" + description + ", image1=" + image1 + ", image2=" + image2
				+ ", category=" + category + "]";
	}
	
	
}
