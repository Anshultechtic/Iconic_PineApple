package pageLayer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.UtilClass;

public class Shop_All_Page extends UtilClass {

	private WebDriver driver;

	public Shop_All_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//*[@id='m-navigation-product-list-wrapper']//*[@class='info'])")
	private List<WebElement> products_inside_categories;
	
	public void clickOnProduct() {
		
		for (WebElement product : products_inside_categories) {
			product.click();
			
		}
	}
	

}
