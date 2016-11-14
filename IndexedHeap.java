/**
 * 	Class to implement Indexed Heap
 *  @author Soorya Prasanna Ravichandran
 */

import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> 
{
	/** Build a priority queue with a given array q */
	IndexedHeap(T[] q, Comparator<T> comp) 
	{
		super(q, comp);
	}

	/** Create an empty priority queue of given maximum size */
	IndexedHeap(int n, Comparator<T> comp) 
	{
		super(n, comp);
	}

	/** restore heap order property after the priority of x has decreased */
	void decreaseKey(T x) 
	{
		percolateUp(x.getIndex());
	}

	/*
	 * The following methods are overrided from the Binary Heap class
	 * since Binary Heap doesn't extend Indexed Heap class
	 * This is required to set the index
	 */

	/*
	 * Method to add an element to the end of the queue and
	 * percolating it up to the appropriate position
	 */
	public void add(T x) 
	{
		// Increase heapsize when an element is added
		pq[heapsize+1] = x;
		((Index) pq[heapsize+1]).putIndex(heapsize+1); // setting the index of the new element
		percolateUp(heapsize+1); // percolate up to maintain the heap order
		heapsize++;
	}

	/*
	 * Method to remove the minimum element from the queue and
	 * percolating it down to maintain the heap order
	 */
	@SuppressWarnings("unchecked")
	public T remove() 
	{ 
		T min;
		if(heapsize == 0)
			return null;
		else
			min = (T) pq[1];
		pq[1] = pq[heapsize--];  // Decrease the heapsize when an element is removed
		((Index) pq[1]).putIndex(1); // change the index after an element has been removed
		percolateDown(1); // percolate down to maintain the heap order
		return min;
	}

	/* 
	 * Method to move the element up in the heap to the appropriate 
	 * location to maintain the heap order
	 */
	@SuppressWarnings("unchecked")
	void percolateUp(int i) 
	{
		while(i>1 && pq[i/2] != null && c.compare((T)pq[i/2],(T)pq[i]) > 0)
		{
			// Swap the elements up that violates the heap order
			T temp = (T) pq[i/2];
			pq[i/2] = pq[i];
			pq[i] = temp;

			// Swap the index of the elements
			((Index) pq[i/2]).putIndex(i/2); 
			((Index) pq[i]).putIndex(i);

			i = i/2;
		}
	}

	/* 
	 * Method to move the element down in the heap to the appropriate 
	 * location to maintain the heap order
	 */
	@SuppressWarnings("unchecked")
	void percolateDown(int i) 
	{
		int left = 2*i;
		int right = 2*i + 1;
		int min;

		// Check for the appropriate location for the element to move down (left or right)
		if(left <= heapsize && c.compare((T) pq[left],(T) pq[i]) < 0)
			min = left;
		else
			min = i;

		if(right <= heapsize && c.compare((T) pq[right],(T) pq[min]) < 0)
			min = right;

		if(min != i)
		{
			// Swap the elements up that violates the heap order
			T temp = (T) pq[i];
			pq[i] = pq[min];
			pq[min] = temp;

			// Swap the index of the elements
			((Index) pq[i]).putIndex(i);
			((Index) pq[min]).putIndex(min);

			// After swapping recursive call is made to validate the entire heap
			percolateDown(min);
		}
	}   
}
