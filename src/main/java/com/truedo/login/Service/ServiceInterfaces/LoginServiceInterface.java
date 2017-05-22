package com.truedo.login.Service.ServiceInterfaces;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;

import com.truedo.login.Model.LoginModel;

public interface LoginServiceInterface {
	
	
	//create operations
	//to insert a user.
	public HttpStatus insertUser(String name,String username, String password, String address, BigDecimal phone); 
	
	
	
	
	//read operations
	//to verify login (using post for security reasons)
	public HttpStatus verifyLogin(String username, String password);
	//to return all users' info
	public List<LoginModel> getAllUsers(String type);
	//to return a user's info
	public LoginModel getUserById(String username);
	
	
	
	
	//update operations
	//changing password (previous password is known)
	public HttpStatus changePassword(String username, String old_password, String new_password);
	//changing phone number
	public HttpStatus changePhone(String username, String password, BigDecimal phone);
	//changing phone number
	public HttpStatus changeAddress(String username, String password, String address);
		
	
	
	
	//delete operations
	public HttpStatus deleteUser(String username, String password);
	
}
