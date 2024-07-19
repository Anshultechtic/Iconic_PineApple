package testLayer;

import org.testng.annotations.Test;

public class Aptest2 {

	@Test(groups = { "smoke" })
	public void test1() {

		System.out.println("Smoke");

	}

	@Test(groups = { "sanity" })
	public void test2() {

		System.out.println("Sanity");

	}

	@Test(groups = { "Functional"})
	public void test3() {

		System.out.println("Functional");

	}

	@Test(groups = { "Regression" })
	public void test4() {

		System.out.println("Regression");

	}

}
