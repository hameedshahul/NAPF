package ch.post.pf.nf.ta.execution;

import java.util.Map;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import ch.post.pf.nf.ta.baseutils.ReadData;
import ch.post.pf.nf.ta.baseutils.Setup;
import ch.post.pf.nf.ta.scenarios.Scenarios;


public class NapfTestCase extends Setup {

	protected Scenarios scenario = new Scenarios();
	
	@Ignore
	@Test(priority = 0, dataProvider = "getLaungageDataReader", dataProviderClass = ReadData.class,
	description = "To check wheather the Home Page of NAPF application is getting open.")
	public void HomepageMenuListValidation(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " :" + testdata.get("Language"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.appmenulistValidation();
	}

	
	@Ignore
	@Test(priority = 1, dataProvider = "getDataReader", dataProviderClass = ReadData.class)
	public void CreateNF(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		log.info(testdata.toString());
		if (testdata.get("Mode").equalsIgnoreCase("Online")) {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.createNFWithPaTa(testdata);
			scenario.DcGenralTabFieldValidation(testdata);
			scenario.DcClosureFieldDataValidation(testdata);
			scenario.DcProductCodeDataValidation(testdata);
		} else {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.openNFInvestigation(testdata);
			scenario.DcGenralTabFieldValidation(testdata);
			scenario.DcClosureFieldDataValidation(testdata);
			scenario.DcProductCodeDataValidation(testdata);
		}
	}

	@Ignore
	@Test(priority = 2, dataProvider = "getActivitesDataReader", dataProviderClass = ReadData.class)
	public void Activities(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		if (testdata.get("Mode").equalsIgnoreCase("Online")) {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.createNFWithPaTa(testdata);
			scenario.activitiesTab(testdata);
		} else {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.openNFInvestigation(testdata);
			scenario.activitiesTab(testdata);
		}
	}
	
	@Ignore
	@Test(priority = 3, dataProvider = "getNotesDataReader", dataProviderClass = ReadData.class)
	public void notesTabAdd(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.createNFWithPaTa(testdata);
		scenario.notesTabAddEditDelete(testdata);
	}
	
	@Ignore
	@Test(priority = 4, dataProvider = "getDataReader", dataProviderClass = ReadData.class)
	public void productTabValidation(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		if (testdata.get("Mode").equalsIgnoreCase("Online")) {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.createNFWithPaTa(testdata);
			scenario.ProductTabFieldValidation(testdata);
		} 
	}
	
	@Ignore
	@Test(priority = 5,dataProvider = "getLaungageDataReader", dataProviderClass = ReadData.class)
	public void userInbox(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName +"Field and Group button");
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.userInboxFieldValidation();
	}
	
	@Ignore
	@Test(priority = 6,dataProvider = "getUserInboxGroupDataReader", dataProviderClass = ReadData.class)
	public void userInboxcreateNFsearchandCheckGroup(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
		+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.createNFWithPaTa(testdata);
		scenario.userInboxNFsearchAndGroupcheck();
	}
	
	@Ignore
	@Test(priority = 7,dataProvider = "getLaungageDataReader", dataProviderClass = ReadData.class)
	public void userInboxOpenNFsearchandCreateMess(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName);
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.userInboxNFsearchAndopenCreateMess(ReadData.getTestProperty("openNfFIPO"));
	}
	
	@Ignore
	@Test(priority = 8, dataProvider = "getUserInboxGroupNavigateDataReader", dataProviderClass = ReadData.class)
	public void UserInboxGroupNavigate(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.createNFWithPaTa(testdata);
		scenario.userGroupNavigate(testdata);
	}	

	@Ignore
	@Test(priority = 9, dataProvider = "getDataReader", dataProviderClass = ReadData.class)
	public void onHoldReminder(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName + " for PA and TA :" + testdata.get("Process Name")
				+ testdata.get("TA") + "DC:" + testdata.get("Mode"));
		if (testdata.get("Mode").equalsIgnoreCase("Online")) {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.createNFWithPaTa(testdata);
			scenario.OnHoldreminder(testdata);
		}
		else {
			scenario.profileMenuSelect(testdata.get("Language"));
			scenario.openNFInvestigation(testdata);
			scenario.OnHoldreminder(testdata);
		}
	}
	
	@Ignore
	@Test(priority = 10, dataProvider = "getUserInboxGroupDataReader", dataProviderClass = ReadData.class)
	public void searchInvestigationFieldVerification(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName +testdata.get("Menu 1"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.searchInvestigationFields_buttons(testdata);
	}
	
	@Ignore
	@Test(priority = 11, dataProvider = "getSearchInvestigation", dataProviderClass = ReadData.class)
	public void SearchInvestigation(Map<String, String> testdata) throws Exception {
		logger = extentreport.createTest(methodName +testdata.get("headername"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.SearchDataInSearchInvestigation(testdata);
	}
	
	@Ignore
	@Test(priority = 12, dataProvider = "getUserInboxGroupDataReader", dataProviderClass = ReadData.class)
	public void MoreLessResetButtonCheckSearchInvestigation(Map<String, String> testdata) throws Exception {
		logger = parent.createNode(methodName);
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.ResetClickFunction(testdata);
	}
	
	@Test(priority = 13, dataProvider = "getUserInboxGroupDataReader", dataProviderClass = ReadData.class)
	public void SearchInvestigationdownloadClick(Map<String, String> testdata) throws Exception {
		logger = extentreport.createTest(methodName +testdata.get("headername"));
		scenario.profileMenuSelect(testdata.get("Language"));
		scenario.SearchInvestigationDownload(testdata);
	}
}