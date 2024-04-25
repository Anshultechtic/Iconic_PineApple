package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelR {
	
	

	@DataProvider
	public Object[][] readExcelData() throws IOException {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelSheets\\Book14.xlsx";
		System.out.println(path);
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFRow firstRow = null;
		int noOfCellinFirstRow = 0;
		XSSFSheet sh = null;
		DataFormatter formatter = new DataFormatter();
		Object[][] data = null;
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {

			if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("login")) {

				System.out.println("Sheet Found!!");

				sh = wb.getSheetAt(i);
				firstRow = sh.getRow(0);

				data = new Object[sh.getPhysicalNumberOfRows() - 1][firstRow.getLastCellNum()];

				for (int j = 0; j < sh.getPhysicalNumberOfRows()-1; j++) {

					Row rw = sh.getRow(j + 1);
//					System.out.println("Row num =" + j);
					for (int k = 0; k < firstRow.getLastCellNum(); k++) {
						System.out.println("Cell num =" + k);
						Cell cell = rw.getCell(k);

						System.out.println(formatter.formatCellValue(cell));

						data[j][k] = formatter.formatCellValue(cell);

					}

				}

			}

		}

		return data;
	}

	@Test(dataProvider = "readExcelData")
	public void data(String Email, String pass, String rem, String sub, String status) {

		System.out.println(Email + " " + pass + " " + rem + " " + sub + " " + status+ " ");

	}

}
