package pageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UtilClass;

public class LoginPage extends UtilClass {

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	} 

	
	@FindBy(id = "CustomerEmail")
	private WebElement emailId_txtBox;
	@FindBy(id = "CustomerPassword")
	private WebElement pwd_txtBox;
	@FindBy(css = "form[id='customer_login'] button")
	private WebElement login_btn;
	@FindBy(css = "[href='/account/register']")
	private WebElement create_account_link;
	@FindBy(css = "[href='#recover']")
	private WebElement forgot_pwd_link;
	@FindBy(css = "[id='rc_login'] a")
	private WebElement manage_subscription_link;
	@FindBy(css = "[class='errors'] li")
	private WebElement error_message;
	
	

	@FindBy(xpath = "//*[@role='alert']")
	private WebElement toaster_box_Otp_sent_success;
	@FindBy(xpath = "//*[@role='alert']")
	private WebElement toaster_box_sign_in_success;
	
	@FindBy(css = ".ng-star-inserted small")
	private WebElement password_error_message;
	
	@FindBy(id = "login")
	private WebElement main_body_link;
	
	
	
	public void click_on_create_account() {
		
		ScrollIntoView(create_account_link);
	
		create_account_link.click();
		
	}

	

	public void enterEmailId(String eId) {

		emailId_txtBox.sendKeys(eId);

	}

	public void enterPassword(String pwd) {

		pwd_txtBox.sendKeys(pwd);

	}

	public void clickOnLoginButton() {

		ScrollIntoView(login_btn);
		login_btn.click();


	}

	
	

	public void getToasterTextOfOtp() {

	System.out.println(toaster_box_Otp_sent_success.isDisplayed() + " " + toaster_box_Otp_sent_success.getText());
		waitTillInvisible(toaster_box_Otp_sent_success, 5);

	}

	public void getToasterTextOfSignInSuccess() {

		System.out.println(toaster_box_Otp_sent_success.isDisplayed() + " " + toaster_box_Otp_sent_success.getText());

	}

	public String toasterText() {

		return toaster_box_Otp_sent_success.getText();

	}

	public String getErrorMessage() {

		return error_message.getText();
	}



}
