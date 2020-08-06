package com.abc.testng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script1 
{

	 
	
	   @Test
	   public void testcase1()
	   { 
		   System.out.println("Test case1 executed");
	   }
	   @Test
	   public void testcase2()
	   {
		   System.out.println("Test case2 executed");
	   }
	   @BeforeMethod
	   public void beforeMethod() 
	   {
	   System.out.println("Before method executed");
	   }
	   public void afterMethod()
	   {
		   System.out.println("After method executed");
	   }
	

}
