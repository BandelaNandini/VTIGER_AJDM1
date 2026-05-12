package ListenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.Baseclass;

public class Listeners implements ISuiteListener, ITestListener {
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Execution started-Adv report configuration", true);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		// Configure the Report
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReports/VtigerReport" + time + ".html");
		spark.config().setDocumentTitle("Reports of CRM Vtiger");
		spark.config().setReportName("VTIGER Contact_Org Reports");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser", "Chrome-147.0.7727.139");
		report.setSystemInfo("OS", "Windows - 11th Gen");

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		// Create the test
		test = report.createTest(testname + time);
		UtilityObjectClass.setTest(test);
		test.log(Status.INFO, testname + " : Test execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		test.log(Status.PASS, testname + " : Test execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.log(Status.FAIL, testname + " : Test execution Failure - Screenshot");

		TakesScreenshot ts = (TakesScreenshot) Baseclass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src, testname + "_" + time);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		test.log(Status.WARNING, testname + " : Test execution skipped");
	}

	@Override
	public void onFinish(ISuite suite) {
		test.log(Status.INFO, "Suite Execution finished-Report Backup");
		report.flush();
	}

}
