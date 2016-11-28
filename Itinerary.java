// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.*;
import java.lang.Integer;

public class Itinerary
{
	private MinHeap minheap;
	private String start;
	private String end;
	
	// constructor
	// FIXME
	public Itinerary(String src, String dest, MinHeap mh) 
	{
		start = src;
		end = dest;
		minheap = mh;
	}

	public boolean isFound() 
	{
		return !minheap.isInHeap(end);
	}

	public void print() 
	{
		// FIXME
	}

}
