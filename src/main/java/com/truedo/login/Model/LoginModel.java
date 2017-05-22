package com.truedo.login.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LoginModel {
	
	String name;
	String username;
	String password;
	String address;
	BigDecimal phone;
	Timestamp register_timestamp;
	Timestamp login_timestamp;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPhone() {
		return phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	
	public Timestamp getRegister_timestamp() {
		return register_timestamp;
	}
	
	public void setRegister_timestamp(Timestamp register_timestamp) {
		this.register_timestamp = register_timestamp;
	}
	
	public Timestamp getLogin_timestamp() {
		return login_timestamp;
	}
	
	public void setLogin_timestamp(Timestamp login_timestamp) {
		this.login_timestamp = login_timestamp;
	}
}
