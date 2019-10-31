package com.ovs.programs;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static void main(String[] args) throws Exception {

		XSSFWorkbook book = new XSSFWorkbook("20contacts.xlsx");

		int count = book.getNumberOfSheets();
		System.out.println("Sheet count = " + count);

		XSSFSheet sheet = book.getSheetAt(0);
		String name = sheet.getSheetName();
		System.out.println("Sheet name = " + name);

		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();

			Iterator<Cell> cellIterator = row.iterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
				default:
				}
				System.out.println(", ");

			}

			System.out.println();
		}

		book.close();

	}
}
