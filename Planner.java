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
		int i = 0;
		
		for (Airport port : portList)
		{
			port.setId(i);
			hm.put(port.name(), port);
			i++;
		}
		
		for (Flight flt : fltList)
		{
			Airport src = hm.get(flt.source());
			src.addFlight(flt);
		}
	}

	private Flight findBestFlight(String s, String d, int time)
	{
		// time: time when passenger arrives at airport s
		Airport srcPort = hm.get(s);
		if (srcPort == null)
			return null;
		
		Destination dest = srcPort.findDest(d);
		if (dest == null)
			return null;
		
		int mct = srcPort.mct(); //mct in minutes
		int departTime = time + mct; // earliest possible departure time considering mct
		return dest.findBestFlight(departTime);
	}
	
	public Itinerary Schedule(String start, String end, String departure) 
	{
		int departTime = convert2min(Integer.parseInt(departure));
		MinHeap minheap = new MinHeap(hm, start);
		
		// initialize
		HeapEntry startEntry = minheap.extractMin();
		for (HeapEntry v : minheap) // for each v in V-S (i.e. minheap)
		{
			Flight bestFlt = findBestFlight(start, v.name(), departTime);
			if (bestFlt != null)
			{	
				// dist = waiting time + onboard time
				int dist = duration(departTime, bestFlt.stime()) + bestFlt.onboardTime(); 
				minheap.updateEntry(v.name(), bestFlt, dist);
			}
		}
		
		while (minheap.isInHeap(end)) // while end airport is in V-S (i.e. minheap)
		{
			HeapEntry minEntry = minheap.extractMin();
			Flight minFlight = minEntry.flight();
			for (HeapEntry v : minheap)
			{
				Flight bestFlt =  findBestFlight(minEntry.name(), v.name(), minFlight.dtime());
				if (bestFlt == null)
					continue;
				
				int dist = duration(minFlight.dtime(), bestFlt.stime()) + bestFlt.onboardTime();
				if (minEntry.distance() + dist < v.distance())
					minheap.updateEntry(v.name(), bestFlt, minEntry.distance() + dist);
			}
		}
		
		return new Itinerary(start, end, minheap);
	}
	
	public static int convert2min(int time)
	{
		return (time / 100) * 60 + (time % 100);
	}
	
	public static int convert2hr(int time)
	{
		return (time /60) * 100 + time % 60;
	}
	
	// calculate the duration of from start to end in minutes
	public static int duration(int start, int end)
	{
		int diff = end - start;
		if (diff >= 0)
			return diff;
		
		return diff + DAY;
	}
}

