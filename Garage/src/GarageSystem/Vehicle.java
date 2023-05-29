package GarageSystem;

public class Vehicle {
	private int modelYear;
	public int vehicleID = 0;
	public static int counter = 0;
	private double width;
	private double length;
	private String ownerMobileNo;
	private String email;
	private String modelName;

	public Vehicle() {
		this.vehicleID = counter;
		counter++;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setOwnerMobileNo(String ownerMobileNo) {
		this.ownerMobileNo = ownerMobileNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// default constructor

//	public Vehicle(VehicleBuilder builder)
//	{
//		this.modelName=builder.modelName;
//		this.modelYear=builder.modelYear;
//		this.width=builder.width;
//		this.length=builder.length;
//		this.ownerMobileNo=builder.ownerMobileNo;
//		this.email=builder.email;
//		vehicleID=counter;
//		counter++;
//	}
//		

	// parameterized constructor
//	public Vehicle(String modelName, int modelYear ,double width,double length,String ownerMobileNo) 
//	{
//		this.modelName = modelName;
//	    this.modelYear = modelYear;
//		this.width = width;
//		this.length = length;
//		this.ownerMobileNo=ownerMobileNo;
//	    vehicleID=counter;
//	    counter ++;
//	   
//	}

	public String getModelName() // function to get the modelName of the vehicle
	{
		return modelName;
	}

	public int getModelYear() // function to get the modelYear of the vehicle
	{
		return modelYear;
	}

	public int getID() // function to get the ID of the vehicle
	{
		return vehicleID;
	}

	public double getWidth() // function to get the width of the vehicle
	{
		return width;
	}

	public double getLength() // function to get the length of the vehicle
	{
		return length;
	}

	public String getOwnerMobileNo() {
		return ownerMobileNo;
	}

	public String getEmail() {

		return email;
	}

	@Override // Function inhereted from class object,used to print the vehicle's info
	public String toString() {
		return "Vehicle [modelName= " + modelName + ", modelYear= " + modelYear + ", ID= " + vehicleID + " , Width= "
				+ String.format("%.2f", this.width) + " , Length= " + String.format("%.2f", this.length) + " ]";
	}
}
