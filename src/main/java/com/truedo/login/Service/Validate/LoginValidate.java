package com.truedo.login.Service.Validate;

import java.math.BigDecimal;

public class LoginValidate {
		
	public boolean validateName(String name)
	{
		if (name.length()<4)
			return false;
		return true;
	}
	
	public boolean validateUsername(String username)
	{
		if(username.length()<7)
			return false;
		return true;
	}
	
	public boolean validatePassword(String password)
	{
		if(password.length()>5)
			return true;
		return false;
	}
	
	public boolean validateAddress(String address)
	{
		if(address.length()>7)
			return false;
		return true;
	}
	
	public boolean validatePhone(BigDecimal phone)
	{
		if(phone.toString().length()==10)
			return true;
		return false;
	}
	
}
