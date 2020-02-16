package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveTests {

	private WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browser) {
//		Create driver
		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Selected "+browser+"browser but not allowed. Default browser is Chrome!");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		
	}
	
	@Test
	public void loginTest() {
		System.out.println("Start - Login Test");
		
//		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
//		expand window
		driver.manage().window().maximize();
		
//		enter username
		//$x("")
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		//Enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		//Click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(2000);
		
//		Verifications:
//		New url
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		Assert.assertEquals(actualUrl, expectedUrl);
		
//		 logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(),"Logout button is not displayed!");
		
//		 successful login message
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String actualMessage = successMessage.getText();
		String expectedMessage = "You logged into a secure area!";
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Login messages don't match. Actual: "+actualMessage+ " - Expected: "+expectedMessage);
		tearDown();
		
	}

	@AfterMethod
	private void tearDown() {
		//Close browser
		driver.quit();
	}
	
	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
