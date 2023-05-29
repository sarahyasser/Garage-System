package GarageSystem;

import java.util.List;

public class BestFit implements ISlotFit {

	@Override
	public Slot slotSelection(List<Slot> list, Vehicle v) {
		double widthDiff = 3000;
		double lengthDiff = 3000;
		double diff1;
		double diff2;
		Slot candidateSlot = null;
		for (Slot i : list) {
			if (i.isAvailable) {
				if ((v.getLength() == i.getDepth()) && (v.getWidth() == i.getWidth())) {
					return i;
				} else {
					if (i.isAvailable && (i.getWidth() >= v.getWidth()) && (i.getDepth() >= v.getLength())) {
						diff1 = i.getWidth() - v.getWidth();
						diff2 = i.getDepth() - v.getLength();

						if ((diff1 < widthDiff) && (diff2 < lengthDiff)) {
							widthDiff = diff1;
							lengthDiff = diff2;
							candidateSlot = i;
						}

					}
				}

			}
		}
		return candidateSlot;
	}

}
