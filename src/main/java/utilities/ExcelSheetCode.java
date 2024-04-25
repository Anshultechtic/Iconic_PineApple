package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelSheetCode {

	@DataProvider
	public Object[][] readData() {

		String excelPath = System.getProperty("user.dir") + "\\resources\\ExcelSheets\\Book2.xlsx";
		FileInputStream fis = null;
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		DataFormatter formatter = new DataFormatter();

		int r = 0;
		int c = 2;
		try {
			fis = new FileInputStream(excelPath);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheetAt(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		String sheetName = sh.getSheetName();

		int noOfRows = sh.getPhysicalNumberOfRows();

		System.out.println(sheetName + " has total " + noOfRows + " rows");
		Row rw = null;

		Object[][] data = null;

		for (int i = 0; i < noOfRows; i++) {
			rw = sh.getRow(i);
			int noOfCells = rw.getLastCellNum();
			data = new Object[noOfRows][noOfCells];
			for (int j = 0; j < noOfCells; j++) {

				Cell cell = rw.getCell(j);
				String value = formatter.formatCellValue(cell);

				data[i][j] = formatter.formatCellValue(cell);

				System.out.println(data + " " + value);

			}

		}
		
		return data;

	}

	@Test(dataProvider = "readData")
	public void data(String fname, String lastname) {

		System.out.println(fname + " " + lastname);

	}

	public void writeData() {

	}

}
