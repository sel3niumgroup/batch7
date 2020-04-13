package ebank.automation;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import PageObjects.NewCustomerPage;
import utils.TestBase;

public class CreateNewCustomer extends TestBase{

	
	
	@Test(priority=1)
	public void createNewCustomer() {
		JSONObject data = getData("data.json", "NewCustomerDetails", 0);
		
		NewCustomerPage obj = new NewCustomerPage(driver);
		obj.NewCustomerBtn.click();
		String cxName =  (String) data.get("Customer Name");
		String Gender = (String) data.get("Gender");
		String dob = (String) data.get("Date of Birth");
		String City = (String) data.get("City");
		String State = (String) data.get("State");
		String PIN = (String) data.get("PIN");
		String mobNum = (String) data.get("Mobile Number");
		String email = (String) data.get("E-mail");
		String pswd = (String) data.get("Password");

		
	}
}
