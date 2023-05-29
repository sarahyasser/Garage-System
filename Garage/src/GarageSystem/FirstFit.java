package GarageSystem;

import java.util.List;

public class FirstFit implements ISlotFit {

	@Override
	public Slot slotSelection(List<Slot> list, Vehicle vehicle) {

		for (Slot i : list) {
			if (i.isAvailable && (i.getWidth() >= vehicle.getWidth()) && (i.getDepth() >= vehicle.getLength())) {
				return i;
			}
		}
		return null;
	}

}
