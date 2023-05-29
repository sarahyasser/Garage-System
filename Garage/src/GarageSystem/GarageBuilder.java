package GarageSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GarageBuilder implements IGarageBuilder {

	private Garage garage;
	private List<Slot> listOfParkingSlots = new ArrayList<>();// Used to store the slots
	private int size = 0;
	private double width;
	private double length;
	private double hourlyRate;
	private String slotType;

	@Override
	public void createGarage() {
		this.garage = new Garage();
	}

	@Override

	public void buildSlots(int numberOfSlots) {

		size += numberOfSlots;

		for (int i = listOfParkingSlots.size(); i < (size); i++) {

			Slot slot = new Slot();// width 50->120 , length 50->200
			listOfParkingSlots.add(slot);
			slot.setDimension(length, width);
			slot.setHourlyRate(hourlyRate);
			slot.setSlotType(slotType);

		}

	}

	@Override
	public void setDimensions(double length, double width) {
		// TODO Auto-generated method stub
		this.length = length;
		this.width = width;
		// listOfParkingSlots.get(listOfParkingSlots.size()-1).setDimension(length,
		// width);

	}

	@Override
	public void setSlotType(String slotType) {
		this.slotType = slotType;
		// listOfParkingSlots.get(listOfParkingSlots.size()-1).setSlotType(slotType);
	}

	@Override
	public void setHourlyRate(double hourlyRate) {
		// TODO Auto-generated method stub
		this.hourlyRate = hourlyRate;
		// listOfParkingSlots.get(listOfParkingSlots.size()-1).setHourlyRate(hourlyRate);

	}

	public List<Slot> getListOfSlots() {
		return listOfParkingSlots;
	}

	public Garage getGarage() {
		garage.setListOfSlots(listOfParkingSlots);
		garage.setSize(size);
		return garage;
	}

}
