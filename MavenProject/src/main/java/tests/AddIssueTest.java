package tests;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;
import utils.*; 

public class AddIssueTest {
	public WebDriver driver;
	public Login login;
	public AddIssue addIssue;
	public Utils util; 
	
	@Test
	public void Should_BeAbleToAddIssue_When_ProvidingCorrectData() throws InterruptedException {
		String name = util.generateString(30);
		String descriptionName = util.generateString(40);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);
		
		addIssue.add(name, "Greta", "Active", "Obsolete", descriptionName, severity);
		
		Assert.assertEquals(addIssue.getCreatedIssueDescriptionText().getText(), descriptionName, "Missing description. Or issues not created.");
	}

	@Test
	public void Should_NotBeAbleToAddIssue_When_IssueNameIsMissing() throws InterruptedException {
		String descriptionName = util.generateString(100);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);

		addIssue.add("", "Greta", "Active", "Obsolete", descriptionName, severity);
	
		Assert.assertEquals(addIssue.getErrorMessage1().getText(), "Incorrect value: Required value is missing.",
				"Validation message is missing when an issue name is missing");
	}
	
	@Test
	public void Should_NotBeAbleToAddIssue_When_IssueNameIsEmptyString() throws InterruptedException {
		String descriptionName = util.generateString(100);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);
		addIssue.add("                  ", "Greta", "Active", "Obsolete", descriptionName, severity);
	
		Assert.assertEquals(addIssue.getErrorMessage1().getText(), "Incorrect value: Required value is missing.");
	}

	@Test
	public void Should_NotBeAbleToAddIssue_When_SeverityLevelIsTooHigh() throws InterruptedException {
		String descriptionName = util.generateString(100);
		String name = util.generateString(30);
		int severityTemp = util.generateSeverity(10, 100);
		String severity = Integer.toString(severityTemp);
		
		addIssue.add(name, "Greta", "Active", "Obsolete", descriptionName, severity);
		
		Assert.assertEquals(addIssue.getErrorMessage2().getText(), "Incorrect value: Number is too big.",
				"Validation message is missing when severity level is too high");
	}
	
	@Test
	public void Should_NotBeAbleToAddIssue_When_AssignedToInvalid() throws InterruptedException {
		String descriptionName = util.generateString(40);
		String name = util.generateString(30);
		String assignedTo = util.generateString(10);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);
		
		addIssue.add(name, assignedTo, "Active", "Obsolete", descriptionName, severity);
		
		Assert.assertEquals(addIssue.getNoMatchingAssigneeError().getText(), "Incorrect value: No matching item is selected.",
				"Validation message is missing when assignee is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToAddIssue_When_StatusInvalid() throws InterruptedException {
		String descriptionName = util.generateString(50);
		String name = util.generateString(30);
		String status = util.generateString(10);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);
		
		addIssue.add(name, "Greta", status, "Obsolete", descriptionName, severity);
		
		Assert.assertEquals(addIssue.getNoMatchingStatusError().getText(), "Incorrect value: No matching item is selected.",
				"Validation message is missing when status is invalid");
	}
	
	@Test
	public void Should_NotBeAbleToAddIssue_When_ReasonInvalid() throws InterruptedException {
		String descriptionName = util.generateString(50);
		String name = util.generateString(30);
		String reason = util.generateString(10);
		int severityTemp = util.generateSeverity(0, 5);
		String severity = Integer.toString(severityTemp);
		
		addIssue.add(name, "Greta", "Active", reason, descriptionName, severity);
		
		Assert.assertEquals(addIssue.getNoMatchingReasonError().getText(), "Incorrect value: No matching item is selected.",
				"Validation message is missing when reason is invalid");
		
	}
	
	@BeforeClass
	public void beforeClass() {

		util = new Utils();
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		driver.manage().window().maximize();
		driver.get(util.getUrl() + "/client/issues/addissue.php?folder=5");
		login = new Login(driver);
		login.login(util.getUserName(), util.getPassword());
		addIssue = new AddIssue(driver);
	}

	@AfterClass
	public void afterClass() {
      driver.quit();
	}

}
