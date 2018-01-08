import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/*LinkedList allows for constant-time insertions or removals using 
	 iterators, but only sequential access of elements. In other words, LinkedList 
	 can be searched forward and backward but the time it takes to traverse the
	 list is directly proportional to the size of the list.
	 list = new java.util.LinkedList()

	 */
public class LinkedList {
	// reference to the head node.
	private Node head;
	private int listCount;

	// LinkedList constructor
	public LinkedList() {
		// this is an empty list, so the reference to the head node
		// is set to a new node with no data
		head = new Node(null);
		listCount = 0;
	}

	public void addFirst(Object item) {
		head = new Node(item, head);
	}

	public void add(Object data)
	// post: appends the specified element to the end of this list.
	{
		Node temp = new Node(data);
		Node current = head;
		// starting at the head node, crawl to the end of the list
		while (current.getNext() != null) {
			current = current.getNext();
		}
		// the last node's "next" reference set to our new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}

	public void add(Object data, int index)
	// post: inserts the specified element at the specified position in this
	// list.
	{
		Node temp = new Node(data);
		Node current = head;
		// crawl to the requested index or the last element in the list,
		// whichever comes first
		for (int i = 1; i < index && current.getNext() != null; i++) {
			current = current.getNext();
		}
		// set the new node's next-node reference to this node's next-node
		// reference
		temp.setNext(current.getNext());
		// now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}

	public Object get(int index)
	// post: returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if (index <= 0)
			return null;

		Node current = head.getNext();
		for (int i = 1; i < index; i++) {
			if (current.getNext() == null)
				return null;

			current = current.getNext();
		}
		return current.getData();
	}

	public boolean remove(int index)
	// post: removes the element at the specified position in this list.
	{
		// if the index is out of range, exit
		if (index < 1 || index > size())
			return false;

		Node current = head;
		for (int i = 1; i < index; i++) {
			if (current.getNext() == null)
				return false;

			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}

	public int size()
	// post: returns the number of elements in this list.
	{
		return listCount;
	}

	public String toString() {
		Node current = head.getNext();
		String output = "";
		while (current != null) {
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		return output;
	}

	public void reverseIteratively() {
		Node current = head;
		Node previous = null;
		Node forward = null;

		// traversing linked list until there is no more element
		while (current.next != null) {

			// Saving reference of next node, since we are changing current node
			forward = current.next;

			// Inserting node at start of new list
			current.next = previous;
			previous = current;

			// Advancing to next node
			current = forward;
		}

		head = current;
		head.next = previous;
	}

	private Node reverseRecursively(Node node) {
		Node newHead;

		// base case - tail of original linked list
		if ((node.next == null)) {
			return node;
		}
		newHead = reverseRecursively(node.next);

		// reverse the link e.g. C->D->null will be null
		node.next.next = node;
		node.next = null;
		return newHead;
	}

	public void reverseRecursively() {
		head = reverseRecursively(head);
	}

	private class Node {
		// reference to the next node in the chain,
		// or null if there isn't one.
		Node next;
		// data carried by this node.
		// could be of any type you need.
		Object data;

		// Node constructor
		public Node(Object _data) {
			next = null;
			data = _data;
		}

		// another Node constructor if we want to
		// specify the node to point to.
		public Node(Object _data, Node _next) {
			next = _next;
			data = _data;
		}

		// these methods should be self-explanatory
		public Object getData() {
			return data;
		}

		public void setData(Object _data) {
			data = _data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			next = n;
		}

	}

	public static boolean isRotatedVersion(String str, String rotated) {
		boolean isRotated = false;
		if (str == null || rotated == null) {
			return false;
		} else if (str.length() != rotated.length()) {
			isRotated = false;
		} else {
			String concatenated = str + str;
			isRotated = concatenated.contains(rotated);
		}
		return isRotated;
	}

	public Object getLastNode(int n) {
		Node fast = head;
		Node slow = head;
		int start = 1;
		while (fast.next != null) {
			fast = fast.next;
			start++;
			if (start > n) {
				slow = slow.next;
			}
		}
		return slow.data;
	}

	public static void reverse(int[] input) {
		System.out.println("original array : " + Arrays.toString(input));
		if (input == null || input.length <= 1) {
			return;
		}
		for (int i = 0; i < input.length / 2; i++) {
			int temp = input[i];
			// swap numbers
			input[i] = input[input.length - 1 - i];
			input[input.length - 1 - i] = temp;
		}
		System.out.println("reversed array : " + Arrays.toString(input));
	}

	public static int missingNumberFromSortedArray(int[] numbers) {
		if (numbers == null || numbers.length <= 0) {
			throw new IllegalArgumentException("Null or Empty array not permitted");
		}
		int left = 0;
		int right = numbers.length - 1;
		while (left <= right) {
			int middle = (right + left) >> 1;
			if (numbers[middle] != middle) {
				if (middle == 0 || numbers[middle - 1] == middle - 1) {
					return middle;
				}
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		throw new RuntimeException("No missing number");
	}

	// Recursive method which actually prints all permutations
	private static void permutation(String perm, String word) {
		if (word.isEmpty()) {
			System.err.println(perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
			}
		}
	}
	// * Given an array of integers finds two elements in the array whose sum is
	// equal to n.

	public static void printPairsUsingSet(int[] numbers, int n) {
		if (numbers.length < 2) {
			return;
		}
		Set set = new HashSet(numbers.length);
		for (int value : numbers) {
			int target = n - value;
			// if target number is not in set then add
			if (!set.contains(target)) {
				set.add(value);
			} else {
				System.out.printf("(%d, %d) %n", value, target);
			}
		}
	}
	// Simple way to count number of 1's in a Java Integer

	public static int countOne(int number) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((number & 1) == 1) {
				count++;
			}
			number = number >>> 1;
		}
		return count;
	}

	// we are using AND operation and number -1 to gradually reduce number of
	// 1's from original number
	/* * Find all duplicate characters in a String and print each of them. */
	public static void printDuplicateCharacters(String word) {
		char[] characters = word.toCharArray();
		// build HashMap with character and number of times they appear in
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		for (Character ch : characters) {
			if (charMap.containsKey(ch)) {
				charMap.put(ch, charMap.get(ch) + 1);
			} else {
				charMap.put(ch, 1);
			}
		} // Iterate through HashMap to print all duplicate characters of String
		Set<Map.Entry<Character, Integer>> entrySet = charMap.entrySet();
		System.out.printf("List of duplicate characters in String '%s' %n", word);
		for (Map.Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue() > 1) {
				System.out.printf("%s : %d %n", entry.getKey(), entry.getValue());
			}
		}
	}

	public static int countSetBits(long number) {
		int count = 0;
		while (number > 0) {
			++count;
			number &= number - 1;
		}
		return count;
	}

	/*
	 * * // Java method to find GCD of two number using Euclid's method
	 * * @return GDC of two numbers in Java
	 */
	private static int findGCD(int number1, int number2) {
		// base case
		if (number2 == 0) {
			return number1;
		}
		return findGCD(number2, number1 % number2);
	}

	private static boolean isPowerOfTwo(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("number: " + number);
		}
		if ((number & -number) == number) {
			return true;
		}
		return false;
	}

	/**
	 * * Return true if rotated is rotation of input String * * @param input
	 * * @param rotated * @return true if one String is rotation of other
	 */
	public static void usingRecursion(int number) {
		if (number > 1) {
			usingRecursion(number - 1);
		}
		System.out.println(number);
	}

	public static boolean isRotated(String input, String rotated) {
		String x= rotated+rotated;
				return x.contains(input);
//		if (input == null || rotated == null) {
//			return false;
//		} else if (input.length() != rotated.length()) {
//			return false;
//		}
//		int index = rotated.indexOf(input.charAt(0));
//		if (index > -1) {
//			if (input.equalsIgnoreCase(rotated)) {
//				return true;
//			}
//			int finalPos = rotated.length() - index;
//			return rotated.charAt(0) == input.charAt(finalPos)
//					&& input.substring(finalPos).equals(rotated.substring(0, index));
//		}
//		return false;
	}
public static void  main(String[] arg)
{
	
	System.out.println(8>>> 1);

}
}