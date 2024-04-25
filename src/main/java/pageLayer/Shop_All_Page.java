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

	@FindBy(id = "email")
	private WebElement email_news_offers;

	@FindBy(id = "Select0")
	private WebElement country_origin_DD;

	@FindBy(id = "TextField0")
	private WebElement first_name;

	@FindBy(id = "TextField1")
	private WebElement last_name;

	@FindBy(id = "TextField2")
	private WebElement company;

	@FindBy(id = "shipping-address1")
	private WebElement address;

	@FindBy(css = "[role='option']")
	private List<WebElement> list_suggested_cities;

	@FindBy(xpath = "(//*[@id='shipping-address1']/following::div)[2]")
	private WebElement add_appartment_button;

	@FindBy(xpath = "//*[@placeholder='Apartment, suite, etc. (optional)']")
	private WebElement add_appartment_textbox;

	@FindBy(name = "postalCode")
	private WebElement postal_code;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(id = "Select2")
	private WebElement province_DD;

	@FindBy(name = "phone")
	private WebElement phone_num;

	@FindBy(id = "save_shipping_information")
	private WebElement save_info_checkbox;

	@FindBy(id = "basic-PAYPAL_EXPRESS")
	private WebElement paypal_radio_button;

	@FindBy(id = "basic-paymentOnDelivery")
	private WebElement cod_radio_button;

	@FindBy(id = "tipping_list-tipping_list_options")
	private WebElement add_tip_checkbox;

	@FindBy(id = "Password")
	private List<WebElement> tips_options;

	@FindBy(xpath = "//*[@aria-label='Increase Custom tip']")
	private WebElement custom_tip_plus;

	@FindBy(xpath = "//*[@aria-label='Decrease Custom tip']")
	private WebElement custom_tip_minus;

	@FindBy(xpath = "(//*[@class='b7U_P']//*[@type='submit'])[1]")
	private WebElement update_tip_btn;

	// quantity =[class='blA7b'] [class='_6zbcq524 _1fragemkj _1fragem2i _6zbcq52b']
	// :nth-child(1) div :nth-child(2) :nth-child(1) div

	@FindBy(css = "[class='blA7b'] [class='_1m6j2n3e _1fragemih _1fragemok _1fragemp4'] :nth-child(1) :nth-child(1)")
	private List<WebElement> check_out_Quantity;

	@FindBy(css = "[class='blA7b'] [class='_6zbcq524 _1fragemkj _1fragem2i _6zbcq52b'] p")
	private List<WebElement> check_out_Names;

	@FindBy(css = "[class='blA7b'] [class='_6zbcq524 _1fragemkj _1fragem2i _6zbcq52b'] :nth-child(4) span")
	private WebElement paypal_radio_price;
	
	
	@FindBy(css = ".product__price span")
	private List <WebElement> product_price;

	@FindBy(id = "[class='nfgb6p0'] :nth-child(4) div :nth-child(2)")
	private WebElement total_price;

	@FindBy(name = "//div[@aria-label='Pay with PayPal']")
	private WebElement pay_btn;

	@FindBy(css = "[id='pay-button-container'] [type='submit']")
	private WebElement complete_order_btn;
	
	@FindBy(css = ".os-header__heading h2")
	private WebElement success_message;

	public void enterEmail(String email) {

		email_news_offers.sendKeys(email);
	}

	public void select_Country(String country) {

		country_origin_DD.click();

		Select s = new Select(country_origin_DD);

		s.selectByVisibleText(country);

	}

	public void enter_firstName(String f_name) {

		first_name.sendKeys(f_name);
	}

	public void enter_lastName(String l_name) {
		last_name.sendKeys(l_name);
	}

	public void enter_Company_name(String company_optional) {
		company.sendKeys(company_optional);
	}

	public void selectAddress(String s_address) {
		Actions a = new Actions(driver);
		address.sendKeys(s_address);
		waitTillVisible(list_suggested_cities.get(0), 5);
		for (WebElement city : list_suggested_cities) {
			System.out.println(city.getText());
			if (city.getText().toLowerCase().contains(s_address.toLowerCase())) {

				System.out.println("Inside loop =>"+city.getText());
				waitTillClickable(city,5);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a.moveToElement(city).click().build().perform();

				break;

			}

		}

	}

	public void add_Appartment(String pwd) {

	}

	public void enter_PostalCode(String pwd) {

	}

	public void enter_City(String pwd) {

	}

	public void selectProvince(String pwd) {

	}

	public void enter_PhoneNumber(String number) {
		phone_num.sendKeys(number);
	}

	public void check_SaveInfo_checkbox() {
		save_info_checkbox.click();
		
	}

	public void select_Payment_Mode_cod() {
		cod_radio_button.click();
	}

	public void click_On_Complete_Order() {
		ScrollIntoView(complete_order_btn);
		waitTillClickable(complete_order_btn,5);
		complete_order_btn.click();
	}

	public String success_Message() {
		
		return success_message.getText();
	}

	public void get_List_Of_product_price() {
		ArrayList<Integer> priceList = new ArrayList<>();
		
		
		for(WebElement element :product_price) {
			System.out.println(element.getText().split(".")[0]);
//			String p_price= element.getText().split(null)
			
//			int price = Integer.parseInt(produ)
//			priceList.add(price);
			
		}
		
	}
	
	
	
	public void handle_preview_bar() {

		driver.switchTo().frame("preview-bar-iframe");
		System.out.println(driver.findElement(By.cssSelector("[class='admin-bar'] p")).getText());
		driver.findElement(By.xpath("//button[text()='Hide bar']")).click();
		driver.switchTo().parentFrame();
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
