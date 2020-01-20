package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import pageobject.*;
import utils.*; 

public class LoginTest {
	public WebDriver driver;
	public Login login;
	public Utils util; 

	@Test(groups = { "smoke", "testingRegression" })
	public void Should_BeAbleToLogin_When_UserNameAndPasswordAreValid() {
		login.login(util.getUserName(), util.getPassword());
		
//		pabaigti rasyti naudojant Assert.assertTrue, kad jeigu kontainina cookie elementa [zr konsoleje --> [WebIssuesSID=3jitcgfs019acu16hui04rngedl0ia7r; path=/register/; domain=www.qaontime.com]
//		ir pasirinkti metoda, kad jeigu containina (.contains) cookie fragmenta WebIssuesSID, reiskias sekmingai isiloginau. 
//		System.out.println(driver.manage().getCookies().toString());
// 		Paaiskinimas, kam reikalingas metodas toString: https://www.geeksforgeeks.org/object-tostring-method-in-java/ 
		Assert.assertTrue(driver.manage().getCookies().toString().contains("WebIssuesSID"), "WebIssuesSID cookie does not exist");
		
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_UserNameIsInvalid() throws InterruptedException {
		login.login(util.generateString(40), util.getPassword());

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a user name is invalid");
	}

	@Test
	public void Should_NotBeAbleToLogin_When_PasswordIsInvalid() throws InterruptedException {
		login.login(util.getUserName(), util.generateString(40));

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordIsEmptyStrings() throws InterruptedException {
		login.login(util.getUserName(), "     .      ");

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordIsCrossSiteScripted() throws InterruptedException {
		login.login(util.getUserName(), "<b>jslvhlskdh</b> ");

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordContainsScript() throws InterruptedException {
		login.login(util.getUserName(), "<script>alert('testing')</script>");

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordContainsSQL() throws InterruptedException {
		login.login(util.getUserName(), "or 1=1--");

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordContainsSymbols() throws InterruptedException {
		login.login(util.getUserName(), "*-/%^&**$£");

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToLogin_When_PasswordContainsNewLineCharacters() throws InterruptedException {
		login.login(util.getUserName(), "/n");
		
		util.readExcelFileWithJava();

		Assert.assertEquals(login.getErrorMessage().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing when a password is invalid");
	}
	
///////Sita dali deti prie Login testu
//
//@Test
//public void Should_NotBeABleToLogin_When...
//List<String> al = util.readLoginPasswords();
//
//for (String s : al) {
//	
//	System.out.println(s);
//	login.login(util.getUsername(), s);
	
	
//	
//	try {
//		WebElement elementName = driver.findElement(By.className("error"));
//		
//		Assert.assertEquals(elementName.getText(), "Incorrect value: Invalid login or password.", 
//				"not working when pass = " + s);
//		
//	} catch (NoSuchElementException) {
//		false, 	"not working when pass = " + s);
//		// TODO: handle exception
//	}
//}
//*************************************************************************************************	
	
	// logout galima padaryti ir istrinant cookie --> 
	//driver.manage().deleteCookieNamed(" kukio pavadinimas"); ARBA --> 
	// driver.manage().deleteAllCookies();
	
	@BeforeClass(groups = { "smoke", "testingRegression" })
	public void beforeClass() {
		util = new Utils();
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(util.getUrl());
		login = new Login(driver);
	}

	@AfterClass(groups = { "smoke", "testingRegression" })
	public void afterClass() {
      driver.quit();
	}
}
