package ch.post.pf.nf.ta.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ch.post.pf.nf.ta.baseutils.Setup;

public class NAPFLocators extends Setup{
	
	public NAPFLocators() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName = "pf-portal-header")
	public WebElement headerProfileMenulist;
	
	public String headerProfileMenu ="return document.querySelector('pf-portal-header').shadowRoot.querySelector('pf-menu-item')";
	
	@FindBy(xpath = "//pf-menu-item[@visual=\"menu_small\"]")
	public List<WebElement> profilemenuList;
	
	@FindBy(xpath="//pf-menu-item[@slot='app-menu']")
	public List<WebElement> app_Menu_List;
	
	@FindBy(tagName="pf-text-field")
	public WebElement open_NF_input;
	
	public String createInvestigation="return document.querySelector('pf-portal-header > pf-menu-item').shadowRoot.querySelector('div.outer.leaf > a > slot > pf-icon').shadowRoot.querySelector('svg')";
	public String create_Inves_page="return document.querySelector('pf-editor-page').shadowRoot.querySelector('div > div.main-container > pf-grid > pf-editor-header').shadowRoot.querySelector('pf-text')";

	public String PaInitiator ="return document.querySelector('pf-combobox[testselector=\"gentab.processorName\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#input').shadowRoot.querySelector('input')";
	
	public String TaInitiator="return document.querySelector('pf-combobox[testselector=\"gentab.ta\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#input').shadowRoot.querySelector('input')";
	
	//save button
	public String savecss="return document.querySelector('pf-portal-header').shadowRoot.querySelector('pf-menu-item').shadowRoot.querySelector('pf-icon').shadowRoot.querySelector('svg')";
	
	// reading nf number
	public String NfNumber="return document.querySelector('pf-editor-page').shadowRoot.querySelector('pf-editor-header').shadowRoot.querySelector('pf-text')";
	
	// open NF click
	public String openNFclick="return document.querySelector('pf-tile-card').shadowRoot.querySelector('pf-button').shadowRoot.querySelector('vaadin-button').shadowRoot.querySelector('#button')";
	
	//field valdiation
	
	public String custom_field="return document.querySelector('pf-custom-control').shadowRoot.querySelector('div.left-label-container > pf-text')";
	
	public String custom_field_one="return document.querySelectorAll('pf-custom-control')[";
	public String custom_field_two="].shadowRoot.querySelector(\"div.vertical-label-container\")";
	
	@FindBy(tagName = "pf-text-area")
	public List<WebElement> textareaField;
	
	@FindBy(xpath="(//pf-form-layout[@class=\"hydrated inside-grid\"])[1]/pf-checkbox")
	public List<WebElement> checkboxField;
	
	@FindBy(xpath="(//pf-form-layout[@class=\"hydrated inside-grid\"])[1]/pf-text-field")
	public List<WebElement> textField;
	
	@FindBy(xpath="(//pf-form-layout[@class=\"hydrated inside-grid\"])[1]/pf-combobox")
	public List<WebElement> comboboxField;
	
	@FindBy(xpath="(//pf-form-layout[@class=\"hydrated inside-grid\"])[1]/pf-date-picker")
	public List<WebElement> datepicketField;

	@FindBy(xpath="(//pf-form-layout[@class=\"hydrated inside-grid\"])[1]/pf-account-field")
	public List<WebElement> accountField;
	
	@FindBy(tagName = "pf-tile-card")
	public WebElement openNfButton;
	
	public String clouserReason="return document.querySelector('pf-combobox[testselector=\"gentab.closureReason\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#input').shadowRoot.querySelector('input')";
	public String clouserReasonToggle="return document.querySelector('pf-combobox[testselector=\"gentab.closureReason\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#toggleButton')";
	
	public String productReason="return document.querySelector('pf-combobox[testselector=\"gentab.productCode\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#input').shadowRoot.querySelector('input')";
	public String productReasonToggle="return document.querySelector('pf-combobox[testselector=\"gentab.productCode\"]').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#toggleButton')";
	
	//menu click in editor
	public String editormenu="return document.querySelector('pf-tabs').shadowRoot.querySelectorAll('pf-menu-item')";
	
	
	//table data
	public String activityFieldValue ="return document.querySelector('pf-table').shadowRoot.querySelector('vaadin-grid')";
	
	public String firstfield="vaadin-grid-cell-content[slot=\"vaadin-grid-cell-content-1\"]";
	public String secondfield="vaadin-grid-cell-content[slot=\"vaadin-grid-cell-content-2\"]";
	public String thirdfield="vaadin-grid-cell-content[slot=\"vaadin-grid-cell-content-3\"]";
	public String fourthfield="vaadin-grid-cell-content[slot=\"vaadin-grid-cell-content-4\"]";
	public String fifthfield="vaadin-grid-cell-content[slot=\"vaadin-grid-cell-content-5\"]";
	
	//notes tab
	public String notesadd="return document.querySelector('pf-menu-item[test-selector=\"note-detail-add\"]').shadowRoot.querySelector('pf-icon').shadowRoot.querySelector('#Layer_1')";
	
	public String textarea="return document.querySelectorAll('pf-text-area[test-selector=\"noteTextArea\"]')[1].shadowRoot.querySelector('vaadin-text-area').shadowRoot.querySelector('textarea')";
	
	@FindBy(xpath="//pf-button[@test-selector='notes-add-edit-save-button']")
	public WebElement saveButton;
	
	//use activityFieldValue webelement and travel with below shadow root
	public String editdeletebutton="vaadin-grid-cell-content";
	
	public String notesEdit="pf-icon-button[icon=\"sl:pencil\"]";

	public String notesDelete="pf-icon-button[icon=\"sl:delete\"]";
	//user inbox 
	@FindBy(xpath = "//pf-button[@testselector='editor-header-action--sourceGroupQueue']")
	public WebElement sourceGroupQueue;
	
	@FindBy(xpath = "//pf-button[@testselector='editor-header-action--previousGroupQueue']")
	public WebElement previousGroupQueue;
	
	@FindBy(xpath = "//pf-button[@testselector='editor-header-action--otherGroupQueue']")
	public WebElement otherGroupQueue;
	
	public String userInboxNfField="return document.querySelector('pf-table').shadowRoot.querySelectorAll('vaadin-grid > vaadin-grid-cell-content')";
	
	public String landingPage_text="return document.querySelector('pf-editor-header').shadowRoot.querySelector('pf-text')";
	
	public String FirstNf_number="return document.querySelector(\"pf-table\").shadowRoot.querySelectorAll(\"vaadin-grid-cell-content\")[32]";
	
	public String all_NfNumber_search="return document.querySelector(\"#userProfileFrom > pf-table\").shadowRoot.querySelectorAll('pf-link')";
	public String all_NfNumber_search_2="return document.querySelector(\"#userProfileFrom > pf-table\").shadowRoot.querySelectorAll(\"vaadin-checkbox\")[";
	public String all_NfNumber_search_checkbox="].shadowRoot.querySelector(\"label\")";

	@FindBy(xpath = "//pf-text-area[@test-selector=\"customerTextArea\"]")
	public WebElement customer_description;
	
	@FindBy(xpath="//pf-menu-item[@test-selector='customer.mes.add']")
	public WebElement CreateMess;
	
	@FindBy(xpath="//pf-menu-item[@test-selector='customer.detail-save']")
	public WebElement save_mess;
	
	public String checkboxmess_click="return document.querySelector(\"pf-table\").shadowRoot.querySelector(\"vaadin-grid > vaadin-grid-cell-content:nth-child(25) > vaadin-checkbox\")";
	public String To_Do_Mess="return document.querySelector('pf-table').shadowRoot.querySelectorAll('vaadin-grid-cell-content')[0]";
	
	public String todo_mess_saved="return document.querySelector('pf-table').shadowRoot.querySelectorAll('vaadin-grid-cell-content')[2].querySelector('pf-text')";
	@FindBy(xpath = "//pf-editor-header[@testselector=\"userInboxHeader\"]/pf-button")
	public List<WebElement> userInboxHeader;
	
	public String groupQueueList="return document.querySelector(\"pf-table\").shadowRoot.querySelectorAll(\"vaadin-grid-cell-content\")";
	public String groupqueueselection="return document.querySelector(\"pf-table\").shadowRoot.querySelectorAll(\"vaadin-grid-cell-content\")[12]document.querySelector(\"pf-table\").shadowRoot.querySelectorAll(\"vaadin-grid-cell-content\")[";
	public String groupque2="]";
	
	@FindBy(xpath = "//pf-button[@testselector=\"saveDialog\"]")
	public WebElement saveGroup;
	
	// on hold 
	public String periodcalederdays="return document.querySelector('pf-text-field:nth-child(1)').shadowRoot.querySelector('vaadin-text-field').shadowRoot.querySelector('#vaadin-text-field-input-23 > slot:nth-child(2) > input')";
	public String periodcalanderdayserrormess="return document.querySelector('pf-text-field').shadowRoot.querySelector('vaadin-text-field').shadowRoot.querySelector('#vaadin-text-field-error-23')";

	//search investigation flied paths
	
	public String MoreOption="return document.querySelector(\"pf-search-panel\").shadowRoot.querySelector(\"pf-button\")";
	
	@FindBy(xpath = "//pf-button[@testselector='searchInv.reset']")
	public WebElement ResetButton;
	
	@FindBy(xpath = "//pf-button[@testselector='searchInv.search']")
	public WebElement SearchButton;
	
	public String LessOption="return document.querySelector('pf-search-panel').shadowRoot.querySelector('div.search-parameter-container > div > pf-button')";
	
	public String SearchInvestigationLandingText="return document.querySelector(\"body > app-root > app-navigation > pf-page-container > pf-grid > pf-card > app-search-investigation > pf-search-panel\").shadowRoot.querySelector(\"div.search-title-line > pf-text\")";
	
	
	
	//all search field
	
	@FindBy(xpath="(//pf-search-panel[@testselector=\"inv-search-panel\"])/pf-combobox")
	public List<WebElement> allSearchcomboboxField;
	
	@FindBy(xpath="(//pf-search-panel[@testselector=\"inv-search-panel\"])/pf-account-field")
	public List<WebElement> allSearchaccountField;
	
	@FindBy(xpath="(//pf-search-panel[@testselector=\"inv-search-panel\"])/pf-checkbox")
	public List<WebElement> allSearchcheckboxField;
	
	@FindBy(xpath="(//pf-search-panel[@testselector=\"inv-search-panel\"])/pf-text-field")
	public List<WebElement> allSearchpfTextField;
	
	public String allSearchpfcustomcontrol="(//pf-form-layout[@slot=\"additional-search-input\"])/pf-custom-control";
	
	public String processNameInput="return document.querySelector('pf-combobox').shadowRoot.querySelector('vaadin-combo-box').shadowRoot.querySelector('#input').shadowRoot.querySelector('#vaadin-text-field-input-3 > slot:nth-child(2) > input')";
	
	@FindBy(xpath = "(//pf-custom-control[@testselector='searchInv.amount_range']/pf-text-field)[1]")
	public WebElement AmountRangeFrom;
	
	@FindBy(xpath = "(//pf-custom-control[@testselector='searchInv.amount_range']/pf-text-field)[2]")
	public WebElement AmountRangeTo;
	
	@FindBy(xpath = "//pf-date-picker[@testselector=\"searchInv.nf_date_range_from\"]")
	public WebElement NFdateRangeFrom;
	
	@FindBy(xpath = "//pf-date-picker[@testselector=\"searchInv.nf_date_range_to\"]")
	public WebElement NFdateRangeTo;
	
	@FindBy(xpath = "//pf-text-field[@testselector='searchInv.nf_id_range_from']")
	public WebElement NFIdRangeFrom;
	
	@FindBy(xpath = "//pf-text-field[@testselector='searchInv.nf_id_range_to']")
	public WebElement NFIdRangeto;
	
	public String OutBICInput="return document.querySelector(\"#frmBookRoom > pf-search-panel > pf-form-layout:nth-child(2) > pf-text-field.ng-pristine.ng-valid.hydrated.inside-grid.col-md-6.ng-touched.hide-prefix\").shadowRoot.querySelector(\"vaadin-text-field\").shadowRoot.querySelector(\"#vaadin-text-field-input-47 > slot:nth-child(2) > input\")";
	
	@FindBy(xpath = "//pf-account-field[@testselector=\"searchInv.post_account_own_search\"]")
	public WebElement MoneyReceiver;
	
	
	//searchpop up locators
	public String PopUPtab="return document.querySelector(\"body > app-root > app-navigation > pf-page-container > pf-grid > pf-card > app-search-investigation > pf-status-dialog\").shadowRoot.querySelector(\"pf-dialog\").shadowRoot.querySelector(\"pf-fullscreen-container > pf-card\")";
	
	public String PopUptext="return document.querySelector(\"body > app-root > app-navigation > pf-page-container > pf-grid > pf-card > app-search-investigation > pf-status-dialog\").shadowRoot.querySelector(\"pf-dialog > div > pf-grid > pf-text\")";
	
	public String CloseButton="return document.querySelector(\"body > app-root > app-navigation > pf-page-container > pf-grid > pf-card > app-search-investigation > pf-status-dialog\").shadowRoot.querySelector(\"pf-dialog > pf-button\").shadowRoot.querySelector(\"vaadin-button\").shadowRoot.querySelector(\"#button\")";
	
	
	//search data locators
	@FindBy(xpath = "(//pf-button-row[@slot='search-result']/pf-text)[1]")
	public WebElement searchResults;
	
	@FindBy(xpath = "(//pf-button-row[@slot='search-result']/pf-text)[2]")
	public WebElement NoOfResults;

	public String touserInbox="return document.querySelector('pf-search-panel > pf-button-row:nth-child(33) > pf-button.hydrated.button-index-1')";
	public String unlockInverstigation="return document.querySelector('pf-search-panel > pf-button-row:nth-child(33) > pf-button.hydrated.button-index-2')";
	
	@FindBy(xpath="//*[@data-test-selector=\"moveNfToInboxId\"]")
	public WebElement toUserInbox;
	
	@FindBy(xpath="//*[@data-test-selector=\"unleashNfId\"]")
	public WebElement UnlockInverstigation;
	
	public String State="return document.querySelector(\"pf-search-panel > pf-combobox:nth-child(2)\").shadowRoot.querySelector(\"vaadin-combo-box\").shadowRoot.querySelector(\"#input\").shadowRoot.querySelector(\"#vaadin-text-field-input-4 > slot:nth-child(2) > input\")" ;
	
	@FindBy(xpath = "//pf-combobox[@testselector='searchInv.result_curr_search']")
	public WebElement Currency;
	
	public String TA="return document.querySelector(\"#frmBookRoom > pf-search-panel > pf-form-layout:nth-child(2) > pf-combobox.ng-pristine.ng-valid.hydrated.inside-grid.col-md-6.ng-touched\").shadowRoot.querySelector(\"vaadin-combo-box\").shadowRoot.querySelector(\"#input\").shadowRoot.querySelector(\"#vaadin-text-field-input-480 > slot:nth-child(2) > input\")";

	public String searchTableData="return document.querySelector('pf-table').shadowRoot.querySelectorAll('vaadin-grid-cell-content')";
	
	@FindBy(xpath = "//pf-icon-button[@test-selector=\"searchInv.search\"]")
	public WebElement downloadclick;
	
	@FindBy(xpath = "//pf-menu-item[@testselector=\"portal-header--toolbar--automatic--search\"]")
	public WebElement headersearchButton;
}	


