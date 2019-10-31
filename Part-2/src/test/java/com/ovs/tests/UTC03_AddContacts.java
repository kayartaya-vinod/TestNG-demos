package com.ovs.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ovs.entity.Contact;
import com.ovs.utils.SeleniumUtil;

public class UTC03_AddContacts {
	WebDriver driver;

	@BeforeClass
	public void beforeMethod() {
		driver = SeleniumUtil.getWebDriver();
		driver.get("https://vinod.co/phonebook-webapp/");

	}

	@AfterClass
	public void afterMethod() {
		driver.quit();
	}

	@Test(enabled = false)
	public void someTest() {
		System.out.println("some test going on...");
	}

	@DataProvider
	public Iterator<Contact> source1() {
		List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact(3, "Kiran Kumar", "kirankumar@example.come", "9484488993"));
		contacts.add(new Contact(4, "Ramesh Kumar", "ramesh@example.come", "9484488922"));
		contacts.add(new Contact(5, "Arun", "arun@example.come", "9483388993"));
		return contacts.iterator();
	}

	@DataProvider
	public Object[] source2() {
		return new Object[] { 
				new Contact(3, "Kiran Kumar", "kirankumar@example.come", "9484488993"),
				new Contact(4, "Ramesh Kumar", "ramesh@example.come", "9484488922") };
	}

	@DataProvider
	public Iterator<Contact> source3_csv() throws Exception {

		String filename = "100contacts.csv";
		FileReader file = new FileReader(filename);
		BufferedReader in = new BufferedReader(file);
		String line;

		List<Contact> list = new ArrayList<>();

		while ((line = in.readLine()) != null) {
			if (line.startsWith("id"))
				continue; // skip the rest of the loop block
			String[] arr = line.split(",");
			Contact c = new Contact();
			c.setId(Integer.parseInt(arr[0]));
			c.setName(arr[1]);
			c.setEmail(arr[2]);
			c.setPhone(arr[3]);
			list.add(c);
		}
		in.close();
		file.close();

		return list.iterator();
	}

	@DataProvider
	public Iterator<Contact> source4_jdbc() {
		String url = "jdbc:h2:tcp://localhost/~/sdet_training";
		String user = "sa";
		String password = "";
		String sql = "select * from contacts";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			List<Contact> list = new ArrayList<>();
			while (rs.next()) {
				Contact c = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(c);
			}
			return list.iterator();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@DataProvider
	public Iterator<Contact> source5_excel() throws Exception {
		XSSFWorkbook book = new XSSFWorkbook("20contacts.xlsx");
		XSSFSheet sheet = book.getSheetAt(0);

		Iterator<Row> iterator = sheet.iterator();
		List<Contact> list = new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			Contact c = new Contact();

			Iterator<Cell> cellIterator = row.iterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getColumnIndex()) {
				case 0:
					c.setId((int) cell.getNumericCellValue());
					break;
				case 1:
					c.setName(cell.getStringCellValue());
					break;
				case 2:
					c.setEmail(cell.getStringCellValue());
					break;
				case 3:
					cell.setCellType(CellType.STRING);
					c.setPhone(cell.getStringCellValue());
				default:

				}
			}
			list.add(c);
		}
		book.close();
		return list.iterator();
	}

	@Test(dataProvider = "source5_excel")
	public void UTC03_01_AddValidContact(Contact c) { // dependency injection

		int recordCountBefore = getContactsCount(driver);

		driver.findElement(By.id("name")).sendKeys(c.getName());
		driver.findElement(By.id("email")).sendKeys(c.getEmail());
		driver.findElement(By.id("phone")).sendKeys(c.getPhone());

		driver.findElement(By.id("btnAdd")).click();

		int recordCountAfter = getContactsCount(driver);

		Assert.assertEquals(recordCountAfter, (recordCountBefore + 1));

	}

	int getContactsCount(WebDriver driver) {
		int recordCount;
		List<WebElement> rows = driver.findElements(By.cssSelector("table > tbody > tr"));
		recordCount = rows.size();
		return recordCount;
	}
}
