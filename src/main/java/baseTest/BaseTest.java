package baseTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageLayer.CheckoutPage;
import pageLayer.Collections_Page;
import pageLayer.EnterPassword;
import pageLayer.HomePage;
import pageLayer.LoginPage;
import pageLayer.MyAccountPage;
import pageLayer.PDP_Page;
import pageLayer.SearchAProduct;
import pageLayer.SignUpPage;

import utilities.UtilClass;

public class BaseTest {

	private Properties prop;
	public WebDriver driver;
	public HomePage hp_obj;
	public UtilClass util_obj;
	public LoginPage lp_obj;
	public SignUpPage sign_up_pge_obj;
	public EnterPassword enter_web_obj;
	public Collections_Page co_page_obj;
	public PDP_Page pdp_obj;
	public CheckoutPage checkout_obj;
	public MyAccountPage my_account_obj;
	public SearchAProduct search_pg_obj;
	public static Logger logger;

	@BeforeTest
	public void OnTeststart() {
		logger = Logger.getLogger("Shopify Test Cases Suite");
		PropertyConfigurator.configure("Log4jfile.properties");
		logger.info("Framework Execution started");
	}

	@AfterTest
	public void OnTestend() {
		logger.info("Suite execution stopped");
	}

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

			options.addArguments("--headless","--window-size=1920,1200");
			driver = new ChromeDriver(options);
//			driver = new ChromeDriver();

		}
		if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}
		if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		util_obj = new UtilClass(driver);
		hp_obj = new HomePage(driver);
		lp_obj = new LoginPage(driver);
		enter_web_obj = new EnterPassword(driver);
		sign_up_pge_obj = new SignUpPage(driver);
		co_page_obj = new Collections_Page(driver);
		pdp_obj = new PDP_Page(driver);
		checkout_obj = new CheckoutPage(driver);
		my_account_obj=new MyAccountPage(driver);
		search_pg_obj=new SearchAProduct(driver);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		System.out.println(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));

//		driver.get("https://www.google.co.in/");

		return driver;

	}

	public String getScreenshot(String testCase, WebDriver driver) {
		logger.info("Taking ScreenShot");
		String desPath = System.getProperty("user.dir") + "\\test-OutputFiles\\";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(desPath + testCase+System.currentTimeMillis()+".png");
		try {
			FileHandler.copy(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return desPath + testCase + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws InterruptedException {

		driver = launchBrowser();

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {

		driver.close();
		Robot r;
		try {
			r = new Robot();
			r.delay(500);
		} catch (AWTException e) {
			// TODO Auto-generated catch block

		}

		driver.quit();

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {map, map}

	}

}
