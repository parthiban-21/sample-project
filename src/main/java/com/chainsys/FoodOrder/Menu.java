package com.chainsys.FoodOrder;

public class Menu {
	private int menuID;
	private String menuName;
	private String menuType;
	private int menuPrice;
	
	
	public int getMenuID() {
		return menuID;
	}


	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuType() {
		return menuType;
	}


	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}


	public int getMenuPrice() {
		return menuPrice;
	}


	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}


	@Override
	public String toString() {
		return "Menu [menuID=" + menuID + ", menuName=" + menuName + ", menuType=" + menuType + ", menuPrice="
				+ menuPrice + "]";
	}
}
