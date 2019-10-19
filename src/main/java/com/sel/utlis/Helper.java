/**
 * 
 */
package com.sel.utlis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.xml.sax.SAXException;

/**
 * @author CHIRAG
 *
 */
public class Helper {

	public static By loctor(String loc) throws ParserConfigurationException, SAXException, IOException
	{
		HashMap<String, String> objMap = ReadXMLtoRun.getObjects();
		Set<String> key = objMap.keySet();
		String locValue=null;
		String locName=null;
		By by=null;
		for(String s : key)
		{
			if(s.equalsIgnoreCase(loc))
			{
				locValue = objMap.get(s).split(",")[1];
				locName = objMap.get(s).split(",")[0];
				by = getLocator(locName, locValue);
				break;
			}			
		}
		return by;
	}
	
	public static By getLocator(String locName,String locValue)
	{
		By by=null;
		if(locName.equalsIgnoreCase("id"))
		{
			by = By.id(locName);
		}else if(locName.equalsIgnoreCase("css"))
		{
			by = By.cssSelector(locValue);
		}else if(locName.equalsIgnoreCase("class"))
		{
			by = By.className(locValue);
		}else if(locName.equalsIgnoreCase("linkText"))
		{
			by = By.linkText(locValue);
		}else if(locName.equalsIgnoreCase("name"))
		{
			by = By.name(locValue);
		}else if(locName.equalsIgnoreCase("tagName"))
		{
			by = By.tagName(locValue);
		}else if(locName.equalsIgnoreCase("xpath"))
		{
			by = By.xpath(locValue);
		}else if(locName.equalsIgnoreCase("parLinkText"))
		{
			by = By.partialLinkText(locValue);
		}
		return by;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		loctor("Search_Gmail");
	}
}
