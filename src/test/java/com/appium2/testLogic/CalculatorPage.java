package com.appium2.testLogic;

import static org.junit.Assert.assertEquals;

public class CalculatorPage extends com.appium2.pageObjects.CalculatorPage{
	
	public void addition() {
		click(btn3);
		click(plus);
		click(btn3);
		assertEquals(getText(resultPreview), "6");
		click(equals);
		swipeElement(dragHandle, SwipeDirection.DOWN, 500);
		System.out.println("Hist : "+getText(historyFormulas.get(1)));
	}
}
