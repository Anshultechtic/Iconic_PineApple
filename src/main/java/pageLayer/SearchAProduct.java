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

public class SearchAProduct extends UtilClass {
	private WebDriver driver;
	public ArrayList<String> listOfCategories = new ArrayList<String>();

	public SearchAProduct(WebDriver driver) {
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

	@FindBy(css = ".toolbarbottom.pagination .action.next")
	private WebElement pagination_Next_btn;
	
	@FindBy(id = "search")
	private WebElement search_text_box;
	
	@FindBy(css = ".mst-searchautocomplete__wrapper li")
	private WebElement searched_item;

	public void closePopup() {
		popup_close_button.click();
	}

	public void Search_box(String sku) {
		
		search_text_box.sendKeys(sku);
		
		
		waitTillClickable(searched_item, 5);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searched_item.click();
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
