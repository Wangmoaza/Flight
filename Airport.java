// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;
import java.lang.Integer;


public class Airport
{
	private String port;
	private int mct; // minimum connection time in minutes
	private LinkedList<Destination> destList;
	private int id;

	// constructor
	public Airport(String port, String connectTime) 
	{
		this.port = port;
		int time = Integer.parseInt(connectTime);
		this.mct = Planner.convert2min(time); // convert to minutes
		destList = new LinkedList<Destination>();
	}	

	public void print() 
	{
		System.out.print(port);
	}
	
	public String name()
	{
		return port;
	}
	
	public int mct()
	{
		return mct;
	}
	
	public void setId(int i)
	{
		id = i;
	}
	
	public int id()
	{
		return id;
	}
	
	public void addFlight(Flight flt)
	{
		ListIterator<Destination> destIt = destList.listIterator();
		while (destIt.hasNext())
		{
			Destination dest = destIt.next();
			if (dest.name().equals(flt.dest()))
			{
				dest.addFlight(flt);
				break;
			}	
		}
		
		if (!destIt.hasNext()) //there was no match in destList
		{
			Destination newDest = new Destination(flt.dest());
			newDest.addFlight(flt);
			destList.addFirst(newDest);
		}
	}
	
	public LinkedList<Destination> destList()
	{
		return destList;
	}
	
	public Destination findDest(String target)
	{
		ListIterator<Destination> destIt = destList.listIterator();
		while (destIt.hasNext())
		{
			Destination dest = destIt.next();
			if (dest.name().equals(target))
				return dest;
		}
		return null;
	}
}
