package Bewakoof.Common;


import java.io.IOException; 
import java.util.concurrent.TimeUnit; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite; 
import org.testng.annotations.BeforeSuite; 
//import java.io.File;
//import java.text.SimpleDateFormat;
//
//import org.openqa.selenium.WebDriver;
//
public class GlobalCommon {
//
//    /**
//     * the browser driver
//     */
//    private static WebDriver driver ;
//   
//
//
//    /**
//     * the test suite's error buffers
//     */
//    public static StringBuffer dataTestErrors;
//    public static StringBuffer dataSuiteErrors;
//    public static StringBuffer imageTestErrors;
//    public static StringBuffer imageSuiteErrors;
//
//    /**
//     * test, error, and output line counts defined
//     */
//    public static int imageCount = 0;
//    public static int dataErrorCount = 0;
//    public static int imageErrorCount = 0;
//    public static int imageCountTotal = 0;
//    public static int dataErrorCountTotal = 0;
//    public static int imageErrorCountTotal = 0;
//    public static int testCount = 0;
//
//    /**
//     * the test suite's and individual test start and end times
//     */
//    public static long suiteStartTime;
//    public static long suiteEndTime;
//    public static long testStartTime;
//    public static long testEndTime;
//
//    /**
//     * filenames for the test result, image names, test suite and individual
//     * tests
//     */
//    public static String testResultFile = "";
//    public static String suiteName = "";
//    public static String testName = "";
//    public static String imageName = "";
//
//    /**
//     * global browser settings
//     */
//    public static int browserHeight = 900;
//    public static int browserWidth = 1500;
//    public static int imageHeight = 1300;
//    public static int imageWidth = 1000;
//    public static int browserBookmarkHeight = 55;
//
//    /**
//     * date formatting strings
//     */
//    public static SimpleDateFormat fileDateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//    public static SimpleDateFormat consoleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    protected static void setUpCommonAutomation() throws Exception {
//        // setup global error buffers
//        dataSuiteErrors = new StringBuffer();
//        imageSuiteErrors = new StringBuffer();
//        dataTestErrors = new StringBuffer();
//        imageTestErrors = new StringBuffer();
//
//
//        // start test suite timer
//        suiteStartTime = System.currentTimeMillis();
//
////        if( ConfProperties.screenshotOnFail ){
////            File screenshotDir = new File( ConfProperties.getFailedTestsScreenshotsPath() );
////            FileUtils.cleanDirectory(screenshotDir);
////        }
//    }
//
////    protected static void setDriverInNMSGlobals(WebDriver wd){
////        ui.nms.Globals.setDriver(wd);
////    }
//
//
//	public static WebDriver getDriver() {
//		return driver.get();
//	}
//
//	public static void setDriver(WebDriver driver) {
//		GlobalCommon.driver.set(driver);
//	}
//
	

	  
	
	  
	    public static WebDriver driver = null; 
	  
	    @BeforeSuite
	    public void initialize() throws IOException, InterruptedException { 
	  
//	        System.setProperty("webdriver.chrome.driver", 
//	        System.getProperty("user.dir") +  
//	            "\\src\\test\\java\\drivers\\chromedriver.exe"); 
	    	
	    	System.setProperty("webdriver.chrome.driver", "driver/chromedriver_mac");
	    	ChromeOptions opt = new ChromeOptions();
	    	opt.addArguments("disable-infobars");
	    	opt.addArguments("--start-maximized");
	    	opt.addArguments("--disable-extensions");
	    	 driver = new ChromeDriver(opt);
	    
	    } 
	  
	    @AfterSuite
	    // Test cleanup 
	    public void TeardownTest() { 
	       driver.quit(); 
	    } 
	    
	    public static void quit(){
	    	driver.quit();
	    }
	
	
}



