package com.paralleltest.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	Logger log;
	String testName;
	String testMethodName;

	public void onTestStart(ITestResult result) {
		this.testMethodName = result.getMethod().getMethodName();
		log.info("[Starting test method " + testMethodName + "]");
	}


	public void onTestSuccess(ITestResult result) {
		log.info("[Test method " + testMethodName + " passed]");
	}


	public void onTestFailure(ITestResult result) {
		log.info("[Test method " + testMethodName + " failed]");
	}


	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("[Test method " + testMethodName + " passed partially]");

	}

	
	public void onStart(ITestContext context) {
		this.testName = context.getCurrentXmlTest().getName();
		this.log = LogManager.getLogger(testName);
		log.info("[MAIN TEST " + testName + " STARTED]");
	}

	
	public void onFinish(ITestContext context) {
		log.info("[ALL " + testName + " FINISHED]");
	}


}
