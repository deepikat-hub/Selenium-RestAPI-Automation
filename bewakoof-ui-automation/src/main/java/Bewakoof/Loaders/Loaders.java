//package Myntra.Loaders;
//
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import base.ui.Functions;
//import ui.GlobalsCommon;
//
//public class Loaders {
//	/**
//	 * Waits for any jQuery or Javascript activity on the page to complete
//	 * 
//	 * @throws Exception
//	 */
//	public static void loadingCheckScripts() throws Exception {
//		final WebDriverWait wait = new WebDriverWait(driver.getDriver(), 30);
//		final JavascriptExecutor jsExecutor = (JavascriptExecutor) GlobalsCommon.getDriver();
//		final ExpectedCondition<Boolean> jQueryComplete = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				try {
//					return ((Long) jsExecutor
//							.executeScript("return typeof window.jQuery != 'undefined' && jQuery.active") == 0);
//				} catch (Exception | Error e) {
//					return true;
//				}
//			}
//		};
//		final ExpectedCondition<Boolean> documentReady = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return jsExecutor.executeScript("return document.readyState").toString().equals("complete") ? true
//						: false;
//			}
//		};
//		try {
//			wait.until(jQueryComplete);
//			wait.until(documentReady);
//		} catch (Exception | Error e) {
//			Functions.logger.logUIFail("loading check scripts timed out");
//		}
//	}
//}
