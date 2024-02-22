package StepDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObjects.HomePage;
import PageObjects.ValidateAssertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.common.ConfigReader;
import util.common.TakeScreenShot;

public class AmazonSteps {

	private WebDriver driver;
	private HomePage homePage;
	private ValidateAssertions validateAssertions;
	private TakeScreenShot takeScreenShot;

	@Given("User is on Amazon.in homepage")
	public void user_is_on_amazon_in_homepage() throws IOException {

//		String currentDirectory=System.getProperty("user.dir");
//		System.out.println("currentDirectory"+currentDirectory);
		String driverPath = ConfigReader.getDriverPath();
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();

		homePage = new HomePage(driver);
		validateAssertions = new ValidateAssertions(driver);
		takeScreenShot = new TakeScreenShot(driver);

		homePage.naviagateToAmazonHomePage();
		validateAssertions.verifyTitle();
	}

	@When("User clicks on Fashion and selects Mens")
	public void user_clicks_on_fashion_and_selects_mens() throws InterruptedException, IOException {
		homePage.clickFashionAndMens();
	}

	@When("User filters products by Average customer review of four stars and Up")
	public void user_filters_products_by_average_customer_review_of_four_stars_and_up() {
		homePage.filterByCustomerReview();
	}

	@When("user filters products by price range")
	public void user_filters_products_by_price_range() {
		homePage.filterByPriceRange();
	}

	@When("User selects Allen Solley in Brands")
	public void user_selects_allen_solley_in_brands() throws InterruptedException {
		homePage.filterByBrand();
	}

	@When("User counts the number of results in the first page")
	public void user_counts_the_number_of_results_in_the_first_page() throws InterruptedException {
		homePage.countOftheProduct();
		validateAssertions.verifyBrandFilter();
	}

	@When("User clicks on the second product and add it to Cart {string}")
	public void user_clicks_on_the_second_product_and_add_it_to_cart(String expectedValue)
			throws InterruptedException, IOException {
		String actualValue = homePage.clickAddtocart();
		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("Add to Cart Verification Success");
	}

	@Then("User validates that the number on the Cart is increased by {string}")
	public void user_validates_that_the_number_on_the_cart_is_increased_by(String expectedCount)
			throws IOException, InterruptedException {
		String actualValue = (String) homePage.getCartCount();
		takeScreenShot.takeScreenShotPath("AddtoCartPage.png");
		Assert.assertEquals(actualValue, expectedCount);
		System.out.println("Cart count Verified Successfully");

		if (!actualValue.equals(expectedCount)) {
			System.out.println("Cart Count Assert Verification failed");
			takeScreenShot.takeScreenShotPath("CartCountError.png");
		}
	}

	@Then("User proceed to buy the item")
	public void user_proceed_to_buy_the_item() {
		homePage.buyProduct();
	}
}
