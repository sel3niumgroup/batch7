package ebank.automation;

import java.io.IOException;

import org.testng.annotations.Test;

import PageObjects.LoginPage;
import utils.TestBase;
import utils.TestUtils;

public class Login extends TestBase {

	@Test
	public void login() throws IOException {

		String username = TestUtils.jsonHandler("data.json", "loginDetails", 0, "username");
		String password = TestUtils.jsonHandler("data.json", "loginDetails", 0, "password");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.username.sendKeys(username);
		loginPage.password.sendKeys(password);
		loginPage.loginBtn.click();
	}

}
