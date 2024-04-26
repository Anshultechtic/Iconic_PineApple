package pageLayer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UtilClass;

public class Collections_Page extends UtilClass {

	private WebDriver driver;

	public Collections_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pdp_obj = new PDP_Page(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//slider-component)[2]//li/div/div/*[@class='card__content']//h3")
	private List<WebElement> listOfProducts;

	@FindBy(id = "")
	private WebElement name_product;

	@FindBy(name = "commit")
	private WebElement submit_btn;

	public void getProductName(String nameOfProduct) {
		Actions a = new Actions(driver);
		for (WebElement element : listOfProducts) {
//			System.out.println("Product name => "+element.getText().trim());
			System.out.println();
			
			if (element.getText().trim().equalsIgnoreCase(nameOfProduct)) {
				
				System.out.println("Product name => " + element.getText().trim());

				waitTillClickable(element, 5);
				a.moveToElement(element).click().build().perform();

				break;
			}

		}
	}

	public void add_multiple_Product_To_Cart(String product, int number_of_product) {
		String clickontab = Keys.chord(Keys.CONTROL, Keys.ENTER);

		List<WebElement> listOfProducts = driver
				.findElements(By.xpath("//*[@id='product-grid']/li/div/div/*[@class='card__content']"));
		for (WebElement element : listOfProducts) {
//			System.out.println("Product name => "+element.getText().trim());

			if (element.findElement(By.tagName("h3")).getText().trim().equalsIgnoreCase(product)) {

				System.out.println("Product name => " + element.getText().trim());
//				ScrollIntoView(element.findElement(By.tagName("h3")));
				waitTillClickable(element, 5);

				element.findElement(By.tagName("a")).sendKeys(clickontab);
				break;
			}

		}

		Set<String> all_id = driver.getWindowHandles();
		Iterator<String> it = all_id.iterator();
		String p_id = driver.getWindowHandle();
		System.out.println("C_id" + p_id);
		while (it.hasNext()) {

			String c_id = it.next();
			System.out.println("C_id" + c_id);
			if (!p_id.equals(c_id)) {
				System.out.println("Inside loop");
				driver.switchTo().window(c_id);

				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.close();

			}
			driver.switchTo().window(p_id);
			driver.navigate().refresh();

		}

	}

	

}
