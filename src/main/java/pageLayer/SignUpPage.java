package pageLayer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UtilClass;

public class SignUpPage extends UtilClass {

	private WebDriver driver;

	public SignUpPage(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[name='customer[first_name]']")
	private WebElement fName_txt_box;
//	@FindBy(xpath="//*[@formcontrolname='first_name']")
//	private WebElement fName_Error_msg;
	@FindBy(css = "[name='customer[last_name]']")
	private WebElement lName_txt_box;
	@FindBy(css = "[name='customer[email]']")
	private WebElement email_txt_box;
	@FindBy(css = "[customer[password]']")
	private WebElement pwd_txt_box;
	@FindBy(css = "[id='create_customer'] button")
	private WebElement create_account_btn;
	@FindBy(css = "[id= 'create_customer'] a")
	private WebElement login_Link;

	@FindBy()
	private WebElement birthDate_txt_box;

	@FindBy(css = ".gen_opt [value='Male']")
	private WebElement sexTypeMale;
	@FindBy(xpath = ".gen_opt [value='Female']")
	private WebElement sexTypeFemale;
	@FindBy(css = ".num-email-in  input[formcontrolname = 'phone']")
	private WebElement mobileNumber_txt_box;
	@FindBy(css = "[id='create_customer'] button")
	private WebElement Create_Account_btn;

	@FindBy(css = ".num-email-in  input[formcontrolname = 'confirmEmail']")
	private WebElement confirmEmail_txt_box;
	@FindBy(css = "[name='customer[password]']")
	private WebElement password_txt_box;
	@FindBy(css = "input[formcontrolname= 'location']")
	private WebElement Address_txt_box;
	@FindBy(css = ".agree input[formcontrolname= 'terms']")
	private WebElement term_condition_check_box;

	public void Enter_fName_txt_box(String fName) {

		fName_txt_box.sendKeys(fName);
	}

	
	public void Enter_lName_txt_box(String lName) {

		lName_txt_box.sendKeys(lName);
	}

	public void enter_email(String email) {

		email_txt_box.sendKeys(email);
	}

	public void enter_password(String pwd) {

		password_txt_box.sendKeys(pwd);

	}

	public void click_On_Create_Account_btn() {

		ScrollIntoView(Create_Account_btn);
		Create_Account_btn.click();
	}

}
