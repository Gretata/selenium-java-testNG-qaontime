package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.*;

public class Login extends PageObject {
	public Utils util = null;
	
	@FindBy(id="field-login-login")
	private WebElement loginField;
	
	@FindBy(id="field-login-password")
	private WebElement passwField;
	
	@FindBy(id="field-login-loginSubmit")
	private WebElement loginSubmit;
	
	@FindBy(className="error")
	private WebElement errorMessage;
	
	@FindBy(linkText="Log Out")
	private WebElement logoutButton;
	
	// susikuriame KONSTRUKTORIU, kad veliau galetume accessinti visus savo  susikurtus metodus kituose failuose-->
	public Login(WebDriver driver) {
		super(driver);
	}
		
		public WebElement getLoginField() {
			return loginField;
		}
		
		public WebElement getPasswField() {
			return passwField;
		}
		
		public WebElement getLoginSubmit() {
			return loginSubmit;
		}
		
		public WebElement getErrorMessage() {
			return errorMessage;
		}

		public WebElement getLogoutButton() {
			return logoutButton;		}
		
		public void login(String userName, String password) {
			Utils util = new Utils();
			driver.get(util.getUrl() + "/client/index.php?folder=5");
			this.loginField.clear();
			this.passwField.clear();
			this.loginField.sendKeys(userName);
			this.passwField.sendKeys(password);
			this.loginSubmit.click();
			
		}
	 }

