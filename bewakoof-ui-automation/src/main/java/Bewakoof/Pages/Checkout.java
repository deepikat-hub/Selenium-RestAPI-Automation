package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;
import io.qameta.allure.Step;

public class Checkout extends GlobalCommon{

	@Step
	public static void deliverHere(){
		Functions.waitFor(20,By.xpath(Locators.deliverHereButton),"DELIVER HERE");
		WebElement deliverHereButtonElement=driver.findElement(By.xpath(Locators.deliverHereButton));
		deliverHereButtonElement.click();
	}
}
