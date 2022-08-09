package com.chainsys.FoodOrder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JUnitValidation{
	
	FoodOrderValidation val = new FoodOrderValidation();
	
	@Test
	public void chechName() {
		assertEquals("Parthiban",val.checkName("Parthiban"));
	}
	
	@Test
	public void chechPassword() {
		assertEquals("Parthi@123",val.checkPassword("Parthi@123"));
	}
}