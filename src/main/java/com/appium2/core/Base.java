package com.appium2.core;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium2.factory.ConfigFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsContextSwitching;

public abstract class Base {
	public static AppiumDriver driver;

	public Base() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	}

	public void activateApp(String packageName) {
		((AndroidDriver) driver).activateApp(packageName);
	}

	public void setContext(String context) {
		((SupportsContextSwitching) driver).context(context);
	}
	
	public void click(WebElement ele) {
		waitForElementVisibility(ele).click();
	}

	public WebElement waitForElementVisibility(WebElement ele) {
		return waitForElementVisibility(ele, Duration.ofSeconds(10));
	}

	public WebElement waitForElementVisibility(WebElement ele, Duration duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public String getText(WebElement ele) {
		return waitForElementVisibility(ele).getText();
	}

	public WebElement waitForElement(WebElement ele, ExpectedCondition<WebElement> expectedCondition) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(expectedCondition);
	}

	public void swipeTillElementVisible(WebElement ele, SwipeDirection swipeDirection) {
		Dimension rect = driver.manage().window().getSize();
		while (true) {
			if (waitForElementVisibility(ele, Duration.ofSeconds(1)).isDisplayed()) {
				break;
			}
			swipe(new Point((int) (rect.getWidth() / 2), (int) (rect.getHeight() / 2)), swipeDirection, 100);
		}

	}

	public void swipeElement(WebElement ele, SwipeDirection swipeDirection, int amount) {
		Rectangle rect = waitForElementVisibility(ele).getRect();
		int x = rect.x + (int) (rect.getWidth() / 2);
		int y = rect.y + (int) (rect.getHeight() / 2);
		swipe(new Point(x, y), swipeDirection, amount, Duration.ofSeconds(1));
	}

	public void swipe(Point point, SwipeDirection swipeDirection, int amount, Duration duration) {
		PointerInput p1 = new PointerInput(Kind.TOUCH, "f1");
		Sequence seq = new Sequence(p1, 0);
		seq.addAction(p1.createPointerMove(Duration.ZERO, Origin.viewport(), point.x, point.y));
		seq.addAction(p1.createPointerDown(MouseButton.LEFT.asArg()));
		switch (swipeDirection) {
		case DOWN:
			point.y += amount;
			break;
		case UP:
			point.y -= amount;
			break;
		case RIGHT:
			point.x += amount;
			break;
		case LEFT:
			point.x -= amount;
			break;
		default:
			break;
		}
		seq.addAction(p1.createPointerMove(duration, Origin.viewport(), point.x, point.y));
		seq.addAction(new Pause(p1, Duration.ofSeconds(1)));
		seq.addAction(p1.createPointerUp(MouseButton.LEFT.asArg()));
		seq.addAction(new Pause(p1, Duration.ofSeconds(1)));
		driver.perform(Arrays.asList(seq));
	}

	public void swipe(Point point, SwipeDirection swipeDirection, int amount) {
		swipe(point, swipeDirection, amount, Duration.ofSeconds(0));
	}

	public enum SwipeDirection {
		UP, DOWN, RIGHT, LEFT;
	}

	public void longPress(WebElement ele, Duration duration) {
		Rectangle rect = ele.getRect();
		PointerInput p1 = new PointerInput(Kind.TOUCH, "f1");
		Sequence seq = new Sequence(p1, 0);
		seq.addAction(p1.createPointerMove(Duration.ZERO, Origin.viewport(), (int) (rect.x + (rect.getWidth() / 2)),
				(int) (rect.y + (rect.getHeight() / 2))));
		seq.addAction(p1.createPointerDown(MouseButton.LEFT.asArg()));
		seq.addAction(new Pause(p1, duration));
		driver.perform(Arrays.asList(seq));
	}

}
