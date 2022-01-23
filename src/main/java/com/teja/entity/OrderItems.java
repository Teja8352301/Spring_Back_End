package com.teja.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="orderitems")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItems {
	
	@Id
	@Column(name="productOrderId")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String productOrderId;
	
	@Column(name="title")
	String title;
	
	@Column(name="price")
	int price;
	
	public OrderItems() {
		super();
	}

	public OrderItems(String title, int price, int quantity, Orders orderId) {
		super();
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	@Column(name="quantity")
	int quantity;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="orderId")
	Orders orderId;

	public String getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderId() {
		return orderId.getOrderId();
	}

	public void setOrderId(Orders orderId) {
		this.orderId = orderId;
	}
	
}
