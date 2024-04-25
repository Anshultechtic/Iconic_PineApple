package utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportCode {

    public static ExtentReports ExtentReport() {

	String extentPath = System.getProperty("user.dir")+"\\test-OutputFiles\\MGM_ExtentReport.html";
		
		File path =new File(extentPath);
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("MGM Test Report");
		
		reporter.config().setReportName("MGM Test_Cases Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anshul Badgaiyan");
		
		return extent;
    }
}

