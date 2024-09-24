package com.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testBase.Base;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter esr;  //UI of the report
	public ExtentReports er;         //populate common info on the report
	public ExtentTest et;            //creating test case entires in the report and update status of the test methods

	String repName;
	
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		esr = new ExtentSparkReporter("./reports/" + repName);

		esr.config().setDocumentTitle("Opencart Automation Report");
		esr.config().setDocumentTitle("Opencart Functional Testing");
		esr.config().setTheme(Theme.STANDARD);

		er = new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Application", "opencart");
		er.setSystemInfo("Module", "Admin");
		er.setSystemInfo("Sub Module", "Customers");
		er.setSystemInfo("UserName", System.getProperty("user.name"));
		er.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		er.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		er.setSystemInfo("Browser", browser);
		
		List<String> includedGroups  =context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		er.setSystemInfo("Groups", includedGroups.toString());
		}		
	}

	public void onTestSuccess(ITestResult result) {
		et = er.createTest(result.getTestClass().getName());
		et.assignCategory(result.getMethod().getGroups());
		et.log(Status.PASS, result.getName() + "got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		et = er.createTest(result.getTestClass().getName());
		et.assignCategory(result.getMethod().getGroups());
		
		et.log(Status.FAIL, result.getName() + "got failed");
		et.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String imgpath = new Base().captureScreen(result.getName());
		et.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		et = er.createTest(result.getTestClass().getName());
		et.assignCategory(result.getMethod().getGroups());
		
		et.log(Status.SKIP, result.getName() + "got skipped");
		et.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		er.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"/reports/"+repName;
		File extentReport  = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e2){
			e2.printStackTrace();
		}
	}

}
