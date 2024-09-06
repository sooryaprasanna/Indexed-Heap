Implemented Indexed Priority Queues, Prim’s Minimum Spanning Tree Algorithm, Dijkstra’s Shortest Path Algorithm along with performance comparison.

**Prim’s Algorithm**
- Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a connected weighted undirected graph.

**Dijkstra’s Algorithm**
- Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph.

**Priority Queue**
- Priority queue is an abstract data type which in addition to the queue data structure, each element has a "priority" associated with it. In a priority queue, an element with higher priority is served before an element with lower priority.

**Indexed Priority Queue**
- Priority Queue has an index for each element for faster access. As we are changing the priorities of the vertices by using decreaseKey function, we need to maintain the correct positions of the indices. ImplementING PQ interface to keep track of the indices by implementing the putIndex and getIndex methods.

**Methods implemented**
1. add (x) — Method to add an element to the end of the queue.
2. remove ( ) — Method to remove the minimum element from the queue.
3. peek ( ) — Method to peek the minimum element from the priority queue.
4. percolateUp ( i ) — Method to move the element up in the heap to the appropriate location to maintain the heap order.
5. isEmpty ( ) — Checks whether the heap is empty or not.
6. percolateDown ( i ) — Method to move the element down in the heap to the appropriate location to maintain the heap order.
7. buildHeap ( ) — to create or build a new heap.
8. heapSort ( ) — Sorted order used to build heap.
9. PrimMST ( ) — Implementation of Prim's MST Algorithm using Indexed heap.
10. Prim1MST ( ) — Implementation of Prim's MST Algorithm using Priority Queue.
11. DijkstraShortestPaths ( ) — Implementation of Dijkstra Shortest Paths Algorithm using Indexed heap.

**Interface**
- Prim’s MST using Indexed heap performs slightly better than Prim’s MST using Priority Queue as I have built an index on it.

**Results**
- Input 1 - Running Time : 3 ms
- Input 2 - Running Time : 5 ms
- Input 3 - Running Time : 1441 sec