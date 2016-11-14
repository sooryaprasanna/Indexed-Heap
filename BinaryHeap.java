/**
 *  Class to implement Binary Heap
 *  @author Soorya Prasanna Ravichandran
 */

import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> 
{
	Object[] pq;
	Comparator<T> c;
	static int heapsize = 0;
	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		c = comp;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	BinaryHeap(int n, Comparator<T> comp) 
	{
		pq = new Object[n+1];
		c = comp;
	}

	// Insert an element to the priority queue
	public void insert(T x) 
	{
		add(x);
	}

	// Delete the minimum element from the priority queue
	public T deleteMin() 
	{
		return remove();
	}

	// Peek the minimum element from the priority queue
	public T min() 
	{ 
		return peek();
	}

	/*
	 * Method to add an element to the end of the queue and
	 * percolating it up to the appropriate position
	 */
	public void add(T x) 
	{
		// Increase heapsize when an element is added
		pq[heapsize+1] = x;
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
		pq[1] = pq[heapsize--]; // Decrease the heapsize when an element is removed
		percolateDown(1); // percolate down to maintain the heap order
		return min;
	}

	// Method to peek the minimum element from the priority queue
	@SuppressWarnings("unchecked")
	public T peek() 
	{
		if(heapsize >= 1)
			return (T) pq[1];
		else
			return null;
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
			i = i/2;
		}
	}

	// Method to check whether the heap is empty
	public boolean isEmpty()
	{
		if(heapsize == 0)
			return true;
		else
			return false;
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

			// After swapping recursive call is made to validate the entire heap
			percolateDown(min);
		}
	}

	// Method to build a heap
	void buildHeap() 
	{
		heapsize = pq.length - 1;
		for(int i = heapsize / 2; i >= 1; i--)
		{
			percolateDown(i);
		}
	}

	/* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
	 */
	public static<T> void heapSort(T[] A, Comparator<T> comp) 
	{
		BinaryHeap<T> bh = new BinaryHeap<T>(A, comp);
		/*
		 *  Removing the top element and heapifying the entire heap 
		 */
		for(int i = A.length - 1; i >= 1 ; i--)
		{
			T temp = A[i];
			A[i] = A[1];
			A[1] = temp;
			heapsize--;
			bh.percolateDown(1);
		}
	}

	public static void main(String[] args)
	{
		// Sample input array to implement Heap Sort
		Integer[] arr = {null,200,350,10,4,3,2,8,36};
		BinaryHeap.<Integer>heapSort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}); 
	}
}