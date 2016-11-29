// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;
import java.lang.Integer;


public class Airport
{
	private String port;
	private int mct; // minimum connection time in minutes
	private HashMap<String, Destination> destMap;
	private int id;

	// constructor
	public Airport(String port, String connectTime) 
	{
		this.port = port;
		this.mct = Planner.convert2min(Integer.parseInt(connectTime)); // convert to minutes
		destMap = new HashMap<>();
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
		Destination dest = destMap.get(flt.dest());
		
		if (dest != null)
			dest.addFlight(flt);
		else
		{
			Destination newDest = new Destination(flt.dest());
			newDest.addFlight(flt);
			destMap.put(newDest.name(), newDest);
		}
	}
	
	public Destination findDest(String target)
	{
		return destMap.get(target);
	}
}
