package GarageSystem;

//This class is used to calculate the duration that a vehicle spends in a slot
public class Timer {
	protected double timeIn;
	protected double timeOut;
	protected double entryHour;
	protected double entryMins;
	protected double exitHour;
	protected double exitMins;
	protected String inTimeText;
	protected String outTimeText;

	public Timer() {
		super();
		this.timeOut = 0;
		this.exitHour = 0;
		this.exitMins = 0;
		this.entryHour = 0;
		this.entryMins = 0;
		this.timeIn = 0;

	}

	public Timer(double entryHours, double entryMins) {
		super();
		timeIn = calculateTimeIn(entryHours, entryMins);
	}

	public double calculateTimeIn(double entryHours, double entryMins) {
		this.inTimeText = String.format("%.0f", entryHours) + ":" + String.format("%.0f", entryMins);
		this.entryHour = entryHours;
		this.entryMins = entryMins;
		// calculate the entry time
		this.timeIn = entryHours + (entryMins / 60);
		return timeIn;

	}

	public void calculateTimeOut() {

		// calculate the exit time
		this.timeOut = exitHour + (exitMins / 60);

	}

	public double calculateDuration(double exitHour, double exitMins) {
		this.outTimeText = String.format("%.0f", exitHour) + ":" + String.format("%.0f", exitMins);
		this.exitHour = exitHour;
		this.exitMins = exitMins;
		calculateTimeOut();
		if (timeIn == timeOut) {
			System.out.println("You've spent 24 hours");
			timeIn += 24;
			return (timeIn - timeOut);
		} else if (this.entryHour > this.exitHour) {
			return (timeIn - timeOut);
		} else
			return (timeOut - timeIn);
	}

}