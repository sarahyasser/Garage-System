package GarageSystem;

public class SMS implements Subscriber {

	@Override
	public void sendMessage(Vehicle v, Timer t, double fees) {
		System.out.println("---------------------------------------");
		System.out.println("Your Parking Fees are: " + fees + " EGP\n Entry time: " + t.inTimeText + "  Out time: "
				+ t.outTimeText);
		System.out.println("This message is sent by SMS to ( " + v.getOwnerMobileNo() + " )");
		System.out.println("---------------------------------------");
	}

}
