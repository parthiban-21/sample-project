package com.chainsys.FoodOrder;

import java.sql.SQLException;

public interface FoodOrderInterface {
	
	public void createUser(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void loginUser(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void createOrder(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void addMenu(Menu menu) throws SQLException , ClassNotFoundException;
	public void displayMenu(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void displayOrder(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void updateOrder(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
	public void confirmOrder(Customer customer, Menu menu) throws SQLException , ClassNotFoundException;
}
