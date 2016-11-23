// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;
import java.lang.Short;


public class Airport
{
	private String port;
	private int mct; // minimum connection time in minutes
	private LinkedList<Flight> flights;

	// constructor
	public Airport(String port, String connectTime) 
	{
		this.port = port;
		short time = Short.parseShort(connectTime);
		this.mct = (time / 100) * 60 + (time % 100); // convert to minutes
		flights = new LinkedList<Flight>();
	}	

	public void print() 
	{
		System.out.print(port);
	}
	
	public String name()
	{
		return port;
	}
	
	public LinkedList<Flight> flights()
	{
		return flights;
	}
	
	@Override
	public int hashCode()
	{
		return port.hashCode();
	}
}
