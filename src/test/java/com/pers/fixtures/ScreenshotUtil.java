package com.pers.fixtures;

import com.pers.utils.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotUtil {
	static File scrFile;
	static String destpath;// = "D:\\SeleniumJavaFramework\\Screenshots\\";
	static String timeStamp;// = new
							// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	// String methodname = result.getName();
	static File destFile; // = new File(destpath + timeStamp + ".png");

	public static void getScreenshot() {
		destFile = getPathToStoreScreenshots();
		System.out.println(destFile);

		try {
			scrFile = ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, destFile);
			System.out.println("Screenshot Captured...");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static File getPathToStoreScreenshots() {
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		destpath = System.getProperty("user.dir");
		destFile = new File(destpath +"/Screenshots/"+ timeStamp + ".png");
		return destFile;
	}
}
