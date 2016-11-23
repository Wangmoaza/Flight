// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

// TODO
// Think about this in hash map:
// 1. Key: Airport, Value: Linked List of flights
// 2. Key: String, Value: Airport (Linked list is in airport class)

public class Planner {

	private HashMap<String, Airport> hm;
	
  // constructor
	public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) 
	{
		hm = new HashMap<>();
		ListIterator<Airport> it = portList.listIterator(0);
		while (it.hasNext()) 
		{
			Airport port = it.next();
			hm.put(port.name(), port);
		}
		
		// TODO
	}

	public Itinerary Schedule(String start, String end, String departure) {}

}

