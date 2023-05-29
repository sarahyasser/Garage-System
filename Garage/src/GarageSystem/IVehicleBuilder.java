package GarageSystem;

public interface IVehicleBuilder {

	VehicleBuilder setModelName(String modelName);

	VehicleBuilder setModelYear(int modelYear);

	VehicleBuilder setWidth(double width);

	VehicleBuilder setLength(double length);

	VehicleBuilder setOwnerMobileNo(String ownerMobileNo);

	VehicleBuilder setEmail(String email);

	Vehicle build();

}
