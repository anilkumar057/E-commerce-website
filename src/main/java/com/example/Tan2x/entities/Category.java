package com.example.Tan2x.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private int id;
	private String title;
	private String description;
	private String image;
	
	public Category(int id, String title, String description, String image) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
	}

	public Category(String title, String description, String image) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
	}
	
	public Category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", description=" + description + ", image=" + image +"]";
	}
}
