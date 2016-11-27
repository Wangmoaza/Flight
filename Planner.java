// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

// TODO
// Think about this in hash map:
// 1. Key: Airport, Value: Linked List of flights
// 2. Key: String, Value: Airport (Linked list is in airport class)

public class Planner {

	private HashMap<String, Airport> hm;
	public static final int DAY = 60 * 24;
	
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
		
		ListIterator<Flight> fltIt = fltList.listIterator();
		while (fltIt.hasNext())
		{
			Flight flt = fltIt.next();
			Airport src = hm.get(flt.source());
			src.addFlight(flt);
		}
		
	}

	private Flight findBestFlight(String s, String d, int time)
	{
		Airport srcPort = hm.get(s);
		if (srcPort == null)
			return null;
		
		Destination dest = srcPort.findDest(d);
		if (dest == null)
			return null;
		
		int mct = srcPort.mct(); //mct in minutes
		int departTime = time + mct;
		return dest.findBestFlight(departTime);
	}
	
	public Itinerary Schedule(String start, String end, String departure) 
	{
		// construct initial Itinerary
		
		return new Itinerary();
	}
	
	public static int convert2min(int time)
	{
		return (time / 100) * 60 + (time % 100);
	}
	
	public static int convert2hr(int time)
	{
		return (time /60) * 100 + time % 60;
	}
	
	// calculate the duration of flight in minutes
	public static int duration(int start, int end)
	{
		int diff = end - start;
		if (diff >= 0)
			return diff;
		
		return diff + DAY;
	}
}

