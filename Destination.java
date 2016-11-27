import java.util.*;

public class Destination {
	private String name;
	private LinkedList<Flight> fltList;
	public static int BIGNUM = 9999999;
	
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
	}
	
	public Flight findBestFlight(int t)
	{
		// returns the best flight from departure time t 
		ListIterator<Flight> it = fltList.listIterator();
		while (it.hasNext())
		{
			
		}
	}
}
