package com.selenium.automation.SeleniumAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.automation.SeleniumAutomation.AbstractComponents.AbstractComponent;

public class ConformationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[class*='hero-primary']")
	WebElement conformationMessage;
	
	public String getConformationMessage() {
		return conformationMessage.getText();
		
	}
}
