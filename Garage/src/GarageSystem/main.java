package GarageSystem;

import java.io.BufferedReader;
import java.lang.Long;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * @authors: Sarah Yasser Tageldin 20206029 , Fatma Ahmed Ragab 20206053
 *
 *           This program manages a parking space for a number of vehicles &
 *           helps the garage managers to track the garage easily and calculate
 *           the garage fees
 **/

public class main {
	public static void main(String[] args) throws IOException, NumberFormatException, SlotException, IDException {
		IGarageBuilder builder = new GarageBuilder();
		builder.createGarage();
		String choice = "";
		Scanner scan = new Scanner(System.in);

		int modelYear = 0;
		String modelName;
		ISlotFit selectionType;
		double width, length;
		String slotType;
		int n = 0;
		double hours, mins;

		// Taking the garage info from user (size,rows)
		GarageController garageCTR = GarageController.getInstance();// creating a garage object
		garageCTR.setCalculationMethod(new CalculateFeesByHourlyRate());

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner inputLinee = new Scanner(System.in);
		String[] Time = new String[2];

		Garage g = new Garage();

		int noOfSlots;
		double hourlyRate;

		while (!choice.equals("2")) {
			System.out.println("* * * * * * * * * * * * * * * * * * ");

			System.out.println("Creation Menu: ");
			System.out.println();
			// System.out.println("1-Generate randoms slots");
			System.out.println("1-Generate new slots");
			System.out.println("2-Create My Garage");
			System.out.println();
			System.out.println("* * * * * * * * * * * * * * * * * * ");

			System.out.println("Enter your choice: ");
			choice = scan.next();// taking the user's choice

			switch (choice) {

//		case "1":
//			double loww;
//			double highh;
//			int numOfSlots;
//			System.out.println("Enter the lowest in your range");
//			loww=scan.nextDouble();
//			System.out.println("Enter the highest in your range");
//			highh=scan.nextDouble();
//			System.out.println("Enter the number of slots");
//			numOfSlots=scan.nextInt();
//			builder.generateRandomSlots(numOfSlots, highh, loww);
//			break;

			case "1":
				System.out.print("Enter the Number of slots you want to Generate: ");
				noOfSlots = scan.nextInt();
				System.out.print("Enter the slot type: ");
				slotType = inputLinee.nextLine();
				System.out.print("Enter The width of this slot type: ");
				width = scan.nextDouble();

				System.out.print("Enter The length of this slot type: ");
				length = scan.nextDouble();
				System.out.print("Enter The hourly rate of this slot type: ");
				hourlyRate = scan.nextDouble();
				builder.setDimensions(length, width);
				builder.setHourlyRate(hourlyRate);
				builder.setSlotType(slotType);
				builder.buildSlots(noOfSlots);
				break;

			case "2":
				g = builder.getGarage();
				g.displayGarage();
				break;
			}
		}
		Vehicle[] vehicles = new Vehicle[9];
		Random r = new Random();
		double low = 150;
		double high = 500;

		for (int i = 0; i < 9; i++) {
			double w = r.nextDouble(high - low) + low;
			double l = r.nextDouble(high - low) + low;

			vehicles[i] = new VehicleBuilder().setModelName("s").setModelYear(2003).setOwnerMobileNo("01027758872")
					.setEmail("Sarah@gmail.com").setWidth(w).setLength(l).build();
		}

		selectionType = new BestFit();
		System.out.println("Best Fit: \n");
		for (int i = 0; i < 5; i++) {
			garageCTR.setSelectionMethod(new BestFit());
			System.out.println(vehicles[i]);
			garageCTR.parkIn(vehicles[i], 1, 30, g, new SMS()); // AM
		}

		System.out.println("\n \nFirst Fit: \n");
		selectionType = new FirstFit();
		for (int i = 5; i < 9; i++) {

			garageCTR.setSelectionMethod(new FirstFit());
			System.out.println(vehicles[i]);
			garageCTR.parkIn(vehicles[i], 1, 30, g, new Email()); // AM
		}
		g.displayGarage();

		while (!choice.equals("13")) {
			System.out.println("* * * * * * * * * * * * * * * * * * ");

			System.out.println("Menu: ");

			System.out.println("1- Park In a car");
			System.out.println("2-Park Out a Car & Calculate Fees");
			System.out.println("3-Calculate Total Income");
			System.out.println("4-Get total Number Of vehicles");
			System.out.println("5-Display Garage ");
			System.out.println("6-Number Of Available ParkingSlots ");
			System.out.println("7-Add new Slots with new dimensions ");
			System.out.println("8-Add new slots to an existing slot type");
			System.out.println("9-Remove Slots by slot type ");
			System.out.println("10-Remove one slot by slot ID ");
			System.out.println("11-Update the dimensions of a slot type");
			System.out.println("12-Update the hourly rate of a slot type");
			System.out.println("13-Exit");
			System.out.println("* * * * * * * * * * * * * * * * * * ");

			System.out.println("Enter your choice: ");
			choice = scan.next();// taking the user's choice

			switch (choice) {

			case "1":
				// (try-catch to handle the SlotException) check if garage is full
				try {
					garageCTR.getAvailableSlots(g.getList());
				} catch (SlotException e) {

					System.out.println(e.getMessage());
					break;
				}

				// taking the vehicle's info from user (name name & model year)
				System.out.print("\nEnter the model name of the vehicle: ");
				modelName = scan.next();
				System.out.print("\nEnter the model year of the vehicle: ");
				modelYear = scan.nextInt();
				// check if the year is valid
				while (1900 > modelYear || modelYear > 2022) {
					System.out.print("\nINVALID YEAR (please enter a year between 1900 & 2022 ");
					System.out.print("\nEnter the model year of the vehicle: ");
					modelYear = scan.nextInt();
				}
				System.out.print("\nEnter the vehicle's dimensions: \n width:  ");
				width = scan.nextDouble();
				System.out.print("Length: ");
				length = scan.nextDouble();

				boolean foundAvailavleSlotsForSpecificDimensions = garageCTR
						.displayAvaialableSlotsForSpecificDimensions(width, length, g.getList());
				if (!foundAvailavleSlotsForSpecificDimensions) {
					System.out.println("There is no avaialable slots suitable for this dimensions");
					break;
				}
				System.out.println("choose the type of selection : \n 1-Best fit            2-First fit");
				System.out.print("\n Enter your choice: ");
				n = scan.nextInt();
				if (n == 1) {
					garageCTR.setSelectionMethod(new BestFit());
				} else {
					garageCTR.setSelectionMethod(new FirstFit());

				}

				// taking the entry hours + mins from user
				System.out.print("\nEnter the entry time in hh:mm format: ");
				Time = in.readLine().split(":");
				hours = Double.parseDouble(Time[0]);
				mins = Double.parseDouble(Time[1]);

				while (hours > 12 || mins > 59) {
					// if mins are not valid ,allow the user to enter another mins
					System.out.print(">>INVALID TIME \nEnter hour between 1 & 12 and minutes between 00 & 59: ");
					Time = in.readLine().split(":");
					hours = Double.parseDouble(Time[0]);
					mins = Double.parseDouble(Time[1]);

				}
				// check if it's AM or PM --> if pm , add 12 on the hour
				System.out.print("\n PM  or AM?");
				System.out.print("\n Enter (1) for PM       (2) for AM : ");
				System.out.print("\n Enter your choice: ");
				int Period = scan.nextInt();

				if (Period == 1) {
					hours += 12;

				}
				// Letting the user enter the desired slot

				// (try-catch to handle the IDException) check if garage is full
				System.out.println("How do you want to be notified by the parking fees ? ");
				System.out.println("1-By Email    2-By SMS");
				int c = 0;
				Vehicle v;
				c = scan.nextInt();
				switch (c) {
				case 1:
					System.out.print("\nEnter the email address of the Vehicle owner: ");
					String email = scan.next();
					v = new VehicleBuilder().setModelName(modelName).setModelYear(modelYear).setEmail(email)
							.setWidth(width).setLength(length).build();
					garageCTR.parkIn(v, hours, mins, g, new Email());

					break;
				case 2:
					System.out.println("Enter The Owner's mobile number: ");
					String mobileNo = scan.next();

					while (mobileNo.length() != 11) {
						System.out.print("\nEnter a proper Mobile Number (i.e. Must be 11 digits): ");
						mobileNo = scan.next();
					}

					while (true) {
						if (mobileNo.charAt(0) != '0' || mobileNo.charAt(1) != '1') {
							System.out.print("\nEnter a proper Mobile Number (i.e. Must starts with '01'): ");
							mobileNo = scan.next();
						} else
							break;
					}

					while (mobileNo.charAt(2) > '2') {
						System.out.print(
								"\nEnter a proper Mobile Number (i.e. Must starts with '010'or '011' or '012' or '015'): ");
						mobileNo = scan.next();
					}
					v = new VehicleBuilder().setModelName(modelName).setModelYear(modelYear).setOwnerMobileNo(mobileNo)
							.setWidth(width).setLength(length).build();
					garageCTR.parkIn(v, hours, mins, g, new SMS());
					break;

				}

				g.displayGarage();

				break;

			case "2":
				// taking the exit info from user (slot ID,exit hours &mins)
				System.out.print("\nEnter the slot you want to free: ");
				int id = scan.nextInt();
				while (!garageCTR.checkIfSlotIDExists(id, g.getList())) {
					System.out.print("\nThis slot is not in your garage, Enter another slot: ");
					id = scan.nextInt();
				}

				System.out.print("\nEnter the exit time in hh:mm format: ");
				Time = in.readLine().split(":");
				hours = Double.parseDouble(Time[0]);
				mins = Double.parseDouble(Time[1]);

				while (hours > 12 || mins > 59) {
					// if mins are not valid ,allow the user to enter another mins
					System.out.print(">>INVALID TIME \nEnter hour between 1 & 12 and minutes between 00 & 59: ");
					Time = in.readLine().split(":");
					hours = Double.parseDouble(Time[0]);
					mins = Double.parseDouble(Time[1]);

				}
				System.out.print("\n PM or AM?");
				System.out.print("\n Enter (1) for PM or (2) for AM : ");
				System.out.print("\n Enter your choice: ");
				Period = scan.nextInt();

				if (Period == 1) {
					hours += 12;
				}

				double fees = 0;

				// (try-catch to handle the IDException) check if garage is full

				while (true) {
					try {
						// calling the parkOut method in Garage class & getting the fees
						fees = garageCTR.parkOut(id, hours, mins, g);
						break;
					} catch (IDException e) {
						System.out.println(e.getMessage());
						System.out.print("Enter another slot ID: ");
						id = scan.nextInt();
						continue;
					}
				}
				// printing the parking fees
				System.out.println("Parking fees= " + String.format("%.2f", fees) + "EGP");
				g.displayGarage();

				break;

			case "3":
				// calling the getTotalIncome method in Garage Class
				System.out.println("Total income= " + String.format("%.2f", g.getTotalIncome()) + "EGP");

				break;

			case "4":
				// calling the getTotalNumberOfVehicles method in Garage Class
				System.out.println("Total number of cars that used the garage = " + g.getTotalVehicles());

				break;

			case "5":
				// calling the displayAvailableSlots method in Garage Class
				g.displayGarage();
				break;

			case "6":
				// Try and catch to handle the Slot Exception (if no of available slots=0)
				try {
					System.out.println("Total number of available slots = " + garageCTR.getAvailableSlots(g.getList()));
				} catch (SlotException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "7":
				System.out.print("Enter the slotType for the new Slots: ");

				slotType = inputLinee.nextLine();
				while (garageCTR.checkIfSlotTypeExists(slotType, g.getList())) {
					System.out.println("This slot type already exist");
					System.out.print("Enter another slot type for the new Slots that doesn't already exist: ");

					slotType = inputLinee.nextLine();
				}

				System.out.println("Enter the Number of slots you want to add: ");
				noOfSlots = scan.nextInt();
				System.out.print("Enter the width for the new Slots: ");
				width = scan.nextDouble();
				System.out.print("Enter the length for the new Slots: ");
				length = scan.nextDouble();

				System.out.print("Enter the hourly rate for the new Slots: ");

				hourlyRate = scan.nextDouble();
				garageCTR.addNewSlotsWithNewDimensions(noOfSlots, width, length, slotType, hourlyRate, g.getList());
				g.displayGarage();
				System.out.println("New Slots are added successfully");
				break;
			case "8":
				System.out.print("Enter the Slot Type you want to add to: ");
				slotType = inputLinee.nextLine();
				while (!garageCTR.checkIfSlotTypeExists(slotType, g.getList())) {
					System.out.println("This slot type doesn't exist");
					System.out.print("Enter another slot type that already exist: ");

					slotType = inputLinee.nextLine();
				}
				System.out.println("Enter the Number of slots you want to add: ");
				noOfSlots = scan.nextInt();

				garageCTR.addSlotsToAnExistingSlotType(slotType, noOfSlots, g.getList());
				g.displayGarage();
				System.out.println("Slots are added successfully in " + slotType + " slots");

				break;

			case "9":
				System.out.print("Enter the Slot Type you want to remove: ");
				slotType = inputLinee.nextLine();

				while (!garageCTR.checkIfSlotTypeExists(slotType, g.getList())) {
					System.out.println("This slot type doesn't exist");
					System.out.print("Enter another slot type that already exists: ");

					slotType = inputLinee.nextLine();
				}
				garageCTR.removeSlotsFromTheGarage(slotType, g.getList());
				g.displayGarage();
				System.out.println("Slots are removed successfully");
				break;
			case "10":

				System.out.print("Enter the slot ID you want to remove: ");
				int ID;
				ID = scan.nextInt();
				while (true) {
					try {
						if (garageCTR.removeOneSlot(ID, g.getList())) {
							System.out.println("Slot removed successfully");
							break;
						}

					} catch (IDException e) {

						System.out.println(e.getMessage());
						System.out.print("Enter another slot ID: ");
						ID = scan.nextInt();
						continue;

					} catch (SlotException e) {
						System.out.println(e.getMessage());
						System.out.print("Enter another slot ID: ");
						ID = scan.nextInt();
						continue;

					}
				}

				g.displayGarage();
				break;

			// change hourly rate of a type
			case "11":
				System.out.println("Enter the slot type that you want to update the dimensions of: ");
				slotType = inputLinee.nextLine();
				while (!garageCTR.checkIfSlotTypeExists(slotType, g.getList())) {
					System.out.println("This slot type doesn't exist");
					System.out.print("Enter another slot type for the new Slots that already exist: ");

					slotType = inputLinee.nextLine();
				}
				System.out.println("Enter the new hourly rate: ");
				hourlyRate = scan.nextDouble();

				garageCTR.updateHourlyRate(slotType, hourlyRate, g.getList());
				g.displayGarage();
				System.out.println("Hourly Rate of the slots are updated");

				break;
			case "12":
				System.out.println("Enter the slot type that you want to update the dimensions of: ");
				slotType = inputLinee.nextLine();
				while (!garageCTR.checkIfSlotTypeExists(slotType, g.getList())) {
					System.out.println("This slot type doesn't exist");
					System.out.print("Enter another slot type for the new Slots that already exist: ");

					slotType = inputLinee.nextLine();
				}
				System.out.println("Enter the new width: ");
				width = scan.nextDouble();
				System.out.println("Enter the new length: ");
				length = scan.nextDouble();

				garageCTR.updateDimensions(slotType, width, length, g.getList());
				g.displayGarage();
				System.out.println("Dimensions of the slots are updated");

				break;

			case "13":
				System.out.print("You exit the program");
				break;
			default:

				System.out.println("Please Enter a proper choice");

			}

		}
	}
}
