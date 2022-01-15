package com.teja.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	  
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String id;
	
	@Column(name="title")
	String title = "";
	
	@Column(name="imageUrl")
	String imageUrl = "";
	
	@Column(name="price")
	int price = 1;
	
	@Column(name="description")
	String description = "";
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST},fetch= FetchType.LAZY)
	@JoinColumn(name="userId")
	User userId;
	


//	Constructors
	public Product( String title, String imageUrl, int price, String description) {
		super();
		this.title = title;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
	}

	public Product() {
		super();
	}

//	Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	To String Method
	@Override
	public String toString() {
		return "Products [id=" + id + ", title=" + title + ", imageUrl=" + imageUrl + ", price=" + price
				+ ", description=" + description + "]";
	}

	public String getUserId() {
		return userId.getId();
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
}
