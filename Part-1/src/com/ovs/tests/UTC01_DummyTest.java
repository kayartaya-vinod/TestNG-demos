package com.ovs.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UTC01_DummyTest {

	@AfterSuite
	public void afterSuiteHook() {
		System.out.println("::::::::: afterSuiteHook() called!");
	}

	@BeforeClass
	public void beforeClassHook() {
		System.out.println(">>>>>>>>> beforeClassHook() called!");
	}

	@AfterClass
	public void afterClassHook() {
		System.out.println(">>>>>>>>> afterClassHook() called!");
	}

	@BeforeTest
	public void beforeTestHook() {
		System.out.println(">>>>>>>>> beforeTestHook() called!");
	}
	
	@AfterTest
	public void afterTestHook() {
		System.out.println(">>>>>>>>> afterTestHook() called!");
	}	
		
	@BeforeMethod
	public void beforeMethodHook() {
		System.out.println(">>>>>>>>> beforeMethodHook() called!");
	}
	@AfterMethod
	public void afterMethodHook() {
		System.out.println(">>>>>>>>> afterMethodHook() called!");
	}

	@Test(priority = 1)
	public void UTC01_01_TestLogin() {
		System.out.println("UTC01_01_TestLogin() started");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//		}

		// Assert.fail();
		System.out.println("UTC01_01_TestLogin() done");
		
	}

	@Test(priority = 2, dependsOnMethods = { "UTC01_01_TestLogin" })
	public void UTC01_04_TestUpdatePersonalInfo() {
		System.out.println("UTC01_04_TestUpdatePersonalInfo() started");

//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//		}
		System.out.println("UTC01_04_TestUpdatePersonalInfo() done");
	}

	@Test(priority = 2, dependsOnMethods = { "UTC01_01_TestLogin" })
	public void UTC01_03_TestUpdatePassword() {
		System.out.println("UTC01_03_TestUpdatePassword() started");
		System.out.println("UTC01_03_TestUpdatePassword() done");
	}

	@Test(priority = 4, dependsOnMethods = { "UTC01_04_TestUpdatePersonalInfo", "UTC01_03_TestUpdatePassword" })
	public void UTC01_02_TestLogout() {
		System.out.println("UTC01_02_TestLogout() called");
	}

}
