package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;

public class Checkout extends GlobalCommon{

	public static void deliverHere(){
		Functions.wait(20, Locators.deliverHereButton);
		WebElement deliverHereButtonElement=driver.findElement(By.xpath(Locators.deliverHereButton));
		deliverHereButtonElement.click();
	}
}
