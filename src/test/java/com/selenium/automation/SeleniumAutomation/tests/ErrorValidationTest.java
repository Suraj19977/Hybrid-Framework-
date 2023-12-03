package com.selenium.automation.SeleniumAutomation.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.automation.SeleniumAutomation.PageObjects.CartPage;
import com.selenium.automation.SeleniumAutomation.PageObjects.ProductCataloguePage;
import com.selenium.automation.SeleniumAutomation.TestComponents.BaseTest;
import com.selenium.automation.SeleniumAutomation.TestComponents.Retry;


	public class ErrorValidationTest extends BaseTest {
		
		   @Test(groups= {"ErrorHandling"})//inside bracket ,retryAnalyzer = Retry.class) for rerun failed test case
		   public void LoginErrorValidation() throws IOException, InterruptedException {
			String prodName= "ZARA COAT 3";
			lp.loginApplication("mechanics76@gmail.com", "1@Suraj1997");
			Assert.assertEquals("Incorrect email password.", lp.getErrorMessage()); //("Incorrect email password.",lp.getErrorMessage());
		   }
		   
		     
		   @Test
		   public void ProductErrorValidaton() throws IOException, InterruptedException 
		   {
			String prodName= "ZARA COAT 3";
			ProductCataloguePage pcp = lp.loginApplication("sooraj121roman@gmail.com", "1@Suraj1997");
			List<WebElement> products = pcp.getProductList();
			pcp.addProductToCart(prodName);
			CartPage cp = pcp.goToCartPage();
			Boolean match = cp.VerifyCartDisplay("ZARA COAT 43");
			Assert.assertFalse(match);
		   }

	}

