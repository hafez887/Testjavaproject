
// A Java program to print topological sorting of a DAG
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;;

// This class represents a directed graph using adjacency
// list representation
class AGraph {
	// Time Complexity: O(n)
	// Algorithmic Paradigm: Dynamic Programming
	int maxSubArraySum(int a[], int size) {
		int max_so_far = 0, max_ending_here = 0;
		for (int i = 0; i < size; i++) {
			max_ending_here = max_ending_here + a[i];
			if (max_ending_here < 0)
				max_ending_here = 0;

			/*
			 * Do not compare for all elements. Compare only when
			 * max_ending_here > 0
			 */
			else if (max_so_far < max_ending_here)
				max_so_far = max_ending_here;
		}
		return max_so_far;
	}
	// Dynamic Programming | Set 4 (Longest Common Subsequence)
	/*
	 * If last characters of both sequences match (or X[m-1] == Y[n-1]) then
	 * L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
	 * 
	 * If last characters of both sequences do not match (or X[m-1] != Y[n-1])
	 * then L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]),
	 * L(X[0..m-1], Y[0..n-2])
	 */

	int lcs(char[] X, char[] Y, int m, int n) {
		int L[][] = new int[m + 1][n + 1];

		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that
		 * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					L[i][j] = 0;
				else if (X[i - 1] == Y[j - 1])
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
			}
		}
		return L[m][n];
	}

	// Convert array into Zig-Zag fashion
	/*
	 * Example: Input: arr[] = {4, 3, 7, 8, 6, 2, 1} Output: arr[] = {3, 7, 4,
	 * 8, 2, 6, 1} // A Simple Solution is to first sort the array. After
	 * sorting, exclude the first element, swap the remaining elements in pairs.
	 * (i.e. keep arr[0] as it is, swap arr[1] and arr[2], swap arr[3] and
	 * arr[4], and so on). Time complexity is O(nlogn) since we need to sort the
	 * array first.
	 * 
	 * // We can convert in O(n) time using an Efficient Approach. The idea is
	 * to use modified one pass of bubble sort. Maintain a flag for representing
	 * which order(i.e. < or >) currently we need. If the current two elements
	 * are not in that order then swap those elements otherwise not.
	 */ static int arr[] = new int[] { 4, 3, 7, 8, 6, 2, 1 };

	// Method for zig-zag conversion of array
	static void zigZag() {
		// Flag true indicates relation "<" is expected,
		// else ">" is expected. The first expected relation
		// is "<"
		boolean flag = true;

		int temp = 0;

		for (int i = 0; i <= arr.length - 2; i++) {
			if (flag) /* "<" relation expected */
			{
				/*
				 * If we have a situation like A > B > C, we get A > B < C by
				 * swapping B and C
				 */
				if (arr[i] > arr[i + 1]) {
					// swap
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}

			} else /* ">" relation expected */
			{
				/*
				 * If we have a situation like A < B < C, we get A < C > B by
				 * swapping B and C
				 */
				if (arr[i] < arr[i + 1]) {
					// swap
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			flag = !flag; /* flip flag */
		}
	}

	// Find subarray with given sum | Set 1 (Nonnegative Numbers)
	// Method 2 (Efficient)
	// Initialize a variable curr_sum as first element. curr_sum indicates the
	// sum of current subarray. Start from the second element and add all
	// elements one by one to the curr_sum. If curr_sum becomes equal to sum,
	// then print the solution. If curr_sum exceeds the sum, then remove
	// trailing elemnents while curr_sum is greater than sum.

	int subArraySum(int arr[], int n, int sum) {
		int curr_sum = arr[0], start = 0, i;

		// Pick a starting point
		for (i = 1; i <= n; i++) {
			// If curr_sum exceeds the sum, then remove the starting elements
			while (curr_sum > sum && start < i - 1) {
				curr_sum = curr_sum - arr[start];
				start++;
			}

			// If curr_sum becomes equal to sum, then return true
			if (curr_sum == sum) {
				int p = i - 1;
				System.out.println("Sum found between indexes " + start + " and " + p);
				return 1;
			}

			// Add this element to curr_sum
			if (i < n)
				curr_sum = curr_sum + arr[i];

		}

		System.out.println("No subarray found");
		return 0;
	}

	/*
	 * The Celebrity Problem In a party of N people, only one person is known to
	 * everyone. Such a person may be present in the party, if yes, (s)he
	 * doesn’t know anyone in the party. We can only ask questions like “does A
	 * know B? “. Find the stranger (celebrity) in minimum number of questions.
	 * 
	 * Graph) We can model the solution using graphs. Initialize indegree and
	 * outdegree of every vertex as 0. If A knows B, draw a directed edge from A
	 * to B, increase indegree of B and outdegree of A by 1. Construct all
	 * possible edges of the graph for every possible pair [i, j]. We have NC2
	 * pairs. If celebrity is present in the party, we will have one sink node
	 * in the graph with outdegree of zero, and indegree of N-1. We can find the
	 * sink node in (N) time, but the overall complexity is O(N^2) as we need to
	 * construct the graph first.
	 * 
	 * We have following observation based on elimination technique (Refer
	 * Polya’s How to Solve It book).
	 * 
	 * If A knows B, then A can’t be celebrity. Discard A, and B may be
	 * celebrity. If A doesn’t know B, then B can’t be celebrity. Discard B, and
	 * A may be celebrity. Repeat above two steps till we left with only one
	 * person. Ensure the remained person is celebrity. (Why do we need this
	 * step?) We can use stack to verity celebrity.
	 * 
	 * Push all the celebrities into a stack. Pop off top two persons from the
	 * stack, discard one person based on return status of HaveAcquaintance(A,
	 * B). Push the remained person onto stack. Repeat step 2 and 3 until only
	 * one person remains in the stack. Check the remained person in stack
	 * doesn’t have acquaintance with anyone else. Person with 2 is celebrity
	 */
	static int MATRIX[][] = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };

	// Returns true if a knows b, false otherwise
	static boolean knows(int a, int b) {
		boolean res = (MATRIX[a][b] == 1) ? true : false;
		return res;
	}

	// Returns -1 if celebrity is not present.
	// If present, returns id (value from 0 to n-1).
	static int findCelebrity(int n) {
		Stack<Integer> st = new Stack<>();
		int c;

		// Step 1 :Push everybody onto stack
		for (int i = 0; i < n; i++) {
			st.push(i);
		}

		while (st.size() > 1) {
			// Step 2 :Pop off top two persons from the
			// stack, discard one person based on return
			// status of knows(A, B).
			int a = st.pop();
			int b = st.pop();

			// Step 3 : Push the remained person onto stack.
			if (knows(a, b)) {
				st.push(b);
			}

			else
				st.push(a);
		}

		c = st.pop();

		// Step 5 : Check if the last person is
		// celebrity or not
		for (int i = 0; i < n; i++) {
			// If any person doesn't know 'c' or 'a'
			// doesn't know any person, return -1
			if (i != c && (knows(c, i) || !knows(i, c)))
				return -1;
		}
		return c;
	}

	/*
	 * Check for balanced parentheses in an expression 1) Declare a character
	 * stack S. 2) Now traverse the expression string exp. a) If the current
	 * character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) then push it to
	 * stack. b) If the current character is a closing bracket (‘)’ or ‘}’ or
	 * ‘]’) then pop from stack and if the popped character is the matching
	 * starting bracket then fine else parenthesis are not balanced. 3) After
	 * complete traversal, if there is some starting bracket left in stack then
	 * “not balanced”
	 * 
	 */
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List

	// Constructor
	AGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// A recursive function used by topologicalSort
	void topologicalSortUtil(int v, boolean visited[], Stack stack) {
		// Mark the current node as visited.
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this
		// vertex
		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i])
				topologicalSortUtil(i, visited, stack);
		}

		// Push current vertex to stack which stores result
		stack.push(new Integer(v));
	}

	// The function to do Topological Sort. It uses
	// recursive topologicalSortUtil()
	void topologicalSort() {
		Stack stack = new Stack();

		// Mark all the vertices as not visited
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				topologicalSortUtil(i, visited, stack);

		// Print contents of stack
		while (stack.empty() == false)
			System.out.print(stack.pop() + " ");
	}

	// Driver method
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		AGraph g = new AGraph(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		System.out.println("Following is a Topological " + "sort of the given graph");
		g.topologicalSort();
	}
}