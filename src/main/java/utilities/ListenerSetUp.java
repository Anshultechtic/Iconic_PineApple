package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

import baseTest.BaseTest;


public class ListenerSetUp extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportCode.ExtentReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("test Started");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id(ErrorValidationTest)->test

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test Passed");
		logger.info("test Finished");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable());

		logger.info("test skipped ...");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());//
	

			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}

			String ssPath = getScreenshot(result.getMethod().getMethodName(), driver);
			System.out.println(ssPath);
			extentTest.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
			logger.info("test Failed ..Took ScreenShot");
		

		logger.info("test Failed and Finished");
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		logger.info("Result locked in the report");
	}

}
