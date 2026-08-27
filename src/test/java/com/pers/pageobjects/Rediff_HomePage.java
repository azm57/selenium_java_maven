package com.pers.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pers.fixtures.ActionsUtil;
import com.pers.fixtures.WebTableUtil;
import com.pers.utils.Browser;
import com.pers.utils.CustomeExceptionUtil;
import com.pers.utils.ExcelUtil;
import com.pers.utils.report.Log;

public class Rediff_HomePage {
	public String strCategory;
	public String strsubcat;
	public String strCatgoryXpath;
	public String strSubCatXpath;

	WebDriver driver = Browser.getDriver();

	@FindBy(xpath = "//*[@name='username']")
	WebElement txtUsername;

	@FindBy(xpath = "//*[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//*[@type='submit']")
	List<WebElement> btnSubmitt;

	@FindBy(linkText = "My Watchlist")
	List<WebElement> lnkWatchlist;

	@FindBy(id = "stocks")
	List<WebElement> tblWatchlist;

	@FindBy(linkText = "Sign Out")
	WebElement lnkSignout;

	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement lblDashboard;

	public void Login() throws Exception {

		Set<String> s = driver.getWindowHandles();
		System.out.println(s);
		Iterator<String> I1 = s.iterator();
		driver.switchTo().window(I1.next());
		System.out.println(driver.getTitle());
		// Enter Username
		if (!ActionsUtil.gFunc_SendText(txtUsername, ExcelUtil.objTestDataMap.get("Login_User"))) {
			throw new CustomeExceptionUtil("Username field not found");
		} else {
			Log.info("Username field found and entered text");
		}

		// Enter Password
		if (!ActionsUtil.gFunc_SendText(txtPassword, ExcelUtil.objTestDataMap.get("Login_Password"))) {
			throw new CustomeExceptionUtil("Password Field not found");
		} else {
			Log.info("Password entered successfully");
		}

		// Click on Sign-in button
		if (!ActionsUtil.gFunc_ClickElement(btnSubmitt.get(0))) {
			throw new CustomeExceptionUtil("SignIn Button not found");
		} else {
			Log.info("SignIn button found and clicked successfully");
		}

		// Verify the login successful
		if (!ActionsUtil.bFunc_WaitForObjectExistance(lblDashboard)) {
			throw new CustomeExceptionUtil("SignIn failed");
		} else {
			Log.info("SignIn Successfull");
		}
	}

	public void NavToWatchlistAndVerify() throws Exception {

		if (!ActionsUtil.gFunc_ClickElement(lnkWatchlist.get(0))) {
			throw new CustomeExceptionUtil("Watchlist link not found");
		} else {
			Log.info("Watchlist Link found and clicked successfully");
		}

		Set<String> s = driver.getWindowHandles();
		System.out.println(s);
		Iterator<String> I1 = s.iterator();
		driver.switchTo().window(I1.next());
		System.out.println(driver.getTitle());
		// Verify the presence of Watchlist table
		if (!ActionsUtil.bFunc_WaitForObjectExistance(tblWatchlist.get(0))) {
			throw new CustomeExceptionUtil("Watchlist Table not found");
		} else {
			Log.info("Watchlist table presence verified on page successfully");
		}

		// Verify the rows in Watchlist table
		// List<WebElement> rowsWatchlist = driver.findElements(By.xpath("//table[@class='dataTable sortable']/tbody/tr"));
		int actRows = WebTableUtil.getTableRowCount("//table[@class='dataTable sortable']/tbody");
		// int actRows = rowsWatchlist.size();
		String sActRows = String.valueOf(actRows - 1);
		String expRows = ExcelUtil.objTestDataMap.get("WatchlistRows");

		// Verify the number of rows in Watchlist
		if (!(sActRows.equalsIgnoreCase(expRows))) {
			throw new CustomeExceptionUtil("Watchlist Rows did not match");
		} else {
			Log.info("Watchlist Rows did matched successfully " + actRows);
		}

		if (!ActionsUtil.gFunc_ClickElement(lnkSignout)) {
			throw new CustomeExceptionUtil("Signout Button not found");
		} else {
			Log.info("Signout button found and clicked successfully");
		}
		driver.close();
	}

}