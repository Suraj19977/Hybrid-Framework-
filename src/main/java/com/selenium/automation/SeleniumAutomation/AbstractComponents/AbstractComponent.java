package com.selenium.automation.SeleniumAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.automation.SeleniumAutomation.PageObjects.CartPage;
import com.selenium.automation.SeleniumAutomation.PageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy (css= "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy (css= "[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}

	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
	//	WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(2));
	//	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
