package com.teja.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cart")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

	@Id
	@Column(name="cartId")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String cartId;
	
	@Column(name="totalPrice")
	int totalPrice;
	
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="userId")
	User userId;
	
	
	@OneToMany(mappedBy="cartId",cascade= {CascadeType.ALL})
	List<CartItems> cartItems;	

//	public List<CartItems> getCartItems() {
//		return cartItems;
//	}
//
//	public void setCartItems(CartItems cartItem) {
//		if(this.cartItems == null) {
//			this.cartItems = new ArrayList<CartItems>();
//		}
//		this.cartItems.add(cartItem);
//	}

	public Cart(int i) {
		super();
		this.totalPrice = i;
	}

	public Cart() {
		super();
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId.getId();
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + ", userId=" + userId.getId() + "]";
	}
	
	
	
	
}
