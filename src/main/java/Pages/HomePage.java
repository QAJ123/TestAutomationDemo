package Pages;

import org.openqa.selenium.By;

import Base.BaseClass;
import Base.CommonFunctions;

public class HomePage extends BaseClass {

	// XPaths
	public static By locationText = By.id("in.dmart:id/et_activity_pincode_pincode");
	public static By listedPinCode = By
			.xpath("//android.widget.TextView[@resource-id='in.dmart:id/txt_listitem_pincode_pincode']");

	public static By proceedWithLocation = By.id("in.dmart:id/btn_activity_pincode_proceed");
	public static By homePagePincode = By.id("in.dmart:id/tvEarliestPincode");

	// navigation bar and items
	public static By navigationBar = By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']");
	public static By frozenSnacksMenu = By.xpath("//android.widget.TextView[@text='Frozen Snacks']");

	public static By frozenSnacksHeader = By.id("in.dmart:id/actionbarTitleText");

	// Methods
	public static String enterPincode(String pincode) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(locationText).sendKeys(pincode);
		Thread.sleep(3000);
		driver.findElement(listedPinCode).click();
		Thread.sleep(3000);
		driver.findElement(proceedWithLocation).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(homePagePincode).getText());
		return driver.findElement(homePagePincode).getText();

	}

	public static String clickOnFrozenSnacks() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(navigationBar).click();
		Thread.sleep(3000);
		driver.findElement(frozenSnacksMenu).click();
		CommonFunctions.turnOnImplicitWait();
		return driver.findElement(frozenSnacksHeader).getText();

	}

}
