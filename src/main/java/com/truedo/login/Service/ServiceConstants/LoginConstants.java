package com.truedo.login.Service.ServiceConstants;

public class LoginConstants {
	
	public static String checkSql="SELECT count(*) FROM USER WHERE username = ?";
	public static String insertSql="Insert into USER(name, username, password, address, phone, register_timestamp, login_timestamp) values(?,?,?,?,?,?,?)";
	public static String verifySql="SELECT count(*) FROM USER WHERE username = ? and password=? and status='Active'";
	public static String updateTimestamp="Update user set login_timestamp=? where username=?";
	public static String AllUsersQuery="Select * from user where status='Active'";
	public static String UserById="Select * from user where username=? and status='Active'";
	public static String updatePassword="Update user set password=? where username=? and status='Active'";
	public static String updatePhone="Update user set phone=? where username=? and status='Active'";
	public static String updateAddress="Update user set address=? where username=? and status='Active'";
	public static String deleteUser="Update user set status='Passive' where username=? and status='Active'";
	
	
}
