package pageLayer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UtilClass;

public class PDP_Page extends UtilClass {

	private WebDriver driver;

	public PDP_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".product-info-main h5")
	private WebElement descriptionOfproduct;
	
	@FindBy(css = ".page-title .base")
	private WebElement nameOfproduct;

	@FindBy(css = "[class='product-form__buttons'] [type='submit']")
	private WebElement add_to_cart_button;

	@FindBy(css = "product-info [class='product__title'] h1")
	private WebElement Product_name;

	@FindBy(css = "product-info [class='price__regular'] [class='price-item price-item--regular']")
	private WebElement product_price;

	@FindBy(css = "[class='quantity'] [name='plus']")
	private WebElement product_quant_plus_sign_btn;

	@FindBy(css = "[class='quantity'] [name='minus']")
	private WebElement product_quant_minus_sign_btn;

	public String get_Description() {

		return descriptionOfproduct.getText();

	}
	
	public String get_nameOfproduct() {

		return nameOfproduct.getText();

	}
	
	

	

}
