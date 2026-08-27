package com.pers.fixtures;

import com.pers.utils.Browser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.function.Function;

public class ActionsUtil {
	public static Actions act = new Actions(Browser.getDriver());

	public static boolean gFunc_ClickElement(WebElement eleWebElement) {
		boolean blnStatus = false;
		try {
			blnStatus = bFunc_WaitForObjectExistance(eleWebElement);
			if (blnStatus) {
				eleWebElement.click();
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	public static String gFunc_GetElementText(WebElement eleWebElement) {
		boolean blnStatus = false;
		String strText = null;
		try {
			blnStatus = bFunc_WaitForObjectExistance(eleWebElement);
			if (blnStatus) {
				strText = eleWebElement.getText();
			} else
				strText = "";
		} catch (Exception e) {
		}
		return strText;
	}

	public static boolean gFunc_SendText(WebElement eleWebElement, String strText) throws Exception {
		boolean blnStatus = false;
		try {
			blnStatus = bFunc_WaitForObjectExistance(eleWebElement);
			if (blnStatus) {
				eleWebElement.sendKeys(strText);
			}
		} catch (NoSuchElementException e) {
		}
		return blnStatus;
	}

	public static boolean gFunc_SelectValue(WebElement eleWebElement, String strText) throws Exception {
		boolean blnStatus = false;
		try {
			blnStatus = bFunc_WaitForObjectExistance(eleWebElement);
			if (blnStatus) {
				Select oSelect = new Select(eleWebElement);
				String[] arrSplit = strText.split(",");
				for (int i = 0; i < arrSplit.length; i++) {
					oSelect.selectByVisibleText(arrSplit[i]);
				}
			}
		} catch (NoSuchElementException e) {
		}
		return blnStatus;
	}

	public static boolean bFunc_WaitForObjectExistance(final WebElement element) throws Exception {
		boolean blnStatus = false;
		// boolean handleAlert = false;
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Browser.getDriver());
			wait.pollingEvery(Duration.ofSeconds(5L));
			wait.withTimeout(Duration.ofSeconds(30L));
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					boolean blnFound = false;
					// boolean handleAlert = false;
					System.out.println("Waiting for Element Existance : --" + element);
					try {
						if (element != null && element.isDisplayed()) {
							blnFound = true;
						}
					} catch (NoSuchElementException nse) {
						blnFound = false;

					}

					return blnFound;
				}
			};
			blnStatus = wait.until(function);
		} catch (Exception e) {
			blnStatus = false;
		}
		return blnStatus;
	}

	public static boolean MoveMousetoElement(WebElement eleWebElement) {
		boolean blnStatus = false;
		try {
			blnStatus = ActionsUtil.bFunc_WaitForObjectExistance(eleWebElement);

			if (blnStatus) {

				act.moveToElement(eleWebElement);
				act.build().perform();
			}
		} catch (Exception e) {
			System.out.println("Element Not found in MoveMouseToElement Method");
		}
		return blnStatus;
	}

	public static boolean MoveToAndMouseClick(WebElement eleWebElement) {
		boolean blnStatus = false;
		try {
			blnStatus = MoveMousetoElement(eleWebElement);

			if (blnStatus) {
				act.click(eleWebElement);
				act.build().perform();
			}

		} catch (Exception e) {
			System.out.println("Element not clicked in MoveToAndMouseClick method");
		}
		return blnStatus;

	}

}
