//package com.listeners;
//
//
//import base.ui.Functions;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import properties.ConfProperties;
//import ui.GlobalsCommon;
//import ui.nms.Globals;
//
//import java.io.File;
//
//public class ScreenShotFailListener implements ITestListener {
//
//    WebDriver driver=null;
//    private static String fileSeparator  = System.getProperty("file.separator");
//
//    @Override
//    public void onFinish(ITestContext context) {}
//
//    @Override
//    public void onStart(ITestContext context) {}
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
//
//    @Override
//    public void onTestSkipped(ITestResult result) {}
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        if( !ConfProperties.screenshotOnFail ){
//            return;
//        }
//
//        String instanceName = result.getInstanceName();
//        String testMethodName = result.getName().trim();
//        String testImage = testMethodName + "-" + System.currentTimeMillis() + ".png";
//
//        if( instanceName.startsWith("soapapi") ){
//            return;
//        }
//
//        driver = GlobalsCommon.getDriver();
//
//        try {
//            String pathToScreenshot = ConfProperties.getFailedTestsScreenshotsPath() + fileSeparator + instanceName + fileSeparator;
//
//            File path = new File(pathToScreenshot);
//            if (!path.exists()) {
//                path.mkdir();
//            }
//
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File targetFile = new File(pathToScreenshot + fileSeparator + testImage);
//
//            FileUtils.copyFile(screenshot, targetFile);
//            Functions.logger.logUIInfo("Screenshot saved: " + pathToScreenshot + fileSeparator + testImage );
//        } catch (Exception e){
//            Functions.logger.logUIInfo("Cannot save screenshot for " + testMethodName +  "; Error: " + e.toString());
//        }
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {}
//
//    @Override
//    public void onTestStart(ITestResult result) {}
//}
