// Bongki Moon (bkmoon@snu.ac.kr)
/**
 * Represents a graph
 * supports Dijkstra Algorithm
 * @author Ha-Eun Hwangbo
 */
import java.util.*;

public class Planner {

	private HashMap<String, Airport> hm;
	public static final int DAY = 60 * 24;
	public static final int BIGNUM = 999999;
	
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

	private Flight findBestFlight(String s, String d, int time, boolean startFlag)
	{
		// time: time (in minutes) when passenger arrives at airport s
		Airport srcPort = hm.get(s);
		if (srcPort == null)
			return null;
		
		Destination dest = srcPort.findDest(d);
		if (dest == null)
			return null;
		
		int departTime = time;
		if (!startFlag) // if s is not "the" start airport, consider mct
		{
			departTime += srcPort.mct(); // earliest possible departure time considering mct
			if (departTime >= DAY) departTime -= DAY; // validity check
		}
		return dest.findBestFlight(departTime);
	}
	
	public Itinerary Schedule(String start, String end, String departure) 
	{
		// validity check
		if (!hm.containsKey(start) || !hm.containsKey(end))
			return new Itinerary(false);
		
		int departTime = convert2min(Integer.parseInt(departure));
		if (departTime >= DAY) departTime -= DAY; // validity check
		MinHeap minheap = new MinHeap(hm, start);
		
		// initialize
		minheap.extractMin(); // extract start airport
		for (HeapEntry v : minheap) // for each v in V-S (i.e. minheap)
		{
			Flight bestFlt = findBestFlight(start, v.name(), departTime, true);
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
			
			// validity check: when a unconnected airport is extracted
			if (minEntry.distance() >= BIGNUM || minFlight == null)
				return new Itinerary(false);
			
			for (HeapEntry v : minheap) //for each fringe v in V - S
			{
				Flight bestFlt =  findBestFlight(minEntry.name(), v.name(), minFlight.dtime(), false);
				if (bestFlt == null) // edge doesn't exist
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
		return (time / 60) * 100 + time % 60;
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

