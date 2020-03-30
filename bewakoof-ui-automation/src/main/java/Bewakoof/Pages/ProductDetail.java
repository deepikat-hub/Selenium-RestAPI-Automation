package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;
import io.qameta.allure.Step;

public class ProductDetail extends GlobalCommon{
	
	@Step
	public static void selectSize() throws Exception{
		Functions.waitFor(40, By.xpath(Locators.sizeMedium),"M");
		WebElement sizeElement= driver.findElement(By.xpath(Locators.sizeMedium));
		sizeElement.click();
	}
	
	@Step
	public static void addToBag() throws Exception{
		Functions.waitFor(40, By.xpath(Locators.addToBag),"ADD TO BAG");
		WebElement addToBagElement= driver.findElement(By.xpath(Locators.addToBag));
		addToBagElement.click();
	}
	
	@Step
	public static void goToBag() throws InterruptedException{
		Functions.waitFor(40, By.xpath(Locators.goToBag),"GO TO BAG");
		WebElement goToBagElement= driver.findElement(By.xpath(Locators.goToBag));
		goToBagElement.click();
	}
}
