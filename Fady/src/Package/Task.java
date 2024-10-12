package Package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task {
	
	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fady\\Downloads\\chromedriver-win64\\chromedriver-win64");
	WebDriver driver = new ChromeDriver();
	Actions actions = new Actions(driver);
	
	
	
	
	
	@BeforeTest
	public void openWebsite() {
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	
		

	}
	
	@Test (priority=0)
	public void checkVisibility() {
		Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
	
	}
	
	@Test (priority=1)
	public void validCred() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		WebElement div = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
		Assert.assertTrue(div.isDisplayed());
		String validMsg = div.getText();
		Assert.assertEquals(validMsg, "Swag Labs");
		
	
	}
	
	@Test (priority=2)
	public void invalidCred() {
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("user-name")).sendKeys("invalid_user");
		driver.findElement(By.id("password")).sendKeys("wrong_sauce");
		driver.findElement(By.id("login-button")).click();
		WebElement div = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
		Assert.assertTrue(div.isDisplayed());
		String invalidMsg = div.getText();
		Assert.assertEquals(invalidMsg, "Epic sadface: Username and password do not match any user in this service");
	
	}
	
	
	
	@Test (priority=3)
	public void emptyUser() {
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		WebElement div = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
		Assert.assertTrue(div.isDisplayed());
		String emptyMsg = div.getText();
		Assert.assertEquals(emptyMsg, "Epic sadface: Username is required");
	
	}
	
	@Test (priority=4)
	public void emptyPass() {
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("login-button")).click();
		WebElement div = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
		Assert.assertTrue(div.isDisplayed());
		String emptyMsg = div.getText();
		Assert.assertEquals(emptyMsg, "Epic sadface: Password is required");
	
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
