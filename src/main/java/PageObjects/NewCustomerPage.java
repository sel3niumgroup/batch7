package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomerPage {

	@FindBy(linkText = "New Customer")
	public WebElement NewCustomerBtn;
	
	@FindBy(name= "name")
	public WebElement CustomerName;
	
	
	
	
	
	

	public NewCustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
