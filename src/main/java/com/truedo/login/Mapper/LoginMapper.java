package com.truedo.login.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.truedo.login.Model.LoginModel;



public class LoginMapper implements RowMapper<LoginModel>{
	
	@Override
	public LoginModel mapRow(ResultSet rs, int i) throws SQLException {
		
		LoginModel lm=new LoginModel();
		lm.setName(rs.getString("name"));
		lm.setUsername(rs.getString("username"));
		lm.setPhone(rs.getBigDecimal("phone"));
		lm.setAddress(rs.getString("address"));
		lm.setRegister_timestamp(rs.getTimestamp("register_timestamp"));
		lm.setLogin_timestamp(rs.getTimestamp("login_timestamp"));
		return lm;
	}
}