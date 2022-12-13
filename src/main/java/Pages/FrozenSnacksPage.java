package Pages;

import org.openqa.selenium.By;

import Base.BaseClass;
import Base.CommonFunctions;

public class FrozenSnacksPage extends BaseClass{

	public static By firstItemAdd = By
			.xpath("(//android.widget.LinearLayout[@resource-id='in.dmart:id/includeAddViewAddToCart'])[1]");
	public static By secondItemAdd = By
			.xpath("(//android.widget.LinearLayout[@resource-id='in.dmart:id/includeAddViewAddToCart'])[2]");
	
	
	public static By cartCount=By.id("in.dmart:id/cart_item_count");
	public static By totalAmount=By.id("in.dmart:id/txt_total_cart_amount");
	public static By superSaversList=By.id("in.dmart:id/txt_widget_listview_header");


	public static By firstItem = By.xpath("(//android.widget.ImageView[@resource-id='in.dmart:id/plpPlusImage'])[1]");
	public static By secondItem = By.xpath("(//android.widget.ImageView[@resource-id='in.dmart:id/plpPlusImage'])[2]");

	public static By backButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	
	public static boolean checkoutIseNabled() {
		CommonFunctions.turnOnImplicitWait();
		String amount=driver.findElement(totalAmount).getText();
		System.out.println("amount="+amount);
		amount.replaceFirst("₹", "");
		if (Integer.parseInt(amount) >= 500)
			return true;
		else
			return false;
	}
	
	public static void goToHomePage() {
		CommonFunctions.turnOnImplicitWait();
		driver.findElement(backButton).click();
	}
	
	
}
