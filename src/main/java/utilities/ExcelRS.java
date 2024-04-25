package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRS {

	// Identify Testcases coloum by scanning the entire 1st row
	// once coloumn is identified then scan entire testcase coloum to identify
	// purcjhase testcase row
	// after you grab purchase testcase row = pull all the data of that row and feed
	// into test

	public static ArrayList<String> getData(String testcaseName) {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelSheets\\Book14.xlsx");
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("login")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify Testcases column by scanning the entire 1st row
				System.out.println("Sheet Found!");
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;

					}

					k++;
				}

//				System.out.println(coloumn);

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {

//						 after you grab purchase testcase row = pull all the data of that row and
//						 feed into test

						Iterator<Cell> cv = r.cellIterator();
						cv.next(); // added to skip first column value
						while (cv.hasNext()) {
							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}

			}
		}
		return a;

	}

	public static void main(String[] args) {

//		System.out.println(getData("login3"));

		for (String data : getData("Invalid Creds")) {

			System.out.println(data);
		}

	}

}
