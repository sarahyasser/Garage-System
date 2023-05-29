package GarageSystem;

public interface IGarageBuilder {

	// void setSlots(int numberOfSlots,double low,double high);
	void setDimensions(double Length, double width);

	void setHourlyRate(double HourlyRate);

	// void generateSlots(int numberOfSlots, double width, double length, double
	// hourlyRate,String slotType);
	void createGarage();

	public Garage getGarage();
	// void generateRandomSlots(int numberOfSlots, double high, double low);

	// void buildSlots(int noOfSlots, double width, double length, String slotType);
	// void buildSlots(int numberOfSlots, double width, double length, double
	// hourlyRate, String slotType);
	void buildSlots(int numberOfSlots);

	void setSlotType(String slotType);

}
