package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	public static WebDriver driver;
	
	/*
	 * public static Properties Repository = new Properties(); public static File f;
	 * public FileInputStream FI;
	 * 
	 * 
	 * public void loadPropertiesFile() throws IOException { f = new
	 * File(System.getProperty("user.dir") +
	 * "/src/main/java/config/config.properties"); FI = new FileInputStream(f);
	 * Repository.load(FI); }	 
	 */
	
	@BeforeTest
	public void setup() throws IOException {
		init();
	}
	
	@AfterClass
	public void endTest() throws InterruptedException {
		driver.close();
	}

	
	public void init() throws IOException {
		//loadPropertiesFile();
		selectBrowser(TestUtils.jsonHandler("data.json", "loginDetails", 0, "browser"));
		driver.get(TestUtils.jsonHandler("data.json", "loginDetails", 0, "url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	
	
	public WebDriver selectBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			return driver;
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			return driver;
		}
		return null;
	}

}
