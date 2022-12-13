package testCases;

import org.testng.annotations.Test;

import Base.BaseClass;
import Base.CommonFunctions;
import Pages.FrozenSnacksPage;
import Pages.HomePage;
import junit.framework.Assert;

public class DMARTTestCases extends BaseClass{
	
	@Test(priority = 1)
	public void addFrozenSnacks() throws InterruptedException {
		//HomePage.clickOnFrozenSnacks();
		Assert.assertEquals(HomePage.clickOnFrozenSnacks(),"Frozen Snacks");
		CommonFunctions.turnOnImplicitWait();
		driver.findElement(FrozenSnacksPage.firstItemAdd).click();
		CommonFunctions.turnOnImplicitWait();
		driver.findElement(FrozenSnacksPage.firstItemAdd).click();
		CommonFunctions.turnOnImplicitWait();
		Assert.assertEquals((driver.findElement(FrozenSnacksPage.cartCount).getText()),"2");	
		
	}
	
	@Test(priority = 2)
	public void verifyProceedToChecoutEnabled() throws InterruptedException {
		CommonFunctions.turnOnImplicitWait();
		driver.findElement(FrozenSnacksPage.cartCount).click();	
		Assert.assertTrue(FrozenSnacksPage.checkoutIseNabled());
		
	}
	@Test(priority=3)
	public void scrollToElement() throws InterruptedException {
		FrozenSnacksPage.goToHomePage();
		CommonFunctions.turnOnImplicitWait();
		CommonFunctions.scroll();
	}
}
