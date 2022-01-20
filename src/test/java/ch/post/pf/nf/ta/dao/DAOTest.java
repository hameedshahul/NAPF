package ch.post.pf.nf.ta.dao;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.annotations.IFactoryAnnotation;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import ch.post.pf.nf.ta.baseutils.CommonUtils;
import ch.post.pf.nf.ta.baseutils.Log4jUtil;
import ch.post.pf.nf.ta.baseutils.ReadData;
import ch.post.pf.nf.ta.baseutils.Setup;
import ch.post.pf.nf.ta.pagefactory.NAPFLocators;

public class DAOTest extends Setup {

	Logger log = Log4jUtil.loadLogger(DAOTest.class);
	CommonUtils commonUtils = new CommonUtils();
	String NFNumber = null;
	public String searchedCreatedNF = null;

	public boolean appLanguage() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		boolean click = commonUtils.click(commonUtils.jsElementFinder(ls.headerProfileMenu));
		if (click) {
			flag = true;
		}
		return flag;
	}

	public boolean langugeselect(String Language) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		List<WebElement> profilemenuList = ls.profilemenuList;
		for (int i = 0; i < profilemenuList.size(); i++) {
			String label = profilemenuList.get(i).getAttribute("actual-label");
			if (label.equalsIgnoreCase(Language)) {
				boolean click = commonUtils.click(profilemenuList.get(i));
				if (click) {
					flag = true;
				}
				break;
			}
		}
		return flag;
	}

	public boolean appMenuList() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(3000);
		List<WebElement> app_Menu_List = ls.app_Menu_List;
		for (int i = 0; i < app_Menu_List.size(); i++) {
			String text = app_Menu_List.get(i).getAttribute("label");
			WebElement element = app_Menu_List.get(i);
			if (element.isDisplayed()) {
				if (commonUtils.isClickable(element)) {
					log.info("the element :" + text + " is present in the page");
					logger.log(Status.INFO, "the element :" + text + " is present in the page");
					commonUtils.click(app_Menu_List.get(i));
					logger.log(Status.INFO, "And landed in " + text + "page");
					logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, text + "page"));
					flag = true;
				} else {
					assertTrue(flag, "the element is interepected from clicking");
				}
			}
		}
		return flag;
	}

	public boolean appMenuList(String appname) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(3000);
		List<WebElement> app_Menu_List = ls.app_Menu_List;
		for (int i = 0; i < app_Menu_List.size(); i++) {
			String text = app_Menu_List.get(i).getAttribute("label");
			WebElement element = app_Menu_List.get(i);
			if (text.equalsIgnoreCase(appname)) {
				log.info("the element :" + text + " is present in the page");
				logger.log(Status.INFO, "the element :" + text + " is present in the page");
				commonUtils.click(app_Menu_List.get(i));
				logger.log(Status.INFO, "And landed in " + text + "page");
				logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, text + "page"));
				flag = true;
			}
		}
		return flag;
	}

	public boolean open_NF_input_field() throws Exception {
		boolean flag = false;
		NAPFLocators ll = new NAPFLocators();
		WebElement open_NF_input = ll.open_NF_input;
		boolean click = commonUtils.click(open_NF_input);
		logger.log(Status.INFO, "Open Nf input field is found and editable.");
		if (click) {
			flag = true;
		}
		return flag;
	}

	public boolean CreateInvestigation() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(3000);
		commonUtils.click(commonUtils.jsElementFinder(ls.createInvestigation));
		commonUtils.switchToWindow(1);
		commonUtils.waitTime(2000);
		WebElement jsElementFinder = commonUtils.jsElementFinder(ls.create_Inves_page);
		if (jsElementFinder.isDisplayed()) {
			log.info(jsElementFinder.getText());
			logger.log(Status.INFO, "clicked in Create investgation icon");
			flag = true;
		}

		return flag;
	}

	public boolean selectPATADD(String pa, String ta) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		try {
			log.info("selecting the PA and TA");
			logger.log(Status.INFO, "selecting the PA");
			commonUtils.waitTime(4000);
			WebElement ProcessNameElement = commonUtils.expandRootElement(ls.PaInitiator);
			boolean type = commonUtils.type(ProcessNameElement, "selecting Process name", pa);
			commonUtils.downenter();
			commonUtils.waitTime(5000);
			logger.log(Status.INFO, "selecting the TA");
			WebElement TaElement = commonUtils.expandRootElement(ls.TaInitiator);
			boolean type2 = commonUtils.type(TaElement, "selecting Process name", ta);
			commonUtils.downenter();
			if (type && type2) {
				flag = true;
				logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, pa + " " + ta + "page"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return flag;
	}

	public boolean saveButton() {
		boolean flag = false;
		log.info("selecting the save icon");
		logger.log(Status.INFO, "selecting the save icon");
		NAPFLocators ls = new NAPFLocators();
		try {
			WebElement element = commonUtils.expandRootElement(ls.savecss);
			if (element.isDisplayed()) {
				logger.log(Status.INFO, "Save icon is present and Clicked");
				element.click();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}

		return flag;
	}

	public List<String> fieldValidation(String Mode) throws InterruptedException, Exception {
		boolean flag = false;
		log.info("Reading the Feild from web page");
		logger.log(Status.INFO, "Reading the Fields of " + Mode + " DC from web page");
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(4000);
		commonUtils.scrollintoView();
		List<String> browserField = new ArrayList<String>();
		try {
			// reading all field label and adding it to list
			ls.accountField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});
			ls.datepicketField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.textField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.comboboxField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.textareaField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});
			ls.checkboxField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});
			try {
				List<WebElement> findElement = driver.findElements(By.tagName("pf-custom-control"));
				for (int i = 0; i < findElement.size(); i++) {
					boolean displayed = findElement.get(i).isDisplayed();
					if (displayed) {
						WebElement expandRootElement = commonUtils.expandRootElement(ls.custom_field);
						browserField.add(expandRootElement.getText().trim());
					}
				}

			} catch (Exception e) {
				log.info("custom_field is not presentin this page");
			}
			// sort the list browserField
			Collections.sort(browserField);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return browserField;
	}

	public List<String> fieldValidationOnOpenNF(String mode) throws Exception {
		constructOpenNfAndLoad(mode);
		commonUtils.scrollintoView();
		List<String> field = fieldValidation(mode);
		return field;
	}

	public void constructOpenNfAndLoad(String mode) throws Exception {
		String split = ReadData.getTestProperty(mode);
		driver.get(ReadData.getTestProperty("opennfurl") + split);
		logger.log(Status.INFO, "opened the mode: " + mode + " nf id :" + split);
		commonUtils.waitTime(6000);

	}

	public List<String> readFiledNameFromSheet(String sheet) throws Exception {
		List<String> li = new ArrayList<String>();
		log.info("Reading the date from repository");
		logger.log(Status.INFO, "Reading the date from repository");
		Map<String, String> readDataField = ReadData.readDataField(sheet + " NF");
		// reading the values from map
		Collection<String> values = readDataField.values();
		li.addAll(values);
		Collections.sort(li);

		return li;

	}

	public Set<String> readFiledNameFromPATA(String feild, String name) throws Exception {
		log.info("Reading" + name + "data from repository ");
		logger.log(Status.INFO, "Reading" + name + "data from repository ");

		String[] split = feild.split("@@");
		Set<String> li = new TreeSet<String>();
		for (String string2 : split) {
			li.add(string2.trim());
		}
		return li;
	}

	public String readNFNumber() {

		try {
			log.info("Reading the NF number created");
			logger.log(Status.INFO, "Reading the NF number created");
			NAPFLocators ls = new NAPFLocators();
			commonUtils.waitTime(3000);
			boolean displayed = commonUtils.expandRootElement(ls.NfNumber).isDisplayed();
			if (displayed) {
				NFNumber = commonUtils.expandRootElement(ls.NfNumber).getText();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
			logger.log(Status.ERROR, e);
		}
		return NFNumber;
	}

	public boolean openNfClick() throws AWTException {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		try {
			log.info("current window id: " + driver.getWindowHandle());
			WebElement expandRootElement = commonUtils.expandRootElement(ls.openNFclick);
			expandRootElement.click();
			if (flag != true) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
			logger.log(Status.ERROR, e);
		}
		return flag;

	}

	public boolean openNf(String number) throws Exception {
		boolean flag = false;
		try {
			NAPFLocators ll = new NAPFLocators();
			WebElement open_NF_input = ll.open_NF_input;
			boolean click = commonUtils.click(open_NF_input);
			open_NF_input.sendKeys(number);
			boolean openNfClick = openNfClick();
			if (openNfClick) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
			logger.log(Status.ERROR, e);
		}

		return flag;
	}

	public Set<String> closureValuesValidation(int size, String mode) throws Exception {
		Set<String> li = new TreeSet<String>();
		try {
			logger.log(Status.INFO, "Selecting the closure values");
			NAPFLocators ls = new NAPFLocators();
			WebElement clouserReason = commonUtils.expandRootElement(ls.clouserReason);
			if (mode.equalsIgnoreCase("Swift")) {
				commonUtils.click(clouserReason);
				commonUtils.selectAllClean();
				commonUtils.click(commonUtils.expandRootElement(ls.clouserReasonToggle));
			}
			for (int i = 0; i < size; i++) {
				commonUtils.click(clouserReason);
				commonUtils.downenter();
				commonUtils.waitTime(2000);
				String attribute = commonUtils.expandRootElement(ls.clouserReason).getAttribute("value").trim();
				li.add(attribute);
			}
			if (mode.equals("Online")) {
				commonUtils.expandRootElement(ls.clouserReason).clear();
			} else {
				commonUtils.expandRootElement(ls.clouserReason).clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(false);
			logger.log(Status.ERROR, e);
		}

		return li;
	}

	public Set<String> productValuesValidation(int size, String mode) throws Exception {
		Set<String> li = new TreeSet<String>();
		try {
			logger.log(Status.INFO, "Selecting the product values");
			NAPFLocators ls = new NAPFLocators();
			WebElement productReason = commonUtils.expandRootElement(ls.productReason);
			commonUtils.scrollintoView();
			if (mode.equalsIgnoreCase("Swift")) {
				commonUtils.click(productReason);
				commonUtils.selectAllClean();
				commonUtils.click(commonUtils.expandRootElement(ls.productReasonToggle));
			}
			for (int i = 0; i < size; i++) {
				commonUtils.click(productReason);
				commonUtils.downenter();
				commonUtils.waitTime(2000);
				String attribute = commonUtils.expandRootElement(ls.productReason).getAttribute("value").trim();
				li.add(attribute);
			}
			if (mode.equals("Online")) {
				commonUtils.expandRootElement(ls.productReason).clear();
			} else {
				commonUtils.expandRootElement(ls.productReason).clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.ERROR, "product Values Validation");
			e.printStackTrace();
			assertTrue(false);
			logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, "productValuesValidation"));
			logger.log(Status.ERROR, e);
		}

		return li;
	}

	public boolean listvalidation(Set<String> readclosureNameFromPATA, Set<String> closureValuesValidation) {
		// TODO Auto-generated method stub
		logger.log(Status.INFO, "Validating the data between repository and web page");
		List<String> li = new ArrayList<String>();
		for (String string : readclosureNameFromPATA) {
			li.add(string);
		}
		List<String> li2 = new ArrayList<String>();
		for (String string : closureValuesValidation) {
			li2.add(string);
		}
		boolean flag = false;
		for (int i = 0; i < li.size(); i++) {
			if (li2.contains(li)) {
				flag = true;
			} else {
				flag = false;
				assertTrue(flag);
				logger.log(Status.ERROR, "both repository and webpage data are not same");
			}
		}
		return flag;
	}

	public boolean editorMenuSelect(String tabname) throws Exception {
		boolean flag = false;
		logger.log(Status.INFO, "Moving to " + tabname + " tab");
		Screenzoomout();
		commonUtils.waitTime(2000);
		NAPFLocators ns = new NAPFLocators();
		List<WebElement> menunames = commonUtils.expandRootsElement(ns.editormenu);
		for (int i = 0; i < menunames.size(); i++) {
			String attribute = menunames.get(i).getAttribute("actual-label");
			if (attribute.equalsIgnoreCase(tabname)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", menunames.get(i));
				logger.log(Status.INFO, "Clicking on the Tab:" + attribute);
				log.info("Clicking on the Tab:" + attribute);
				flag = true;
			}
		}
		Screenzoomin();
		return flag;
	}

	public Map<String, String> ActivityTableData() {
		logger.log(Status.INFO, "Reading the Data from Activities tab");
		Map<String, String> ma = new TreeMap<String, String>();
		try {

			NAPFLocators ns = new NAPFLocators();
			WebElement expandRootElement = commonUtils.expandRootElement(ns.activityFieldValue);
			commonUtils.waitTime(2000);
			WebElement firstfield = expandRootElement.findElement(By.cssSelector(ns.firstfield));
			WebElement secondfield = expandRootElement.findElement(By.cssSelector(ns.secondfield));
			WebElement thirdfield = expandRootElement.findElement(By.cssSelector(ns.thirdfield));
			WebElement fourthfield = expandRootElement.findElement(By.cssSelector(ns.fourthfield));
			WebElement fifthfield = expandRootElement.findElement(By.cssSelector(ns.fifthfield));
			ma.put("Erstelldatum", firstfield.getText());
			ma.put("Typ", secondfield.getText());
			ma.put("durch", thirdfield.getText());
			ma.put("Briefkasten", fourthfield.getText());
			ma.put("Status", fifthfield.getText());
			logger.log(Status.INFO, "table data found in activity tab");
			logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, "ActivityTableData"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.log(Status.ERROR, e);

		}
		return ma;
	}

	public void defaultWindow() {
		commonUtils.switchToWindow(0);
	}

	public boolean notesAdd() {

		boolean flag = false;
		try {
			NAPFLocators ns = new NAPFLocators();
			commonUtils.waitTime(4000);
			logger.log(Status.INFO, "Clicking on Add notes");
			commonUtils.click(commonUtils.expandRootElement(ns.notesadd));
			WebElement textareades = commonUtils.expandRootElement(ns.textarea);
			commonUtils.type(textareades, "note area description", "note description");
			commonUtils.waitTime(2000);
			commonUtils.click(ns.saveButton);
			logger.log(Status.INFO, "Saving the notes created");
			commonUtils.waitTime(2000);
			WebElement expandRootElement3 = commonUtils.expandRootElement(ns.activityFieldValue);
			List<WebElement> vaadingridcellcontent = expandRootElement3.findElements(By.tagName(ns.editdeletebutton));
			boolean elementPresent = commonUtils
					.isElementPresent(vaadingridcellcontent.get(vaadingridcellcontent.size() - 1));
			if (elementPresent) {
				flag = true;
				logger.log(Status.INFO, "notes table edit and delete icon are present");
				logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, null));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}

		return flag;

	}

	public boolean notesEditDeleteFunction() {
		boolean flag = false;
		try {
			NAPFLocators ns = new NAPFLocators();
			logger.log(Status.INFO, "Notes table edit and delete icon Vaidation");
			WebElement editbuttonshadow = commonUtils.expandRootElement(ns.activityFieldValue);
			WebElement notesEdit = editbuttonshadow.findElement(By.cssSelector(ns.notesEdit));
			notesEdit.click();
			WebElement textareades = commonUtils.expandRootElement(ns.textarea);
			commonUtils.type(textareades, "note area description", "notedescription");
			commonUtils.click(ns.saveButton);
			log.info("notes tabled edit action has been performed");
			logger.log(Status.INFO, "Notes table edit icon is present");
			logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, null));
			if (true) {
				// WebElement delete = commonUtils.expandRootElement(ns.notesDelete);
				commonUtils.waitTime(2000);
				WebElement delete = editbuttonshadow.findElement(By.cssSelector(ns.notesDelete));
				if (delete.isDisplayed()) {
					delete.click();
					log.info("Notes tabled delete action has been performed");
					logger.log(Status.INFO, "Notes table delete icon is present and performed");
					logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, null));
					commonUtils.waitTime(4000);
				}
				if (true) {
					WebElement expandRootElement3 = commonUtils.expandRootElement(ns.activityFieldValue);
					List<WebElement> vaadingridcellcontent = expandRootElement3
							.findElements(By.tagName(ns.editdeletebutton));
					boolean elementPresent = commonUtils
							.isElementPresent(vaadingridcellcontent.get(vaadingridcellcontent.size() - 1));
					if (elementPresent != true) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			assertTrue(flag);
			logger.log(Status.ERROR, e);
		}

		return false;

	}

	public boolean AppMenuSelection(String menuName) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(3000);
		List<WebElement> app_Menu_List = ls.app_Menu_List;
		for (int i = 0; i < app_Menu_List.size(); i++) {
			String text = app_Menu_List.get(i).getAttribute("label");
			if (text.equals(menuName)) {
				log.info("the element :" + text + " is present in the page");
				logger.log(Status.INFO, "the element :" + text + " is present in the page");
				commonUtils.click(app_Menu_List.get(i));
				logger.log(Status.INFO, "And landed in " + text + "page");
				logger.addScreenCaptureFromPath(commonUtils.getScreenhot(driver, text + "page"));
				String text2 = commonUtils.expandRootElement(ls.landingPage_text).getText();
				if (text2.equalsIgnoreCase(menuName)) {
					flag = true;
					assertTrue(flag);
				}
			}
		}
		return flag;
	}

	public boolean column_field_sorting_validation() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		List<WebElement> userInboxNfField = commonUtils.expandRootsElement(ls.userInboxNfField);
		for (int i = 0; i < 1; i++) {
			boolean checkBox = userInboxNfField.get(i).isEnabled();
			boolean NF_Id_Sorting = userInboxNfField.get(i + 2).isEnabled();
			boolean status_def_Nf = userInboxNfField.get(i + 3).isEnabled();
			boolean transaktion = userInboxNfField.get(i + 4).isEnabled();
			boolean Letzte = userInboxNfField.get(i + 5).isEnabled();
			boolean Briefkasten = userInboxNfField.get(i + 6).isEnabled();
			boolean Anlieferung = userInboxNfField.get(i + 7).isEnabled();
			if (checkBox && NF_Id_Sorting && status_def_Nf && transaktion && Letzte && Briefkasten && Anlieferung) {
				log.info("All field are present in Benutzerbriefkasten page");
				logger.log(Status.INFO, "All field are present in Benutzerbriefkasten page");
				String text = commonUtils.expandRootElement(ls.FirstNf_number).getText();
				log.info(text);
				WebElement NFField = userInboxNfField.get(i + 2);
				NFField.click();
				commonUtils.waitTime(5000);
				log.info("sorting the Nf Id field");
				logger.log(Status.INFO, "sorting the Nf Id field");
				String text2 = commonUtils.expandRootElement(ls.FirstNf_number).getText();
				log.info(text2);
				if (!text.equals(text2)) {
					flag = true;
					assertTrue(true, "Nf Id sorting is working as expected");
					logger.log(Status.INFO, "Nf Id sorting is working as expected");
				} else {
					assertTrue(false, "Nf sorting is not working as expected");
					logger.log(Status.ERROR, "Nf sorting is not working as expected");
				}
			} else {
				assertTrue(flag);
				logger.log(Status.ERROR, "Field is not displayed in User inbox");
			}

		}

		return flag;
	}

	public boolean userInboxGroupElementCheck() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		log.info("Validating the group elements in Benutzerbriefkasten page");
		logger.log(Status.INFO, "Validating the group elements in Benutzerbriefkasten page");
		boolean sourceGroupQueue = commonUtils.isClickable(ls.sourceGroupQueue);
		boolean previousGroupQueue = commonUtils.isClickable(ls.previousGroupQueue);
		boolean otherGroupQueue = commonUtils.isClickable(ls.otherGroupQueue);
		if (sourceGroupQueue && previousGroupQueue && otherGroupQueue) {
			log.info(
					"All the group elements is enabled in Benutzerbriefkasten page since the NF id check box is clicked");
			logger.log(Status.INFO,
					"All the group elements is enabled in Benutzerbriefkasten page since the NF id check box is clicked");
		} else {
			log.info("All the group elements is disabled in Benutzerbriefkasten page");
			logger.log(Status.INFO, "All the group elements is disabled in Benutzerbriefkasten page");
			flag = true;

		}
		return flag;
	}

	public WebElement NfsearchInUserInbox(String NFID, String data) throws Exception {
		String replace = null;
		if (NFID.contains("|")) {
			replace = NFID.replace("|", "");
			System.out.println(replace);
		}
		NAPFLocators ls = new NAPFLocators();

		driver.navigate().refresh();
		commonUtils.waitTime(5000);
		log.info("Searching the Nf number in Benutzerbriefkasten and moving to appropriate group");
		logger.log(Status.INFO, "Searching the Nf number in Benutzerbriefkasten and moving to appropriate group");
		List<WebElement> nfsearch = commonUtils.expandRootsElement(ls.all_NfNumber_search);
		WebElement Nf_id_text_data = null;
		for (int i = 0; i < nfsearch.size(); i++) {
			WebElement Nf_id_text = nfsearch.get(i);
			if (Nf_id_text.getAttribute("test-selector").equals(replace)) {
				Nf_id_text_data = nfsearch.get(i);
				searchedCreatedNF = Nf_id_text.getText();
				if (data.equalsIgnoreCase("search")) {
					log.info("Found the NFid and click on the checkbox to check the group element enabling");
					logger.log(Status.INFO,
							"Found the NFid and click on the checkbox to check the group element enabling");
					WebElement expandRootElement = commonUtils
							.expandRootElement(ls.all_NfNumber_search_2 + (i + 2) + ls.all_NfNumber_search_checkbox);
					commonUtils.JSClick(expandRootElement);
					log.info("clicked in respective checkbox");
				}
				break;
			}
			commonUtils.scrollBy("pf-table");
		}

		return Nf_id_text_data;
	}

	public boolean open_Searched_NF(String Nf_id_element, String data) throws Exception {
		boolean flag = false;
		commonUtils.waitTime(2000);
		log.info("scrolling to NF Id searched");
		logger.log(Status.INFO, "Scrolling to NF Id searched");
		WebElement nfsearchInUserInbox = NfsearchInUserInbox(Nf_id_element, data);
		if (nfsearchInUserInbox == null) {
			assertTrue(false, "Unable to find the element from User Inbox");
		}
		String text = nfsearchInUserInbox.getAttribute("test-selector");
		log.info("clicking in searched NF");
		logger.log(Status.INFO, "clicking in searched NF");
		commonUtils.waitTime(2000);
		commonUtils.JSClick(nfsearchInUserInbox);
		commonUtils.switchToWindow(1);
		String readNFNumber = readNFNumber();
		if (readNFNumber.contains(text)) {
			log.info("opened the correct NF id");
			logger.log(Status.INFO, "Opened the correct NF id");
			flag = true;
			assertTrue(flag);
		} else {
			assertTrue(flag);
			logger.log(Status.ERROR, "Opened the in-correct NF id");
		}
		return flag;
	}

	public boolean CreateMessTab(String mess) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		boolean Kundenmitteilung = editorMenuSelect("Kundenmitteilung");
		commonUtils.waitTime(2000);
		boolean enabled = ls.customer_description.isDisplayed();
		if (enabled) {
			log.info("customer description is disabled");
			logger.log(Status.INFO, "Customer description is disabled");
			assertTrue(true);
			WebElement createmess = ls.CreateMess;
			if (createmess.isEnabled()) {
				log.info("clicking on create mess");
				logger.log(Status.INFO, "Clicking on create mess");
				boolean jsClick = commonUtils.JSClick(createmess);
				boolean customer_description = ls.customer_description.isEnabled();
				if (customer_description) {
					log.info("Customer description is enabled");
					logger.log(Status.INFO, "Customer description is enabled");
					commonUtils.waitTime(2000);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].setAttribute('value','" + mess + "')", ls.customer_description);
					// commonUtils.type(ls.customer_description, "Enter mess", mess);
					log.info("Saving the mess created");
					logger.log(Status.INFO, "Saving the mess created");
					commonUtils.waitTime(2000);
					WebElement element = (WebElement) js.executeScript(
							"return document.querySelector(\"pf-section.col-md-4.hydrated.inside-grid > pf-menu-item\").shadowRoot.querySelector(\"div.outer.leaf > a\")");
					js.executeScript("arguments[0].click();", element);
					assertTrue(jsClick, "The mess created is not added to the table row");
					logger.log(Status.INFO, "The mess created is added to the table row");
					flag = true;
				}
			} else {
				assertTrue(false);
				log.info("create mess is disabled");
				logger.log(Status.ERROR, "create mess is disabled");

			}
		} else {
			assertTrue(false, "customer description is enabled");
			log.info("customer description is enabled");
			logger.log(Status.ERROR, "customer description is enabled it should be disbaled");

		}
		return flag;
	}

	public void seelctGroupOption(String group) throws Exception {
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		log.info("Selecting the Group " + group);
		logger.log(Status.INFO, "Selecting the Group " + group);
		List<WebElement> userInboxHeader = ls.userInboxHeader;
		for (WebElement groupHeader : userInboxHeader) {
			String text = groupHeader.getText();
			if (group.equalsIgnoreCase(text)) {
				commonUtils.click(groupHeader);
				if (group.equalsIgnoreCase("zu anderem GBK")) {
					WebElement saveGroup = ls.saveGroup;
					saveGroup.click();
				}
				logger.log(Status.INFO, "clicked in " + text + " Gbk");
				commonUtils.waitTime(5000);
			}
		}

	}

	public boolean groupQueueSummary(String Groupsummary) throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		log.info("Selecting the Group queue summary app menu");
		logger.log(Status.INFO, "Selecting the Group queue summary app menu");
		List<WebElement> groupQueueList = commonUtils.expandRootsElement(ls.groupQueueList);
		for (int i = 12; i < groupQueueList.size(); i++) {
			String text = groupQueueList.get(i).getText();
			if (text.equals(Groupsummary)) {
				log.info("Selecting the Group summary :" + Groupsummary);
				logger.log(Status.INFO, "Selecting the Group summary :" + Groupsummary);
				// commonUtils.scrollintoView(groupQueueList.get(i));
				groupQueueList.get(i).click();
				flag = true;
				assertTrue(flag);
				log.info("clicked in Group summary :" + Groupsummary);
				logger.log(Status.INFO, "Clicked in Group summary :" + Groupsummary);

			}
		}
		commonUtils.waitTime(7000);
		return flag;
	}

	public void Screenzoomout() throws Exception {
		Robot r = new Robot();
		for (int i = 0; i < 4; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public void Screenzoomin() throws Exception {
		Robot r = new Robot();
		for (int i = 0; i < 3; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ADD);
			r.keyRelease(KeyEvent.VK_ADD);
			r.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public void opentabandSwitch() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + ReadData.getTestProperty("groupsummaryUrl") + "','_blank');");
		commonUtils.switchToWindow(1);
	}

	public boolean onHoldcalanderdayscheck() throws Exception {
		boolean flag = false;
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		WebElement periodcalederdays = commonUtils.expandRootElement(ls.periodcalederdays);
		periodcalederdays.sendKeys("d");
		WebElement periodcalanderdayserrormess = commonUtils.expandRootElement(ls.periodcalanderdayserrormess);
		String errormess = periodcalanderdayserrormess.getText();
		if (errormess.equalsIgnoreCase("ungültiges Format")) {
			flag = true;
			assertTrue(flag);
			log.info("Period of days doesnt accept alphabets");
			logger.log(Status.INFO, "Period of days doesnt accept alphabets");
			logger.log(Status.INFO, commonUtils.getScreenhot(driver, "onHoldcalanderdayscheck"));
		} else {
			log.info("Period of days accept alphabets");
			logger.log(Status.FAIL, commonUtils.getScreenhot(driver, "onHoldcalanderdayscheck"));
			assertTrue(flag, "Period of days accept alphabets");
		}
		return flag;
	}

	String landinfpageheading(WebElement locator) {
		log.info("Validating the Header Text in Landing page");
		logger.log(Status.INFO, "Validating the Header Text in Landing page");
		String text = locator.getText();
		return text;
	}

	public void searchpageHeadingValidation(String appmenu) throws Exception {
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		String landinfpageheading = landinfpageheading(
				commonUtils.expandRootElement(ls.SearchInvestigationLandingText));
		if (landinfpageheading.equalsIgnoreCase(appmenu)) {
			log.info("Landed in " + landinfpageheading + "page");
			logger.log(Status.INFO, "Landed in " + landinfpageheading + "page");
			assertTrue(true);
		} else {
			log.info("Landed in " + landinfpageheading + "page");
			logger.log(Status.ERROR, "Landed in " + landinfpageheading + "page");
			assertTrue(false);
		}
	}

	public boolean searchpageoptionbutton() throws Exception {
		boolean flag = false;
		log.info("Validating the more option - reset and search buttons are displayed and clickable");
		logger.log(Status.INFO, "Validating the more option - reset and search buttons are displayed and clickable");
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(4000);
		commonUtils.scrollintoView();

		if (commonUtils.isClickable(commonUtils.expandRootElement(ls.MoreOption))) {
			if (commonUtils.isClickable(ls.ResetButton) == true) {
				if (commonUtils.isClickable(ls.SearchButton) == true) {
					flag = true;
					assertTrue(flag, "the fields in search investigation is not displayed and clickable");

				}
			}
		} else {
			assertTrue(flag, "the fields in search investigation is displayed and non clickable");
		}

		return flag;
	}

	public Set<String> allSearchFieldValidation() throws Exception {

		boolean flag = false;
		log.info("Reading the search Feild from web page on clicking more options");
		logger.log(Status.INFO, "Reading the Search Fields of DC from web page on clicking more options");
		NAPFLocators ls = new NAPFLocators();
		commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
		commonUtils.waitTime(2000);
		commonUtils.scrollintoView();
		Set<String> browserField = new TreeSet();
		try {

			// reading all field label and adding it to list
			ls.allSearchcomboboxField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.allSearchaccountField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.allSearchcheckboxField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			ls.allSearchpfTextField.forEach((WebElement name) -> {
				browserField.add(name.getAttribute("label").trim());
			});

			try {
				List<WebElement> findElement = driver.findElements(By.tagName("pf-custom-control"));
				log.info("custom_field element is present in this page");
				for (int i = 0; i < findElement.size(); i++) {
					boolean displayed = findElement.get(i).isDisplayed();
					if (displayed) {
						WebElement expandRootElement = commonUtils
								.expandRootElement(ls.custom_field_one + i + ls.custom_field_two);
						browserField.add(expandRootElement.getText().trim());
					}
				}

			} catch (Exception e) {
				log.info("custom_field is not presentin this page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.ERROR, e);
		}
		return browserField;
	}

	public void SearchInvestigationsearch(String locator, String data, String name) throws Exception {
		log.info(name + " search validation");
		logger.log(Status.INFO, name + " search validation");
		NAPFLocators ls = new NAPFLocators();
		commonUtils.waitTime(2000);
		WebElement processNameInput = commonUtils.expandRootElement(locator);
		commonUtils.type(processNameInput, "type data in " + name, data);
	}

	public void searchClick() throws Exception {
		log.info("clicking in search button");
		logger.log(Status.INFO, "clicking in search button");
		commonUtils.waitTime(2000);
		NAPFLocators ls = new NAPFLocators();
		commonUtils.JSClick(ls.SearchButton);
	}

	public boolean SearchResult(String data, String data2, String HeaderName) throws Exception {
		boolean flag = false;
		log.info("search Results validation");
		logger.log(Status.INFO, "Search Results validation");
		commonUtils.waitTime(4000);
		NAPFLocators ls = new NAPFLocators();
		 try {
			 if (commonUtils.expandRootElement(ls.PopUPtab).isDisplayed()) {
					WebElement PopUptext = commonUtils.expandRootElement(ls.PopUptext);
					if (PopUptext.getText().equalsIgnoreCase("Zu viele Resultate gefunden.")) {
						WebElement ClosButton = commonUtils.expandRootElement(ls.CloseButton);
						ClosButton.click();
						flag = true;
						assertTrue(flag, "the popup is present and the close button is not found");

					} else {
						log.info("Text in PopUp is not same as expected");
						logger.log(Status.INFO, "Text in PopUp is not same as expected");
						assertTrue(flag, "the popup is present and the close button is not found");
					}
				}
		 }catch(Exception e) {
			log.info("Popup is not found");
			logger.log(Status.INFO, "Pop up is not found");
			String text = ls.searchResults.getText();
			if (text.equals("Suchresultate")) {
				log.info("Search result data is found");
				logger.log(Status.INFO, "Search result data is found");
				int SearchResultNumber = noOfSearchResult();
				if (commonUtils.isdisabledjs(ls.touserInbox)!=true && commonUtils.isdisabledjs(ls.unlockInverstigation)!=true) {
					log.info("To user inbox and Unlock investigation are in enabled status");
					logger.log(Status.INFO, "To user inbox and Unlock investigation are in enabled status");
					assertTrue(flag, "the field userinbox and unlockinvestigation is enabled");

				} else {
					log.info("To user inbox and Unlock investigation are in disabled status");
					logger.log(Status.INFO, "To user inbox and Unlock investigation are in disabled status");
					int noofResultDataValidation = NoofResultDataValidation(data, HeaderName, data2);
					if (SearchResultNumber == noofResultDataValidation) {
						flag = true;
						assertTrue(flag, "the userinbox search result is not same");
					}
				}
			}
		}
		return flag;
	}

	public int NoofResultDataValidation(String data, String headername, String data2) throws Exception {
		log.info("Table Data Validation ");
		logger.log(Status.INFO, "Table Data Validation ");
		NAPFLocators ls = new NAPFLocators();
		int tableDatacount = 0;
		if (headername.equalsIgnoreCase("Prozessname") || headername.equalsIgnoreCase("Transaktion")
				|| headername.equalsIgnoreCase("Status der NF") || headername.equalsIgnoreCase("Währung")
				|| headername.equalsIgnoreCase("Anlieferung")
				|| headername.equalsIgnoreCase("Postkonto-Nr. Auftraggeber")) {
			List<WebElement> searchTableDatas = commonUtils.expandRootsElement(ls.searchTableData);
			for (int i = 34; i < searchTableDatas.size(); i++) {
				String text = searchTableDatas.get(i).getText();
				if (text.equalsIgnoreCase(data)) {
					i = i + 10;
					tableDatacount++;
					commonUtils.scrollBy("pf-table");
					commonUtils.waitTime(1000);
				}
				
			}
			
		} else if (headername.equalsIgnoreCase("Betrag") || headername.equalsIgnoreCase("NF-ID")) {
			List<WebElement> searchTableDatas = commonUtils.expandRootsElement(ls.searchTableData);
			for (int i = 34; i < searchTableDatas.size(); i++) {
				String query = "return document.querySelector(\"pf-table\").shadowRoot.querySelectorAll('vaadin-grid-cell-content')["
						+ i + "]";
				WebElement nfid = commonUtils.expandRootElement(query);
				String text = nfid.getText();
				System.out.println(data);
				System.out.println(text);
				if (text.equalsIgnoreCase(data)||(Integer.parseInt(text)==Integer.parseInt(data))||(Integer.parseInt(text)>Integer.parseInt(data))) {
					double pagedata = Double.parseDouble(text);
					double pagefromdata = Double.parseDouble(data);
					double pagetodata = Double.parseDouble(data2);
					if ((pagefromdata == pagedata || pagedata > pagefromdata)
							&& (pagedata == pagetodata || pagedata < pagetodata)) {
						i = i + 10;
						tableDatacount++;
						commonUtils.scrollBy("pf-table");
						commonUtils.waitTime(1000);
					}
				} else 
					{
						i = i + 5;
						String query1 = "document.querySelector(\"pf-table\").shadowRoot.querySelectorAll('vaadin-grid-cell-content')["
								+ i + "]";
						WebElement amount = commonUtils.expandRootElement(query);
						String amounttext = nfid.getText();
						double pagedata = Double.parseDouble(amounttext);
						double pagefromdata = Double.parseDouble(data);
						double pagetodata = Double.parseDouble(data2);
						if ((pagefromdata == pagedata || pagedata > pagefromdata)
								&& (pagedata == pagetodata || pagedata < pagetodata)) {
							i = i + 10;
							tableDatacount++;
							commonUtils.scrollBy("pf-table");
							commonUtils.waitTime(1000);
						}
					}

			}

		}
		else {
			List<WebElement> searchTableDatas = commonUtils.expandRootsElement(ls.searchTableData);
			for (int i = 34; i < searchTableDatas.size(); i++) {
				String text = searchTableDatas.get(i).getText();
				if (!text.equalsIgnoreCase(data)) {
					i = i + 10;
					tableDatacount++;
					commonUtils.scrollBy("pf-table");
					commonUtils.waitTime(1000);
				}
				
			}
		}
		return tableDatacount;

	}

	public void ClickReset() throws Exception {
		log.info("click reset button works ");
		logger.log(Status.INFO, "click reset button works ");
		NAPFLocators ls = new NAPFLocators();
		WebElement resetbutton = ls.ResetButton;
		commonUtils.click(resetbutton);

	}
	public void downloadClick() throws Exception {
		log.info("download button is works ");
		logger.log(Status.INFO, "download button is works ");
		NAPFLocators ls = new NAPFLocators();
		WebElement downloadClick = ls.downloadclick;
		commonUtils.click(downloadClick);
	}
	
	public int noOfSearchResult() throws Exception {
		commonUtils.waitTime(4000);
		NAPFLocators ls = new NAPFLocators();
		String NumberText = ls.NoOfResults.getText();
		log.info("reading the number search result of data");
		logger.log(Status.INFO, "reading the number search result of data");
		String[] Number = NumberText.split("Anzahl Resultate:");
		String string = Number[1];
		int SearchResultNumber = Integer.parseInt(string);
		return SearchResultNumber;
	}
	
	public void headersearchButton() throws Exception {
		log.info("headersearchButton button is works ");
		logger.log(Status.INFO, "headersearchButton button is works ");
		NAPFLocators ls = new NAPFLocators();
		WebElement headersearchButton = ls.headersearchButton;
		commonUtils.click(headersearchButton);
	}
}
