package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lowagie.text.Row;

public class ExcelWriteData {

	static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelSheets\\Standard_Sheet_9.xlsx";

	public static void main(String[] args) {

		try {
			writeDataInExcel3("contacts", "Email", createEmail(50000));
			writeDataInExcel3("contacts", "First_Name", firstName(50000));
			writeDataInExcel3("contacts", "Last_Name", lastName(50000));
			writeDataInExcel3("contacts", "Phone_Number", phoneNumber(50000));
			writeDataInExcel3("contacts", "Date_Of_Birth", DOB(50000));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<String> createEmail(int noOfRows) {

		List<String> emailList = new ArrayList<>();

		for (int i = 1; i <= noOfRows; i++) {

			if (i % 2 == 0) {
				emailList.add("User" + i + "@yopmail.com");
			} else if (i % 3 == 0) {
				emailList.add("User" + i + "@gmail.com");
			} else if (i % 5 == 0) {
				emailList.add("User" + i + "@yahoo.com");
			} else {
				emailList.add("User" + i + "@outlook.com");
			}
		}

		return emailList;

	}

	public static List<String> firstName(int noOfRows) {

		List<String> emailList = new ArrayList<>();

		for (int i = 1; i <= noOfRows; i++) {
			emailList.add("UserFName_" + i);

		}

		return emailList;

	}

	public static List<String> lastName(int noOfRows) {

		List<String> emailList = new ArrayList<>();

		for (int i = 1; i <= noOfRows; i++) {

			emailList.add("UserLName_" + i);
		}

		return emailList;

	}

	public static List<String> phoneNumber(int noOfRows) {

		List<String> phoneList = new ArrayList<>();

		for (int i = 1; i <= noOfRows; i++) {
			Random random = new Random();

			// Generate the first digit (should be between 6 and 9)
			int firstDigit = 6 + random.nextInt(4); // 6, 7, 8, or 9

			// Generate the remaining 9 digits
			long remainingDigits = 1000000000L + random.nextLong(9000000000L);

			// Combine the first digit with the remaining digits
			phoneList.add(firstDigit + String.valueOf(remainingDigits).substring(1));

		}

		return phoneList;

	}

	public static List<String> DOB(int noOfRows) {
		List<String> dateList = new ArrayList<String>();

		for (int i = 1; i <= noOfRows; i++) {
			Random random = new Random();

			// Generate a random year between 1900 and 2023
			int year = 1900 + random.nextInt(2023 - 1900 + 1);

			// Generate a random month between 1 and 12
			int month = 1 + random.nextInt(12);

			// Generate a random day based on the month and whether it is a leap year
			int day;
			if (month == 2) {
				// Check if the year is a leap year
				boolean isLeapYear = java.time.Year.isLeap(year);
				day = 1 + random.nextInt(isLeapYear ? 29 : 28);
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 1 + random.nextInt(30);
			} else {
				day = 1 + random.nextInt(31);
			}

			// Create a LocalDate object with the generated year, month, and day
			LocalDate randomDate = LocalDate.of(year, month, day);

			// Format the LocalDate object into DD MM YYYY format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			dateList.add(formatter.format(randomDate));
//			System.out.println(formatter.format(randomDate));
		}

		return dateList;
	}

	public static void writeDataInExcel3(String sheetName, String columnName, List<String> dataToWrite)
			throws IOException {

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetName);
		XSSFRow row = sh.getRow(0);
		int column = -1; // Initialize to -1 to check if column is found
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
				column = i;
				break; // Break the loop once the column is found
			}
		}

		if (column == -1) {
			throw new IllegalArgumentException("Column " + columnName + " not found.");
		}

		System.out.println(dataToWrite.size());

		for (int i = 2; i < dataToWrite.size(); i++) { // Use < instead of <=
			XSSFRow row1 = sh.getRow(i);
			if (row1 == null) {
				row1 = sh.createRow(i);
			}
//			System.out.println("Row Number is " + (i + 1));
			Cell cell = row1.createCell(column);
			cell.setCellValue(dataToWrite.get(i));

		}

		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
		fis.close(); // Close the FileInputStream after you're done using it
		System.out.println(columnName+" written successfully");
	}

	public static void writeDataInExcel(String sheetName, String columnName, String dataToWrite) throws IOException {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelSheets\\Book19.xlsx";

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetName);
		XSSFRow row = sh.getRow(0);
		int column = 0;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
				column = i;
			}
		}
		int rowNum = 0;
		for (int i = 0; i < sh.getLastRowNum(); i++) {
			if (sh.getRow(i).getCell(column).getStringCellValue().equalsIgnoreCase(dataToWrite)) {
				rowNum = i;
			}
		}

		XSSFRow row1 = sh.getRow(rowNum + 1);
		Cell cell = row1.createCell(column);
		cell.setCellValue(dataToWrite);
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
		System.out.println("Data written successfully");
	}

}
