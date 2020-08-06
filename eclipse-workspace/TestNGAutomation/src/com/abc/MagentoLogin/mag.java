package com.abc.MagentoLogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class mag {
	WebDriver driver;
	@BeforeClass
	public void log()
	{
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get("https://www.magento.com");
	driver.findElement(By.linkText("My Account")).click();
 	driver.findElement(By.id("email")).sendKeys("kundansinghmahisour@gmail.com");
 	driver.findElement(By.id("pass")).sendKeys("Welcome@123");
 	driver.findElement(By.id("send2")).click();
 	
	}
	
	@Test
	public void 
}
