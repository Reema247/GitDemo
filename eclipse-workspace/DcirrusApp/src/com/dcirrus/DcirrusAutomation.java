package com.dcirrus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DcirrusAutomation 
{
   WebDriver driver;
   @BeforeClass
   @Parameters({"id","password","corporateID"})
   public void setUp(String id,String password,String corporateID)
   {
  	 driver=new ChromeDriver();
  	 driver.manage().window().maximize();
  	 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
  	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  	// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
	 driver.manage().deleteAllCookies();
  	 driver.get("https://dcirrus.co.in/appnew/drive.html");
     driver.findElement(By.linkText("Business User")).click();
	 driver.findElement(By.id("loginid")).sendKeys(id);
	 driver.findElement(By.id("password")).sendKeys(password);
	 driver.findElement(By.id("corporateid")).sendKeys(corporateID);
	 String capchaval=JOptionPane.showInputDialog("Please enter the capcha value");
	 driver.findElement(By.id("logincaptcha")).sendKeys(capchaval);
	 driver.findElement(By.id("btnlogin")).click();
   }
   
   
   
   @AfterClass
   public void tearDown()
   {
	   driver.quit();
   }
   
   
   @Test(groups="createFolder",priority=1)
   @Parameters({"name1"})
   public void createNewFolder(String name1) throws InterruptedException
   {
	  
	   driver.findElement(By.id("btncreatefolderpopupolevel")).click();
	   driver.findElement(By.id("admfoldername")).sendKeys(name1);
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']/div/label/p")).click();
	   String txt= driver.findElement(By.className("top-alert-message")).getText();
	   System.out.println(txt);
	   String actualtxt= "Folder created successfully";
	   Assert.assertEquals(actualtxt,txt,"Folder Exists");
   }
   
   
   @Test(groups="createFolder",priority=2)
   @Parameters({"name"})
   public void existingFolder(String name) throws InterruptedException
   {
	   driver.get("https://dcirrus.co.in/appnew/drive.html");
	   driver.findElement(By.id("btncreatefolderpopupolevel")).click();
	   driver.findElement(By.id("admfoldername")).sendKeys(name);
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']/div/label/p")).click();
	   String txt= driver.findElement(By.className("top-alert-message")).getText();

	   if(txt.equals("Folder exists"))
	   {
		   System.out.println("Folder exists");
	   }
	   else 
	   {
		   System.out.println("Folder doesn't exists already");
	   }
	   String actualtxt= "Folder exists";
	   Assert.assertEquals(actualtxt,txt);
	   
   }
   
   @Test(groups="createFolder",priority=3)
   public void enterFolderName() throws InterruptedException 
   {   
	   driver.get("https://dcirrus.co.in/appnew/drive.html");
	   driver.findElement(By.id("btncreatefolderpopupolevel")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']/div/label/p")).click();
	   String txt= driver.findElement(By.className("top-alert-message")).getText();
	   System.out.println(txt);
	   String actualtxt= "Please enter folder name";
	   Assert.assertEquals(txt,actualtxt);
   }
   
   @Test(priority=4)
   @Parameters({"subFoldername"})
   public void subFolder(String subFoldername) throws InterruptedException   //creating subfolder inside 2nd folder
   {    
	   driver.get("https://dcirrus.co.in/appnew/drive.html");
	 
	   driver.findElement(By.xpath("//*[@id='adm_foldername_1']/h2/a")).click(); // finding Parent folder
	   Thread.sleep(10000);
	   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
	   driver.findElement(By.xpath("//*[@id='btncreatefolder']")).click();
	   driver.findElement(By.xpath("//*[@id='admfoldername']")).sendKeys(subFoldername);
	   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
	   Thread.sleep(3000);
	   String txt= driver.findElement(By.className("top-alert-message")).getText();
	
	   if(txt.equals("Folder created successfully"))
	   {
		   System.out.println("Sub-Folder created successfully");
	   }
	   else 
	   {
		   System.out.println("Sub-Folder exists with the given name");
	   }
	   String actualtxt= "Folder created successfully";
	   Assert.assertEquals(actualtxt,txt);
	 }
   
   
   @Test(priority=12)
   @Parameters({"deleteName"})
   public void deleteFolder(String deleteName) throws InterruptedException
   {
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	   
	   driver.findElement(By.id("btncreatefolderpopupolevel")).click(); 
	   driver.findElement(By.id("admfoldername")).sendKeys(deleteName); 
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//p[text()='ADD']")).click();         
	   Thread.sleep(5000);
       driver.findElement(By.xpath("//*[@data-foldername='aaaa']/div[7]/ul/li/img")).click();
       driver.findElement(By.xpath("//*[@id='adm_delete_folder_0']")).click();
       Thread.sleep(3000);
       driver.findElement(By.xpath("//*[@id='adm_folderrow_0']/div[7]/ul/li/img")).click();
       driver.findElement(By.xpath("//*[@id='adm_permdelete_folder_0']")).click();
       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']/div/label/p")).click();
       Thread.sleep(6000);
  }
  
  @Test(priority=6)
  public void rename()
  {
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	   driver.findElement(By.xpath("//*[@id='adm_folderrow_0']/div[7]/ul/li/img")).click();
	   driver.findElement(By.xpath("//*[@id='adm_edit_folder_0']")).click();
	   driver.findElement(By.id("rename_admfoldername")).clear();
	   driver.findElement(By.id("rename_admfoldername")).sendKeys("Documents");
	   driver.findElement(By.id("rename_btncreatefolderdone")).click();
	 
  }

  @Test(priority=7)
  public void restore() throws InterruptedException   
  {
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
      driver.findElement(By.xpath("//*[@id='adm_folderrow_0']/div[7]/ul/li/img")).click();
      driver.findElement(By.xpath("//*[@id='adm_delete_folder_0']")).click();
      Thread.sleep(4000);
      driver.findElement(By.xpath("//*[@id='adm_folderrow_0']/div[7]/ul/li/img")).click();
      driver.findElement(By.id("adm_restore_folder_0")).click();
  }
   @Test(priority=5)
  public void uploadFile() throws InterruptedException, IOException
  {  
	   driver.get("https://dcirrus.co.in/appnew/drive.html");
	   driver.findElement(By.xpath("//*[@id='adm_foldername_1']/h2/a")).click();
	   Thread.sleep(10000);
	//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']/i")));
	  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
	  driver.findElement(By.id("btnuploadfile")).click();
	  driver.switchTo().frame("adm_fileuploadId");
	 
	  WebElement fileInput = driver.findElement(By.id("uploadFileId"));
	  
	  fileInput.sendKeys("C:/Users/91863/Documents/reema.docx");
	   Thread.sleep(3000);
	  driver.switchTo().parentFrame();
	  String txt= driver.findElement(By.className("top-alert-message")).getText();
	  System.out.println(txt);
	  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
   
  }
  
  @Test(priority=11)
  public void downloadFolder() throws InterruptedException
  {   
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	  driver.findElement(By.xpath("//*[@id='adm_download_folder_1']")).click();
      Thread.sleep(4000);
      String progress=driver.findElement(By.xpath("//*[@id='downloadingperc_1614']")).getText();
	  System.out.println("Folder downloaded" + progress);
	   Assert.assertEquals("100%",progress,"Folder not downloaded");
	   
	   Thread.sleep(4000);
  }

  

  
  @Test(groups="DownloadFile",priority=9)
  public void downloadFile() throws InterruptedException           //Download 1st file of 6th folder
  {   
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	  driver.findElement(By.xpath("//*[@id='adm_foldername_5']/h2/a")).click();
	  driver.findElement(By.id("admdownloadfile_1843")).click();
	  Thread.sleep(5000);
	 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
	 System.out.println(Popupmsg);
	 Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
	 File f=new File("C:\\Users\\91863\\Downloads\\funny.zip");
     if(f.exists())
    {
   	  System.out.println("File Downloaded");
    }
     
  }
  
  @Test(groups="DownloadFile",priority=10)
  public void downloadMultipleFiles() throws InterruptedException
  {     
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	  driver.findElement(By.xpath("//*[@id='adm_foldername_5']/h2/a")).click();        //download 1st and 2nd file of 6th folder
	  driver.findElement(By.xpath("//*[@id='adm_doc_name_0']/label/span")).click();
	  driver.findElement(By.xpath("//*[@id='adm_doc_name_1']/label/span")).click();
	  driver.findElement(By.id("moreall")).click();
	  driver.findElement(By.id("adm_download_file")).click();
	  Thread.sleep(5000);
	  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
	  System.out.println(Popupmsg);
	  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
	  
  }
  @Test(groups="DownloadFile",priority=8)
  public void downloadVersionList() throws InterruptedException
  {  
	  driver.get("https://dcirrus.co.in/appnew/drive.html");
	  driver.findElement(By.xpath("//*[@id='adm_foldername_5']/h2/a")).click();     //6th folder contains subfolder... inside subfolder ,1st file have version list..download the recent version
	  driver.findElement(By.id("admfileversionname_0")).click();
	  driver.findElement(By.xpath("//*[@id='adm_download_Version_Popup_0']/div/img")).click();
	  Thread.sleep(5000);
	  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
	  System.out.println(Popupmsg);
	  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
  }
   
  
}
