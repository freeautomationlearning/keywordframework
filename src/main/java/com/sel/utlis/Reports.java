/**
 * 
 */
package com.sel.utlis;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author CHIRAG
 *
 */
public class Reports {


	private static ExtentReports extent;

	public static ExtentReports getInstance(String fileName) {
		if (extent == null)
			createInstance(fileName);

		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		// make the charts visible on report open
		htmlReporter.config().setChartVisibilityOnOpen(true);

		// report title
		htmlReporter.config().setDocumentTitle("Chirag - ExtentReports");

		// encoding, default = UTF-8
		htmlReporter.config().setEncoding("UTF-8");

		// protocol (http, https)
		htmlReporter.config().setProtocol(Protocol.HTTPS);

		// report or build name
		htmlReporter.config().setReportName("Automation Test Report");

		// chart location - top, bottom
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);

		// theme - standard, dark
		htmlReporter.config().setTheme(Theme.STANDARD);

		// set timeStamp format
		htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");

		// add custom css
		htmlReporter.config().setCSS("css-string");

		// add custom javascript
		htmlReporter.config().setJS("js-string");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", "win10");

		return extent;
	}
}
