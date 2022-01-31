package com.bitcoinprice.dataparsing.user;

import javax.persistence.Id;

import org.bson.types.ObjectId;

public class Login {

	@Id
	private ObjectId _id;
	public String email;
	public String password;
	
	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}




}
