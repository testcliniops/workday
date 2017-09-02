package work;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusable {

	//static WebDriver dr ;  
	static String htmlname;
	static Date cur_dt = null;
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String exeStatus = "True";
	static int report;
	static int j = 1;
	static String str_time;
	
	/* 
	 * Name of the Method: enterText
	 * Brief description : Enter text into text box field
	 * Arguments: obj --> Webelement Object, textval --> Text to be entered, objName --> name of hte object
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName,WebDriver dr) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass","entertext",textVal+" is entered in "+objName,dr);
		}else{
			Update_Report("Fail","entertext",objName+" field is not displayed,please check application",dr);

		}

	}


	//Name of the method:ButtonClick
	//Brief description:used to click the button
	//arguments:obj->WebElement,objname->name of the object
	//created by:Automation team
	//creation date:12/15/2016
	//modified date:12/15/2016
	public static void ButtonClick(WebElement obj,String objname,WebDriver dr) throws IOException{
		if(obj.isDisplayed())
		{
			obj.click();
			Update_Report("Pass","ButtonClick",objname+" is clicked",dr);
		}
		else{
			Update_Report("Fail","ButtonClick",objname+" is not displayed",dr);
		}	

	}
	/* Name Of the Method: validateMSG
	 * Description: Validate message displayed
	 * Arguments: objXpath  --> Xpath of the object, expectedMsg --> expected message,  objName --> Name of the object
	 * Created by: Let's restart Team
	 * Creation time:Sept 10 2015
	 * Last Modified: Setp 10 2015
	 * */
	 public static void validateMSG (WebElement obj, String expectedMsg, String objName,WebDriver dr) throws IOException{
		 
	 if (obj.isDisplayed()){
	 String actualMsg = obj.getText();
	 if (expectedMsg.equals(actualMsg)){
	 Update_Report("Pass", "validateMSG", "Actual message is matching with expected message " + expectedMsg,dr);
	 System.out.println("Pass: Actual message is matching with expected message " + expectedMsg);
	 }else{		 
	 Update_Report("Fail", "validateMSG",  " did not match with expected message " + expectedMsg,dr);
	 System.out.println("Fail: Actual message " + actualMsg+ " did not match with expected message " + expectedMsg);
	 }
	 }else{
	 int resultFlag = 1;
	 System.out.println("Fail: " +objName + " field does not exist,please check the application");
	     }
	 }

	/*
	 * Name of the Method: readSheet
	 * Brief description : read excel sheet data
	 * Arguments: datatable --> report path,sheetname:sheet name to access in the excel file
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */

	public static String[][] readSheet(String dataTable, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File xlFile = new File(dataTable);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);


		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount =  sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();//Row count starts from '0' in excel

		System.out.println("Total row = " + iRowCount + " total col = " + iColCount);

		String[][] xlData = new String[iRowCount][iColCount];

		for(int i =0; i<iRowCount;i++){
			for(int j = 0; j <iColCount;j++){
				xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue().trim();
			}

		}

		return xlData;
	}
	/*
	 * Name of the Method: startReport
	 * Brief description : Creates HTML report template
	 * Arguments: scriptname:test script name to run,ReportsPath:HTML report path to create,browserName:browser the script is running
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */
//	public static void startReport(String scriptName, String ReportsPath,String browserName) throws IOException{
//
//		String strResultPath = null;
//		String testScriptName =scriptName;
//
//		cur_dt = new Date(); 
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//		String strTimeStamp = dateFormat.format(cur_dt);
//
//		if (ReportsPath == "") { 
//
//			ReportsPath = "C:\\";
//		}
//
//		if (ReportsPath.endsWith("\\")) { 
//			ReportsPath = ReportsPath + "\\";
//		}
//
//		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
//		File f = new File(strResultPath);
//		f.mkdirs();
//		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
//				+ ".html";
//
//		bw = new BufferedWriter(new FileWriter(htmlname));
//
//		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
//		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
//		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
//				+ browserName + "</B></FONT></TD></TR>");
//		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
//		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
//				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
//				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
//				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
//				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");
//
//
//	}
//	/*
//	 * Name of the Method: Update_Report
//	 * Brief description : Updates HTML report with test results
//	 * Arguments: Res_type:holds the response of test script,Action:Action performed,result:contains test results
//	 * Created by: Automation team
//	 * Creation date : July 17 2017
//	 * last modified:  July 17 2017
//	 */
//
//	
//	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
//		String str_time;
//		Date exec_time = new Date();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//		str_time = dateFormat.format(exec_time);
//		if (Res_type.startsWith("Pass")) {
//			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
//					+ (j++)
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
//					+Action
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
//					+ str_time
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
//					+ "Passed"
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
//					+ result + "</FONT></TD></TR>");
//
//		} else if (Res_type.startsWith("Fail")) {
//			
//			String ssPath = "C:/Users/Sreeram/Desktop/WorkDayScreenShots/ss.png";
//			String  ss1Path = "C:/Users/Sreeram/Desktop/WorkDayScreenShots/"+ Action+str_time+".jpg";
//			screenShot(ssPath);
//			exeStatus = "Failed";
//			report = 1;
//			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
//					+ (j++)
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
//					+Action
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
//					+ str_time
//					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
//					+ "<a href= "
//					//+ htmlname
//					+ssPath
//					+ "  style=\"color: #FF0000\"> Failed </a>"
//
//						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
//						+ result + "</FONT></TD></TR>");
//
//
//		} 
//	}
//	
	
//	public static void screenShot() throws IOException{
//		
//		String fileName = "C:/Users/Sreeram/Desktop/WorkDayScreenShots/ss.png";
//		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File(fileName));
//		
//		
//		
//		//driver.quit();
////		 File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
////		 FileUtils.copyFile(screenshotFile, new File("C:/Users/Sreeram/Desktop/WorkDayScreenShots/ss.png"));
//		}
	
	
	/*
	 * Name of the Method: startReport
	 * Brief description : Creates HTML report template
	 * Arguments: scriptname:test script name to run,ReportsPath:HTML report path to create,browserName:browser the script is running
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */
	
	
	public static void startReport(String scriptName, String ReportsPath,String browserName) throws IOException{

		String strResultPath = null;
		String testScriptName =scriptName;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ browserName + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}
	/*
	 * Name of the Method: Update_Report
	 * Brief description : Updates HTML report with test results
	 * Arguments: Res_type:holds the response of test script,Action:Action performed,result:contains test results
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */

	
	public static void Update_Report(String Res_type,String Action, String result,WebDriver dr) throws IOException {
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			//To generate report in only single file
			
			String ss1Path= screenshot(dr);
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ ss1Path
					
					+ "  style=\"color: #FF0000\"> Failed </a>"

						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
						+ result + "</FONT></TD></TR>");


		} 
	}
	
	public static String screenshot(WebDriver dr) throws IOException{
		
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		//String fileName = "C:/Users/Sreeram/Desktop/WorkDayScreenShots/ss.png";
		String  ss1Path = "C:/Users/Sreeram/Desktop/WorkDayScreenShots/"+ str_time+".png";
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ss1Path));
		return ss1Path;
	}
	

}


