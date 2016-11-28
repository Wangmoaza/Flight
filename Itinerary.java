// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.*;
import java.lang.Integer;

public class Itinerary
{
	public static final int BIGNUM = 999999;
	private MinHeap minheap;
	private String start;
	private String end;
	private int size; // number of elements currently in heap
	
	// constructor
	public Itinerary(HashMap<String, Airport> hm, String src, String dest) 
	{
		start = src;
		end = dest;
		minheap = new MinHeap(hm, start);
	}

	public boolean isFound() 
	{
		return !minheap.isInHeap(end);
	}

	public void print() 
	{
		// FIXME
	}

	class MinHeap
	{
		private HeapEntry[] heap;
		private int[] pos;
		private int size;
		private HashMap<String ,Airport> hm;
		
		public MinHeap(HashMap<String ,Airport> portMap, String start)
		{
			hm = portMap;
			heap = new HeapEntry[portMap.size()];
			pos = new int[heap.length]; // contains position in heap by port ID
			size = heap.length;  
			
			// iterate through airportSet to construct heap
			Iterator<String> it = hm.keySet().iterator();
			int startPos = -1;
			int idx = 0;
			while (it.hasNext())
			{
				String curr = it.next();
				int entryId = getId(curr);
				if (start.equals(curr))
					startPos = idx;
				heap[idx] = new HeapEntry(curr, BIGNUM);
				pos[entryId] = idx;
				idx++;
			}
			
			// validity check
			if (startPos == -1)
			{
				System.out.println("Error: start airport not found");
				return;
			}
			
			// move start airport to root
			heap[startPos].setDistance(0);
			swapHeap(startPos, 0);
			// update pos[]
			pos[getId(heap[0].name())] = 0;
			pos[getId(heap[startPos].name())] = startPos;
			
		}
		
		public HeapEntry extractMin()
		{
			if (isEmpty())
				return null;
			
			HeapEntry minEntry = heap[0];
			swapHeap(0, size-1); // swap the root with last element
			// update pos[]
			pos[getId(minEntry.name())] = size -1; // not part of the heap anymore
			pos[getId(heap[0].name())] = 0;
			size--;
			siftDown(0);
			return minEntry;
		}
		
		public void updateEntry(String port, Flight flt, int dist)
		{
			int id = getId(port);			
			heap[pos[id]].setDistance(dist);
			heap[pos[id]].setParent(flt);
			siftUp(pos[id]); // heapify bottom-up	
		}
		
		public boolean isInHeap(String port)
		{
			return pos[getId(port)] < size;
		}
		
		public boolean isEmpty()
		{
			return size == 0;
		}
		
		private int getId(String port)
		{
			return hm.get(port).id();
		}
		
		private void swapHeap(int idx1, int idx2)
		{
			HeapEntry temp = heap[idx1];
			heap[idx1] = heap[idx2];
			heap[idx2] = temp;
		}
		
		private void siftDown(int idx)
		{
			while (idx <= size/2 - 1) // while idx is not leaf
			{
				int smaller = 2 * idx + 1; // left child
				if (smaller < size - 1 && heap[smaller].compareTo(heap[smaller+1]) > 0)
					smaller++; // change to right child
				
				if (heap[idx].compareTo(heap[smaller]) <= 0)
					return;
				
				swapHeap(idx, smaller);
				// update pos[]
				pos[getId(heap[smaller].name())] = smaller;
				pos[getId(heap[idx].name())] = idx;
				idx = smaller;
			}
		}
		
		private void siftUp(int idx)
		{
			// sift up while parent is bigger idx
			while (idx > 0 && heap[(idx-1)/2].compareTo(heap[idx]) > 0)
			{
				swapHeap(idx, (idx-1)/2);
				// update pos[]
				pos[getId(heap[(idx-1)/2].name())] = (idx-1)/2;
				pos[getId(heap[idx].name())] = idx;
				idx = (idx-1)/2; // climb up
			}
		}
	}
}
