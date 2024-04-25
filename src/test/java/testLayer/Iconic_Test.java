package testLayer;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest2;

public class Iconic_Test extends BaseTest2 {
	
	
	
	
	@Test(dataProvider = "readData")
	public  void main(String data, String Bp) {
		
		driver.get("https://iconicpineapple.com/admin/");
		
		iconic_obj.enterusername(null);
		iconic_obj.enterpassword(null);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		iconic_obj.clickSignIn();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iconic_obj.clickOnCatalog();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iconic_obj.clickOnProduct();
		
		iconic_obj.sendDataInSearchBox(data);
		
		
		
	}
	


	@DataProvider
	public static Object[][] readData(String sheetname) {

		String path = System.getProperty("user.dir") + "\\resources\\ExcelSheets\\"+sheetname+".xlsx";
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
	
	
	
	
	
	
	
	
	
	
	


