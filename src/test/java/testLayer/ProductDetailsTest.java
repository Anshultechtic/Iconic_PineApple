package testLayer;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseTest.BaseTest;
import baseTest.BaseTest2;
import utilities.UtilClass;

public class ProductDetailsTest extends BaseTest {

//	@Test
	public void getAllCategoriesName() throws InterruptedException, IOException {

		hp_obj.closePopup();
		hp_obj.getProductDetailsOfAbstractCategory();

	}

	@Test(dataProviderClass = UtilClass.class, dataProvider = "dp")
	public void checkProductDetails(String sku, String name, String dimensions, String description)
			throws InterruptedException, IOException {
		System.out.println(sku + "\n" + name + "\n" + dimensions + "\n" + description);
		hp_obj.closePopup();
		search_pg_obj.Search_box(sku);
		
		System.out.println("=====================");
		System.out.println(pdp_obj.get_nameOfproduct().trim() + "===" + name.trim());
		System.out.println(pdp_obj.get_Description().trim() + "===" + description.trim());
		System.out.println(pdp_obj.get_Size_Of_product().trim() + "===" + dimensions.trim());

		Assert.assertEquals(pdp_obj.get_Description().trim(), description.trim());
		Assert.assertEquals(pdp_obj.get_Size_Of_product().trim(), dimensions.trim());
		Assert.assertEquals(pdp_obj.get_nameOfproduct().trim(), name.trim());
		System.out.println("Test Passed");

	}

//	@Test(dataProviderClass = UtilClass.class, dataProvider = "dp")
	public void checkProductDetails2(String sku, String name, String dimensions, String description)
			throws InterruptedException, IOException {
		System.out.println(sku + "\n" + name + "\n" + dimensions + "\n" + description);
		hp_obj.closePopup();
		search_pg_obj.Search_box(sku);
		
		System.out.println("=====================");
		System.out.println(pdp_obj.get_nameOfproduct().trim() + "===" + name.trim());
		System.out.println(pdp_obj.get_Description().trim() + "===" + description.trim());
		System.out.println(pdp_obj.get_Size_Of_product().trim() + "===" + dimensions.trim());
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(pdp_obj.get_Description().trim(), description.trim());
		sa.assertEquals(pdp_obj.get_Size_Of_product().trim(), dimensions.trim());
		sa.assertEquals(pdp_obj.get_nameOfproduct().trim(), name.trim());
		System.out.println("Test Passed");

	}
}
