package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;
import io.qameta.allure.Step;

public class LoginPage extends GlobalCommon{
	public static String homePageTitle= "Online Shopping for Men, Women Clothing & Accessories at Bewakoof";
	
	@Step
	public static void validateTitleLogin() throws Exception{
		Functions.loadingCheckScripts();
		Functions.getTitle(homePageTitle);
	}
	
	@Step
	public static void Login() throws Exception{
		Functions.waitFor(40,By.id(Locators.goToLoginButton), "Login");
		WebElement preLoginElement=driver.findElement(By.id(Locators.goToLoginButton));
		Functions.hoverAndClick(driver, preLoginElement, preLoginElement);
		Functions.waitFor(40,By.xpath(Locators.signup), "SIGN UP");
		WebElement emailElement=driver.findElement(By.id(Locators.email));
		emailElement.sendKeys("deepitripathi13@gmail.com");
		WebElement continueButtonElement=driver.findElement(By.xpath(Locators.continueButton));
		continueButtonElement.click();
		Functions.wait(40, Locators.password);
		WebElement passwordElement=driver.findElement(By.xpath(Locators.password));
		passwordElement.sendKeys("Freecharge@1");
		Functions.waitFor(40,By.xpath(Locators.login), "LOG IN");
		WebElement loginElement=driver.findElement(By.xpath(Locators.login));
		loginElement.click();
		Functions.wait(40, Locators.loginIcon);
	}
	

}
