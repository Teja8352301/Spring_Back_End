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
@Table(name="orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {

	@Id
	@Column(name="orderId")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String orderId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	User userId;

	public Orders() {
		super();
	}

	public Orders(User userId) {
		super();
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId.getId();
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	
	
}
