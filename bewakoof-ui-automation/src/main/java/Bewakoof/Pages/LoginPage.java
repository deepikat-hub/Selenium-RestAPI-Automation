package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;

public class LoginPage extends GlobalCommon{
	public static String homePageTitle= "Online Shopping for Men, Women Clothing & Accessories at Bewakoof";
	
	public static void validateTitleLogin() throws Exception{
		Functions.loadingCheckScripts();
		Functions.getTitle(homePageTitle);
	}
	
	public static void Login() throws Exception{
//		Functions.loadingCheckScripts();
		Thread.sleep(2000);
		WebElement preLoginElement=driver.findElement(By.id(Locators.goToLoginButton));
		Functions.hoverAndClick(driver, preLoginElement, preLoginElement);
//		Functions.loadingCheckScripts();
		Thread.sleep(2000);
		WebElement emailElement=driver.findElement(By.id(Locators.email));
		emailElement.sendKeys("deepitripathi13@gmail.com");
		WebElement continueButtonElement=driver.findElement(By.xpath(Locators.continueButton));
		continueButtonElement.click();
		Functions.wait(40, Locators.password);
		WebElement passwordElement=driver.findElement(By.xpath(Locators.password));
		passwordElement.sendKeys("Freecharge@1");
		Thread.sleep(2000);
		WebElement loginElement=driver.findElement(By.xpath(Locators.login));
		loginElement.click();
		Thread.sleep(2000);
	}
	

}
