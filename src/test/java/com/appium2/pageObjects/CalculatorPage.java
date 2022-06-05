package com.appium2.pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.appium2.core.Base;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CalculatorPage extends Base {

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"3\"]")
	@iOSXCUITFindBy(id = "3")
	protected WebElement btn3;

	@AndroidFindBy(accessibility = "equals")
	protected WebElement equals;

	@AndroidFindBy(accessibility = "plus")
	protected WebElement plus;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'result_preview')]")
	protected WebElement resultPreview;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'drag_handle_view')]")
	protected WebElement dragHandle;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'history_recycler')]")
	protected WebElement historyRecycler;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'history_formula')]")
	protected List<WebElement> historyFormulas;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'history_result')]")
	protected List<WebElement> historyResults;

}
