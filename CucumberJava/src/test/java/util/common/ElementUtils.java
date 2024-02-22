package util.common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	
	private static final Duration DEFAULT_TIMEOUT =Duration.ofSeconds(10);
	
	public static boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public static WebElement waitForVisibility(WebDriver driver, By locator, Duration defaultTimeout) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//Overloaded method with default timeout
	public static WebElement waitForVisibility(WebDriver driver, By locator) {
		return waitForVisibility(driver, locator, DEFAULT_TIMEOUT);
	}
	
	public static List<WebElement> waitForVisibilityofAllElements(WebDriver driver, By locator, Duration defaultTimeout) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	//Overloaded method with default timeout
	public static List<WebElement> waitForVisibilityofAllElements(WebDriver driver, By locator) {
		return waitForVisibilityofAllElements(driver, locator, DEFAULT_TIMEOUT);
	}

}
