package GarageSystem;

public class VehicleBuilder implements IVehicleBuilder {

	private String modelName;
	private int modelYear;
	public int vehicleID = 0;
	public static int counter = 0;
	private double width;
	private double length;
	private String ownerMobileNo;
	private String email;

	@Override
	public VehicleBuilder setModelName(String modelName) {
		this.modelName = modelName;
		return this;
	}

	@Override
	public VehicleBuilder setModelYear(int modelYear) {
		this.modelYear = modelYear;
		return this;
	}

	@Override
	public VehicleBuilder setWidth(double width) {
		this.width = width;
		return this;
	}

	@Override
	public VehicleBuilder setLength(double length) {
		this.length = length;
		return this;
	}

	@Override
	public VehicleBuilder setOwnerMobileNo(String ownerMobileNo) {
		this.ownerMobileNo = ownerMobileNo;
		return this;
	}

	@Override
	public VehicleBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public Vehicle build() {
		// We can validate the arguments, before creating a CanadianAddress instance.
		Vehicle v = new Vehicle();
		v.setLength(length);
		v.setWidth(width);
		v.setEmail(email);
		v.setModelName(modelName);
		v.setModelYear(modelYear);

		v.setOwnerMobileNo(ownerMobileNo);

		return v;
	}
}
