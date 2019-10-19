/**
 * 
 */
package com.sel.bussinessfunction;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.sel.utlis.Helper;
import com.sel.utlis.Keywords;

/**
 * @author CHIRAG
 *
 */
public class BussinessCustomFunction {

	Keywords keywords = new Keywords();

	public void SearchFunction() throws ParserConfigurationException, SAXException, IOException
	{
		keywords.EnterText("Search_Textfield", "Chirag",1);
		System.out.println("*********************SearchFunction********************");

	}

	public void TestChirag() throws ParserConfigurationException, SAXException, IOException, InterruptedException
	{
		keywords.Click("Search_Gmail",2);
		Thread.sleep(1000);
		System.out.println("*********************TestChirag********************");
	}

	public void ClearSearch() throws ParserConfigurationException, SAXException, IOException, InterruptedException
	{
		Thread.sleep(1000);
		Keywords.driver.findElement(Helper.loctor("Search_Textfield")).clear();
		Thread.sleep(1000);
		System.out.println("*********************ClearSearch********************");
	}
}