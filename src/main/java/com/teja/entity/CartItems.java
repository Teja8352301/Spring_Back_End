package com.teja.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cartitems")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItems {
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String id;
	
	@Column(name="productTitle")
	String productTitle;
	
	@Column(name="quantity")
	int quantity;
	
	@Column(name="price")
	int price;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="productId")
	Product productId;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="cartId")
	Cart cartId;

	public CartItems() {
		super();
	}
	
	

	public Product getProductId() {
		return productId;
	}



	public void setProductId(Product productId) {
		this.productId = productId;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
}
