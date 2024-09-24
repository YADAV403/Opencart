package com.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base {
	
public static WebDriver wd;
public Logger logger;
public Properties p;

	
	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters("browser")
	public void setup(String br) throws Exception {
		
		logger = LogManager.getLogger(this.getClass());
		
		FileInputStream fis = new FileInputStream("./src//test//resources//config.properties");
		p = new Properties();
		p.load(fis);
		
		switch(br) {
		case "chrome" : wd=new ChromeDriver();break;
		case "edge" : wd=new EdgeDriver();break;
		case "firefox" : wd=new FirefoxDriver();break;
		default : 
			System.out.println("choose correct browser");
			return;
		}
		
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		wd.get("https://demo.opencart.com");	
		wd.get(p.getProperty("appurl2"));
	}
	
	@AfterClass(groups = {"sanity","regression","master"})
	public void teardown() {
		wd.quit();
	}
	
	public String randString() {
		String geneString = RandomStringUtils.randomAlphabetic(5);
		return geneString;
	}
	
	public String randNum1() {
		String geneNum1 = RandomStringUtils.randomNumeric(3);
		return geneNum1;
	}
	
	public String randNum2() {
		String geneNum2 = RandomStringUtils.randomNumeric(10);
		return geneNum2;
	}
	
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) wd;
		File sf = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/sceenshots/"+tname+"_"+timeStamp;
		File tf = new File(targetFilePath);
		sf.renameTo(tf);
		
		return targetFilePath;
	}

}
