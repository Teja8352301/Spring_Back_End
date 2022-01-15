package com.teja.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Column(name="id")
	@Id
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String id;
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;
	
	@OneToMany(mappedBy="userId",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	List<Product> products;
	
	@OneToOne(cascade= {CascadeType.ALL},mappedBy="userId")
	Cart cartId;

	public User(String id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getCartId() {
		return cartId.getCartId();
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
	
}
