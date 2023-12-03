package com.selenium.automation.SeleniumAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.automation.SeleniumAutomation.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class*='hero-primary']")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage() 
	{
		return confirmationMessage.getText();
	}  

}
