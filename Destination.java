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
	}
}
