// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

// TODO
// Think about this in hash map:
// 1. Key: Airport, Value: Linked List of flights
// 2. Key: String, Value: Airport (Linked list is in airport class)

public class Planner {

	private HashMap<String, Airport> hm;
	public static final int BIGNUM = 9999999;
	
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
		
		int min = BIGNUM;
		
		
	}
	
	public static int addTime(int t1, int t2)
	{
		int min = t1 % 100 + t2 % 100;
		int hr = t1 / 100 + t2 / 100 + min / 60;
		return hr * 100 + min % 60;
	}
	
	public static int substractTime(int t1, int t2)
	{
		int big = 0;
		int small = 0;
		if (t1 > t2)
		{
			big = t1;
			small = t2;
		}
		else if (t1 < t2)
		{
			big = t2;
			small = t1;
		}
		else
			return 0;
		
		int min = big % 100 - small % 100;
		int hr = big / 100 - small / 100;
		if (min < 0)
		{
			
		}
		
	}
	
	public Itinerary Schedule(String start, String end, String departure) 
	{
		return new Itinerary();
	}

}

