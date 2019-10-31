package com.ovs.tests;

import org.testng.annotations.Test;

public class UTC01_HelloTestng {

	@Test(priority = 1)
	public void UTC01_01_BasicTestCase() {
		System.out.println("Hello, world!");
	}
}
