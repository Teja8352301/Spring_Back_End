package com.teja.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="authheaders")
public class AuthHeader {

	@Id
	@Column(name="authId")
	@GeneratedValue(generator = "UUID")
	 @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	String authId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	User user;

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AuthHeader(String authId, User user) {
		super();
		this.authId = authId;
		this.user = user;
	}

	public AuthHeader() {
		super();
	}
	
	
	
}
