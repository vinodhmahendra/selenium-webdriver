package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	
	@Test
	public void loginTest() {
		System.out.println("starting login test");
		
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		//sleep for 3 seconds
		sleep(3000);
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//open test page
		String url= "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened");
		
		//sleep for 2 seconds
		sleep(2000);
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1000);
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(3000);
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		//Verifications
		//new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
		//logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@href='/logout']"));
		Assert.assertTrue(logoutButton.isDisplayed(),"Log out button is not visible");
		
		//successful login message
//		WebElement successMessage = driver.findElement(By.cssSelector("#flash")); success
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage,expectedMessage,"Actual message is not  the same as expected.");
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message does not contain expected message. \n"
				+ "Actual Message:" +actualMessage + "\n Expected Message: "+expectedMessage);
		
		//close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
