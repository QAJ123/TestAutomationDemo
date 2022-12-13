package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonFunctions extends BaseClass {

	// read excel data
	public static String readDataFromExcel() throws IOException {
		String pathofExcelSheet = "C:\\Users\\003QIP744\\eclipse-workspace\\Mobileautomationassignment\\Test Data.xls";
		File file = new File(pathofExcelSheet);
		FileInputStream fis = new FileInputStream(file);

		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheetOfInterest = wb.getSheet("Sheet1");
		HSSFRow rowOfInterest = sheetOfInterest.getRow(0);
		System.out.println(rowOfInterest.getCell(0).getStringCellValue().toString());
		int x=(int)sheetOfInterest.getRow(0).getCell(1).getNumericCellValue();
		
		return x+"";

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/Reports/" + screenshotName + dateName + ".jpeg";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String ReadDataFromPropFile(String propName) throws IOException {
		File configfile = new File(
				"C:\\Users\\003QIP744\\eclipse-workspace\\Mobileautomationassignment\\configuration.properties");
		FileInputStream fis = new FileInputStream(configfile);
		Properties prop = new Properties();
		prop.load(fis);
		
		return prop.getProperty(propName);

	}
	
	public static void turnOnImplicitWait() {
		//int sec=Integer.parseInt("WAITINSECONDS");
		//System.out.println(sec);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}
	public static void scroll() {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
				+ ".instance(0)).scrollIntoView(new UiSelector().textContains(\"Super Savers\").instance(0))");
	}

}
