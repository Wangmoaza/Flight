
public class HeapEntry {

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
}
