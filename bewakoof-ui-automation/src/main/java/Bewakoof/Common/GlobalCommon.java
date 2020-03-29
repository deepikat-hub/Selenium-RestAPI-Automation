package Bewakoof.Common;


import java.io.IOException; 
import java.util.concurrent.TimeUnit; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite; 
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager; 

public class GlobalCommon {
	    
	    public static WebDriver driver = null; 
	  
	    @BeforeSuite
	    public void initialize() throws IOException, InterruptedException { 
	  

	    	WebDriverManager.chromedriver().setup();
	    	ChromeOptions opt = new ChromeOptions();
	    	opt.addArguments("disable-infobars");
	    	opt.addArguments("--start-maximized");
	    	opt.addArguments("--disable-extensions");
	    	 driver = new ChromeDriver(opt);
	    
	    } 
	  
	    @AfterSuite
	    public void TeardownTest() { 
	       driver.quit(); 
	    } 
	    
	    public static void quit(){
	    	driver.quit();
	    }
	
	
}



