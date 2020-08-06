package com.abc.MagentoLogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginValidation 
{
     WebDriver driver;
     @BeforeMethod
     @Parameters({"browser","url"})
     public void openBrowser(String browser,String url)
     {
    	 if(browser.equals("chrome"))
    	 {
    		 driver=new ChromeDriver();
    		 driver.manage().window().maximize();
    	 }
    	 else if(browser.equals("firefox"))
    	 {
    		 driver=new FirefoxDriver();
    	 }
    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	 driver.get(url);
    	 
     }
     
     @AfterMethod
     public void terminate()
     {
    	 driver.quit();
     }
     
     @Test(priority=1)
     public void loginValidation() 
     {
		
    	driver.findElement(By.linkText("My Account")).click();
     	driver.findElement(By.id("email")).sendKeys("kundansinghmahisour@gmail.com");
     	driver.findElement(By.id("pass")).sendKeys("Welcome@123");
     	driver.findElement(By.id("send2")).click();
     	driver.findElement(By.linkText("Log Out")).click();
	}
     
     @Test(priority=2)
     public void clickRegister()
     {
		
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.xpath("//span[text()='Register']")).click();
	}
}
