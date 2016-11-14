/**
 * Class to represent a vertex of a graph
 * @author Soorya Prasanna Ravichandran
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Vertex implements Index, Comparator<Vertex>, Iterable<Edge> 
{
	int name; // name of the vertex
	boolean seen; // flag to check if the vertex has already been visited
	int d;  Vertex p;  // fields used in algorithms of Prim and Dijkstra
	List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList

	int index; // to maintain the index of each element

	// Method to get the index
	public int getIndex() 
	{
		return index;
	}

	// Method to set the index
	public void putIndex(int i) 
	{
		index = i;
	}

	// Method to compare the source and adjacent vertex
	public int compare(Vertex u, Vertex v) 
	{ 
		if(u.d == v.d)
			return 0;
		else if(u.d < v.d)
			return -1;
		else
			return 1;	
	}

	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		d = Integer.MAX_VALUE;
		p = null;
		adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	}

	public Iterator<Edge> iterator() { return adj.iterator(); }

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}
