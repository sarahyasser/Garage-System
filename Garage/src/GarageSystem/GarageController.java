package GarageSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GarageController implements Subject {
	// private int size;
	private ISlotFit selectionMethod;
	private ICalculateFees calculationMethod;
	private HashMap<Integer, Timer> timeMap = new HashMap<Integer, Timer>();// used to map (or connect) the slot's ID
																			// with Timer object
	private static GarageController garage = null;
	private HashMap<Vehicle, Subscriber> notificationMap = new HashMap<Vehicle, Subscriber>();
	private List<Subscriber> listOfSubscribers = new ArrayList<>();

	private GarageController() {
	}

	public static GarageController getInstance() {
		if (garage == null) {
			garage = new GarageController();
		}
		return garage;
	}

	/**
	 * function to add new slots with new attributes to the Garage
	 * 
	 * @param noOfSlots
	 * @param width
	 * @param length
	 * @param slotType
	 * @param hourlyRate
	 */
	public void addNewSlotsWithNewDimensions(int noOfSlots, double width, double length, String slotType,
			double hourlyRate, List<Slot> listOfParkingSlots) {
		// System.out.println(size);
		for (int i = 0; i < noOfSlots; i++) {
			Slot slot = new Slot();// width 120->200 , length 200->300
			slot.setDimension(length, width);
			slot.setSlotType(slotType);
			slot.setHourlyRate(hourlyRate);
			listOfParkingSlots.add(slot);
		}
		// size+=noOfSlots;
		// availableSlots+=noOfSlots;

	}

	/**
	 * function to add slots to an existing slot type
	 * 
	 * @param slotType
	 * @param noOfSlots
	 */
	public void addSlotsToAnExistingSlotType(String slotType, int noOfSlots, List<Slot> listOfParkingSlots) {

		int newID = listOfParkingSlots.size() + noOfSlots; // to set IDs in the right order

		for (int i = listOfParkingSlots.size() - 1; i > 0; i--) // loop over the list from the back
		{

			Slot slot = listOfParkingSlots.get(i); // get the slot from the current index
			if (slot.getSlotType().equals(slotType)) // if slot type was found
			{
				// get the slot's properties
				double width = slot.getWidth();
				double length = slot.getDepth();
				double hourlyRate = slot.getHourlyRate();
				String s = slot.getSlotType();
				for (int j = noOfSlots; j > 0; j--) // to decrease the ID
				{
					Slot slot1 = new Slot();// width 120->200 , length 200->300
					slot1.setDimension(length, width);

					slot1.setSlotType(slotType);
					slot1.setHourlyRate(hourlyRate);
					listOfParkingSlots.add(slot1);
					((List<Slot>) listOfParkingSlots).add(i + 1, slot1); // add in the middle of the list ,at the end of
																			// the desired slot type
					// size++; //increment the size
					newID--;
				}
				// availableSlots+=noOfSlots;
				break; // break out of the loop because the slots are added so no need to keep looping

			}

		}

	}

	/**
	 * Function to remove a slot type from the garage
	 * 
	 * @param slotType
	 */
	public void removeSlotsFromTheGarage(String slotType, List<Slot> listOfParkingSlots) {
		// if there are no avaialable slots ,we can't remove
//    	try{
//    		getAvailableSlots();
//    	}
//    	catch(SlotException e){
//
//            System.out.println(e.getMessage());
//            
//        }
		int counter = 0;

		for (int i = 0; i < listOfParkingSlots.size(); i++) // loop over the list of slots
		{
			Slot slot = listOfParkingSlots.get(i);// get the slot from index
			if (slot.getSlotType().equals(slotType)) {
				// if slot is not taken by a vehicle
				if (slot.isAvailable) {
					slot = null; // deleting the object by asssigning it to null
					listOfParkingSlots.remove(i); // remove the slot from the list by its index
					i--; // to check on the slot at this index after deleting the old slot that was at
							// this index (slots are going to be shiftted backwards)
					// size--;
					counter++;
				} else {
					System.out.println("Couldn't remove Slot (" + slot.getSlotID() + ") because it contains a vehicle");
				}
			}
		}
		// availableSlots-=counter;

	}

	/**
	 * Remove one slot by its ID
	 * 
	 * @param ID
	 * @return
	 * @throws IDException
	 * @throws SlotException
	 */
	public boolean removeOneSlot(int ID, List<Slot> listOfParkingSlots) throws IDException, SlotException {
//    	//if there are no avaialable slots ,we cant remove
//    	try{
//    		getAvailableSlots();
//    	}
//    	catch(SlotException e){
//
//            System.out.println(e.getMessage());
//            
//        }
		// if ID doesn't exist
		if (!checkIfSlotIDExists(ID, listOfParkingSlots)) {
			throw new IDException("The ID you entered is not in your garage");
		}

		boolean found = false;
		for (int i = 0; i < listOfParkingSlots.size(); i++) {
			Slot slot = listOfParkingSlots.get(i);
			if (slot.getSlotID() == ID)// if ID is found
			{
				if (slot.isAvailable) // if slot doesn't contain a vehicle
				{
					found = true;
					slot = null; // deleting the obj by assigning it to null
					listOfParkingSlots.remove(i); // remove from the list
					// availableSlots--;
					// size--;
					i--;
				} else {
					throw new SlotException(
							"Couldn't remove Slot (" + slot.getSlotID() + ") because it contains a vehicle");
				}

			}
		}
		// Throws exception if slot is not in range

		return found;

	}

	/**
	 * Function to check if the slot ID is valid
	 * 
	 * @param ID
	 * @return
	 */
	public boolean checkIfSlotIDExists(int ID, List<Slot> listOfParkingSlots) {
		for (Slot i : listOfParkingSlots) {
			if (i.getSlotID() == ID) // return true if it's found
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Function to check if the slot type is valid
	 * 
	 * @param slotType
	 * @return
	 */
	public boolean checkIfSlotTypeExists(String slotType, List<Slot> listOfParkingSlots) {

		for (Slot i : listOfParkingSlots) {
			if (slotType.equals(i.getSlotType())) // return true if it's found
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Funtion to call the selection method and throws an exception if no slot was
	 * suitable for the vehicle's dimensions
	 * 
	 * @param vehicle
	 * @return
	 * @throws SlotException
	 */
	public Slot selectSlot(Vehicle vehicle, List<Slot> listOfParkingSlots) throws SlotException {
		Slot s = selectionMethod.slotSelection(listOfParkingSlots, vehicle); // get the right slot from the
																				// selectionMethod function
		if (s == null) // if condition to check if there is no avalialbe slots
		{
			throw new SlotException("No Avaialable Slots for this vehicle's dimensions\n"); // throws an
																							// exception(custom) if the
																							// condition is true

		} else // returns the available slots if the condition is false
		{
			return s;
		}

	}

	/**
	 * Function that adds vehicle to a slot
	 * 
	 * @param vehicle
	 * @param entryHours
	 * @param entryMins
	 */

	public void parkIn(Vehicle vehicle, double entryHours, double entryMins, Garage g, Subscriber notificationMethod) {
		List<Slot> listOfParkingSlots = g.getList();
		Slot s = null;
		// handles the exception if the no suitable slot was found
		try {
			s = selectSlot(vehicle, listOfParkingSlots);
		} catch (SlotException e) {

			System.out.println(e.getMessage());
			return;
		}

		s.addVehicleToSlot(vehicle);
		System.out.println("Parked in successfully in ID: " + s.getSlotID() + " , " + s.getSlotType() + " slot\n");
		Timer calculator = new Timer(entryHours, entryMins); // creating a object from timer class

		timeMap.put(s.getSlotID(), calculator); // add the slot's ID and the timer object to the map
		g.setTotalVehicles(1);// increment no of totalVehicles
//		availableSlots--; //decrement no of  availableSlots
		notificationMap.put(vehicle, notificationMethod);
		subscribe(notificationMethod);

	}

	/*
	 * This function is used to remove a vehicle & calculate it's Parking fees This
	 * function takes slot ID as a parameter to help find the slot's timer object
	 * from timeMap This function returns the amount of fees that the vehicle is
	 * supposed to pay
	 * 
	 */
	public double parkOut(int slotID, double exitHours, double exitMins, Garage g) throws IDException {
		List<Slot> listOfParkingSlots = g.getList();
		// getting the timer object from the timeMap using the slot ID
		Timer t = timeMap.get(slotID);
		// Throws Exception if Time object of this Slot ID is equal to null
		if (t == null) {
			throw new IDException("This slot is already empty ");
		}
		// loop over the whole list
		Slot slot = null;
		Vehicle v = null;

		for (Slot i : listOfParkingSlots) {

			// find the right slot by matching IDs
			if (i.getSlotID() == slotID) {
				// remove the vehcile
				slot = i;
				v = slot.getVehicle();

				i.removeVehicleFromSlot();
				timeMap.put(i.getSlotID(), null);
				break;
			}

		}

		// calculate fees
		// subscribe(notificationMap.get(v));
		double fees = calculationMethod.calculateFees(t.calculateDuration(exitHours, exitMins), slot);
		notifySubscribers(v, t, fees, notificationMap.get(v));
		unsubscribe(notificationMap.get(v));
		notificationMap.put(null, null);
		g.setTotalIncome(fees);
//    	totalIncome+=fees;//increase total income by the value of the fees
		// availableSlots++;//inc available slots
		return fees;

	}

	/**
	 * function that return no of available slots (has an exception )
	 * 
	 * @return
	 * @throws SlotException
	 */
	public int getAvailableSlots(List<Slot> listOfParkingSlots) throws SlotException {

		int counter = 0;
		for (Slot i : listOfParkingSlots) {
			if (i.isAvailable) {
				counter++;
			}

		}
		int availableSlots = counter;

		if (availableSlots == 0) // if condition to check if there is no avalialbe slots
		{
			throw new SlotException("The garage is full"); // throws an exception(custom) if the condition is true
		} else // returns the available slots if the condition is false
		{
			return availableSlots;
		}
	}

	/**
	 * Display of Avaialable Slots For Specific Dimensions
	 * 
	 * @param v
	 * @return
	 */

	public boolean displayAvaialableSlotsForSpecificDimensions(double width, double length,
			List<Slot> listOfParkingSlots) {
		System.out.println("\n\n-----------------------------------------------------");
		System.out.println("\nThe Display of Avaialable Slots For Specific Dimensions\n\n");
		boolean found = false;
		int counter = 0;// count the slots at a specific slot type , restart it when the slot type
						// changes
		int countSlots = -1;
		String slotType = "~$*";

		for (Slot i : listOfParkingSlots) {
			if (countSlots == -1) // if it's the first slot in the list -> print its slot type
			{
				System.out.print(i.getSlotType() + " Slots:  (" + i.getHourlyRate() + " EGP)  \n\n");
				countSlots++;
				slotType = i.getSlotType(); // string to store the current slot type in
			}
			if (slotType != i.getSlotType()) // if slot type changed
			{
				if (counter == 0) // if counter of slots =0 that means that there are no available slots in this
									// slot type
				{
					System.out.println("\t\t No Avaialable slots\n");
				}

				else {
					counter = 0;// restart counter
				}
				System.out.print("\n" + i.getSlotType() + " Slots  (" + i.getHourlyRate() + " EGP) : \n\n");
				slotType = i.getSlotType(); // change the slot type

			}
			if (i.isAvailable)// if slot was found
			{

				if (length <= i.getDepth() && width <= i.getWidth()) {
					found = true;
					if (i.getSlotID() > 9) {
						System.out.print(" | " + i + "|   \n");
					} else
						System.out.print(" | " + i + " |   \n");
					counter++; // increase slot counter
					countSlots++;

				}
			}
		}
		if (counter == 0) {
			System.out.println("\t\tNo Avaialable slots\n");
		}
		System.out.println("\n\n-----------------------------------------------------\n");
		return found;
	}

	/**
	 * Function to update the hourly rate of a slot type
	 * 
	 * @param slotType
	 * @param hourlyRate
	 * @return
	 */
	public boolean updateHourlyRate(String slotType, double hourlyRate, List<Slot> listOfParkingSlots) {
		boolean found = false;
		for (int i = 0; i < listOfParkingSlots.size(); i++) {
			Slot slot = listOfParkingSlots.get(i);
			if (slot.getSlotType().equals(slotType)) {
				found = true;
				slot.setHourlyRate(hourlyRate);
			}
		}

		return found;
	}

	/**
	 * Function to update the dimensions of a slot type
	 * 
	 * @param slotType
	 * @param width
	 * @param length
	 */
	public void updateDimensions(String slotType, double width, double length, List<Slot> listOfParkingSlots) {

		for (int i = 0; i < listOfParkingSlots.size(); i++) {
			Slot slot = listOfParkingSlots.get(i);
			if (slot.getSlotType().equals(slotType)) {

				slot.setDimension(length, width);
			}
		}

	}

	/**
	 * setter
	 * 
	 * @param selectionMethod
	 */
	public void setSelectionMethod(ISlotFit selectionMethod) {
		this.selectionMethod = selectionMethod;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public ISlotFit getSelectionMethod() {
		return selectionMethod;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public ICalculateFees getCalculationMethod() {

		return calculationMethod;
	}

	/**
	 * setter
	 * 
	 * @param calculationMethod
	 */
	public void setCalculationMethod(ICalculateFees calculationMethod) {
		this.calculationMethod = calculationMethod;
	}

	@Override
	public void subscribe(Subscriber s) {
		// TODO Auto-generated method stub
		listOfSubscribers.add(s);
	}

	@Override
	public void unsubscribe(Subscriber s) {
		// TODO Auto-generated method stub

		for (int i = 0; i < listOfSubscribers.size(); i++) {

			listOfSubscribers.remove(s);
		}
	}

	@Override
	public void notifySubscribers(Vehicle v, Timer t, double fees, Subscriber sub) {
		for (Subscriber s : listOfSubscribers) {
			if (s.equals(sub))
				s.sendMessage(v, t, fees);

		}

	}

}
