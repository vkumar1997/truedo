package com.truedo.login.Service.ServiceClasses;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.truedo.login.Mapper.LoginMapper;
import com.truedo.login.Model.LoginModel;
import com.truedo.login.Service.ServiceConstants.LoginConstants;
import com.truedo.login.Service.ServiceInterfaces.LoginServiceInterface;

@Service
public class LoginService implements LoginServiceInterface{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	
	//used in multiple business method (verifying login, updating password)
	private boolean checkLogin(String username, String password)
	{
		String verifySql=LoginConstants.verifySql;
		int count = jdbcTemplate.queryForObject(verifySql, new Object[] { username,password }, Integer.class);
		if(count==1)
			return true;
		return false;
	}
	
	
	
	
	//get current time and date
	private Timestamp getTimestamp()
	{
		Date date=new Date();
		return (new Timestamp(date.getTime()));
	}
	
	
	
	
	
	
	// for inserting a new user
	public HttpStatus insertUser(String name,String username, String password, String address, BigDecimal phone)
	{
		try
		{
			//checking if username already exists
			 String sql = LoginConstants.checkSql;
			 int count = jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
			 

			 //handle duplicate entry
			 if (count > 0) {
			 	return HttpStatus.NOT_ACCEPTABLE;
			 }
			
			 //add to database
			 else
			 {
				 String insertsql=LoginConstants.insertSql;
				 jdbcTemplate.update(insertsql,new Object[]{name,username,password,address,phone,getTimestamp(),getTimestamp()});
				 return HttpStatus.ACCEPTED;
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	
	
	
	
	
	//for verifying login
	public HttpStatus verifyLogin(String username, String password)
	{
		try
		{
			// user found
			if(checkLogin(username, password))
			{
				//update login time stamp
				jdbcTemplate.update(LoginConstants.updateTimestamp,new Object[]{getTimestamp(),username});
				return HttpStatus.ACCEPTED;
			}
			
			//user not found
			else
				return HttpStatus.NOT_FOUND;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
		
	}
	
	
	
	
	//for getting all users
	public List<LoginModel> getAllUsers(String type)
	{
		
		try
		{
			if(type.equals("Admin"))
			{
				List<LoginModel> allUsersList=new ArrayList<>();
				allUsersList=jdbcTemplate.query(LoginConstants.AllUsersQuery,new LoginMapper());
				return allUsersList;
			}
			return null;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	//for getting a single user
	public LoginModel getUserById(String username)
	{
		
		try
		{
			LoginModel lm=jdbcTemplate.queryForObject(LoginConstants.UserById,new LoginMapper(),username);
			return lm;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	//for changing password
	public HttpStatus changePassword(String username, String old_password, String new_password)
	{
		try
		{
			// user found
			if(checkLogin(username, old_password))
			{
				jdbcTemplate.update(LoginConstants.updatePassword,new Object[]{new_password,username});
				return HttpStatus.OK;
			}
			
			//user not found
			else
				return HttpStatus.NOT_FOUND;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	

	
	
	//for changing phone number
	public HttpStatus changePhone(String username, String password, BigDecimal phone)
	{
		try
		{
			// user found
			if(checkLogin(username, password))
			{
				jdbcTemplate.update(LoginConstants.updatePhone,new Object[]{phone,username});
				return HttpStatus.OK;
			}
			
			//user not found
			else
				return HttpStatus.NOT_FOUND;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	
	
	
	
	//for changing address
	public HttpStatus changeAddress(String username, String password, String address)
	{
		try
		{
			if(checkLogin(username, password))
			{
				jdbcTemplate.update(LoginConstants.updateAddress,new Object[]{address,username});
				return HttpStatus.OK;
			}
			
			//user not found
			else
				return HttpStatus.NOT_FOUND;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}

	
	
	
	
	//for deleting user
	public HttpStatus deleteUser(String username, String password)
	{
		try
		{
			if(checkLogin(username, password))
			{
				jdbcTemplate.update(LoginConstants.deleteUser,username);
				return HttpStatus.OK;
			}
			
			//user not found
			else
				return HttpStatus.NOT_FOUND;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return HttpStatus.BAD_REQUEST;
		}
	}
		
}