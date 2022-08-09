package com.chainsys.FoodOrder;

import java.sql.SQLException;
import java.util.Scanner;

public class FoodOrderTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner ip = new Scanner(System.in);
		FoodOrderImpl imp = new FoodOrderImpl();
		Customer customer = new Customer();
		Menu menu = new Menu();

		System.out.println("1 - Login\n2 - Create Account");
		int i=ip.nextInt();
		if(i==1) {
			imp.loginUser(customer, menu);
		}
		else if(i==2) 
			imp.createUser(customer, menu);
		else
			System.out.println("Invalied Input");
	}
}
