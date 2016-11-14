/**
 * 	Class to implement Prim's MST algorithm using Indexed Heap (priority queue of vertices)
 *  @author Soorya Prasanna Ravichandran
 */

import java.util.Comparator;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class MST 
{
	// Initializing all the distances infinity
	static final int Infinity = Integer.MAX_VALUE;

	/*
	 * Method to set the parent to null, distance to infinity
	 * Initially there is no parent so we set it as null
	 * Distance from the source is 0
	 */
	static void init(Graph g, Vertex s)
	{
		for (Vertex v : g.v) 
		{
			if(v != null)
			{
				v.d = Infinity; // distance to max value
				v.p = null; // parent is null
			}
		}
		s.d = 0; // distance from source is zero
	}

	/*
	 * Implementation of Prim's MST Algorithm using Indexed Heap
	 */
	static int PrimMST(Graph g, Vertex s)
	{
		int wmst = 0;
		
		// Creating an instance for Indexed Heap implementing Comparator
		IndexedHeap<Vertex> queue = new IndexedHeap<>(g.v.size(), new Comparator<Vertex>() {

			@Override
			public int compare(Vertex v1, Vertex v2) 
			{
				return v1.compare(v1, v2);
			}
		});

		init(g,s); // setting distance to infinity, parent to null 

		// Adding all the vertices to the indexed heap
		for (Vertex vertex : g.v) 
		{
			if(vertex != null)
				queue.add(vertex);
		}

		// Performing the operation till the queue is not empty
		while(!queue.isEmpty())
		{
			Vertex u = queue.remove();
			u.seen = true;

			wmst = wmst + u.d; // adding the weight of vertex u to wmst

			// Checking the weight of the edge with its adjacent vertex
			for (Edge e : u.adj) 
			{
				Vertex v = e.otherEnd(u);
				if(!(v.seen) && e.weight < v.d)
				{
					v.p = u; // set u as parent of v
					v.d = e.weight;
					queue.decreaseKey(v); // if the weight of the edge is less, decrease the value of Vertex
				}
			}
		}
		return wmst;
	}

	// Main method to execute Prim's MST algorithm using Indexed Heap
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner in;
		if (args.length > 0) 
		{
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} 
		else 
		{
			in = new Scanner(System.in);
		}
		Graph g = Graph.readGraph(in);
		Vertex s = g.getVertex(1);
		Timer t = new Timer();
		System.out.println(PrimMST(g, s));
		System.out.println(t.end());
	}
}
