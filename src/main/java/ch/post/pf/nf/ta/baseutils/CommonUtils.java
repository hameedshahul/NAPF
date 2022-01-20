package ch.post.pf.nf.ta.baseutils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import com.aventstack.extentreports.Status;

public class CommonUtils extends Setup {

	static Logger log = Log4jUtil.loadLogger(CommonUtils.class);

	public static WebDriverWait driverWait() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		return explicitWait;
	}

	public boolean isElementPresent(WebElement ele) {
		try {
			driverWait().until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public WebElement jsElementFinder(String query) {
		WebElement element = null;
		try {
			log.info("finding the element via JavaScript query :" + query);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			element = (WebElement) js.executeScript(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
		}
		return element;
	}

	public String switchToWindow(int windowid) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			log.info("current window id : " + driver.getWindowHandle());
			List<String> li = new ArrayList<String>();
			li.addAll(windowHandles);
			driver.switchTo().window(li.get(windowid));
			log.info("switched window id : " + driver.getWindowHandle());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
		}

		return driver.getWindowHandle();

	}

	public boolean click(WebElement locator) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				locator.click();
				log.info("Clicked on element: " + locator);
				blnVal = true;
			}

		} catch (Exception e) {
			log.error("Unable to click on element: " + locator);
			logger.log(Status.ERROR, "Unable to click on element: " + locator);
			e.printStackTrace();
			assertTrue(blnVal);
			throw (e);
		}
		return blnVal;
	}

	public boolean type(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				locator.clear();
				Actions acc = new Actions(driver);
				acc.sendKeys(locator, strData).build().perform();
				log.info("Text entered in the textbox is: " + strData);
				logger.log(Status.INFO, message + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			logger.log(Status.ERROR, "Unable to Enter the value in the Textbox :" + locator);
			assertTrue(false);
			logger.log(Status.ERROR, localRuntimeException);
		}
		return blnVal;

	}

	public boolean JSClick(WebElement locator) throws Exception {
		boolean flag = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", locator);
				log.info("Clicked on the element using Javascript: " + locator);
				flag = true;
			}
		} catch (Exception e) {
			log.error("Error in clicking on the element using Javascript: " + locator);
			getScreenhot(driver, "locator not found");
			logger.log(Status.ERROR, "Error in clicking on the element using Javascript: " + locator);
			e.printStackTrace();
			logger.log(Status.ERROR, e);

		}
		return flag;

	}

	public void scrollintoView() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
	}

	public void scrollBy(String tagname) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('" + tagname + "').scrollBy(0,10)");
	}

	public void scrollintoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollintoview(true);", element);
	}

	public void scrolloutView() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-600)");
	}

	public WebElement expandRootElement(WebElement element) {
		WebElement ele = null;
		try {
			ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
			log.info("Ready to navigate to ShadowRoot element:" + element);
		} catch (Exception e) {
			log.error("Unable to navigate the shadow root element : " + element);
			logger.log(Status.ERROR, "Unable to navigate the shadow root element : " + element);
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return ele;
	}

	public WebElement expandRootElement(String element) {
		WebElement ele = null;
		try {
			ele = (WebElement) ((JavascriptExecutor) driver).executeScript(element);
			log.info("Ready to navigate to ShadowRoot element:" + element);
		} catch (Exception e) {
			log.error("Unable to navigate the shadow root element : " + element);
			logger.log(Status.ERROR, "Unable to navigate the shadow root element : " + element);
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return ele;
	}

	public List<WebElement> expandRootsElement(String element) {
		List<WebElement> ele = null;
		try {
			ele = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(element);
			log.info("Ready to navigate to ShadowRoot element:" + element);
		} catch (Exception e) {
			log.error("Unable to navigate the shadow root element : " + element);
			logger.log(Status.ERROR, "Unable to navigate the shadow root element : " + element);
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return ele;
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under target
		String destination = System.getProperty("user.dir") + "/target/FailedTestsScreenshots/" + screenshotName
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public void tab() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}

	public void ctrlTab() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void downenter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void loadURL() throws Exception {
		driver.get(ReadData.getProperty("url"));
	}

	public void waitTime(int time) throws Exception {
		Thread.sleep(time);
	}

	public void selectAllClean() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
	}

	public boolean isClickable(WebElement element) {
		boolean flag = false;
		try {
			WebElement until = driverWait().until(ExpectedConditions.elementToBeClickable(element));
			flag = true;
			log.info("Element is clickable");
			logger.log(Status.INFO, "Element is clickable");
		} catch (Exception e) {
			log.info("Element is not clickable");
			logger.log(Status.INFO, "Element is not clickable");
		}

		return flag;
	}

	public void scrolltoPageHeight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public boolean isdisabledjs(String locator) {
		log.info("checking the element is disabled");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = (Boolean) js.executeScript(locator + ".disabled");
		return flag;
	}
	
	public String splitString(String data) {
		log.info("reading only the number data");
		return data.replace(".0", "");
	}
}
