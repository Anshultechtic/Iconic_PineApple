package baseTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pageLayer.CheckoutPage;
import pageLayer.Collections_Page;
import pageLayer.EnterPassword;
import pageLayer.HomePage;
import pageLayer.IconicPage;
import pageLayer.LoginPage;
import pageLayer.PDP_Page;

import pageLayer.SignUpPage;

import utilities.UtilClass;

public class BaseTest2 {

	private Properties prop;
	public WebDriver driver;
	public HomePage hp_obj;
	public UtilClass util_obj;
	public LoginPage lp_obj;
	public SignUpPage sign_up_pge_obj;
	public EnterPassword enter_web_obj;
	public Collections_Page co_page_obj;
	public PDP_Page pdp_obj;
	public IconicPage iconic_obj;
	public CheckoutPage checkout_obj;

	public WebDriver launchBrowser() throws InterruptedException {

		String configPath = System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\config.properties";
		prop = new Properties();

		FileInputStream fis;
		try {
			fis = new FileInputStream(configPath);
			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--headless");
//			driver = new ChromeDriver(options);
			driver = new ChromeDriver();

		}
		if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}
		if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		util_obj = new UtilClass(driver);
		iconic_obj = new IconicPage(driver);
		hp_obj = new HomePage(driver);
		lp_obj = new LoginPage(driver);
		enter_web_obj = new EnterPassword(driver);
		sign_up_pge_obj = new SignUpPage(driver);
		co_page_obj = new Collections_Page(driver);
		pdp_obj = new PDP_Page(driver);
		checkout_obj = new CheckoutPage(driver);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

//		System.out.println(prop.getProperty("url2"));
//		driver.get(prop.getProperty("url2"));

//		driver.get("https://www.google.co.in/");

		return driver;

	}

	public void getScreenshot(WebDriver driver) {

		String ssPath = System.getProperty("user.dir") + "\\OutputFiles\\ScreenShots\\";

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(ssPath + "Fail_" + System.currentTimeMillis() + ".png");
		try {
			FileHandler.copy(src, des);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void launchApplication() throws InterruptedException {

		driver = launchBrowser();

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {

//		driver.close();
		Robot r;
		try {
			r = new Robot();
			r.delay(500);
		} catch (AWTException e) {
			// TODO Auto-generated catch block

		}

//		driver.quit();

	}

}
