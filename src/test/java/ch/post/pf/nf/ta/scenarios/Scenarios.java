package ch.post.pf.nf.ta.scenarios;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page.SetWebLifecycleStateState;
import org.slf4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import ch.post.pf.nf.ta.baseutils.CommonUtils;
import ch.post.pf.nf.ta.baseutils.Log4jUtil;
import ch.post.pf.nf.ta.baseutils.ReadData;
import ch.post.pf.nf.ta.baseutils.Setup;
import ch.post.pf.nf.ta.dao.DAOTest;
import ch.post.pf.nf.ta.pagefactory.NAPFLocators;

public class Scenarios extends Setup {

	Logger log = Log4jUtil.loadLogger(Scenarios.class);
	CommonUtils commonUtils = new CommonUtils();

	/*
	 * Validating all app menu list in home page
	 * 
	 */
	public void appmenulistValidation() throws Exception {
		log.info("check the app menu list is found in the page with open NF field");
		logger.log(Status.INFO, "check the app menu list is found in the page with open-NF field");
		DAOTest daoTest = new DAOTest();
		commonUtils.waitTime(2000);
		boolean appMenuList = daoTest.appMenuList();
		boolean open_NF_input_field = daoTest.open_NF_input_field();
		if (appMenuList) {
			assertTrue(appMenuList, "App Menulist is not found in the page");
			logger.log(Status.INFO, "App Menu list is found");
		} else {
			assertTrue(false);
			logger.log(Status.ERROR, "App Menu list is not found");
		}
		if (open_NF_input_field) {
			Assert.assertTrue(open_NF_input_field, "Open_NF field is not found");
			logger.log(Status.INFO, "Open-Nf field is found");
		} else {
			assertTrue(false);
			logger.log(Status.ERROR, "Open-Nf field is not found");
		}
	}

	/*
	 * Selecting the appropriate langue from profile menu
	 * 
	 */

	public void profileMenuSelect(String Language) throws Exception {
		log.info("select the Appropriate languge for execution: " + Language);
		DAOTest daoTest = new DAOTest();
		commonUtils.waitTime(2000);
		boolean appLanguage = daoTest.appLanguage();
		Assert.assertTrue(appLanguage, "Profile icon is not present");
		boolean langugeselect = daoTest.langugeselect(Language);
		Assert.assertTrue(langugeselect, "Langugae given is not present in the profile menu");
	}

	/*
	 * Creating a online NF investigation from WEB using the PA and TA and reading
	 * the NF number created...
	 */

	public void createNFWithPaTa(Map<String, String> testdata) throws Exception {
		log.info("create Investigation And check The Field Based on PA :" + testdata.get("Process Name") + " TA :"
				+ testdata.get("TA"));
		DAOTest daoTest = new DAOTest();
		commonUtils.waitTime(2000);
		boolean createInvestigation = daoTest.CreateInvestigation();
		assertTrue(createInvestigation, "unable to create investigation");
		boolean selectPATADD = daoTest.selectPATADD(testdata.get("Process Name"), testdata.get("TA"));
		assertTrue(selectPATADD, "both Pa and Ta were not selected");
		boolean saveButton = daoTest.saveButton();
		commonUtils.waitTime(10000);
		assertTrue(saveButton, "The investigation save is not done");
	}

	/*
	 * opening the NF investigation
	 * 
	 */

	public void openNFInvestigation(Map<String, String> testdata) throws IOException, Exception {
		logger.log(Status.INFO, "Opening the Nf id of DC : " + testdata.get("Mode"));
		DAOTest daoTest = new DAOTest();
		boolean openNf = daoTest.openNf(ReadData.getTestProperty(testdata.get("Mode")));
		assertTrue(openNf, "The NF page could not be found.");
		daoTest.constructOpenNfAndLoad(testdata.get("Mode"));
	}

	/*
	 * Verify the activities tab data is present .
	 * 
	 */

	public void activitiesTab(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		commonUtils.waitTime(4000);
		daoTest.editorMenuSelect(testdata.get("menutab"));
		Map<String, String> activityTableData = daoTest.ActivityTableData();
		log.info("Recent Activity table data" + activityTableData);
	}

	/*
	 * Validating the notes tab menu by adding a notes editing the notes deleting
	 * the notes
	 * 
	 */

