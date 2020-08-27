package test;

import FunctionLibraries.FunctionLibrary;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestResult;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class blaze {
	
	public static WebDriver driver;
	public FunctionLibrary lib = new FunctionLibrary(driver);
	
 @BeforeClass
  public void testSetup() {
	  System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @BeforeMethod(description="Verify Home Page navigation was successful")
  public void navigateToPage() {
	  String title_expected = "BlazeDemo";
	  String title_actual = driver.getTitle();
	  String siteUrl = "https://blazedemo.com/";
	  driver.get(siteUrl); 	  
	  System.out.print(title_actual);
  }
    
  @Test(description="Find Flights and navigate to reservation")
  public void findFlights() {
	  String departure_city = "Portland";
      String destination_city = "Cairo";
	  Select dropdown_toPort = new Select(driver.findElement(By.name("toPort")));
	  List<WebElement> toPort_options = dropdown_toPort.getOptions();
	  for(WebElement we : toPort_options)
	  {
		  if(departure_city.equalsIgnoreCase(we.getAttribute("value")))
		  {	
			  Assert.assertEquals(departure_city, we.getAttribute("value"));
			  dropdown_toPort.selectByValue(departure_city);
			  break;
		  }
	  }
	  Select dropdown_fromPort =new Select(driver.findElement(By.name("fromPort")));
	  List<WebElement> fromPort_options = dropdown_fromPort.getOptions();
	  for(WebElement we : fromPort_options)
	  {
		  if(destination_city.equalsIgnoreCase(we.getAttribute("value")))
		  {
			  Assert.assertEquals(destination_city, we.getAttribute("value"));
			  dropdown_fromPort.selectByValue(destination_city);
			  break;
		  }
	  }
	  
	  WebElement btn_findFlights = driver.findElement(By.xpath("//input[@value=\"Find Flights\"]"));
	  btn_findFlights.click();
	  Assert.assertEquals(driver.getTitle(),"BlazeDemo - reserve");
	  	  	  
  }
 
  
@Test(description="Choose Flight with given flight code") 
public void selectFlight() {
	String flightcode = "AL969";
	WebElement btn_chooseFlight = driver.findElement(By.xpath("//table/tbody/tr/form[@name='"+flightcode+"']/following-sibling::td/input"));
	btn_chooseFlight.click();	   
	Assert.assertEquals(driver.getTitle(),"BlazeDemo Purchase");
}

@Test(description="Enter Details and purchase the ticket")
public void purchaseTicket()
{
	lib.fillFormAndPurchase("TestName","Testaddress","TestCity","TestState",999999,
						"Visa",99999999,12,2030,"Test Card Name");
	
	Assert.assertEquals(driver.getTitle(), "BlazeDemo Confirmation");
}

@AfterMethod
public void tearDown(ITestResult result) {
   if (result.getStatus() == ITestResult.FAILURE) {
	   TakesScreenshot scrShot =((TakesScreenshot)driver);
	   File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
   }        
}
 
  @AfterClass
  public void afterClass() {
	  driver.close();
	  driver.quit();
  }

}
