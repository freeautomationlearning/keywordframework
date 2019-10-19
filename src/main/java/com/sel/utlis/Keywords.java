/**
 * 
 */
package com.sel.utlis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sel.run.Runs;
import com.sel.session.BaseClass;

/**
 * @author CHIRAG
 *
 */
public class Keywords {

	static public WebDriver driver;
	public static ExtentTest test;
	static int testCaseNumber = 0;
	
	public void StartTestCase(int time) 
	{
		try {
			ReadXMLtoRun obj = new ReadXMLtoRun();
			ArrayList<String> tc = obj.getExecutionTestCase();
			
			test = ReadXMLtoRun.extent.createTest(tc.get(testCaseNumber));
			testCaseNumber=testCaseNumber+1;
		} catch (Exception e) {
			
		}
	}
	
	public void OpenBrowser(String browser, int time)
	{
		//StartTestCase();
		BaseClass obj = new BaseClass();
		obj.invokeBrowser(browser);
		driver = obj.getDriver();
		test.log(Status.PASS, "Browser is Opened : "+browser);
	}
	
	public void GetUrl(String url,int time)
	{
		driver.get(url);
		test.log(Status.PASS, "URL is Opened : "+url);
		waitTime(time);
	}
	
	public void EnterText(String object,String data,int time) throws ParserConfigurationException, SAXException, IOException
	{
		driver.findElement(Helper.loctor(object)).sendKeys(data);
		test.log(Status.PASS, "Enter data : "+data +" into : "+object);
		waitTime(time);
	}
	
	public void Click(String loc,int time) throws ParserConfigurationException, SAXException, IOException
	{
		try {
			driver.findElement(Helper.loctor(loc)).click();
			Runs.result=0;
			test.log(Status.PASS, loc+" : Click is done successfully.");
		} catch (Exception e) {
			Runs.result=1;
			test.log(Status.FAIL, loc+" : Click is not done.");
		}
		waitTime(time);
	}
	
	public void ValtdateText()
	{
		System.out.println("ValtdateText");
	}
	
	public void CloseBrowser(int time)
	{
		String text = "Browser is closed";
		Markup m = MarkupHelper.createLabel(text, ExtentColor.BLUE);
		try {
			driver.quit();
			test.log(Status.PASS, m);
		} catch (Exception e) {
			// TODO: handle exception
		}
		waitTime(time);
	}
	
	public void generateTestCaseReport()
	{
		
		ReadXMLtoRun.extent.flush();
	}
	public void CheckElementExistance(String loc,String data,int times) throws InterruptedException
	{
		String dataSplit[] = data.split("\\*");
		int loop = Integer.parseInt(dataSplit[0]);
		int time = Integer.parseInt(dataSplit[1]);
		for(int i=1;i<=loop;i++)
		{
			try {
				driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
				driver.findElement(Helper.loctor(loc)).isDisplayed();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Runs.result=0;
				break;
			} catch (Exception e) {
				Thread.sleep(time);
				Runs.result=1;
			}
		}
		if(Runs.result==0)
		{
			test.log(Status.PASS, loc+" : Element is found successfully.");
		}else
		{
			test.log(Status.FAIL, loc+" : Element is not found successfully.");
		}
		waitTime(times);
	}
	
	public static String captureScreenshot()
	{
		String path = System.getProperty("user.dir");
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
		Date date = new Date();
		String name = sdf.format(date);
		String capScreenshotName = path+"\\Screenshots\\"+name+".PNG";
		// Code for take screenshot
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Code for copy screenshot to specific location
		try {
			FileUtils.copyFile(file, new File(capScreenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return capScreenshotName;
	}
	
	public void waitTime(int waitTime)
	{
		try {
			Thread.sleep(waitTime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
