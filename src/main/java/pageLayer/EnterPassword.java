package pageLayer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UtilClass;

public class EnterPassword extends UtilClass {

	private WebDriver driver;

	public EnterPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".modal__toggle div")
	private WebElement EnterPasswordLink;

	@FindBy(id = "Password")
	private WebElement pwd_textbox;

	@FindBy(name = "commit")
	private WebElement submit_btn;

	public void enterWebsite() {

		EnterPasswordLink.click();
		pwd_textbox.sendKeys("stahlo");
		submit_btn.click();

	}

	public void handle_preview_bar() {

		driver.switchTo().frame("preview-bar-iframe");
		System.out.println(driver.findElement(By.cssSelector("[class='admin-bar'] p")).getText());
		driver.findElement(By.xpath("//button[text()='Close preview']")).click();
		driver.switchTo().defaultContent();
	}

	public void handle_captche() {

		driver.switchTo().frame(driver.findElement(By.cssSelector("[title='reCAPTCHA']")));

		driver.findElement(By.cssSelector("[class='recaptcha-checkbox-border']")).click();
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("[value='Submit']")).click();
	}
 
}
