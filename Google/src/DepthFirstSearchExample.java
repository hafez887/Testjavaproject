
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.transform.Source;

public class DepthFirstSearchExample {
	
//	
//	Dikstra algo
//	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
//		    source.setDistance(0);
//		 
//		    Set<Node> settledNodes = new HashSet<>();
//		    Set<Node> unsettledNodes = new HashSet<>();
//		 
//		    unsettledNodes.add(source);
//		 
//		    while (unsettledNodes.size() != 0) {
//		        Node currentNode = getLowestDistanceNode(unsettledNodes);
//		        unsettledNodes.remove(currentNode);
//		        for (Entry < Node, Integer> adjacencyPair: 
//		          currentNode.getAdjacentNodes().entrySet()) {
//		            Node adjacentNode = adjacencyPair.getKey();
//		            Integer edgeWeight = adjacencyPair.getValue();
//		            if (!settledNodes.contains(adjacentNode)) {
//		                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
//		                unsettledNodes.add(adjacentNode);
//		            }
//		        }
//		        settledNodes.add(currentNode);
//		    }
//		    return graph;
//		}
	// Adjacency matrices
	// For a graph with |V|  vertices, an
	// adjacency matrix is  V×V, 
	// of 0s and 1s, where the
	// entry in row i  and column j  is 1 if and only if the edge (i,j)
	// (i,j)is in the graph. If  you want to indicate an edge weight, put it in the row
	//
	// Adjacency lists
	// Representing a graph with adjacency lists combines adjacency matrices
	// with edge lists. For each vertex i ii, store an array of the vertices
	// adjacent to it. We typically have an array of |V| V,
	// vertical bar adjacency lists, one adjacency list per vertex. Here's an
	// adjacency-list representation of the social network graph:

	static ArrayList<Node> nodes = new ArrayList<Node>();

	static class Node {
		int data;
		boolean visited;
		Node(int data) {
			this.data = data;

		}
		
		
	}

