package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;
import io.qameta.allure.Step;

public class Home extends GlobalCommon{

	@Step
	public static void search(String searchText){
		Functions.wait(40, Locators.search);
		WebElement searchElement=driver.findElement(By.xpath(Locators.search));

		searchElement.sendKeys(searchText);
		searchElement.sendKeys(Keys.ENTER);
	}
	
	@Step
	public static void selectProduct(){
		Functions.wait(40, Locators.product);
		WebElement productElement= driver.findElement(By.xpath(Locators.product));
		productElement.click();
	}
	
	@Step
	public static void goToURL(String url) throws Exception{
		driver.get(url);
		Functions.loadingCheckScripts();
	}
	
	@Step
	public static void logout(){
		Functions.wait(40, Locators.preLogoutButton);
		WebElement preLogoutElement=driver.findElement(By.xpath(Locators.preLogoutButton));
		WebElement logout=driver.findElement(By.xpath(Locators.logout));
		Functions.wait(10, Locators.password);
		Functions.hoverAndClick(driver, preLogoutElement,logout);
	}
	
	
}
