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
		// put airports to hash map hm by iterating through the linked list
		hm = new HashMap<>();
		ListIterator<Airport> portIt = portList.listIterator();
		while (portIt.hasNext()) 
		{
			Airport port = portIt.next();
			hm.put(port.name(), port);
		}
		
		// TODO
		ListIterator<Flight> fltIt = fltList.listIterator();
		while (fltIt.hasNext())
		{
			Flight flt = fltIt.next();
			Airport src = hm.get(flt.source());
			src.addFlight(flt);
		}
		
	}

	public Itinerary Schedule(String start, String end, String departure) 
	{
		return new Itinerary();
	}

}

