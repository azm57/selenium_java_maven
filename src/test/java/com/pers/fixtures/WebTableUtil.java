package com.pers.fixtures;

import com.pers.utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class WebTableUtil {

	public static int getTableRowCount(String strTableXpath) {
		// Variable declaration
		List<WebElement> eleTableRows = null;
		WebElement WebTable = null;

		WebTable = Browser.getDriver().findElement(By.xpath(strTableXpath));

		// Checking for the WebTable Existence
		if (!WebTable.isDisplayed()) {
			return 0;
		}

		try {
			eleTableRows = WebTable.findElements(By.xpath(strTableXpath + "//tr"));
		}

		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

		// returning the row size
		return eleTableRows.size();
	}

	public int getTableColumnCount(String strTableXpath) {
		// Variable declaration
		WebElement WebTable = null;
		List<WebElement> lstTableColumns = null;
		WebTable = Browser.getDriver().findElement(By.xpath(strTableXpath));

		// Checking for the WebTable Existence
		if (!WebTable.isDisplayed()) {
			return 0;
		}

		try {
			lstTableColumns = WebTable.findElements(By.xpath(strTableXpath + "//th"));
		}

		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// returning the column size
		return lstTableColumns.size();

	}

	public String[] getHeaderNames(String strTableXPath) {
		// Variable declaration
		WebElement WebTable = null;
		List<WebElement> lstTableHeaders = null;
		String[] strHeaderNames = null;

		WebTable = Browser.getDriver().findElement(By.xpath(strTableXPath));

		// Checking for the existence of WebTable
		if (!WebTable.isDisplayed()) {
			return null;
		}

		try {

			lstTableHeaders = WebTable.findElements(By.xpath(strTableXPath + "//th"));
			strHeaderNames = new String[lstTableHeaders.size()];

			// getting the each header name and storing it in string array
			for (int i = 0; i <= strHeaderNames.length - 1; i++) {
				strHeaderNames[i] = lstTableHeaders.get(i).getText();
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// returning the Header Names
		return strHeaderNames;
	}

	public int getRowWithCellText(String strTabaleXPath, String strCellText) {
		// Variable declaration
		WebElement WebTable = null;
		List<WebElement> LstTableRows = null;
		int RowCount = 0;
		int MatchRowNumber = 1;
		List<WebElement> LstTableCols = null;
		int ColCount = 0;
		boolean blfnd = false;

		WebTable = Browser.getDriver().findElement(By.xpath(strTabaleXPath));

		if (!WebTable.isDisplayed()) {
			return 0;
		}

		try {

			LstTableRows = Browser.getDriver().findElements(By.xpath(strTabaleXPath + "//tr"));
			RowCount = LstTableRows.size();
			// iterating for each and every row
			for (int row = 1; row <= RowCount - 1; row++) {
				// Getting the Column Count
				LstTableCols = Browser.getDriver()
						.findElements(By.xpath(strTabaleXPath + "//tr" + "[" + row + "]" + "/td"));
				ColCount = LstTableCols.size();

				// iterating each column of a row
				for (int col = 0; col <= ColCount - 1; col++) {
					// Comparing each cell value with expecting value
					if (strCellText.equalsIgnoreCase(LstTableCols.get(col).getText().trim())) {
						MatchRowNumber = row;
						blfnd = true;
						break;
					}
				}
				// if Text found exit from the loop
				if (blfnd) {
					break;
				}

			}
		}

		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// returning the matched row number
		return MatchRowNumber;

	}

	public String getCelldataFromTable(String strTableXPath, int Row, int Column) {
		String strValue = null;
		String strCellXPATH = null;
		WebElement Cell = null;
		WebElement WebTable = null;

		WebTable = Browser.getDriver().findElement(By.xpath(strTableXPath));

		// Checking for the existence of the web table
		if (!WebTable.isDisplayed()) {
			return null;
		}

		try {
			// generating the xpath for the cell with given row and column num
			strCellXPATH = strTableXPath + "//tr[" + Row + "]/td[" + Column + "]";
			Cell = Browser.getDriver().findElement(By.xpath(strCellXPATH));
			// getting the cell value
			strValue = Cell.getText();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return strValue;
	}

	public int getChildItemCountinTable(String strTableXPAth, int RowNumber, int ColumnNumber) {
		// Variable declaration
		List<WebElement> ItemsinCell = null;
		String RowXPath = null;
		String ColumnXPath = null;
		try {
			WebElement webTable = Browser.getDriver().findElement(By.xpath(strTableXPAth));

			// Checking for the WebTable existence
			if (!webTable.isDisplayed()) {
				return 0;
			}

			RowNumber = RowNumber + 1;
			ColumnNumber = ColumnNumber + 0;

			// Setting row and column xpath
			RowXPath = strTableXPAth + "//tr[" + RowNumber + "]";
			ColumnXPath = RowXPath + "//td[" + ColumnNumber + "]";

			ItemsinCell = Browser.getDriver().findElements(By.xpath(ColumnXPath));
		}

		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return ItemsinCell.size();
	}

}
