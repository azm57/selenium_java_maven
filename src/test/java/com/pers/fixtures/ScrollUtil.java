package com.pers.fixtures;

import com.pers.utils.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

class ScrollUtil {
	public static WebElement we;

	public static void ScrollToEnd() {
		JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public static void ScrollTillElementFound(WebElement webele) throws InterruptedException {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) Browser.getDriver();
			jse.executeScript("arguments[0].scrollIntoView(true)", webele);
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("WebElement not found in Page");
		}
	}

}