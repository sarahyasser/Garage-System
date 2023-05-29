package GarageSystem;

import java.util.ArrayList;
import java.util.List;

public class Garage {
	private List<Slot> listOfParkingSlots = new ArrayList<>();// Used to store the slots
	private static int totalVehicles = 0;
	private static double totalIncome = 0;
	private int size;

//    public CustomizedGarage(GarageBuilder garageBuilder) {
//		this.listOfParkingSlots=garageBuilder.getListOfSlots();
//		this.selectionMethod=garageBuilder.getSlotFitMethod();
//		this.availableSlots=listOfParkingSlots.size();		
//	}

	public void setListOfSlots(List<Slot> list) {
		this.listOfParkingSlots = list;
	}

	/**
	 * This function is used to print out the whole garage (the list of slots)
	 */
	public void displayGarage() {
		int counter = 0;
		String slotType = "~$*";
		System.out.print(
				"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");

		// loop over the entire list
		for (Slot i : listOfParkingSlots) {

			if (slotType != i.getSlotType()) {

				System.out.println("\n\n" + i.getSlotType() + " Slots: \n\n");
				slotType = i.getSlotType();
				counter = 0;
			}

			if (counter == 3) {
				System.out.println();
				counter = 0;// counter begins again from zero and it'll keep increasing again until it's
							// equal to the number rows
			}
			if (i.isAvailable) // if condition to check if the slot is avaiable
			{
				if (i.getSlotID() > 9) {
					System.out.print(" | " + i + "|   ");
				} else
					System.out.print(" | " + i + " |   ");

				counter++;// increment counter
			} else {

				if (i.getSlotID() > 9 || i.getVehicle().getID() > 9) {
					System.out.print(" |    *" + i + "*   |   ");
				} else
					System.out.print(" |    *" + i + "*    |   ");

				counter++;// increment counter

			}
		}
		System.out.println(
				"\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");

	}

	public static int getTotalVehicles() {
		return totalVehicles;
	}

	public static void setTotalVehicles(int n) {
		Garage.totalVehicles += n;
	}

	public static double getTotalIncome() {
		return totalIncome;
	}

	public static void setTotalIncome(double n) {
		Garage.totalIncome += n;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Slot> getList() {
		return listOfParkingSlots;
	}

}
