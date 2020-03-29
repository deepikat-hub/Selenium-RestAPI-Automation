package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;

public class Payment extends GlobalCommon{

	public static void selectPaymentMode(String path) throws InterruptedException{
//		Functions.wait(60, Locators.wallet);
		Thread.sleep(4000);
		WebElement walletElement=driver.findElement(By.xpath(path));
		walletElement.click();
	}
	
	public static void selectPaymentOption(By path) throws Exception{
//		Functions.wait(40, "");
		Thread.sleep(4000);
		WebElement paytmElement=driver.findElement(path);
		paytmElement.click();
	}
	
	public static void payNow() throws InterruptedException{
//		Functions.wait(40, Locators.payNow);
		Thread.sleep(4000);
		WebElement payNowElement=driver.findElement(By.xpath(Locators.payNow));
		payNowElement.click();
	}
}