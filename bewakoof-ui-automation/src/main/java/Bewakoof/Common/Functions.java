package Bewakoof.Common;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Bewakoof.Utils.Logs;

public class Functions extends GlobalCommon {

	public static void hoverAndClick(WebDriver driver,WebElement profileElement,WebElement preLoginElement) {
		Actions action = new Actions(driver);
		action.moveToElement(profileElement).click(preLoginElement).build().perform();
	}

	public static void wait(Integer waitTimeInSeconds, String path){
		WebDriverWait wait= new WebDriverWait (GlobalCommon.driver,waitTimeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
	}

	public static void checkUrl(String expectedKeyword){
		String currentUrl=driver.getCurrentUrl();

		if(currentUrl.contains(expectedKeyword))
		{
			Logs.logger.info("Passed. 3rd party Url is correct");
		}
		else
		{
			Logs.logger.error("Unexpected url" +currentUrl);
		}
	}

	public static void loadingCheckScripts() throws Exception {
		final WebDriverWait wait = new WebDriverWait(driver, 60);
		final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		final ExpectedCondition<Boolean> jQueryComplete = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor
							.executeScript("return typeof window.jQuery != 'undefined' && jQuery.active") == 0);
				} catch (Exception | Error e) {
					return true;
				}
			}
		};
		final ExpectedCondition<Boolean> documentReady = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete") ? true
						: false;
			}
		};
		try {
			wait.until(jQueryComplete);
			wait.until(documentReady);
		} catch (Exception | Error e) {
			//Functions.logger.logUIFail("loading check scripts timed out");
			System.out.println("loading check scripts timed out");
		}
	}

	public static void getTitle(String ExpectedTitle){
		String title=driver.getTitle();
		assertEquals(title,ExpectedTitle);
	}
}
