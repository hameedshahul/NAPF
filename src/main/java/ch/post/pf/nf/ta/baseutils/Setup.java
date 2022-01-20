package ch.post.pf.nf.ta.baseutils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Setup {
	
	protected static Logger log = Log4jUtil.loadLogger(Setup.class);
	protected static ExtentReports extentreport;
	protected static ExtentHtmlReporter extenthtmlreport;
	protected static ExtentTest logger;
	protected static ExtentTest parent;
	protected static ExtentTest suite;
	protected static WebDriver driver;
	protected static String methodName;
	
	/*
	 * Creating a Extend report in html format..
	 * launching the browser 
	 */
	@BeforeSuite
	public void startReport() {
		extenthtmlreport = new ExtentHtmlReporter("NAPFExtentReport.html");
		extentreport = new ExtentReports();
		extentreport.attachReporter(extenthtmlreport);
		suite=extentreport.createTest("NAPF");
	}
	
	@BeforeClass
	public void classNodesReport(ITestContext context) {
		parent=	suite.createNode(context.getClass().getName());
	}
	
	public void browserLaunch(String browser) throws Exception {
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			log.info("Launched the Chrome Browser: "+driver.hashCode());
			break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/Drivers/geckodriver.exe");
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.tabs.remote.autostart", false);
			firefoxProfile.setPreference("browser.tabs.remote.autostart.1", false);
			firefoxProfile.setPreference("browser.tabs.remote.autostart.2", false);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(firefoxProfile);
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
			log.info("Launched the Firefox Browser: "+driver.hashCode());
			break;
		case "Edge":
			System.setProperty("webdriver.edge.driver", "./src/main/resources/Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			log.info("Launched the Chrome Browser: "+driver.hashCode());
			break;
		default:
			log.info("The given Browser is not found.");
			break;
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void startOfTest(ITestContext context, Method method) throws Exception {
		log.info("***************** START TEST *****************");
		browserLaunch(System.getProperty("browserName"));
		log.info(context.getClass().getName());
		methodName = method.getName();
		log.info(methodName);
		driver.get(ReadData.getProperty("url"));
	}
	
	@AfterMethod
	public void endOfTest(ITestResult arg1) throws Exception {
	    if(arg1.getStatus() == ITestResult.FAILURE){
			logger.log(Status.FAIL, "Test Case Failed is "+arg1.getName());
			logger.log(Status.FAIL, "Test Case Failed is "+arg1.getThrowable());
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 			
             String screenshotPath = new CommonUtils().getScreenhot(driver, arg1.getName());
			//To add it in the extent report 
			logger.addScreenCaptureFromPath(screenshotPath);
		}else if(arg1.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, "Test Case Skipped is "+arg1.getName());
		}
		else {
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 			
             String screenshotPath = new CommonUtils().getScreenhot(driver, arg1.getName());
			//To add it in the extent report 
             logger.log(Status.PASS,"Test case passed:"+arg1.getName());
             logger.addScreenCaptureFromPath(screenshotPath);
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		//driver.quit();
	}
	
	/*
	 * method to return the Current running Test case name
	 */
	private String returnMethodName(ITestNGMethod method) {
	    return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}
	
	
	
	@AfterSuite
	public void endReport() {
		// writing everything to document
		//flush() - to write or update test information to your report. 
		
		extentreport.flush();
        log.info("The given test is completed in the given browser:"+ System.getProperty("browser"));
        //driver.quit();
	}
	
	
	
	public void ScreenMaxzoomout() throws Exception {
		Robot r = new Robot();
		for (int i = 0; i < 4; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_CONTROL);
		}
	}
}
