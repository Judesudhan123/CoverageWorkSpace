package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.HomePage;
import util.common.TakeScreenShot;

public class ValidateAssertions {
	public static WebDriver driver;
	public static WebDriverWait wait;
	private HomePage homePage;
	private TakeScreenShot takeScreenShot;
	
	public ValidateAssertions (WebDriver driver){
		this.driver = driver;
		homePage = new HomePage(driver);
		takeScreenShot = new TakeScreenShot(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}
	public static String actualTitle;
	public static String expectedTitle;
	
	public void verifyTitle() {
		try {
			driver.navigate().to("https://www.amazon.in/");
			actualTitle = driver.getTitle();
			expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
//			expectedTitle = "Check";
			
			try {
			Assert.assertEquals("Title test fails", actualTitle,expectedTitle);
			}
			catch(Exception e) {
				System.out.println("Title Assert Verification is Failure :" +e.toString());
			}
			finally {
				if(!actualTitle.equals(expectedTitle)) {
					takeScreenShot.takeScreenShotPath("TitleError.png");
				}
			}
							
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	public void verifyBrandFilter () {
		try {
			List<WebElement> elements = driver
					.findElements(By.xpath("//h2[@class='a-size-mini s-line-clamp-1']//span"));
			
			for (int i = 0; i < elements.size(); i++) {
				if(!elements.get(i).getText().equals("Allen Sol") && !elements.get(i).getText().equals("Levi's")) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].scrollIntoView()", elements.get(i));
					takeScreenShot.takeScreenShotPath("BrandError"+i);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
