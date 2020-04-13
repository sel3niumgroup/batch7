package utils;

public class Pojo {
	private String Policy_No;
	private String Vehicle_Id;
	private String Driver_Id;
	private String Dri_Gender;
	private String Dri_Age;

	public String getPolicy_No() {
		return Policy_No;
	}

	public void setPolicy_No(String policy_No) {
		Policy_No = policy_No;
	}

	public String getVehicle_Id() {
		return Vehicle_Id;
	}

	public void setVehicle_Id(String vehicle_Id) {
		Vehicle_Id = vehicle_Id;
	}

	public String getDriver_Id() {
		return Driver_Id;
	}

	public void setDriver_Id(String driver_Id) {
		Driver_Id = driver_Id;
	}

	public String getDri_Gender() {
		return Dri_Gender;
	}

	public void setDri_Gender(String dri_Gender) {
		Dri_Gender = dri_Gender;
	}

	public String getDri_Age() {
		return Dri_Age;
	}

	public void setDri_Age(String dri_Age) {
		Dri_Age = dri_Age;
	}

	public Pojo(String policy_No, String vehicle_Id, String driver_Id, String dri_Gender, String dri_Age) {
		super();
		Policy_No = policy_No;
		Vehicle_Id = vehicle_Id;
		Driver_Id = driver_Id;
		Dri_Gender = dri_Gender;
		Dri_Age = dri_Age;
	}

	@Override
	public String toString() {
		return "Pojo [Policy_No=" + Policy_No + ", Vehicle_Id=" + Vehicle_Id + ", Driver_Id=" + Driver_Id
				+ ", Dri_Gender=" + Dri_Gender + ", Dri_Age=" + Dri_Age + "]";
	}

}
