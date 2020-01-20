package pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.*;

public class AddIssue extends PageObject {
	
	public Utils util = null;

	@FindBy(linkText = "Add Issue")
	private WebElement selectIssue;
	
	@FindBy(id = "field-issues-issueName")
	private WebElement issueName;

	@FindBy(id = "field-issues-descriptionText")
	private WebElement descriptionName;

	@FindBy(id = "field-issues-value4")
	private WebElement severity;

	@FindBy(id = "field-issues-okSubmit")
	private WebElement okButton;

	@FindBy(xpath = "//*[@id='form-issues']/div/div[1]/p")
	private WebElement errorMessage1;

	@FindBy(xpath = "//*[@id='form-issues']/div/div[2]/div[5]/p")
	private WebElement errorMessage2;
	
	@FindBy(xpath = "//*[@id=\"body\"]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div[2]")
	private WebElement createdIssueDescriptionText;
	
	@FindBy (id = "field-issues-value1")
	private WebElement assignedTo;
	
	@FindBy (id = "field-issues-value2")
	private WebElement status;
	
	@FindBy (id = "field-issues-value3")
	private WebElement reason;
	
	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[2]/p")
	private WebElement noMatchingAssigneeError;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[3]/p")
	private WebElement noMatchingStatusError;
	
	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[4]/p")
	private WebElement noMatchingReasonError;
	
	
	// sukuriame konstruktoriu, kad veliau galetume accessinti metodus --> 
	public AddIssue(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getSelectIssue( ) {
		return selectIssue;
	}

	public WebElement getIssueName() {
		return issueName;
	}

	public WebElement getDescriptionName() {
		return descriptionName;
	}

	public WebElement getSeverity() {
		return severity;
	}

	public WebElement getOkButton() {
		return okButton;
	}

	public WebElement getErrorMessage1() {
		return errorMessage1;
	}

	public WebElement getErrorMessage2() {
		return errorMessage2;
	}
	
	public WebElement getCreatedIssueDescriptionText() {
		return createdIssueDescriptionText;
	}
	
	public WebElement getAssignedTo() {
		return assignedTo;
	}
	
	public WebElement getStatus() {
		return status;
	}
	
	public WebElement getReason() {
		return reason;
	}
	
	public WebElement getNoMatchingAssigneeError() {
		return noMatchingAssigneeError;
	}
	
	public WebElement getNoMatchingStatusError() {
		return noMatchingStatusError;
	}
	
	public WebElement getNoMatchingReasonError() {
		return noMatchingReasonError;
	}

	public void add(String name, String assignedTo, String status, String reason, String description, String severity) throws InterruptedException {
		Utils util = new Utils();
		driver.get(util.getUrl() + "/client/index.php?folder=5");
		this.getSelectIssue().click();
		
//		Thread.sleep(1500);
		
//		Selenium WebDriverWait is one of the Explicit waits. It is applied on certain element with defined EXPECTED CONDITION and TIME
//		vietoj sito thread sleep'o rasome WebDriverWait wait...., bet nesuveikia for some reason (taip ir pas Liuda) --> 
		
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.elementToBeClickable(issueName));  
		
//      bet matome, kad nepadeda, todel bandome toki hacka su getIssueName: klikinam, irasom, istrinam ir vel irasom, turetu veikti--> 
		this.getIssueName().click();
//		this.getIssueName().sendKeys(name);
//		this.getIssueName().clear();
//		this.getIssueName().sendKeys(name);
		
		boolean issueNameExists = false;
	      while(!issueNameExists) {
	          this.issueName.clear();
	          this.issueName.sendKeys(name);
	          if(this.issueName.getAttribute("value").equals(name)) {
	              issueNameExists = true;
	          }
	    }
	      
		this.getAssignedTo().clear();
		this.getAssignedTo().sendKeys(assignedTo);
		
		this.getStatus().clear();
		this.getStatus().sendKeys(status);
		
		this.getReason().clear();
		this.getReason().sendKeys(reason);
		
		this.getDescriptionName().sendKeys(description);
		
		this.getSeverity().clear();
		this.getSeverity().sendKeys(severity);
		
		this.getOkButton().click();
	}
}
