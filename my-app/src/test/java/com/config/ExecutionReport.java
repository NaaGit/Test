package com.config;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExecutionReport implements ITestListener{
	
	public void onStart(ITestContext arg0) {
        System.out.println("Test Started->"+arg0.getName());
	}

	public void onTestStart(ITestResult result) {
        System.out.println("Test has started running:"  + result.getMethod().getMethodName() + " at:" + result.getStartMillis());
}

	public void onTestSuccess(ITestResult arg0) {
        System.out.println("Test Pass->"+arg0.getName());
		
	}

	public void onTestFailure(ITestResult arg0) {
        System.out.println("Test Failed->"+arg0.getName());
		
	}

	public void onTestSkipped(ITestResult arg0) {
        System.out.println("Test skipped->"+arg0.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	
	public void onFinish(ITestContext context) { 
	System.out.println("Passed tests: " +"\n" + context.getPassedTests());       
	System.out.println("Failed tests:" +"\n" + context.getFailedTests()); 
	}

}
