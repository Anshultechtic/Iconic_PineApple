package testLayer;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import baseTest.BaseTest2;

public class AddProductToCart extends BaseTest {

	@Test
	public void getAllCategoriesName() throws InterruptedException, IOException {

		hp_obj.closePopup();
		hp_obj.getProductDetails();
		
	}

}
