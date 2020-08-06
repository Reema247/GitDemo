package com.abc.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleAutomation 
{
   WebDriver driver;
   
   @BeforeMethod
   public void setUp()
   {
	   driver= new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	   driver.get("https://www.google.com");
   }
   
   @Test
   public void googleTitleTest()
   {
	   String title=driver.getTitle();
	   System.out.println(title);
   }
   
   @Test
   public void googleLogoTest()
   {
	   boolean b=driver.findElement(By.xpath("//div[@title='Google']")).isDisplayed();
   }
   
   @AfterMethod
   public void tearDown() 
   {
	 driver.quit();

}
}
