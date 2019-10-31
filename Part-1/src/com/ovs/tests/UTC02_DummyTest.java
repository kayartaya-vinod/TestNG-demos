package com.ovs.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class UTC02_DummyTest {

	@BeforeSuite
	public void beforeSuiteHook() {
		System.out.println("::::::::: beforeSuiteHook() called!");
	}


	@BeforeClass
	public void beforeClassHook() {
		System.out.println(">>>>>>>>> beforeClassHook() called!");
	}

	@AfterClass
	public void afterClassHook() {
		System.out.println(">>>>>>>>> afterClassHook() called!");
	}

	
	@Test
	public void f1() {
		System.out.println("f1() called");
	}
	
	@Test
	public void f2() {
		System.out.println("f2() called");
	}
	
}
