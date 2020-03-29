package Bewakoof.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import Bewakoof.Common.GlobalCommon;

public class Cart extends GlobalCommon{

	public static void selectDeliveryAddress(){
		Functions.wait(40, Locators.selectDeliveryAddress);
		WebElement selectDeliveryAddressElement=driver.findElement(By.xpath(Locators.selectDeliveryAddress));
		selectDeliveryAddressElement.click();
	}
}
