package pageLayer;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
	public ArrayList<String> listOfCategories = new ArrayList<String>();

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

	@FindBy(css = ".right_logo_content p")
	private WebElement logo_content;

	@FindBy(css = ".product-item-link")
	private List<WebElement> products_inside_categories;

	@FindBy(css = ".product-info-main .cust_description.overview")
	private WebElement descriptionOfproduct;

	@FindBy(css = ".page-title .base")
	private WebElement nameOfproduct;

	@FindBy(css = "[itemprop='sku']")
	private WebElement skuOfproduct;

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

	public ArrayList<String> get_All_categoriesNames() throws IOException {
		Actions a = new Actions(driver);

		a.moveToElement(header_Categories).build().perform();
		for (int i = 0; i < categories_Name.size(); i++) {

			System.out.println(categories_Name.get(i).getText());
			WriteData("Iconic", categories_Name.get(i).getText(), i + 1, "Category");

		}

		return listOfCategories;
	}

	public void getProductDetails() throws IOException {

		Actions a = new Actions(driver);

		a.moveToElement(header_Categories).build().perform();
		int i = 1;

		for (WebElement product : categories_Name) {

			System.out.println(product.getText());
			waitTillClickable(product, 5);
			product.click();

			System.out.println("Number of Poduct in this Page is =" + products_inside_categories.size());

			for (WebElement products : products_inside_categories) {
//			for (int i = 0; i < products_inside_categories.size(); i++) {

				waitTillClickable(products_inside_categories.get(i), 5);

				a.moveToElement(products_inside_categories.get(i)).click().build().perform();
//				products_inside_categories.get(i).click();
				System.out.println(descriptionOfproduct.getText() + "==== " + nameOfproduct.getText());
				WriteData3("Iconic Product", descriptionOfproduct.getText().split("Description")[1], i, "Description");
				WriteData3("Iconic Product", nameOfproduct.getText(), i, "Name");
				WriteData3("Iconic Product", skuOfproduct.getText(), i, "SKU");
				i++;
				
				NavigateBack();
				System.out.println("Back");

				

			}

//			}
		}

	}

	public void clickHeaderCategories_links_Dynamic() {

		Actions a = new Actions(driver);

		a.moveToElement(header_Categories).build().perform();

		String clickontab = Keys.chord(Keys.CONTROL, Keys.ENTER);

		for (int i = 0; i < 5; i++) {
			categories_Name.get(i).sendKeys(clickontab);

		}

//		for (WebElement links : categories_Name) {
//
//			links.sendKeys(clickontab);
//
//		}
		Set<String> all_id = driver.getWindowHandles();
		Iterator<String> it = all_id.iterator();
		int j = 0;
		String parent_id = it.next();
		System.out.println("Parent id is: " + parent_id);
		while (it.hasNext()) {
			j++;
			String child_id = it.next();

			driver.switchTo().window(child_id);
			System.out.println(driver.getTitle() + logo_content.getText());

			System.out.println(j + " Child id is: " + child_id);
//			product_inside_categories.click();

		}

	}

}
