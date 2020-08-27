package FunctionLibraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class FunctionLibrary {
	private WebDriver driver;

	public FunctionLibrary(WebDriver driver) {
		this.driver =driver;
	}

public void fillFormAndPurchase(String name, String Address, String city, String state,
								Integer zipcode, String cardtype, long card_no, Integer card_month,
								Integer card_year, String card_name) {
	
	WebElement txt_name = driver.findElement(By.xpath("//*[@id=\"inputName\"]"));
	txt_name.sendKeys(name);
	WebElement txt_Address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
	txt_Address.sendKeys(Address);
	WebElement txt_city = driver.findElement(By.xpath("//*[@id=\"city\"]"));
	txt_city.sendKeys(city);
	WebElement txt_state = driver.findElement(By.xpath("//*[@id=\"state\"]"));
	txt_state.sendKeys(state);
	WebElement txt_zipcode = driver.findElement(By.xpath("//*[@id=\"zipCode\"]"));
	txt_zipcode.sendKeys(""+zipcode);
	Select dropdown_cardtype =new Select(driver.findElement(By.xpath("//*[@id=\"cardType\"]")));
	dropdown_cardtype.selectByValue(cardtype);
	WebElement txt_cardNo = driver.findElement(By.xpath(""));
	txt_cardNo.sendKeys(""+txt_cardNo);  
	WebElement txt_cardMonth = driver.findElement(By.xpath("//*[@id=\"creditCardMonth\"]"));
	txt_cardMonth.sendKeys(""+card_month);
	WebElement txt_cardYear = driver.findElement(By.xpath("//*[@id=\"creditCardYear\"]"));
	txt_cardYear.sendKeys(""+card_year);
	WebElement txt_nameOnCard = driver.findElement(By.xpath("//*[@id=\"nameOnCard\"]"));
	txt_nameOnCard.sendKeys(card_name);
	
	 WebElement btn_purchase = driver.findElement(By.xpath("//input[@value=\"Purchase Flight\" and @class=\"btn btn-primary\"]"));
	 btn_purchase.click();


	
}

}