	public void notesTabAddEditDelete(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		commonUtils.waitTime(4000);
		daoTest.editorMenuSelect(testdata.get("notesTab"));
		daoTest.notesAdd();
		daoTest.notesEditDeleteFunction();

	}

	/*
	 * Validating general tab field for all new and old investigation
	 * 
	 */
	public String readNFNumber = null;

	public void DcGenralTabFieldValidation(Map<String, String> testdata) throws InterruptedException, Exception {
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO, "Validating the general tab Field of " + testdata.get("Mode") + " DC");
		readNFNumber = daoTest.readNFNumber();
		log.info("the Nf number for the created investigation is : " + readNFNumber);
		if (!testdata.get("Mode").equalsIgnoreCase("Online")) {
			List<String> fieldValidationOnOpenNF = daoTest.fieldValidationOnOpenNF(testdata.get("Mode"));
			Collections.sort(fieldValidationOnOpenNF);
			List<String> readFiledNameFromSheet = daoTest.readFiledNameFromSheet(testdata.get("Mode"));
			Collections.sort(readFiledNameFromSheet);
			assertEquals(fieldValidationOnOpenNF, readFiledNameFromSheet, "All fields are not present in General Tab");
			log.info("All field are present in general tab");
			logger.log(Status.INFO, "All field are present in " + testdata.get("Mode") + " DC");
		} else {
			List<String> browserFieldValidation = daoTest.fieldValidation(testdata.get("Mode"));
			Collections.sort(browserFieldValidation);
			List<String> readFiledNameFromSheet = daoTest.readFiledNameFromSheet(testdata.get("Mode"));
			Collections.sort(readFiledNameFromSheet);
			assertEquals(browserFieldValidation, readFiledNameFromSheet, "All fields are not present in General Tab");
			log.info("All field are present in general tab");
			logger.log(Status.INFO, "All field are present in " + testdata.get("Mode") + " DC");
		}
	}

	/*
	 * Validating general tab closure data between repository and web page for all
	 * new and old investigation
	 * 
	 */
	public void DcClosureFieldDataValidation(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO,
				"Validating the Closure data for Pa : " + testdata.get("Process Name") + " Ta : " + testdata.get("TA"));
		Set<String> readclosureNameFromPATA = daoTest.readFiledNameFromPATA(testdata.get("Closure reason"),
				"Closure reason");
		log.info(readclosureNameFromPATA.toString());
		commonUtils.waitTime(3000);
		Set<String> closureValuesValidation = daoTest.closureValuesValidation(readclosureNameFromPATA.size(),
				testdata.get("Mode"));
		log.info(closureValuesValidation.toString());
		assertEquals(readclosureNameFromPATA, closureValuesValidation,
				"both the repository and webpage closure data are not same");
	}

	/*
	 * Validating general tab product code data between repository and web page for
	 * all new and old investigation
	 * 
	 */

	public void DcProductCodeDataValidation(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();

		logger.log(Status.INFO,
				"Validating the Product data for Pa : " + testdata.get("Process Name") + " Ta : " + testdata.get("TA"));
		Set<String> readproductNameFromPATA = daoTest.readFiledNameFromPATA(testdata.get("Product code"),
				"Product code");
		log.info(readproductNameFromPATA.toString());
		commonUtils.waitTime(3000);
		Set<String> productValuesValidation = daoTest.productValuesValidation(readproductNameFromPATA.size(),
				testdata.get("Mode"));
		log.info(productValuesValidation.toString());

		assertEquals(readproductNameFromPATA, productValuesValidation,
				"both the repository and webpage product data are not same");
	}

	/*
	 * method to valdiate all product tab field for both online and other DC channel
	 * 
	 */
	public void ProductTabFieldValidation(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO, "Validating the product tab field for Pa : " + testdata.get("Process Name") + " Ta : "
				+ testdata.get("TA"));
		daoTest.editorMenuSelect(testdata.get("Menu"));
		List<String> productTabWebPageFields = daoTest.fieldValidation(testdata.get("Mode"));
		Set<String> productTabReposirotyData = daoTest.readFiledNameFromPATA(testdata.get("Fields"), "Prodcut tab");
		assertEquals(productTabWebPageFields, productTabReposirotyData,
				"Both the repository and Webpage data field are not same in product tab");

	}
	
	/*
	 * method to valdiate all field inside user inbox with column field sorting..
	 */

	public void userInboxFieldValidation() throws Exception {
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO, "Validating theuser inbox fields");
		daoTest.AppMenuSelection(ReadData.getTestProperty("userMenu"));
		daoTest.column_field_sorting_validation();
		boolean userInboxGroupElementCheck = daoTest.userInboxGroupElementCheck();
		if (userInboxGroupElementCheck) {
			assertEquals(userInboxGroupElementCheck, true, "The group element are in enabled status");
		} else {
			assertEquals(userInboxGroupElementCheck, false, "The group element are in disabled status");
		}
	}
	
	/*
	 *  nf id search in userinbox and check the group elements status..
	 */

	public void userInboxNFsearchAndGroupcheck() throws Exception {
		DAOTest daoTest = new DAOTest();
		String readNFNumber2 = daoTest.readNFNumber();
		logger.log(Status.INFO, "searching the NF-number created :" + readNFNumber2);
		commonUtils.loadURL();
		commonUtils.waitTime(3000);
		WebElement nfsearchInUserInbox = daoTest.NfsearchInUserInbox(readNFNumber2.replaceAll("NF | ", ""), "search");
		boolean userInboxGroupElementCheck = daoTest.userInboxGroupElementCheck();
		if (!userInboxGroupElementCheck) {
			assertTrue(true, "The group element are in disabled status");
		} else {
			assertTrue(false, "The group element are in enabled status");
		}
	}
	
	/*
	 * nf id search in userinbox and opening the nf id from userinbox and create a customer mess..
	 */
	public void userInboxNFsearchAndopenCreateMess(String NFId) throws Exception {
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO, "Searching and opening the NF id: " + NFId);
		boolean open_Searched_NF = daoTest.open_Searched_NF(NFId, "");
		assertTrue(open_Searched_NF, "Unable to iopen the Searched NF");
		boolean createMessTab = daoTest.CreateMessTab("Create a new mess");
		assertTrue(createMessTab, "Create mess is not working as execpted");
	}

	/*
	 * nf id search in userinbox and move the searched nf id to appropriate group and check 
	 * the same is moved to appropriate group..
	 */
	
	public void userGroupNavigate(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		String readNFNumber2 = daoTest.readNFNumber();
		logger.log(Status.INFO, "searching the NF-number created :" + readNFNumber2);
		commonUtils.loadURL();
		commonUtils.waitTime(3000);
		WebElement nfsearchInUserInbox = daoTest.NfsearchInUserInbox(readNFNumber2.replaceAll("NF | ", ""), "search");
		boolean userInboxGroupElementCheck = daoTest.userInboxGroupElementCheck();
		if (!userInboxGroupElementCheck) {
			assertTrue(true, "The group element are in disabled status");
		} else {
			assertTrue(false, "The group element are in enabled status");
		}
		daoTest.seelctGroupOption(testdata.get("Group"));
		daoTest.appMenuList(testdata.get("appmenu"));
		log.info("Searching the NF moved to " + testdata.get("Group"));
		logger.log(Status.INFO, "Searching the NF moved to " + testdata.get("Group"));
		daoTest.groupQueueSummary(testdata.get("Groupsummary"));
		// driver.get(ReadData.getTestProperty("groupsummaryUrl"));
		commonUtils.waitTime(5000);
		WebElement nfsearchIngroupsummary = daoTest.NfsearchInUserInbox(readNFNumber2.replaceAll("NF | ", ""), "");
		String replaceAll = readNFNumber2.replaceAll("NF | ", "");
		String NFIdserach = replaceAll.replace("|", "");
		if (nfsearchIngroupsummary.isDisplayed()) {
			assertEquals(NFIdserach, daoTest.searchedCreatedNF,
					"The Nf id moved to " + testdata.get("Group") + "is not found.");
		} else {
			assertTrue(false, "The Nf id is not found in the Group queue summary");
		}
	}

	public void OnHoldreminder(Map<String, String> testdata) throws Exception {
		// TODO Auto-generated method stub
		DAOTest daoTest = new DAOTest();
		logger.log(Status.INFO, "Validating On Hold Reminder tab");
		daoTest.editorMenuSelect("Wiedervorlage und Mahnungen");
		if (testdata.get("Process Name").equalsIgnoreCase("Ausland")
				&& testdata.get("TA").equalsIgnoreCase("Cash International")) {
			List<String> fieldValidation = daoTest.fieldValidation(testdata.get("Mode"));
			Collections.sort(fieldValidation);
			Set<String> readOnHoldFiledNameFromPATA = daoTest.readFiledNameFromPATA(testdata.get("On Hold Field"),
					"On Hold Field");
			assertEquals(fieldValidation, readOnHoldFiledNameFromPATA,
					"Filed name in repository is not same in web page");
			log.info("Found 5 fields with same name");
			logger.log(Status.INFO, "Found 5 fields and are same between repository and web page");
			boolean onHoldcalanderdayscheck = daoTest.onHoldcalanderdayscheck();
			if (onHoldcalanderdayscheck) {
				assertTrue(onHoldcalanderdayscheck);
			} else {
				assertTrue(onHoldcalanderdayscheck);
			}
		} else {
			List<String> fieldValidation = daoTest.fieldValidation(testdata.get("Mode"));
			Collections.sort(fieldValidation);
			Set<String> readOnHoldFiledNameFromPATA = daoTest.readFiledNameFromPATA(testdata.get("On Hold Field"),
					"On Hold Field");
			assertEquals(fieldValidation, readOnHoldFiledNameFromPATA,
					"Filed name in repository is not same in web page");
			log.info("Found 2 fields with same name");
			logger.log(Status.INFO, "Found 2 fields and are same between repository and web page");
			boolean onHoldcalanderdayscheck = daoTest.onHoldcalanderdayscheck();
			if (onHoldcalanderdayscheck) {
				assertTrue(onHoldcalanderdayscheck);
			} else {
				assertTrue(onHoldcalanderdayscheck);
			}
		}

	}

	public void searchInvestigationFields_buttons(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		daoTest.appMenuList(testdata.get("Menu 1"));
		daoTest.searchpageoptionbutton();
		Set<String> allSearchFieldValidation = daoTest.allSearchFieldValidation();
		Set<String> Allsearchfields = daoTest.readFiledNameFromPATA(testdata.get("All search fields"),
				"All search fields");
		log.info(allSearchFieldValidation.toString());
		log.info(Allsearchfields.toString());
		assertEquals(allSearchFieldValidation, Allsearchfields, "All search fields are not found as execpted");
		log.info("All search fields are found as execpted");
		logger.log(Status.INFO, "All search fields are found as execpted");
	}

	public void SearchDataInSearchInvestigation(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		NAPFLocators ls = new NAPFLocators();
		log.info("Search Data In Search Investigation " + testdata.get("headername"));
		logger.log(Status.INFO, "Search Data In Search Investigation " + testdata.get("headername"));
		switch (testdata.get("headername")) {
		case "Prozessname":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			log.info(testdata.get("DataOne"));
			daoTest.SearchInvestigationsearch(ls.processNameInput, testdata.get("DataOne"),testdata.get("headername"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataOne"), testdata.get("DataTwo"), testdata.get("headername"));
			break;
		case "Status der NF":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			daoTest.SearchInvestigationsearch(ls.State, testdata.get("DataOne"), testdata.get("headername"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataOne"), testdata.get("DataTwo"), testdata.get("headername"));
			break;
		case "Währung":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			WebElement currency = ls.Currency;
			currency.click();
			commonUtils.downenter();
			//commonUtils.type(ls.Currency,testdata.get("headername"),testdata.get("DataOne"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataOne"), testdata.get("DataTwo"), testdata.get("headername"));
			break;
		case "NF-Datum":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			commonUtils.click(ls.NFdateRangeFrom);
			ls.NFdateRangeFrom.sendKeys(testdata.get("DataOne"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataOne"), testdata.get("DataTwo"), testdata.get("headername"));

			break;
		case "Betrag":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			WebElement AmountRangeFrom = ls.AmountRangeFrom;
			AmountRangeFrom.click();
			commonUtils.type(ls.AmountRangeFrom,testdata.get("headername"),testdata.get("DataOne"));
			WebElement AmountRangeTo = ls.AmountRangeTo;
			AmountRangeTo.click();
			commonUtils.type(ls.AmountRangeTo,testdata.get("headername"),testdata.get("DataTwo"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataOne"), testdata.get("DataTwo"), testdata.get("headername"));
			break;
		case "NF-ID":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
			commonUtils.scrollintoView();
			//daoTest.SearchInvestigationsearch(ls.NFIdRangeFrom, testdata.get("DataOne"), testdata.get("headername"));
			//daoTest.SearchInvestigationsearch(ls.NFIdRangeto, testdata.get("DataTwo"), testdata.get("headername"));
			commonUtils.click(ls.NFIdRangeFrom);
			commonUtils.type(ls.NFIdRangeFrom,testdata.get("headername"),commonUtils.splitString(testdata.get("DataOne")));
			commonUtils.click(ls.NFIdRangeto);
			commonUtils.type(ls.NFIdRangeto,testdata.get("headername"),commonUtils.splitString(testdata.get("DataTwo")));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(commonUtils.splitString(testdata.get("DataOne")), commonUtils.splitString(testdata.get("DataTwo")), testdata.get("headername"));
			break;
		case "Postkonto-Nr. Auftraggeber":
			daoTest.appMenuList(testdata.get("Menu 1"));
			daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
			ls.MoneyReceiver.isDisplayed();
			commonUtils.click(ls.MoneyReceiver);
			commonUtils.type(ls.MoneyReceiver, "enter iban number in money receiver", testdata.get("DataOne"));
			commonUtils.click(ls.NFdateRangeFrom);
			ls.NFdateRangeFrom.sendKeys(testdata.get("DataTwo"));
			//commonUtils.type(ls.NFdateRangeFrom, "select a date", testdata.get("DataTwo"));
			commonUtils.scrolltoPageHeight();
			daoTest.searchClick();
			daoTest.SearchResult(testdata.get("DataTwo"), null, testdata.get("headername"));
			break;
			
		default:
			log.info("the given key is not found in a case");
			break;
		}

	}

	public void ResetClickFunction(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		NAPFLocators ls = new NAPFLocators();
		daoTest.appMenuList(testdata.get("Menu 1"));
		commonUtils.waitTime(3000);
		logger.log(Status.INFO, "Reset button working or not working");
		commonUtils.click(commonUtils.expandRootElement(ls.MoreOption));
		commonUtils.scrolltoPageHeight();
		if (commonUtils.expandRootElement(ls.LessOption).isDisplayed()) {
			daoTest.ClickReset();
			if (commonUtils.expandRootElement(ls.MoreOption).isDisplayed()){
				log.info("moreoption and lessoption button is displayed");
				logger.log(Status.INFO, "moreoption and lessoption button is displayed");
				assertTrue(true);
			}
			else {
				log.info("moreoption button is not displayed");
				logger.log(Status.INFO, "moreoption button is not displayed");
				assertTrue(false);
			}

		} else {
			log.info("lessoption button is not displayed");
			logger.log(Status.INFO, "lessoption button is not displayed");
			assertTrue(false);
		}
	}
	
	public void SearchInvestigationDownload(Map<String, String> testdata) throws Exception {
		DAOTest daoTest = new DAOTest();
		NAPFLocators ls = new NAPFLocators();
		daoTest.appMenuList(testdata.get("Menu 1"));
		commonUtils.waitTime(3000);
		daoTest.searchpageHeadingValidation(testdata.get("Menu 1"));
		WebElement AmountRangeFrom = ls.AmountRangeFrom;
		AmountRangeFrom.click();
		commonUtils.type(ls.AmountRangeFrom,testdata.get("headername"),testdata.get("DataOne"));
		WebElement AmountRangeTo = ls.AmountRangeTo;
		AmountRangeTo.click();
		commonUtils.type(ls.AmountRangeTo,testdata.get("headername"),testdata.get("DataTwo"));
		commonUtils.scrolltoPageHeight();
		daoTest.searchClick();
		int noOfSearchResult = daoTest.noOfSearchResult();
		log.info(" Search Investigation download clicking " );
		logger.log(Status.INFO, "Search Investigation download clicking ");
		daoTest.downloadClick();
		commonUtils.waitTime(10000);
	}
	
	public void CreateinvestigationNew(Map<String, String> testdata,String dataOne,String dataTwo) throws Exception {
		DAOTest daoTest = new DAOTest();
		NAPFLocators ls = new NAPFLocators();
		daoTest.appMenuList(testdata.get("Menu 1"));
		commonUtils.waitTime(3000);
		daoTest.CreateInvestigation();
		commonUtils.waitTime(2000);
		commonUtils.ctrlTab();
		log.info("going to the next Create new investigation Tab" );
		daoTest.selectPATADD(dataOne, dataTwo);
		daoTest.saveButton();
		log.info(" save button is  clicking " );
		commonUtils.waitTime(4000);
		daoTest.headersearchButton();
	}
}
