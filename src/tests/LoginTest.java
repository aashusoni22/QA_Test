package tests;

import org.testng.annotations.Test;
import data.DataFile;
import pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	
	LoginPage lp = new LoginPage();
	DataFile data = new DataFile();

	@BeforeMethod
	public void beforeMethod() throws IOException {
		lp.openBrowser();
		lp.openLoginPage();
	}

	@AfterMethod
	public void afterMethod() {
		lp.closeBrowser();
	}

	@Test(priority = 1)
	public void loginWithSpecialCharEmailTest() throws InterruptedException {
		lp.login(data.emailWithSpecialChar, data.wrongPassword);
		Assert.assertEquals(lp.readEmailErr(), data.specialCharErr);
	}

	@Test(priority = 2)
	public void loginWithEmptyEmailTest() throws InterruptedException {
		lp.login("", data.wrongEmail);
		Assert.assertEquals(lp.readEmailErr(), data.emptyEmailErr);
	}

	@Test(priority = 3)
	public void loginWithEmptyPasswordTest() throws InterruptedException {
		lp.login(data.wrongEmail, "");
		Assert.assertEquals(lp.readPasswordErr(), data.emptyPasswordErr);
	}

	@Test(priority = 4)
	public void loginWithWrongEmailPasswordTest() throws InterruptedException {
		lp.login(data.wrongEmail, data.wrongEmail);
		Assert.assertEquals(lp.readGlobalErr(), data.globalErr);
	}

}
