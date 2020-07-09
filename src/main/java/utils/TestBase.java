package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import PageObjects.LoginPage;
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
		login();
	}
	
	@AfterClass
	public void endTest() throws InterruptedException {
		driver.close();
	}

	
	public void init() throws IOException {
		//loadPropertiesFile();
		JSONObject data = getData("data.json", "loginDetails", 0);		
		selectBrowser((String) data.get("browser"));
		driver.get((String) data.get("url"));
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
	
	public static JSONObject getData(String fileName,String arrayName, int index) {
		try {
			JSONParser jsonParser = new JSONParser();		
			Object obj = jsonParser.parse(new FileReader("./testData/"+fileName));
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObj.get(arrayName);
			JSONObject Obj = (JSONObject) jsonArray.get(index);
			//String str = (String) Obj.get(key);
			return Obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Enters user name and password
	 * Clicks login button	
	 */
	public void login() {
		JSONObject data = getData("data.json", "loginDetails", 0);	

		LoginPage loginPage = new LoginPage(driver);
		loginPage.username.sendKeys((String) data.get("username"));
		loginPage.password.sendKeys((String) data.get("password"));
		loginPage.loginBtn.click();
	}

}
