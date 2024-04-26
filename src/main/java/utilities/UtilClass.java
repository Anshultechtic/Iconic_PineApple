package utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import baseTest.BaseTest;

public class UtilClass extends BaseTest {

	private JavascriptExecutor js;
	private WebDriverWait wait;
	private Actions a;
	StringSelection s;
	StringSelection s1;
	public WebDriver driver;

	public UtilClass(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
		a = new Actions(driver);
		PageFactory.initElements(driver, this);

	}

	public void uploadXrayPhotoLocation(String location) {

		s = new StringSelection(location);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(null, null);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void uploadLabResultPhotoLocation(String location) {

		s1 = new StringSelection(location);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s1, null);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void ScrollDown(int pixel) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixel + ")");

	}

	public void ScrollIntoView(WebElement element) {

		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void ScrollUp(int pixels) {

		js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(" + pixels + ",0)");

	}

	public void getElementByClassNameJs(String className) {

		System.out.println(js.executeScript("document.getElementsByClassName('" + className + "')"));

	}

	public void waitTillVisible(WebElement element, int seconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitTillInvisible(WebElement element, int seconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void waitTillCirclesInvisible() {
		WebElement element = driver
				.findElement(By.xpath("//div[@class = 'ng-tns-c14-0 la-3x la-ball-scale-multiple ng-star-inserted']"));
		boolean circles = element.isDisplayed();
		System.out.println("Before wait" + circles);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void waitTillClickable(WebElement element, int seconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void moveToElement(WebElement element) {

		a.moveToElement(element).click().build().perform();

	}

	public void hoverOverElement(WebElement element) {

		a.moveToElement(element).build().perform();

	}

	public void waitUntilPresence(By by, int seconds) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		try {
			// Locate the element (example)
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

			// Interact with the element
//            element.click();
		} catch (StaleElementReferenceException e) {
			// Re-locate the element and retry the interaction
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
//            element.click();
		}
	}

	public void NavigateBack() {

		driver.navigate().back();

	}

	@FindBy(css = ".header__icons.header__icons--localization.header-localization a[href='/cart']")
	WebElement cartIcon;

	public void clickOnCartHeader() {

		cartIcon.click();
	}

	@FindBy(id = "CartDrawer-Checkout")
	WebElement checkOut_btn;

	public void clickOnCheckOut_btn() {

//		waitUntilPresence(By.cssSelector("[id='CartDrawer']"),5);
		waitTillClickable(checkOut_btn, 5);
//		waitUntilPresence(By.cssSelector("[id='CartDrawer']"),5);

		checkOut_btn.click();
	}

	@FindBy(xpath = "//*[contains(@id,'MainContent')]")
	WebElement mainBody_btn;

	public void clickOnMainBody_btn() {

		mainBody_btn.click();
	}

	@DataProvider(name = "dp")
	public static Object[][] readDataBook14() {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelSheets\\Book2.xlsx";
		Object[][] data = null;
		Map<String, String> map = new HashMap<>();
		try {
			FileInputStream fis = new FileInputStream(path);

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sh = null;
			DataFormatter formatter = new DataFormatter();

			sh = wb.getSheet("login");
			Row rw = sh.getRow(0);
			data = new Object[sh.getPhysicalNumberOfRows() - 1][rw.getLastCellNum()];

			for (int i = 0; i < sh.getPhysicalNumberOfRows(); i++) {

				rw = sh.getRow(i + 1);
				for (int j = 0; j < rw.getLastCellNum(); j++) {

					String cell = formatter.formatCellValue(rw.getCell(j));

					data[i][j] = cell;

				}
//				
			}

		}

		catch (Exception e) {

		}
		return data;

	}

	public  void WriteData(String sheetname, String message, int startRownum, String colNmae) throws IOException {

		String path = System.getProperty("user.dir")+ "\\src\\test\\resources\\ExcelSheets\\Book22.xlsx";

		FileInputStream fis = new FileInputStream(path);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		int numOfSheets = wb.getNumberOfSheets();
		int cellNum = 0;
		System.out.println("Number of Sheet in documents is/are :" + numOfSheets);

		for (int i = 0; i < numOfSheets; i++) {

			String sheetName = wb.getSheetName(i);
			Iterator<Row> rowIt = wb.getSheetAt(i).iterator();

			int k = 0;
			int storeCell = 0;
			while (rowIt.hasNext()) {
				rowIt.next();
				k++;
			}

			System.out.println("Cell Number to write is " + cellNum);
			if (sheetName.equalsIgnoreCase(sheetname)) {
				
				Row r =wb.getSheetAt(i).createRow(startRownum);
				

				Iterator<Cell> cellIt = wb.getSheetAt(i).getRow(0).cellIterator();

				while (cellIt.hasNext()) {

					if (cellIt.next().getStringCellValue().equalsIgnoreCase(colNmae)) {
						System.out.println("Found");
						cellNum = storeCell;
						break;
					}
					storeCell++;

				}
				for (int j = startRownum; j < startRownum+1; j++) {

					r.createCell(cellNum).setCellValue(message);
					FileOutputStream fos = new FileOutputStream(path);
					wb.write(fos);
					System.out.println(j +" Done!!");

				}

			}

		}
	}

}
