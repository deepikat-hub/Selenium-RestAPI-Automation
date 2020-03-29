package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;

public class ProductDetail extends GlobalCommon{
	
	
	public static void selectSize() throws Exception{
//		Functions.wait(60, Locators.sizeMedium);
		Thread.sleep(4000);
		WebElement sizeElement= driver.findElement(By.xpath(Locators.sizeMedium));
		Actions action = new Actions(driver);
		action.moveToElement(sizeElement).click().build().perform();
	}
	
	public static void addToBag() throws Exception{
//		Functions.wait(40, Locators.addToBag);
		Thread.sleep(4000);
		WebElement addToBagElement= driver.findElement(By.xpath(Locators.addToBag));
		addToBagElement.click();
	}
	
	public static void goToBag() throws InterruptedException{
//		Functions.wait(40, Locators.goToBag);
		Thread.sleep(4000);
		WebElement goToBagElement= driver.findElement(By.xpath(Locators.goToBag));
		Thread.sleep(3000);
		goToBagElement.click();
	}
}
