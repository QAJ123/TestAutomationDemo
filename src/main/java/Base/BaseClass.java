package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.Assert;

public class BaseClass {
	public static AndroidDriver driver;
	public static  ExtentReports report;
	public static ExtentTest extentTestVar;
	DesiredCapabilities caps;

	String apk_path, DeviceName, PlatformName;

	@BeforeSuite
	public void getDataFromConfig() throws IOException {
		File configfile = new File(
				"C:\\Users\\003QIP744\\eclipse-workspace\\Mobileautomationassignment\\configuration.properties");
		FileInputStream fis = new FileInputStream(configfile);
		Properties prop = new Properties();
		prop.load(fis);
		apk_path = prop.getProperty("APK_PATH");
		DeviceName = prop.getProperty("Device_Name");

		PlatformName = prop.getProperty("PLATFORM_NAME");

	}

	@BeforeTest
	public void BAseMethod() throws IOException, InterruptedException {
		report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReport.html", true);
		report.addSystemInfo("Host Name", "dsf");
		report.addSystemInfo("User Name", "dsfs");
		report.addSystemInfo("Environment", "QA");
		
		//driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
		System.out.println(apk_path);
		File appDir = new File(apk_path);
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PlatformName);
		caps.setCapability("autoGrantPermissions", true);
		caps.setCapability("app", appDir.getAbsolutePath());
		caps.setCapability("autoGrantPermissions", true);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		Assert.assertEquals(HomePage.enterPincode(CommonFunctions.readDataFromExcel()),"560011");



	}
	
	@AfterTest
	public void endReport(){
		report.flush();
		
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		
		extentTestVar = report.startTest(result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTestVar.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent
																							// report
			extentTestVar.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception
																								// in extent report

			String screenshotPath = CommonFunctions.getScreenshot(driver, result.getName());
			extentTestVar.log(LogStatus.FAIL, extentTestVar.addScreenCapture(screenshotPath)); // to add screenshot in
																								// extent report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTestVar.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTestVar.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		report.endTest(extentTestVar); // ending test and ends the current test and prepare to create html report
	
		driver.close();
	
	}
	
}
