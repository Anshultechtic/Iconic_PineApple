package utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import baseTest.BaseTest2;

public class ExelDP extends BaseTest2 {

	@Test(dataProvider = "readData")
	public void readDP(String row, String col) {

		
		System.out.println(row + " " + col + "\n");
		
		
		
		
		

	}

	@DataProvider
	public static Object[][] readData() {

		String path = System.getProperty("user.dir") + "\\resources\\ExcelSheets\\Book2.xlsx";
		Object[][] data = null;
		Map<String, String> map = new HashMap<>();
		try {
			FileInputStream fis = new FileInputStream(path);

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sh = null;
			DataFormatter formatter = new DataFormatter();

			sh = wb.getSheet("Iconic");
			Row rw = sh.getRow(0);
			data = new Object[sh.getPhysicalNumberOfRows() - 1][rw.getLastCellNum()];

			for (int i = 0; i < sh.getPhysicalNumberOfRows(); i++) {

				rw = sh.getRow(i + 1);
				for (int j = 0; j < rw.getLastCellNum(); j++) {

					String cell = formatter.formatCellValue(rw.getCell(j));

					data[i][j] = cell;

				}
//				
			}

		}

		catch (Exception e) {

		}
		return data;

	}
}