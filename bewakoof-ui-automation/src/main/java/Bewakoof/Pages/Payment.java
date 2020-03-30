package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;
import io.qameta.allure.Step;

public class Payment extends GlobalCommon{

	@Step
	public static void selectPaymentMode(String path,String name) throws InterruptedException{
		Functions.waitFor(60, By.xpath(Locators.wallet),name);
		WebElement walletElement=driver.findElement(By.xpath(path));
		walletElement.click();
	}
	
	@Step
	public static void selectPaymentOption(By path,String name) throws Exception{
		if(name.contains("Paytm")) {
			Functions.waitFor(40, By.xpath(Locators.paytmLabel),name);
		}
		WebElement paytmElement=driver.findElement(path);
		paytmElement.click();
	}
	
	@Step
	public static void payNow() throws InterruptedException{
		Functions.waitFor(40, By.xpath(Locators.payNow),"PAY NOW");
		WebElement payNowElement=driver.findElement(By.xpath(Locators.payNow));
		payNowElement.click();
	}
}