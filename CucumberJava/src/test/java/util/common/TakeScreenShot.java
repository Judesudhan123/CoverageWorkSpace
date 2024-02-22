package util.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import util.common.ConfigReader;

public class TakeScreenShot {
	public static WebDriver driver;
	
	public TakeScreenShot(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void takeScreenShotPath(String screenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String basePath = ConfigReader.getBasePath();
		String pathname = basePath+"\\ScreenShot\\"+ screenshotName;
		File Des = new File(pathname);
		FileUtils.copyFile(source, Des);
	}
}
