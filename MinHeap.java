/**
 * MinHeap with locator 
 * also used as set S and V-S in Dijkstra algorithm
 * @author Ha-Eun Hwangbo
 * 
 */

import java.util.*;

public class MinHeap implements Iterable<HeapEntry> {
	public static final int BIGNUM = 999999;
	private HeapEntry[] heap;
	private int[] pos; // pos[i]: position of HeapEntry with id "i" in heap
	private int size; // number of elements in heap
	private HashMap<String ,Airport> hm; // for HeapEntry id
	
	public MinHeap(HashMap<String ,Airport> portMap, String start)
	{
		hm = portMap;
		heap = new HeapEntry[portMap.size()];
		pos = new int[heap.length];
		size = heap.length;  
		
		// iterate through airportSet to construct heap
		int startPos = -1;
		int idx = 0;
		for (String curr : hm.keySet())
		{
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
		// update pos[]
		pos[getId(heap[0].name())] = startPos;
		pos[getId(heap[startPos].name())] = 0;
		swapHeap(startPos, 0);
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
		heap[pos[id]].setFlight(flt);
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
	
	public HeapEntry getNonHeapEntry(String port)
	{
		int id = getId(port);
		if (pos[id] < size) // in heap
			return null;
		
		return heap[pos[id]];
	}
	
	public void print()
	{
		System.out.println("heap array");
		for (int i = 0; i < heap.length; i++)
		{
			System.out.println(String.format("%d : %s\t%s\t%d", 
					i, heap[i].name(), heap[i].flight(), heap[i].distance()));
		}
		
		System.out.println("\npos");
		for(int i = 0; i < pos.length; i++)
		{
			System.out.println(String.format("%d : %d\t%s\t%d", 
					i, getId(heap[pos[i]].name()), heap[pos[i]].name(), pos[i]));
		}
	}
	
	/*** private methods ***/
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
			
			// update pos[]
			pos[getId(heap[smaller].name())] = idx;
			pos[getId(heap[idx].name())] = smaller;
			
			swapHeap(idx, smaller);
			idx = smaller;
		}
	}
	
	private void siftUp(int idx)
	{
		// sift up while parent is bigger idx
		while (idx > 0 && heap[(idx-1)/2].compareTo(heap[idx]) > 0)
		{
			// update pos[]
			pos[getId(heap[(idx-1)/2].name())] = idx;
			pos[getId(heap[idx].name())] = (idx-1)/2;
			
			swapHeap(idx, (idx-1)/2);
			idx = (idx-1)/2; // climb up
		}
	}
	
	/*** Iterator ***/
	public Iterator<HeapEntry> iterator()
	{
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<HeapEntry>
	{
		private HeapEntry[] copy;
		private int cursor;
		
		public HeapIterator()
		{
			copy = new HeapEntry[size];
			for (int i = 0; i < size; i++)
				copy[i] = heap[i];
			cursor = 0;
		}
		
		public boolean hasNext()
		{
			return cursor < size;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		public HeapEntry next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			return copy[cursor++];
		}
	}
}
