package com.appium2.stepdefinitions;

import com.appium2.testLogic.CalculatorPage;

import io.cucumber.java.en.Given;


public class BaseSteps {

	CalculatorPage calc = new CalculatorPage();

	@Given("I test addition")
	public void launch_app() {
		calc.addition();
	}

}
