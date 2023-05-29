package GarageSystem;

public class Slot {
	private int slotID;
	boolean isAvailable = true;
	private Vehicle vehicle;
	private double width;
	private double depth;
	private String slotType;
	private double hourlyRate;
	public static int counter = 0;

	public Slot() {
		this.slotID = counter;
		counter++;
	}

	public void addVehicleToSlot(Vehicle vehicle) {

		this.vehicle = vehicle;
		this.isAvailable = false;

	}

	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	public void removeVehicleFromSlot() {

		this.vehicle = null;
		this.isAvailable = true;

	}

	public int getSlotID() {
		return slotID;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setDimension(double length, double width) // function to set the dimensions of the vehicle
	{
		this.depth = length;
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public double getDepth() {

		return depth;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public String getSlotType() {
		return slotType;
	}

	@Override // function inhereted from class object
	public String toString() {
		if (!isAvailable)
			return "Vehicle's ID: " + vehicle.getID() + " is parked in slot " + slotID;
		else
			return "ID= " + slotID + " width = " + String.format("%.1f", width) + " cm and depth = "
					+ String.format("%.1f", depth) + " cm";
	}

}
