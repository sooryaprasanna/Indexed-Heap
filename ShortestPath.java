/**
 * 	Class to implement Dijkstra's Shortest path algorithm using Indexed Heap
 *  @author Soorya Prasanna Ravichandran
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class ShortestPath 
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

	// Relax method to check the weight is greater and updating the parent accordingly
	static boolean relax(Vertex u, Vertex v, int w)
	{
		if(v.d > (u.d + w))
		{
			v.d = u.d + w;
			v.p = u;

			return true;
		}

		return false;
	}

	/*
	 * Implementation of Dijkstra's Shortest Path Algorithm using Indexed Heap
	 */
	static void DijkstraShortestPaths(Graph g, Vertex s)
	{
		// Shortest paths will be stored in fields d,p in the Vertex class
		init(g,s);
		// List of Vertices
		LinkedList<Vertex> l = new LinkedList<Vertex>();

		// Creating an instance for Indexed Heap implementing Comparator
		IndexedHeap<Vertex> queue = new IndexedHeap<>(g.v.size(), new Comparator<Vertex>() {

			@Override
			public int compare(Vertex v1, Vertex v2) 
			{
				return v1.compare(v1, v2);
			}
		});
		
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
			l.add(u); // adding the vertex to the list
			
			// Checking the weight of the edge with its adjacent vertex
			for (Edge e : u.adj) 
			{
				Vertex v = e.otherEnd(u);
				// Relaxing each edge of u,v leaving u
				if(relax(u, v, e.weight))
				{
					queue.decreaseKey(v);
				}
			}
		}		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;

		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in);
		int src = in.nextInt();
		int target = in.nextInt();
		Vertex s = g.getVertex(src);
		Vertex t = g.getVertex(target);
		DijkstraShortestPaths(g, s);
		System.out.println(t.d);
	}
}
