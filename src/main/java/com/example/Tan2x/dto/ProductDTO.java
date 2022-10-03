package com.example.Tan2x.dto;


public class ProductDTO {
	private long id;
	private String title;
	private int price;
	private boolean offer;
	private boolean view;
	private float weight;
	private String description;
	private String image1;
	private String image2;
	private int categoryId;
	
	
	public ProductDTO(long id, String title, int price, boolean offer, boolean view, float weight, String description, 
			String image1, String image2, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.offer = offer;
		this.view = view;
		this.weight = weight;
		this.description = description;
		this.image1 = image1;
		this.image2 = image2;
		this.categoryId = categoryId;
	}
	
	public ProductDTO(String title, int price, boolean offer, boolean view, float weight, String description, 
			String image1, String image2, int categoryId) {
		super();
		this.title = title;
		this.price = price;
		this.offer = offer;
		this.view = view;
		this.weight = weight;
		this.description = description;
		this.image1 = image1;
		this.image2 = image2;
		this.categoryId = categoryId;
	}
	
	public ProductDTO() {
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
