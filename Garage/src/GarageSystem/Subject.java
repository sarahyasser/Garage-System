package GarageSystem;

import java.util.List;

public interface Subject {
	public void subscribe(Subscriber s);

	public void unsubscribe(Subscriber s);

//	void notifySubscribers(Vehicle v, Timer t, double fees);
	// void notifySubscribers(Vehicle v, Timer t, double fees, Subscriber s);
	// void notifySubscribers(Vehicle v, Timer t, double fees, List<Subscriber>
	// list);
	void notifySubscribers(Vehicle v, Timer t, double fees, Subscriber sub);

}
