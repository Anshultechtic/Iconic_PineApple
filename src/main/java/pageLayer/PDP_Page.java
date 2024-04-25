package pageLayer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	@FindBy(css = "[class='quantity'] [id='Quantity-template--19304403992844__main']")
	private WebElement numberOfProducts;

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

	public void number_Of_Product_To_Add_in_Cart(int numberOfProduct) {

		waitUntilPresence(By.cssSelector("[class='quantity'] [name='plus'] svg"),5);
		
		
		for (int i = 0; i < numberOfProduct-1; i++) {

			waitTillClickable(product_quant_plus_sign_btn, 5);
			product_quant_plus_sign_btn.click();

		}

	}
	
	public void click_Add_To_Cart() {
		
		add_to_cart_button.click();
		
	}
	
	

	
}
