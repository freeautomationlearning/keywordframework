/**
 * 
 */
package com.sel.run;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.collect.ListMultimap;
import com.sel.utlis.*;
import com.sel.bussinessfunction.BussinessCustomFunction;

/**
 * @author CHIRAG
 *
 */
public class Runs {


	public static Keywords keywords;
	public static String runkeywords;
	public static Method method[];
	public static int result;
	private static int fail=0;
	private static long startingTime=0;

	public Runs() throws ParserConfigurationException, SAXException, IOException
	{

		keywords = new Keywords();
		method= keywords.getClass().getMethods();
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		int waitTime=0;
		Runs r = new Runs();
		startingTime = System.currentTimeMillis();
		//keywords.initaliseTestCaseReport();
		ListMultimap<String, Object> map = ReadXMLtoRun.readXML();
		String[] events = map.get("events").toString().split(",");
		String[] testData = map.get("testdata").toString().split(",");
		String[] testObject = map.get("objects").toString().split(",");
		String[] testWaitTime = map.get("waittime").toString().split(",");
		String event=null,data=null,object=null;
		String scriptEvent=null;
		int para=0;

		for(int i =0;i<=events.length-1;i++)
		{
			try {

				try {
					event = events[i].replace("[", "").replace("]", "").trim();
					data = testData[i].replace("[", "").replace("]", "").trim();
					object = testObject[i].replace("[", "").replace("]", "").trim();
					waitTime = Integer.parseInt(testWaitTime[i].replace("[", "").replace("]", "").trim());
				} catch (Exception e) {
					// TODO: handle exception
				}
				for(int j=0;j<=method.length-1;j++)
				{
					scriptEvent = method[j].getName().trim();
					if(event.equals(scriptEvent))
					{
						para = method[j].getParameterCount();
						if(para==2)
						{
							if(object.contains("NA"))
							{
								method[j].invoke(keywords,data,waitTime);
								break;
							}else
							{
								method[j].invoke(keywords,object,waitTime);
								break;
							}
						}else if(para==3)
						{
							method[j].invoke(keywords,object,data,waitTime);
							break;
						}else
						{
							method[j].invoke(keywords,waitTime);
							break;
						}
					}
					if(event.equals("CustomFunction"))
					{
						BussinessCustomFunction bussinessFunction = new BussinessCustomFunction();
						Method method[]= bussinessFunction.getClass().getMethods();
						try {
							for(int m=0;m<=method.length-1;m++)
							{
								String customFun = method[m].getName().trim();
								if(object.equals(customFun))
								{
									method[m].invoke(bussinessFunction);
									break;
								}
							}
							break;
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(result==1)
					{
						fail=fail+1;
						Keywords.test.addScreenCaptureFromPath(Keywords.captureScreenshot());
						new Keywords().CloseBrowser(waitTime);
						result=0;
						break;
					}
				}
			}
			catch (Exception e) {
				e.getMessage();
				fail=fail+1;
				Keywords.test.fail("Exception occured : "+e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(Keywords.captureScreenshot()).build());
				new Keywords().CloseBrowser(waitTime);
				break;
			}
		} 
		long endTime = System.currentTimeMillis();
		long runTime = endTime-startingTime;
		int minutes = (int) ((runTime / (1000*60)) % 60);
		int seconds = (int) (runTime / 1000) % 60 ;
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//Configuration//report.txt");
		int totalRun = ReadXMLtoRun.getExecutionTestCase().size();
		int pass = totalRun-fail;
		pw.write("Total Run : "+totalRun);
		pw.write("\n");
		pw.write("Total Fail : "+fail);
		pw.write("\n");
		pw.write("Total Pass : "+pass);
		pw.close();
		pw.flush();
		keywords.generateTestCaseReport();
		System.out.println("*********************************************************");
		System.out.println("Passed TestCases : "+pass);
		System.out.println("Failed TestCases : "+fail);
		System.out.println("Total  TestCases : "+totalRun);
		System.out.println("Total  ExecutionTime : "+minutes +" minutes : "+seconds+" seconds");
		System.out.println("*********************************************************");
	}
}
