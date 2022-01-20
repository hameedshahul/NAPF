package ch.post.pf.nf.ta.baseutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.testng.annotations.DataProvider;

public class ReadData {
	
	static Logger log =Log4jUtil.loadLogger(ReadData.class);
	
	public static String getProperty(String keyName) throws Exception, IOException {
		File propertyFile = new File("./src/main/resources/data/config.properties");
		Properties prod = new Properties();
		prod.load(new FileInputStream(propertyFile));
		String data= prod.getProperty(keyName);
		log.info("data read from config file is :" +keyName+": " +data);
		return data;
	}
	
	public static String getTestProperty(String keyName) throws Exception, IOException {
		File propertyFile = new File("./src/test/resources/TestData/TestConfig.properties");
		Properties prod = new Properties();
		prod.load(new FileInputStream(propertyFile));
		String data= prod.getProperty(keyName);
		log.info("data read from Test config file is :" +keyName+": " +data);
		return data;
	}
	
	@DataProvider(name = "getDataReader", parallel = false)
	public Object[][] getCSVData() throws Exception {
		Object[][] obj = readDataFromExcel("PA TA)");
		return obj;
	}
	
	@DataProvider(name = "getLaungageDataReader", parallel = false)
	public Object[][] getLanguageData() throws Exception {
		Object[][] obj = readDataFromExcel("GroupMenu");
		return obj;
		
	}
	
	@DataProvider(name = "getNotesDataReader", parallel = false)
	public Object[][] getNotesData() throws Exception {
		Object[][] obj = readDataFromExcel("Notes");
		return obj;
	}
	
	@DataProvider(name = "getActivitesDataReader", parallel = false)
	public Object[][] getActivitesData() throws Exception {
		Object[][] obj = readDataFromExcel("Activites");
		return obj;
	}
	
	@DataProvider(name = "getUserInboxGroupNavigateDataReader", parallel = false)
	public Object[][] UserInboxGroupNavigate() throws Exception {
		Object[][] obj = readDataFromExcel("User Inbox Group Navigate");
		return obj;
	}
	
	@DataProvider(name = "getUserInboxGroupDataReader", parallel = false)
	public Object[][] UserInboxGroup() throws Exception {
		Object[][] obj = readDataFromExcel("User Inbox Group");
		return obj;
	}
	
	@DataProvider(name = "getSearchInvestigation", parallel = false)
	public Object[][] SearchInvestigation() throws Exception {
		Object[][] obj = readDataFromExcel("SearchInvestigation");
		return obj;
	}
	
	
	
	public static Object[][] readDataFromExcel(String sheetName) throws Exception{
		Object[][] obj=null;
		List<Map<String, String>> li = new ArrayList<Map<String,String>>();
		int objincr=0;
		File f = new File("./src/test/resources/TestData/Test_data_General_Tab.xlsx");
		FileInputStream fi = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fi);
		Sheet sh = wb.getSheet(sheetName);
		log.info("Reading data from a sheet: "+sheetName);
		int physicalNumberOfRows = sh.getPhysicalNumberOfRows();
		Row Header = sh.getRow(0);
		for (int i = 1; i < physicalNumberOfRows; i++) {
			Row row = sh.getRow(i);
			int physicalNumberOfCells = row.getPhysicalNumberOfCells();
			Map<String, String> ma = new HashMap<String, String>();
			for (int j = 0; j < physicalNumberOfCells; j++) {
				Cell cell = row.getCell(j);
				@SuppressWarnings("deprecation")
				int cellType = cell.getCellType();
				if (cellType==1) {
					RichTextString stringCellValue = cell.getRichStringCellValue();
					RichTextString headerName = Header.getCell(j).getRichStringCellValue();
					ma.put(headerName.toString().trim(), stringCellValue.toString().trim());
				}
				else if (cellType==0) {
					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
						String format = s.format(dateCellValue);
						String headerName = Header.getCell(j).getStringCellValue().trim();
						ma.put(headerName, format);
					}
					else {
					double numericCellValue = cell.getNumericCellValue();
					
					String valueOf = String.valueOf(numericCellValue);
					String headerName = Header.getCell(j).getStringCellValue().trim();
					ma.put(headerName, valueOf);
					}
				}
			}
			li.add(ma);
	}
		if (li.size() != 0) {
			obj = new Object[li.size()][1];
			for (Map<String, String> entry : li) {
				obj[objincr][0] = entry;
				objincr++;
			}
		}
		return obj;
	}

	public static Map<String, String> readDataField(String sheetName) throws Exception{
		File f = new File("./src/test/resources/TestData/Test_data_General_Tab.xlsx");
		FileInputStream fi = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fi);
		Sheet sh = wb.getSheet(sheetName);
		log.info("Reading data from a sheet: "+sheetName);
		int physicalNumberOfRows = sh.getPhysicalNumberOfRows();
		Row Header = sh.getRow(0);
		Map<String, String> ma = new HashMap<String, String>();
		for (int i = 1; i < physicalNumberOfRows; i++) {
			Row row = sh.getRow(i);
			int physicalNumberOfCells = row.getPhysicalNumberOfCells();
			for (int j = 0; j < physicalNumberOfCells; j++) {
				Cell cell = row.getCell(j);
				int cellType = cell.getCellType();
				if (cellType==1) {
					RichTextString stringCellValue = cell.getRichStringCellValue();
					RichTextString headerName = Header.getCell(j).getRichStringCellValue();
					ma.put(headerName.toString().trim() , stringCellValue.toString().trim());
				}
				else if (cellType==0) {
					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
						String format = s.format(dateCellValue);
						String headerName = Header.getCell(j).getStringCellValue().trim();
						ma.put(headerName, format);
					}
					else {
					double numericCellValue = cell.getNumericCellValue();
					long l = (long)numericCellValue;
					String valueOf = String.valueOf(l);
					String headerName = Header.getCell(j).getStringCellValue().trim();
					ma.put(headerName, valueOf);
					}
				}
			}
	}
		return ma;
	}
	
	
	
}
