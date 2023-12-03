package com.selenium.automation.SeleniumAutomation.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.automation.SeleniumAutomation.PageObjects.CartPage;
import com.selenium.automation.SeleniumAutomation.PageObjects.CheckoutPage;
import com.selenium.automation.SeleniumAutomation.PageObjects.OrderPage;
import com.selenium.automation.SeleniumAutomation.PageObjects.ProductCataloguePage;
import com.selenium.automation.SeleniumAutomation.PageObjects.confirmationPage;
import com.selenium.automation.SeleniumAutomation.TestComponents.BaseTest;



public class StandAloneTest extends BaseTest {
	String prodName= "ZARA COAT 3";
	
	   @Test(dataProvider = "getData", groups = {"Purchase"})
	   public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCataloguePage pcp = lp.loginApplication(input.get("email"), input.get("pwd"));
		
		List<WebElement> products = pcp.getProductList();
		pcp.addProductToCart(input.get("product"));
		CartPage cp = pcp.goToCartPage();
		
		Boolean match = cp.VerifyCartDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cp.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage conformationPage  = checkoutPage.submitOrder();
		String confirmMessage = conformationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	   @Test(dependsOnMethods = {"submitOrder"})
	   public void orderHistory()
	   {
		   ProductCataloguePage pcp = lp.loginApplication("mechanicsf76@gmail.com", "1@Suraj1997");
	       OrderPage op = pcp.goToOrdersPage();
	       Assert.assertTrue(op.VerifyOrderDisplay(prodName));
	   }
	   
	   @DataProvider
	   public Object[][] getData() throws IOException
	   {

		   List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//selenium//automation//SeleniumAutomation//data//PurchaseOrder.json");
		   return new Object [] []   {{data.get(0)},{data.get(1)}};
	   }
	  //or else this can also be applied inside the block 
	  // {
	  //   return new Object [] []  {{"mechanicsf76@gmail.com","1@Suraj1997","ZARA COAT 3"},{"sooraj121roman@gmail.com","1@Suraj1997","ADIDAS ORIGINAL"}};
	  // }
	 //  or
//	   HashMap<String, String> map = new HashMap<String, String>();
//	   map.put("email", "mechanicsf76@gmail.com");
//	   map.put("pwd", "1@Suraj1997");
//	   map.put("product", "ZARA COAT 3");
//	   
//	   HashMap<String, String> map1 = new HashMap<String, String>();
//	   map1.put("email", "sooraj121roman@gmail.com");
//	   map1.put("pwd", "1@Suraj1997");
//	   map1.put("product", "ADIDAS ORIGINAL");
}
