package pageLayer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.UtilClass;

public class IconicPage extends UtilClass {

	private WebDriver driver;

	public IconicPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"popup\"]/div[1]/a")
	private WebElement closePopup;
	@FindBy(xpath = "//a[@ href='https://iconicpineapple.com/customer/account/login/referer/aHR0cHM6Ly9pY29uaWNwaW5lYXBwbGUuY29tLw%2C%2C/']")
	private WebElement loginButton;
	@FindBy(xpath = "//*[@id='login-email']")
	private WebElement email;
	@FindBy(xpath = "//*[@id='login-password']")
	private WebElement password;
	@FindBy(xpath = "//*[@class='action action-login secondary']")
	private WebElement login_submit;
	@FindBy(xpath = "//*[@data-role='loader']")
	private WebElement loader;
	@FindBy(xpath = "(//*[@class='desktopmenu'])/li/a")
	private List<WebElement> menu_links;

	public void login() {
		closePopup.click();
		loginButton.click();
		email.sendKeys("techtic.mukesh@gmail.com");
		password.sendKeys("Vanhise50");
		login_submit.click();
	}

	public void clickOnMenuNav_Links() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(loader));

		for (int i = 0; i < menu_links.size(); i++) {

			// wait.until(ExpectedConditions.invisibilityOf(loader));
			menu_links.get(i).click();

			driver.navigate().back();
		}
	}

}
