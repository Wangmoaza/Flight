
public class HeapEntry implements Comparable<HeapEntry> {

	private String port;
	private int distance;
	private Flight parent;
	
	public HeapEntry(String p, int d)
	{
		port = p;
		distance = d;
		parent = null;
	}
	
	public void setParent(Flight flt)
	{
		parent = flt;
	}
	
	public void setDistance(int d)
	{
		distance = d;
	}
	
	public Flight parent()
	{
		return parent;
	}
	
	public int distance()
	{
		return distance;
	}
	
	public String name()
	{
		return port;
	}
	
	public int compareTo(HeapEntry other)
	{
		if (distance > other.distance())
			return 1;
		else if (distance < other.distance())
			return -1;
		else
			return 0;
	}
}
