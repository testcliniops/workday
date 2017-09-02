package work;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WorkAuto extends Reusable{
	static WebDriver dr;

	//public static void main(String[] args) throws InterruptedException, IOException {

	//@Test
	public  void SampleTest() throws IOException, InterruptedException {


		try{
			//select_browser("FF");
			dr = new FirefoxDriver();
			dr.get("https://wd5-impl.workday.com/wday/authgwy/linkedin5/login.htmld");
			dr.manage().window().maximize();

			dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			//dr.findElement(By.xpath("//input[@type='text']")).sendKeys("dbhavsar");
			WebElement unObj = dr.findElement(By.xpath("//input[@type='text']"));
			enterText(unObj, "dbhavsar", "userName object",dr);
			validateMSG(unObj, "dbhavsar", "userName object",dr);
			//dr.findElement(By.xpath("//input[@type='password']")).sendKeys("Workday123abc");
			WebElement pwObj = dr.findElement(By.xpath("//input[@type='password']"));
			enterText(pwObj, "Workday123ab", "Password object",dr);
			System.out.println("entered password");
			validateMSG(pwObj, "Workday123ab", "Password object",dr);
			//dr.findElement(By.xpath(".//*[@id='container']/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/button")).click();
			WebElement signInObj = dr.findElement(By.xpath(".//*[@id='container']/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/button"));
			ButtonClick(signInObj, "signin Button",dr);
			Thread.sleep(5000);
			System.out.println("clicked on signin ....");

			WebElement expenseObj = dr.findElement(By.xpath(".//span[contains(@aria-label,'Expenses')]"));
			if(expenseObj.isDisplayed())
			{
				//expenseObj.click();
				ButtonClick(expenseObj, "Expense object",dr);
			}

			System.out.println("selected expenses icon...");
			Thread.sleep(5000);
			//dr.findElement(By.xpath("//a[contains(text(),'Create Expense Report')]")).click();
			WebElement expenseReportObj = dr.findElement(By.xpath("//a[contains(text(),'Create Expense Report')]"));
			ButtonClick(expenseReportObj, "Expense Report Object",dr);
			System.out.println("clicked on create expense report");

			WebElement checkboxObj = dr.findElement(By.xpath(".//input[contains(@type,'checkbox')]"));
			if(checkboxObj.isSelected())
			{
				System.out.println("checkbox is enabled....");
			}
			else 
			{
				//checkboxObj.click();
				ButtonClick(checkboxObj, "Check box object",dr);
				System.out.println("checkbox is not enabled....so performed click function");
			}

			//dr.findElement(By.xpath(".//*[@id='workdayApplicationFrame']/div[1]/div[2]/section/div[2]/div[1]/div[1]/button[1]")).click();
			WebElement okObj = dr.findElement(By.xpath(".//*[@id='workdayApplicationFrame']/div[1]/div[2]/section/div[2]/div[1]/div[1]/button[1]"));
			ButtonClick(okObj, "Ok button object",dr);
			System.out.println("clicked on ok button...");

			//dr.findElement(By.xpath(".//textarea[contains(@aria-label,'Process Comment')]")).sendKeys("MemoBill");
			WebElement memoTextBoxObj = dr.findElement(By.xpath(".//textarea[contains(@aria-label,'Process Comment')]"));
			enterText(memoTextBoxObj, "MemoBill", "MemoTextbox Object",dr);
			validateMSG(memoTextBoxObj, "Memo", "MemotextBox object", dr);
			System.out.println("entered memobill into textbox");

			Thread.sleep(5000);

			WebElement expenseMenuObj = dr.findElement(By.xpath("//li[2]/div[2]/div/span/div"));
			if(expenseMenuObj.isDisplayed())
			{
				//expenseMenuObj.click();
				ButtonClick(expenseMenuObj, "expense Menu object",dr);
				//System.out.println("clicked on expense menu");
			}
			else
			{
				System.out.println("element is not found....");
			}

			Thread.sleep(5000);

			WebElement spendCatObj = dr.findElement(By.xpath(".//div[contains(@aria-label,'Submenu By Spend Category')]/div/div/div"));

			if(spendCatObj.isDisplayed())
			{
				//spendCatObj.click();
				ButtonClick(spendCatObj, "By spend category object",dr);
				System.out.println("clicked on \"By spnd category\"");
			}
			else
			{
				System.out.println("spend category element is not displayed....");
			}


			WebElement mobilePhonesObj = dr.findElement(By.xpath("(.//div[@id='promptOption'])[32]"));
			if(mobilePhonesObj.isDisplayed())
			{
				//mobilePhonesObj.click();
				ButtonClick(mobilePhonesObj, "Clicked on mobilephones objects",dr);
				System.out.println("clicked on mobile phones....");
			}
			else
			{
				System.out.println("mobile phones object is not found");
			}

			WebElement allowanceObj = dr.findElement(By.xpath(".//div[@data-automation-checked='Not Checked']"));
			//allowanceObj.click();
			ButtonClick(allowanceObj, "Allowance object",dr);
			System.out.println("Mobile allowance is selected...");


			WebElement memoObj = dr.findElement(By.xpath(".//input[@role='textbox']"));
			if(memoObj.isDisplayed())
			{
				//memoObj.sendKeys("Inside Memo");
				enterText(memoObj, "Inside Memo", "Memo object",dr);
				System.out.println("Entered memo after mome allowance");
			}
			else 
			{
				System.out.println("memo obj is not displayed");
			}

			//Enter Amount

			//WebElement totalAmt = dr.findElement(By.xpath(".//*[@id='56$25977--uid35-input']"));
			WebElement amountObj = dr.findElement(By.xpath("//li[5]/div[2]/div/input"));
			amountObj.sendKeys(Keys.BACK_SPACE);
			amountObj.sendKeys(Keys.BACK_SPACE);
			amountObj.sendKeys(Keys.BACK_SPACE);
			amountObj.sendKeys(Keys.BACK_SPACE);
			enterText(amountObj, "10.00", "Amount entered into amount text box",dr);

			System.out.println("Entered 10$ ");

			//dr.findElement(By.xpath(".//div[@style='visibility: visible;']/input[@aria-required='true']")).sendKeys("xxx merchant");
			WebElement merchantObj = dr.findElement(By.xpath(".//div[@style='visibility: visible;']/input[@aria-required='true']"));
			enterText(merchantObj, "xxx Mechant", "merchant object name",dr);
			System.out.println("entered merchant details");

			//Attach file
			System.out.println("Attaching a file.....");
			String filename = "C:/kiranmai/Test2.txt";
			File file = new File(filename);
			String absolutePath_File = file.getAbsolutePath();
			System.out.println("Absolute path of the file is : "+absolutePath_File);
			dr.findElement(By.xpath("//div[@id='wd-FileUploadAwesome-6$4735']/form/div/input[4]")).sendKeys(absolutePath_File);
			//dr.findElement(By.xpath(".//button[@title='Select files']")).sendKeys(absolutePath_File);
			System.out.println("File got attached....");
			
			/*
			 * 
			if file is attaching by using AutoIT 
			follow steps (prior to next line)
			dr.findElement(By.xpath("//div[@id='wd-FileUploadAwesome-6$4735']/form/div/input[4]")).click();
			ControlFocus("File Upload","","Edit1")
			ControlSetText("File Upload","","Edit1","C:\Users\Sreeram\Desktop\selected pics\image.JPG")
			ControlClick("File Upload","","Button1")
			Runtime.getRuntime().exec("C:/kiranmai/AutiItWorkday/AutoitWorkDay.exe");
			
			*/

			Thread.sleep(5000);

			//Comment TextBox
			//dr.findElement(By.xpath("//li/div/div[2]/div/ul/li/div[2]/div/input")).sendKeys("Report File");
			WebElement commentTextboxObj = dr.findElement(By.xpath("//li/div/div[2]/div/ul/li/div[2]/div/input"));
			enterText(commentTextboxObj, "ReportFile", "comment textbox object",dr);

			dr.findElement(By.xpath(".//*[@id='workdayApplicationFrame']/div[1]/div[2]/section/div[2]/div[1]/div[1]/button[1]")).click();
			WebElement submitBtnObj = dr.findElement(By.xpath(".//*[@id='workdayApplicationFrame']/div[1]/div[2]/section/div[2]/div[1]/div[1]/button[1]"));
			ButtonClick(submitBtnObj, "Submit button object",dr);
			System.out.println("clicked on submit button...");
			WebElement ele = dr.findElement(By.xpath(".//*[@id='wd-PageHeader-NO_METADATA_ID-uid1'] //*[contains(text(), 'Expense Report: Darshankumar Bhavsar (201664)')]"));

			String msg = ele.getText();
			System.out.println(msg);

			//dr.findElement(By.xpath(".//*[@id='wd-Worker-NO_METADATA_ID']/img")).click();
			WebElement profileObj = dr.findElement(By.xpath(".//*[@id='wd-Worker-NO_METADATA_ID']/img"));
			ButtonClick(profileObj, "Profile object",dr);

			//click on signout
			//dr.findElement(By.xpath(".//div[6]/div[3]/span")).click();
			WebElement signOutObj = dr.findElement(By.xpath(".//div[6]/div[3]/span"));
			dr.close();

		}

		catch(Exception e)
		{
			screenshot(dr);
			System.out.println("screen shot taken...");
		}

	}

	public static void select_browser(String browser){
		if (browser.equalsIgnoreCase("FF")) {

			System.setProperty("", "");
			dr = new FirefoxDriver();
			System.out.println("FireFox is launched... ");


		}
		if (browser.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			options.addArguments("--disable-infobars");
			dr = new ChromeDriver(options);
			//driver = new ChromeDriver();
			System.out.println("chrome browser is launched... ");

		}

		if (browser.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", "C:/Drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreZoomSetting", true);
			dr= new InternetExplorerDriver(capabilities);
			//driver = new InternetExplorerDriver();
			dr.manage().window().maximize();
			System.out.println("IE browser is launched... ");

		}
	}
}





