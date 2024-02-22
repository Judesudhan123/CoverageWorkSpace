package PageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.common.ElementUtils;

public class HomePage {
	public static WebDriver driver;
	public static WebDriverWait wait;

	@FindBy(xpath = "//div@class='ur class name")
	private WebElement popupContainer;

	@FindBy(id = "nav-hamburger-menu")
	private WebElement navHamburgerMenu;

	@FindBy(xpath = "//a[@class=\"hmenu-item\" and @data-menu-id=\"10\"]")
	private WebElement mensfashion;

	@FindBy(linkText = "Clothing")
	private WebElement clothing;

	@FindBy(xpath = "(//span[contains(text(),'4 Stars & Up')])[1]")
	private WebElement starRating;

	@FindBy(xpath = "//span[text()='₹1,000 - ₹1,500']")
	private WebElement pricerange;

	@FindBy(xpath = "//li[contains(@id,'Allen Solly')]//following-sibling::i")
	private WebElement allensollyBrand;

	@FindBy(xpath = "//li[contains(@id,'Levi')]//following-sibling::i")
	private WebElement levisBrand;

	@FindBy(xpath = "//span[text()=\"Men's Underwear Uno IntelliSoft Antimicrobial Micro Modal Trunk, Pack of 5\"]")
	private WebElement secondproduct;

	@FindBy(xpath = "//span[text()='Amazon Fashion']")
	private WebElement pageLoad;
	
	@FindAll({@FindBy(xpath = "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a")})
	private List<WebElement> productList;
	
	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	private WebElement addtoCart;

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement buyButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}

	public void naviagateToAmazonHomePage() throws IOException {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
	}

	public boolean isElementPresent(WebElement targetElement) {
		Boolean isPresent = false;
		try {
			return isPresent = targetElement.isDisplayed();
		} catch (Exception ex) {
			ex.printStackTrace();
			return isPresent;
		}

	}

	public boolean waitForVisibility(WebElement targetElement) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(targetElement));
			// wait.until(ExpectedConditions.visibilityOf(targetElement));
			return true;
		} catch (TimeoutException e) {
			System.out.println("Element is not visible: " + targetElement);
			System.out.println(e.getMessage());
			throw new TimeoutException();
		}
	}

	public void clickFashionAndMens() throws InterruptedException, IOException {
		WebElement navMenu = wait.until(ExpectedConditions.elementToBeClickable(navHamburgerMenu));
		navMenu.click();
		WebElement mensFashionLink = wait.until(ExpectedConditions.elementToBeClickable(mensfashion));
		mensFashionLink.click();
		wait.until(ExpectedConditions.elementToBeClickable(clothing));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", clothing);
		// while(!isElementPresent(pageLoad))
		// {

		// }
	}

	public void filterByCustomerReview() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", starRating);
	}

	public void filterByPriceRange() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", pricerange);
	}

	public void filterByBrand() throws InterruptedException {
//		Thread.sleep(3000);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click()", allensollyBrand);
		WebElement allenBrandFilter = wait.until(ExpectedConditions.elementToBeClickable(allensollyBrand));
		allenBrandFilter.click();
		WebElement levisBrandFilter = wait.until(ExpectedConditions.elementToBeClickable(levisBrand));
		levisBrandFilter.click();

	}
		
	public void countOftheProduct() throws InterruptedException {
		Thread.sleep(3000);
		
		List<WebElement> elements = driver
				.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a"));
		
		System.out.println("Counts of the Result   " + " : " + elements.size());

		for (int i = 0; i < elements.size(); i++) {

			if (i == 1) {
				elements.get(i).click();
			}

		}

	}

	public String clickAddtocart() throws InterruptedException {
		String a = driver.getWindowHandle();
		Set<String> b = driver.getWindowHandles();

		for (String c : b) {
			if (!c.equals(a)) {
				driver.switchTo().window(c);
			}
		}
		
		WebElement addtoCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addtoCart));
		addtoCartBtn.click();
		
		WebElement addtocart = driver
				.findElement(By.xpath("//h1[@class=\"a-size-medium-plus a-color-base sw-atc-text a-text-bold\"]"));
		String addTocartsuccess = getElementText(addtocart);
		return addTocartsuccess;

	}

	public String getCartCount() throws InterruptedException {
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='nav-cart-count']")));
		String text = element.getText();
		return text;
	}

	public String getElementText(WebElement element) {
		String text = element.getText();
		return text;
	}

	public void buyProduct() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", buyButton);

	}
}
