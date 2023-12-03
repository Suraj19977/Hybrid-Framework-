
package com.selenium.automation.SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium.automation.SeleniumAutomation.PageObjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//com//selenium//automation//SeleniumAutomation//Resources//GlobalData.properties");
		p.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : p.getProperty("browser");
	 //   String browserName = p.getProperty("browser");
		
	    if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	    }
	    else if(browserName.equalsIgnoreCase("firefox")) {
	    	System.setProperty("webdriver.gecko.driver", 
	    			"C:\\Users\\91700\\Downloads\\geckodriver.exe");
	    	driver = new FirefoxDriver();
	    }
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//reading json to string 
		String jsonContent = FileUtils.readFileToString(new File(filePath),
		StandardCharsets.UTF_8);
        
		//string to HashMap -> Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	   {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	   }
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	

}
