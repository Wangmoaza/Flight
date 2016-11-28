/**
 * Heap Entry in MinHeap
 * @author Ha-Eun Hwangbo
 *
 */
public class HeapEntry implements Comparable<HeapEntry> {

	private String port;
	private int distance;
	private Flight flt;
	
	public HeapEntry(String p, int d)
	{
		port = p;
		distance = d;
		flt = null;
	}
	
	public void setFlight(Flight f)
	{
		flt = f;
	}
	
	public void setDistance(int d)
	{
		distance = d;
	}
	
	public Flight flight()
	{
		return flt;
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
