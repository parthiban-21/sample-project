package com.chainsys.FoodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FoodOrderImpl implements FoodOrderInterface{

	Scanner ip = new Scanner(System.in);
	FoodOrderValidation val = new FoodOrderValidation();
	int customerID;

	public void createUser(Customer customer, Menu menu) throws SQLException , ClassNotFoundException{

		//Get Name from Customer
		System.out.print("Enter the Name: ");
		String name = ip.next();
		name = val.checkName(name);
		customer.setCustomerName(name);

		//Get MailID from Customer
		System.out.print("Enter the Mail-ID: ");
		String mailID = ip.next();
		mailID = val.checkMailID(mailID);
		customer.setMailID(mailID);

		//Get Phone No from Customer
		System.out.print("Enter the Phone Number: ");
		String phoneNo = ip.next();
		phoneNo = val.checkPhoneNo(phoneNo);
		customer.setPhoneNo(phoneNo);

		//Get Address from Customer
		System.out.print("Enter the Address: ");
		String address = ip.next();
		//Need to Validate
		customer.setAddress(address);

		//Get New Password from Customer
		System.out.print("Enter the Password: ");
		String password = ip.next();
		password = val.checkPassword(password);
		customer.setPassword(password);

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "Insert into CUSTOMER(customer_id,customer_name,mail_id,phone_no,address,password)"
					+ "values(customerID.nextval,?,?,?,?,?)";
			ps = c.prepareStatement(query);
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getMailID());
			ps.setString(3, customer.getPhoneNo());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getPassword());
			ps.executeUpdate();
			System.out.println("Account Created Successfully");
			loginUser(customer, menu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
	}

	public boolean checkLogin(String mailID, String password) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "select CUSTOMER_ID from CUSTOMER where MAIL_ID=? and PASSWORD=?";
			ps = c.prepareStatement(query);
			ps.setString(1, mailID);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				customerID = rs.getInt("CUSTOMER_ID");
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			ps.close();
			rs.close();
		}
	}

	public void loginUser(Customer customer, Menu menu) throws SQLException, ClassNotFoundException {

		System.out.println("Login");
		//Get MailID from Customer
		System.out.print("Enter the Mail-ID: ");
		String mailID = ip.next();

		//Get New Password from Customer
		System.out.print("Enter the Password: ");
		String password = ip.next();
		if (mailID.equals("admin@gmail.com") && password.equals("admin")) {
			System.out.println("Admin Login");
			displayAdminControlls(customer, menu);
		}
		else {
			if(checkLogin(mailID, password)) {
				System.out.println("Successfully LoggedIN\n");
				displayMenu(customer,menu);
			}
			else {
				System.out.println("No Account Found");
			}
		}
	}

	public void createOrder(Customer customer, Menu menu) throws SQLException , ClassNotFoundException{

		System.out.println("Enter the ID to add to Cart: ");
		int menuId = ip.nextInt();
		menuId = val.chechMenuID(menuId);

		System.out.println("Enter the Quantity: ");
		int quantity = ip.nextInt();

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "Insert into ORDERS(customer_ID,menu_ID,quantity,order_status)"
					+ "values(?,?,?,'In Cart')";
			ps = c.prepareStatement(query);
			ps.setInt(1, customerID);
			ps.setInt(2, menuId);
			ps.setInt(3, quantity);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
	}

	public void addMenu(Menu menu) throws SQLException , ClassNotFoundException {

		//Get Menu Name
		System.out.print("Enter the Menu Name: ");
		String name = ip.next();
		menu.setMenuName(name);

		//Get Menu Type
		System.out.print("Enter the Menu Type: ");
		String type = ip.next();
		menu.setMenuType(type);

		//Get Menu Price
		System.out.print("Enter the Price: ");
		int price = ip.nextInt();
		menu.setMenuPrice(price);

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "Insert into MENU(menu_ID,menu_name,menu_type,price)"
					+ "values(menuID.nextval,?,?,?)";
			ps = c.prepareStatement(query);
			ps.setString(1, menu.getMenuName());
			ps.setString(2, menu.getMenuType());
			ps.setInt(3, menu.getMenuPrice());
			ps.executeUpdate();
			System.out.println("Menu Added Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
	}

	//Update Menu 
	public void updateMenu(Menu menu) throws SQLException , ClassNotFoundException {

		//Get Menu ID
		System.out.print("Enter the Menu-ID: ");
		int menuID = ip.nextInt();
		menuID = val.chechMenuID(menuID);

		//Get Menu New Price
		System.out.print("Enter the New Price: ");
		int price = ip.nextInt();
		menu.setMenuPrice(price);

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "UPDATE menu SET price = ? WHERE menu_id = ?";
			ps = c.prepareStatement(query);
			ps.setInt(1, menu.getMenuPrice());
			ps.setInt(2, menuID);
			ps.executeUpdate();
			System.out.println("Menu Updated Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
	}

	//Delete Menu 
	public void deleteMenu(Menu menu) throws SQLException , ClassNotFoundException {

		//Get Menu ID
		System.out.print("Enter the Menu-ID: ");
		int menuID = ip.nextInt();
		menuID = val.chechMenuID(menuID);

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "DELETE FROM menu WHERE menu_id = ?";
			ps = c.prepareStatement(query);
			ps.setInt(1, menuID);
			ps.executeUpdate();
			System.out.println("Menu Deleted Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
	}

	public void displayMenu(Customer customer, Menu menu) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "select menu_id,menu_name,price from menu";
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Menu-ID: "+rs.getInt(1));
				System.out.println("Menu-Name: "+rs.getString(2));
				System.out.println("Menu-Price: "+rs.getInt(3));
			}
			//Get Order
			int l=0;
			while(l==0){
				System.out.println("Enter '1' to Add Item and Other to Exit");
				int i=ip.nextInt();
				if (i==1) {
					createOrder(customer, menu);
				}
				else {
					++l;
				}
			}
			displayOrder(customer, menu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
			rs.close();
		}
	}

	//Generate Total Price
	public int priceCalculation() throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total=0;
		try {
			Connection c = DbConnection.getConnection();
			String query="select * from orders where customer_id = ? and order_status = 'In Cart'";
			ps = c.prepareStatement(query);
			ps.setInt(1, customerID);
			rs = ps.executeQuery();
			while(rs.next()) {
				int perPrice = getPrice(rs.getInt(2));
				int itemPrice = perPrice*rs.getInt(3);
				total +=itemPrice;
			}
			return total;
		}
		catch(Exception e) {
			e.printStackTrace();
			return total;
		}
		finally {
			ps.close();
			rs.close();
		}
	}

	//Get Price for Total
	public int getPrice(int menuID) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		try {
			Connection c = DbConnection.getConnection();
			String query="select price FROM menu where MENU_ID = ?";
			ps = c.prepareStatement(query);
			ps.setInt(1, menuID);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public String getItemName(int menuID) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		try {
			Connection c = DbConnection.getConnection();
			String query="select MENU_NAME FROM menu where MENU_ID = ?";
			ps = c.prepareStatement(query);
			ps.setInt(1, menuID);
			rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public void displayOrder(Customer customer, Menu menu) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total=0;
		try {
			Connection c = DbConnection.getConnection();
			String query="select * from orders where customer_id = ? and order_status = 'In Cart'";
			ps = c.prepareStatement(query);
			ps.setInt(1, customerID);
			rs = ps.executeQuery();
			while(rs.next()) {
				String itemName = getItemName(rs.getInt(2));
				int perPrice = getPrice(rs.getInt(2));
				int quantity = rs.getInt(3);
				int itemPrice = perPrice*quantity;
				System.out.println(itemName+" - "+quantity+" Qty - Rs."+itemPrice);
				total +=itemPrice;
			}
			System.out.println("Grand Total: "+total);
			isConfirmed(customer, menu);
		}
		catch(Exception e) {
			e.printStackTrace();

		}
		finally {
			ps.close();
			rs.close();
		}
	}

	public void updateOrder(Customer customer, Menu menu) throws SQLException, ClassNotFoundException {
		System.out.println("Enter the ID to add to Cart: ");
		int menuId = ip.nextInt();
		menuId = val.chechMenuID(menuId);

		System.out.println("Enter the Quantity: ");
		int quantity = ip.nextInt();

		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "update orders set QUANTITY = ? where customer_id = ? and menu_id = ? and order_status = 'In Cart'";
			ps = c.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setInt(2, customerID);
			ps.setInt(3, menuId);
			ps.executeUpdate();
			System.out.println("Updated");
			displayOrder(customer, menu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}		
	}

	public void confirmOrder(Customer customer, Menu menu) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
		try {
			Connection c = DbConnection.getConnection();
			String query = "update orders set ORDER_STATUS = 'Confirmed' where customer_id = ? and order_status = 'In Cart'";
			ps = c.prepareStatement(query);
			ps.setInt(1, customerID);
			ps.executeUpdate();
			System.out.println("Order Confirmed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}	
	}

	public void isConfirmed(Customer customer, Menu menu) throws ClassNotFoundException, SQLException {
		int i=0;
		while(i==0) {
			System.out.println("Enter '1' to Confirm Order\n'2' to Update Order");
			int check = ip.nextInt();
			if(check == 1) {
				confirmOrder(customer, menu);
				++i;
			}
			else if(check == 2) {
				updateOrder(customer, menu);
				++i;
			}
			else {
				System.out.println("Wrong Input\n Try Again...\n");
			}
		}
	}

	public void displayAdminControlls(Customer customer, Menu menu) throws ClassNotFoundException, SQLException {
		int i=0;
		while(i==0) {
			System.out.println("Enter '1' to Add Menu\n'2' to Update Menu\n'3' to Delete Menu");
			int check = ip.nextInt();
			if(check == 1) {
				addMenu(menu);
				++i;
			}

			else if(check == 2) {
				updateMenu(menu);
				++i;
			}

			else if(check == 3) {
				deleteMenu(menu);
				++i;
			}
			else {
				System.out.println("Wrong Input\n Try Again...\n");
			}
		}
	}
}
