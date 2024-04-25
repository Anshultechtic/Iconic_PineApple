package pageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseTest.BaseTest;
import utilities.UtilClass;

public class MyAccountPage extends UtilClass {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='customer account section-template--19304403534092__main-padding']/div[2]/div[2]/p")
	private WebElement logged_in_username;
	
	@FindBy(css = ".errors li")
	private WebElement error;
	
	public String get_logged_in_username() {
		
		return logged_in_username.getText();
		
	}

    public String get_Error_message() {
		
		return error.getText();
		
	}
	
	
}
