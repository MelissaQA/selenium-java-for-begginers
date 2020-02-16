package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

	@Test
	public void incorrectUsernameTest() {
		System.out.println("Start - Incorrect Username Test");
//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
//		expand window
		driver.manage().window().maximize();
		
//		enter username
		//$x("")
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrect");

//		enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		WebElement loginButton = driver.findElement(By.tagName("button"));
//		click login button
		loginButton.click();
		sleep(2000);
		
//		Verifications:
//		Incorrect user message
		WebElement errorMessage = driver.findElement(By.cssSelector("#flash"));
		String actualMessage = errorMessage.getText();
		String expectedMessage = "Your username is invalid!";
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Flash messages don't match. Actual: "+actualMessage+ " - Expected: "+expectedMessage);
		//Close browser
		driver.quit();
		
	}
	
	@Test
	public void incorrectPasswordTest() {
		
		System.out.println("Start - Incorrect Password Test");
//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
//		expand window
		driver.manage().window().maximize();
		
//		enter username
		//$x("")
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrect!");

		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(2000);
		
//		Verifications:
//		Incorrect password message
		WebElement errorMessage = driver.findElement(By.cssSelector("#flash"));
		String actualMessage = errorMessage.getText();
		String expectedMessage = "Your password is invalid!";
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Flash messages don't match. Actual: "+actualMessage+ " - Expected: "+expectedMessage);
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
