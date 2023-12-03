package com.selenium.automation.SeleniumAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.automation.SeleniumAutomation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	WebDriver driver;
	
	public OrderPage (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public Boolean VerifyOrderDisplay(String prodName)
	{
		Boolean match = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(prodName));
        return match;	
	}
	
	
}
