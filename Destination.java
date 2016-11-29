/**
 * Destinations from Airport Vertex (neighboring Vertices)
 * @author Ha-Eun Hwangbo
 */

import java.util.*;

public class Destination {
	private String name;
	private LinkedList<Flight> fltList;
	
	public Destination(String s)
	{
		name = s;
		fltList = new LinkedList<>();
	}
	
	public String name()
	{
		return name;
	}
	
	public LinkedList<Flight> fltList()
	{
		return fltList;
	}
	
	public void addFlight(Flight flt)
	{
		fltList.addFirst(flt);
		System.out.println("\tadded flight: " + fltList.getFirst());
	}
	
	// returns the best flight from departure time t (t: arrival time + mct)
	public Flight findBestFlight(int t)
	{
		Flight best = fltList.getFirst();
		int bestTime = Planner.duration(t, best.stime()) + best.onboardTime(); // waiting time + flight time
		
		// iterate through fltList to find best flight
		System.out.println("*** Find best flight ***"); // FIXME delete this 
		ListIterator<Flight> it = fltList.listIterator();
		while (it.hasNext())
		{
			Flight flt = it.next();
			System.out.println("\t" + flt); // FIXME delete this
			int time = Planner.duration(t, flt.stime()) + flt.onboardTime();
			
			if (time < bestTime)
			{
				best = flt;
				bestTime = time;
			}
		}
		
		return best;
	}
}
