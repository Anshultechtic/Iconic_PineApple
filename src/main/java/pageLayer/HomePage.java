package pageLayer;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.Keys;

import utilities.UtilClass;

public class HomePage extends UtilClass {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".popupWrapper a")
	private WebElement popup_close_button;

	@FindBy(xpath = "//*[@class='desktopmenu']//*[contains(text(),'Categories')]")
	private WebElement header_Categories;

	@FindBy(xpath = "//*[@class= 'megamenu-content']//a")
	private List<WebElement> categories_Name;

	@FindBy(xpath = "//*[@class= 'megamenu-content']")
	private List<WebElement> categories_Name2;

	@FindBy(xpath = "(//*[@id='m-navigation-product-list-wrapper']//*[@class='info'])[1]")
	private WebElement product_inside_categories;

	public void closePopup() {
		popup_close_button.click();
	}

	public void clickHeaderCategories() {

		Actions a = new Actions(driver);

		a.moveToElement(header_Categories).build().perform();

		for (WebElement product : categories_Name) {

			a.moveToElement(header_Categories).build().perform();

			System.out.println(product.getText());

			product.click();

			product_inside_categories.click();

		}

	}

	public void clickHeaderCategories_links_Dynamic() {
		Actions a = new Actions(driver);
		a.moveToElement(header_Categories).build().perform();

		String clickontab = Keys.chord(Keys.CONTROL, Keys.ENTER);

		for (WebElement links : categories_Name2) {

			a.moveToElement(links.findElement(By.tagName("a"))).sendKeys(clickontab).build().perform();

		}
		Set<String> all_id = driver.getWindowHandles();
		Iterator<String> it = all_id.iterator();

	}

}
