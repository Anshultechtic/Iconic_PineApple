package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonUtil {
	
	
	
//	@Test(dataProvider = "getData")
	public void creatAccountTest2(HashMap<String, String> input) throws InterruptedException {

		System.out.println(input.get("email"));

	}

//	@DataProvider
//	public Object[][] getData() throws IOException {
//
//		List<HashMap<String, String>> data = getJsonDataToMap(
//				System.getProperty("user.dir") + "\\src\\main\\java\\data\\loginData.json");
//		System.out.println(data.size());
//		Object[][] object = null;
//		for (int i = 0; i < data.size(); i++) {
//
//			object = new Object[][] { { data.get(i) } };
//		}
//
//		return object;
//
//	}

}
