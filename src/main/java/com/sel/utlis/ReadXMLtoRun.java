/**
 * 
 */
package com.sel.utlis;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
//import com.sel.bussinessfunction.BussinessCustomFunction;

/**
 * @author CHIRAG
 *
 */
public class ReadXMLtoRun {


	public static ExtentReports extent;


	public static ListMultimap<String, Object> readXML() throws ParserConfigurationException, SAXException, IOException
	{
		initalizeReport();
		ArrayList<String> tc = getExecutionTestCase();
		ListMultimap<String, Object> map = ArrayListMultimap.create();
		for(int k =0;k<=tc.size()-1;k++)
		{
			File xmlFile = new File(System.getProperty("user.dir")+"//TestScripts//"+tc.get(k)+".xml");
			System.out.println("************ TC NAME : "+tc.get(k)+" ************");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);


			NodeList nList = doc.getElementsByTagName("categories");
			for (int i = 0; i < nList.getLength(); i++) {
				//System.out.println("Processing element " + (i+1) + "/" + nList.getLength());
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					NodeList prods = element.getElementsByTagName("categoryName");
					for (int j = 0; j < prods.getLength(); j++) {
						Node prod = prods.item(j);
						if (prod.getNodeType() == Node.ELEMENT_NODE) {
							Element product = (Element) prod;
							String event = product.getElementsByTagName("Events").item(0).getTextContent();
							String compName=null;
							if(event.contains("Component"))
							{
								compName = product.getElementsByTagName("Events").item(0).getTextContent().split("-")[1];
								map.put("events", readCompXML(compName).get("events"));
								map.put("objects", readCompXML(compName).get("objects"));
								map.put("testdata", readCompXML(compName).get("testdata"));
								map.put("waittime", readCompXML(compName).get("waittime"));
							} else{
								String eve = product.getElementsByTagName("Events").item(0).getTextContent();
								map.put("events", eve);
								String object = product.getElementsByTagName("Objects").item(0).getTextContent();
								map.put("objects", object);
								String testdata = product.getElementsByTagName("TestData").item(0).getTextContent();
								map.put("testdata", testdata);
								String res = product.getElementsByTagName("WaitTime").item(0).getTextContent();
								map.put("waittime", res);
							}
						}
					}
				}
			}
		}
		return map;
	}

	public static ArrayList<String> getExecutionTestCase() throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<String> tc = new ArrayList<String>();
		File xmlFile = new File(System.getProperty("user.dir")+"//Configuration//ExecutionDetails.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);

		ListMultimap<String, Object> map = ArrayListMultimap.create();
		NodeList nList = doc.getElementsByTagName("categories");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList prods = element.getElementsByTagName("categoryName");
				for (int j = 0; j < prods.getLength(); j++) {
					Node prod = prods.item(j);
					if (prod.getNodeType() == Node.ELEMENT_NODE) {
						Element product = (Element) prod;
						String prodName = product.getElementsByTagName("TESTCASE_NAME").item(0).getTextContent();
						String prodCode = product.getElementsByTagName("RUN").item(0).getTextContent();
						if(prodCode.equalsIgnoreCase("Y"))
						{
							tc.add(prodName);
						}
					}
				}
			}
		}
		return tc;
	}

	public static HashMap<String,String> getObjects() throws ParserConfigurationException, SAXException, IOException
	{
		HashMap<String,String> objMap = new HashMap<String,String>();
		File xmlFile = new File(System.getProperty("user.dir")+"//Configuration//ObjectsDetails.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);

		ListMultimap<String, Object> map = ArrayListMultimap.create();
		NodeList nList = doc.getElementsByTagName("categories");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList prods = element.getElementsByTagName("categoryName");
				for (int j = 0; j < prods.getLength(); j++) {
					Node prod = prods.item(j);
					if (prod.getNodeType() == Node.ELEMENT_NODE) {
						Element product = (Element) prod;
						String prodName = product.getElementsByTagName("ObjectName").item(0).getTextContent();
						String prodCode = product.getElementsByTagName("ObjectValue").item(0).getTextContent();
						objMap.put(prodName, prodCode);
					}
				}
			}
		}
		return objMap;
	}

	public static ListMultimap<String, Object> readCompXML(String compName) throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<String> tc = getExecutionTestCase();
		ListMultimap<String, Object> map = ArrayListMultimap.create();
		File xmlFile = new File(System.getProperty("user.dir")+"//Configuration//ComponentsDetails.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);


		NodeList nList = doc.getElementsByTagName("categories");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList prods = element.getElementsByTagName("categoryName");
				for (int j = 0; j < prods.getLength(); j++) {
					Node prod = prods.item(j);
					if (prod.getNodeType() == Node.ELEMENT_NODE) {
						Element product = (Element) prod;
						String objectNamewith = product.getElementsByTagName("Objects").item(0).getTextContent();
						String objectName =null;
						if(objectNamewith.contains("-"))
						{
							objectName = product.getElementsByTagName("Objects").item(0).getTextContent().split("-")[0];
						}else
						{
							objectName = product.getElementsByTagName("Objects").item(0).getTextContent();
						}
						String object =null;
						if(objectName.equalsIgnoreCase(compName))
						{
							String event = product.getElementsByTagName("Events").item(0).getTextContent();
							map.put("events", event);
							if(objectNamewith.contains("-"))
							{
								object = product.getElementsByTagName("Objects").item(0).getTextContent().split("-")[1];
							}else
							{
								object = product.getElementsByTagName("Objects").item(0).getTextContent();
							}

							map.put("objects", object);
							String testdata = product.getElementsByTagName("TestData").item(0).getTextContent();
							map.put("testdata", testdata);
							String res = product.getElementsByTagName("WaitTime").item(0).getTextContent();
							map.put("waittime", res);
						}

					}
				}
			}
		}
		return map;
	}

	public static void initalizeReport()
	{
		extent = Reports.createInstance("extent.html");
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		readXML();
		getObjects();
	}

}
