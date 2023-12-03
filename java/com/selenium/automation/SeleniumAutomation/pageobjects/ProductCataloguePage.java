package com.selenium.automation.SeleniumAutomation.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.selenium.automation.SeleniumAutomation.AbstractComponents.AbstractComponent;

    public class ProductCataloguePage extends AbstractComponent{
	
	public WebDriver driver;
	
    public ProductCataloguePage(WebDriver driver)
	{
    	super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//	@FindBy(css="button[routerlink*=cart]")
//	WebElement cart;
	
	By productsBy = By.cssSelector(".mb-3");
	By addCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementsToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String prodName) {
		WebElement prod = getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
        return prod;	
	}
    public void addProductToCart(String prodName) throws InterruptedException {
    	WebElement prod = getProductByName(prodName);
    	prod.findElement(addCart).click();
    	waitForElementsToAppear(toastMessage);
    	waitForElementsToDisappear(spinner);	
    }

}
