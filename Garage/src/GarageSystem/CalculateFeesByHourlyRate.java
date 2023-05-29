package GarageSystem;

public class CalculateFeesByHourlyRate implements ICalculateFees {

	public double calculateFees(double duration, Slot slot) {
		double parkingFees;
		parkingFees = duration * slot.getHourlyRate();
		// return the fees
		return parkingFees;

	}
}