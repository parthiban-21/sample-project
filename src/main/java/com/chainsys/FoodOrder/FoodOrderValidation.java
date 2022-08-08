package com.chainsys.FoodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoodOrderValidation {

	Scanner ip = new Scanner(System.in);

	//Validate Name 
	public String checkName(String name) {
		String userPattern = "^(?!.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
		Pattern pattern = Pattern.compile(userPattern);
		Matcher match = pattern.matcher(name);
		if (match.matches())
			return name;
		else {
			int i=0;
			String temp="";
			while(i==0)
			{
				System.out.println("Invalied Input\nTry Again...");
				name = ip.next();
				match = pattern.matcher(name);
				if(match.matches()){
					++i;
					temp = name;
				}
				else {
					i=0;
				}
			}
			return temp;
		}
	}

	//Validate Password
	public String checkPassword(String password) {
		String userPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
		Pattern pattern = Pattern.compile(userPattern);
		Matcher match = pattern.matcher(password);
		if (match.matches())
			return password;
		else {
			int i=0;
			String temp="";
			while(i==0)
			{
				System.out.println("Invalied Input\nTry Again...");
				password = ip.next();
				match = pattern.matcher(password);
				if(match.matches()){
					++i;
					temp = password;
				}
				else {
					i=0;
				}
			}
			return temp;
		}
	}

	//Validate Mail-ID
	public String checkMailID(String password) {
		String userPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
				"[a-zA-Z0-9_+&*-]+)*@" +
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
				"A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(userPattern);
		Matcher match = pattern.matcher(password);
		if (match.matches())
			return password;
		else {
			int i=0;
			String temp="";
			while(i==0)
			{
				System.out.println("Invalied Input\nTry Again...");
				password = ip.next();
				match = pattern.matcher(password);
				if(match.matches()){
					++i;
					temp = password;
				}
				else {
					i=0;
				}
			}
			return temp;
		}
	}

	//Validate Mail-ID
	public String checkPhoneNo(String phoneNo) {
		String userPattern = "^(0/91)?[7-9][0-9]{9}$";
		Pattern pattern = Pattern.compile(userPattern);
		Matcher match = pattern.matcher(phoneNo);
		if (match.matches())
			return phoneNo;
		else {
			int i=0;
			String temp="";
			while(i==0)
			{
				System.out.println("Invalied Input\nTry Again...");
				phoneNo = ip.next();
				match = pattern.matcher(phoneNo);
				if(match.matches()){
					++i;
					temp = phoneNo;
				}
				else {
					i=0;
				}
			}
			return temp;
		}
	}

	public int chechMenuID(int menuID) throws SQLException {
		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query="select * FROM menu where MENU_ID = (?)";
			ps = c.prepareStatement(query);
			int i=0;
			while(i==0)
			{
				ps.setInt(1, menuID);
				int t = ps.executeUpdate();
				if(t==0)
				{
					System.out.print("Menu-ID not Found...\nEnter Menu-ID Again :");
					menuID = ip.nextInt();
				}
				else
				{
					++i;	
				}
			}
			return menuID;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		finally {
			ps.close();
		}

	}
}
