// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.LinkedList;

public class Itinerary
{
	private MinHeap minheap;
	private String start;
	private String end;
	
	// constructor
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
		if (!isFound())
			System.out.println("No Flight Schedule Found.");
		
		// add path to linked list
		LinkedList<Flight> list = new LinkedList<>();
		HeapEntry entry = minheap.getNonHeapEntry(end);
		list.addFirst(entry.flight());
		while (entry != null)
		{
			entry = minheap.getNonHeapEntry(entry.flight().source());
			if (entry.name().equals(start))
				break;
			list.addFirst(entry.flight());
		}
		
		// print path
		for (Flight flt : list)
			flt.print();
		System.out.print("\n");
	}

}
