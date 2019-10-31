package com.ovs.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ovs.utils.SeleniumUtil;

public class UTC03_TestAddContacts {

	WebDriver driver;
	
	@BeforeClass
	public void initializeWebDriver() {
		driver = SeleniumUtil.getWebDriver();
		driver.get("https://vinod.co/phonebook-webapp/");
	}
	
	@AfterClass
	public void closeWebDriver() {
		// driver.quit();
	}
	
	@DataProvider
	public Object[][] getContactsData() {
		
		return new Object[][] {
			{"John Doe", "johndoe@example.com", "5557229876"},
			{"Jane Doe", "janedoe@example.com", "5557229822"},
			{"Ramesh Iyer", "ramesh@example.com", "987329876"},
			{"Santosh Kumar", "santosh@example.com", "3318265546"}
		};
	}
	
	
	@DataProvider
	public Iterator<Object[]> getContactsData1() {
		 List<Object[]> list = new ArrayList<>();
		 
		 list.add(new Object[] {"John Doe", "johndoe@example.com", "5557229000"});
		 list.add(new Object[] {"Jane Doe", "janedoe@example.com", "5557229822"});
		 list.add(new Object[] {"Ramesh Iyer", "ramesh@example.com","987329876"});
		 list.add(new Object[] {"Santosh Kumar", "santosh@example.com","3318265546"});
		
		 return list.iterator();
	}
	
	@Test(dataProvider="getContactsData1")
	public void UTC03_01_AddNewContact(String name, String email, String phone) {
		// String name = "John Doe";
		// String email = "johndoe@example.com";
		// String phone = "5557229876";
		
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("btnAdd")).click();
	}
}







