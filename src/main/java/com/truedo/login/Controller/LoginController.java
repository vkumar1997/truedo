package com.truedo.login.Controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.truedo.login.Model.LoginModel;
import com.truedo.login.Service.ServiceClasses.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService user;
	
	
	//for adding a new user
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus insertUser(@RequestParam("name") String name, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("address") String address, @RequestParam("phone") BigDecimal phone)
	{
		return user.insertUser(name, username, password, address, phone);
	}
	
	
	
	
	//for verifying login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response)
	{
		HttpStatus status=user.verifyLogin(username, password);
		if(status.equals(HttpStatus.ACCEPTED))
		{
			LoginModel model=user.getUserById(username);
			response.addCookie(new Cookie("username",model.getUsername()));
			response.addCookie(new Cookie("name",model.getName()));
			response.addCookie(new Cookie("phone",model.getPhone().toString()));
			response.addCookie(new Cookie("address",model.getAddress()));
		}
		return status;
	}
	
	
	
	
	//return all users
	@RequestMapping(value="/everyone", method=RequestMethod.GET)
	@ResponseBody
	public List<LoginModel> allUsers(@RequestParam("type") String type)
	{
		return user.getAllUsers(type);
	}
	
	
	
	//return one user using username
	@RequestMapping(value="/getUser/{username}", method=RequestMethod.GET)
	@ResponseBody
	public LoginModel UserById(@PathVariable String username)
	{
		return user.getUserById(username);
	}
	
	
	
	//change password
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus changePassword(@RequestParam("username") String username, @RequestParam("old_password") String old_password,@RequestParam("new_password") String new_password)
	{
		return user.changePassword(username, old_password, new_password);
	}
	
	
	
	//change phone
	@RequestMapping(value="/changePhone", method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus changePhone(@RequestParam("username") String username ,@RequestParam("password") String password, @RequestParam("phone") BigDecimal phone)
	{
		return user.changePhone(username, password, phone);
	}
	
	
	
	//change address
	@RequestMapping(value="/changeAddress", method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus changeAddress(@RequestParam("username") String username ,@RequestParam("password") String password, @RequestParam("address") String address)
	{
		return user.changeAddress(username, password, address);
	}
	
	
	
	//delete user
	@RequestMapping(value="/deleteUser", method=RequestMethod.DELETE)
	@ResponseBody
	public HttpStatus deleteUser(@RequestParam("username") String username ,@RequestParam("password") String password)
	{
		return user.deleteUser(username, password);
	}
	
}