	// find neighbors of node using adjacency matrix
	// if adjacency_matrix[i][j]==1, then nodes at index i and index j are
	// connected
	public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) {
		int nodeIndex = -1;

		ArrayList<Node> neighbours = new ArrayList();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).equals(x)) {
				nodeIndex = i;
				break;
			}
		}

		if (nodeIndex != -1) {
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if (adjacency_matrix[nodeIndex][j] == 1) {
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}

	// Recursive DFS
	public void dfs(int adjacency_matrix[][], Node node) {

		System.out.print(node.data + "t");
		ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);
		for (int i = 0; i < neighbours.size(); i++) {
			Node n = neighbours.get(i);
			if (n != null && !n.visited) {
				dfs(adjacency_matrix, n);
				n.visited = true;

			}
		}
	}

	// Iterative DFS using stack
	public void dfsUsingStack(int adjacency_matrix[][], Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(node);
		node.visited = true;
		while (!stack.isEmpty()) {
			Node element = stack.pop();
			System.out.print(element.data + "t");

			ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					stack.add(n);
					n.visited = true;

				}
			}
		}
	}

	public static void main(String arg[]) {
		// create a list using the
		short yy;
		List<String> list = Arrays.asList("Lars", "Simon");
		Random x = new Random();
		// implements Runnable { or extends Thread {
		// public final void join(long millisec)
		// The current thread invokes this method on a second thread, causing
		// the current thread to block until the second thread terminates or the
		// specified number of milliseconds passes.
		/*
		 * public static void yield()
		 * 
		 * Causes the currently running thread to yield to any other threads of
		 * the same priority that are waiting to be scheduled.
		 * 
		 * public void interrupt()
		 * 
		 * Interrupts this thread, causing it to continue execution if it was
		 * blocked for any reason.
		 * 
		 * 
		 */

		System.out.println(x.nextInt(5));
		// alternatively
		List<String> anotherList = new ArrayList<>();
		anotherList.add("Lars");
		anotherList.add("Simon");
		list.sort(String::compareToIgnoreCase);
		list.forEach(System.out::println);
		list.sort((s1, s2) -> s1.compareToIgnoreCase(s2)); // sort ignoring case
		Map<String, String> map = new HashMap<>();
		map.put("Android", "Mobile");
		map.forEach((k, v) -> System.out.printf("%s %s%n", k, v));
		map.keySet().forEach(key -> System.out.println(key + " " + map.get(key)));
		BigDecimal bg3 = new BigDecimal("56565656.70");

		DecimalFormat df = new DecimalFormat("###,###.0000");
		System.out.println(df.format(bg3.doubleValue()));
		double ff = 44.912385;
		System.out.printf("%.2f%n", ff);

		// print each element to the console using method references
		list.forEach(System.out::println);
		anotherList.forEach(System.out::println);

		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);

		nodes.add(node40);
		nodes.add(node10);
		nodes.add(node20);
		nodes.add(node30);
		nodes.add(node60);
		nodes.add(node50);
		nodes.add(node70);
		int adjacency_matrix[][] = { { 0, 1, 1, 0, 0, 0, 0 }, // Node 1: 40
				{ 0, 0, 0, 1, 0, 0, 0 }, // Node 2 :10
				{ 0, 1, 0, 1, 1, 1, 0 }, // Node 3: 20
				{ 0, 0, 0, 0, 1, 0, 0 }, // Node 4: 30
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 5: 60
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 6: 50
				{ 0, 0, 0, 0, 0, 0, 0 }, // Node 7: 70
		};

		DepthFirstSearchExample dfsExample = new DepthFirstSearchExample();

		System.out.println("The DFS traversal of the graph using stack ");
		dfsExample.dfsUsingStack(adjacency_matrix, node40);

		System.out.println();
		clearVisitedFlags();
		final Lock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
		System.out.println("The DFS traversal of the graph using recursion ");
		dfsExample.dfs(adjacency_matrix, node40);

	}

	public static void clearVisitedFlags() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).visited = false;
		}
	}
}/*
	 *By operating
	 * system, the resource management can be done via a) time division
	 * multiplexing b) space division multiplexing 5. What is the ready state of
	 * a process? a) when process is scheduled to run after some execution
	 * 
	 * Monitors have an other feature, the possibility to make a thread waiting
	 * for a condition. During the wait time, the thread temporarily gives up
	 * its exclusive access and must reacquire it after the condition has been
	 * met. You can also signal one or more threads that a condition has been
	 * met. for mutulal only private final Lock lock = new ReentrantLock();
	 * 
	 * public void testA() { lock.lock();
	 * 
	 * try { //Some code } finally { lock.unlock(); } } but for monitor public
	 * class BoundedBuffer { private final String[] buffer; private final int
	 * capacity;
	 * 
	 * private int front; private int rear; private int count;
	 * 
	 * private final Lock lock = new ReentrantLock();
	 * 
	 * private final Condition notFull = lock.newCondition(); private final
	 * Condition notEmpty = lock.newCondition();
	 * 
	 * public BoundedBuffer(int capacity) { super();
	 * 
	 * this.capacity = capacity;
	 * 
	 * buffer = new String[capacity]; }
	 * 
	 * public void deposit(String data) throws InterruptedException {
	 * lock.lock();
	 * 
	 * try { while (count == capacity) { notFull.await(); }
	 * 
	 * buffer[rear] = data; rear = (rear + 1) % capacity; count++;
	 * 
	 * notEmpty.signal(); } finally { lock.unlock(); } }
	 * 
	 * public String fetch() throws InterruptedException { lock.lock();
	 * 
	 * try { while (count == 0) { notEmpty.await(); }
	 * 
	 * String result = buffer[front]; front = (front + 1) % capacity; count--;
	 * 
	 * notFull.signal();
	 * 
	 * return result; } finally { lock.unlock(); } } }
	 * 
	 * 
	 * 
	 * 
	 * public class RadixSort {
	 * 
	 * public static void sort( int[] a) { int i, m = a[0], exp = 1, n =
	 * a.length; int[] b = new int[10]; for (i = 1; i < n; i++) if (a[i] > m) m
	 * = a[i]; while (m / exp > 0) { int[] bucket = new int[10];
	 * 
	 * for (i = 0; i < n; i++) bucket[(a[i] / exp) % 10]++; for (i = 1; i < 10;
	 * i++) bucket[i] += bucket[i - 1]; for (i = n - 1; i >= 0; i--)
	 * b[--bucket[(a[i] / exp) % 10]] = a[i]; for (i = 0; i < n; i++) a[i] =
	 * b[i]; exp *= 10; } } // merge sort public static void sort(int[] a, int
	 * low, int high) { int N = high - low; if (N <= 1) return; int mid = low +
	 * N/2; // recursively sort sort(a, low, mid); sort(a, mid, high); // merge
	 * two sorted subarrays int[] temp = new int[N]; int i = low, j = mid; for
	 * (int k = 0; k < N; k++) { if (i == mid) temp[k] = a[j++]; else if (j ==
	 * high) temp[k] = a[i++]; else if (a[j]<a[i]) temp[k] = a[j++]; else
	 * temp[k] = a[i++]; } for (int k = 0; k < N; k++) a[low + k] = temp[k]; }
	 * 
	 * public void dfs(Node root) { //Avoid infinite loops if(root == null)
	 * return;
	 * 
	 * System.out.print(root.getVertex() + "\t"); root.state = State.Visited;
	 * 
	 * //for every child for(Node n: root.getChild()) { //if childs state is not
	 * visited then recurse if(n.state == State.Unvisited) { dfs(n); } } }
	 * 
	 * public void bfs(Node root) { //Since queue is a interface Queue<Node>
	 * queue = new LinkedList<Node>();
	 * 
	 * if(root == null) return;
	 * 
	 * root.state = State.Visited; //Adds to end of queue queue.add(root);
	 * 
	 * while(!queue.isEmpty()) { //removes from front of queue Node r =
	 * queue.remove(); System.out.print(r.getVertex() + "\t");
	 * 
	 * //Visit child first before grandchild for(Node n: r.getChild()) {
	 * if(n.state == State.Unvisited) { queue.add(n); n.state = State.Visited; }
	 * } }
	 * 
	 * 
	 * } public static Graph createNewGraph() { Graph g = new Graph(); Node[]
	 * temp = new Node[8];
	 * 
	 * temp[0] = new Node("A", 3); temp[1] = new Node("B", 3); temp[2] = new
	 * Node("C", 1); temp[3] = new Node("D", 1); temp[4] = new Node("E", 1);
	 * temp[5] = new Node("F", 1);
	 * 
	 * temp[0].addChildNode(temp[1]); temp[0].addChildNode(temp[2]);
	 * temp[0].addChildNode(temp[3]);
	 * 
	 * temp[1].addChildNode(temp[0]); temp[1].addChildNode(temp[4]);
	 * temp[1].addChildNode(temp[5]);
	 * 
	 * temp[2].addChildNode(temp[0]); temp[3].addChildNode(temp[0]);
	 * temp[4].addChildNode(temp[1]); temp[5].addChildNode(temp[1]);
	 * 
	 * for (int i = 0; i < 7; i++) { g.addNode(temp[i]); } return g; }
	 * 
	 * public static void main(String[] args) {
	 * 
	 * Graph gDfs = createNewGraph(); GraphImplementation s = new
	 * GraphImplementation();
	 * 
	 * System.out.println("--------------DFS---------------");
	 * s.dfs(gDfs.getNode()[0]); System.out.println(); System.out.println();
	 * Graph gBfs = createNewGraph();
	 * System.out.println("---------------BFS---------------");
	 * s.bfs(gBfs.getNode()[0]);
	 * 
	 * }
	 * 
	 * } Graph.java:
	 * 
	 * package graphs;
	 * 
	 * public class Graph {
	 * 
	 * public int count; // num of vertices private Node vertices[];
	 * 
	 * public Graph() { vertices = new Node[8]; count = 0; }
	 * 
	 * public void addNode(Node n) { if(count < 10) { vertices[count] = n;
	 * count++; } else { System.out.println("graph full"); } }
	 * 
	 * public Node[] getNode() { return vertices; } } public class Node {
	 * 
	 * public Node[] child; public int childCount; private String vertex; public
	 * State state;
	 * 
	 * public Node(String vertex) { this.vertex = vertex; }
	 * 
	 * public Node(String vertex, int childlen) { this.vertex = vertex;
	 * childCount = 0; child = new Node[childlen]; }
	 * 
	 * public void addChildNode(Node adj) { adj.state = State.Unvisited;
	 * if(childCount < 30) { this.child[childCount] = adj; childCount++; } }
	 * 
	 * public Node[] getChild() { return child; }
	 * 
	 * public String getVertex() { return vertex; }
	 * 
	 * } public enum State {
	 * 
	 * Unvisited,Visiting,Visited;
	 * 
	 * }
	 * 
	 */
