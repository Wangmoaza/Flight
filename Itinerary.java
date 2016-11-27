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
	public Itinerary(Set<String> airportSet, String src, String dest) 
	{
		start = src;
		end = dest;
		minheap = new MinHeap(airportSet, start);
	}

	public boolean isFound() 
	{
		// FIXME
		return true;
	}

	public void print() 
	{
		// FIXME
	}

	class MinHeap
	{
		private HeapEntry[] entries;
		private int[] posHeap;
		private int size;
		private HashMap<String, Integer> refMap;
		
		public MinHeap(Set<String> airportSet, String start)
		{
			entries = new HeapEntry[airportSet.size()];
			posHeap = new int[entries.length];
			refMap = new HashMap<>(entries.length);
			size = entries.length;
			
			// iterate through airportSet to construct heap
			Iterator<String> it = airportSet.iterator();
			int startIdx = -1;
			int idx = 0;
			while (it.hasNext())
			{
				String curr = it.next();
				if (start.equals(curr))
					startIdx = idx;
				entries[idx] = new HeapEntry(curr, BIGNUM);
				refMap.put(curr, idx);
				posHeap[idx] = idx;
				idx++;
			}
			
			// move start airport to root
			entries[startIdx].setDistance(0);
			swap(startIdx, 0);
		}
		
		public HeapEntry extractMin()
		{
			// TODO
			if (isEmpty())
				return null;
			
			int minIndex = posHeap[0];
			HeapEntry minEntry = entries[minIndex];
			swap(0, size-1); // swap the root with last element
			siftDown(0);
			size--;
			entries[minIndex] = null;
			posHeap[size] = -1; // not needed
			return minEntry;
		}
		
		public void updateEntry(String port, Flight flt, int dist)
		{
			int idx = refMap.get(port);
			if (entries[idx] == null) 
			{
				System.out.println("Error: accessed nonexisting entry");
				return;
			}
			
			entries[idx].setDistance(dist);
			entries[idx].setParent(flt);
			siftUp(idx); // heapify bottom-up	
		}
		
		public boolean isInHeap(String port)
		{
			int idx = refMap.get(port);
			return entries[idx] != null;
		}
		
		public boolean isEmpty()
		{
			return size == 0;
		}
		
		private void swap(int idx1, int idx2)
		{
			int temp = posHeap[idx1];
			posHeap[idx1] = posHeap[idx2];
			posHeap[idx2] = temp;
		}
		
		private void siftDown(int idx)
		{
			while (idx <= size/2 - 1) // while idx is not leaf
			{
				int smaller = 2 * idx + 1; // left child
				if (smaller < size - 1 && entries[smaller].compareTo(entries[smaller+1]) > 0)
					smaller++; // change to right child
				
				if (entries[idx].compareTo(entries[smaller]) <= 0)
					return;
				
				swap(idx, smaller);
				idx = smaller;
			}
		}
		
		private void siftUp(int idx)
		{
			// sift up while parent is bigger idx
			while (idx > 0 && entries[(idx-1)/2].compareTo(entries[idx]) > 0)
			{
				swap(idx, (idx-1)/2);
				idx = (idx-1)/2;
			}
		}
	}
}
