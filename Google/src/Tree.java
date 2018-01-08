import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
// sorting,  linked list, grphs, 

public class Tree {
	Node root; 
	public void insert(int value)
	{
		root= insert(root, value);	
	}
	
	private Node insert(Node node, int value)
	{
		if (node== null)
			{node= new Node(value); return node; }
		if(value < node.data)
			node.left= insert(node.left, value);
		else
			node.right= insert(node.right, value);
		return node;
		
	}
	
	public boolean search(int x)
	{
		return search(root, x);
	}
	private boolean search(Node node, int number)
	{
		if(node == null) return false; 
		if(node.data == number ) return true; 
		if(node.data < number )
			return search(node.right, number);
		else 
			return search(node.left, number);
	}
	public void  traverse()
	{
		
		if(root.left!=null)
			traverse(root.left);
		System.out.println(root.data);
	   if(root.right != null)
				traverse(root.right);
	}
	public void traverse(Node node)
	{
		
		if (node.left != null)
		{traverse(node.left);
		}
		System.out.println(node.data);
		if(node.right!= null)
			{traverse(node.right);}
	}
	public void delete(int number)
	{
		root= delete(root, number);
	}
	private Node delete(Node node, int number)
	   {
	      if (node == null)  throw new RuntimeException("cannot delete.");
	      else
	      if (node.data > number)
	    	  node.left = delete (node.left, number);
	      else
	      if (node.data < number)
	    	  node.right = delete (node.right, number);
	      else
	      {
	         if (node.left == null) return node.right;
	         else
	         if (node.right == null) return node.left;
	         else
	         {
	         // get data from the rightmost node in the left subtree
	        	 node.data = retrieverightmost(node.left);
	         // delete the rightmost node in the left subtree
	        	 node.left =  delete(node.left,node.data) ;
	         }
	      }
	      return node;
	   }
	 private int retrieverightmost(Node p)
	   {
	      while (p.right != null) p = p.right;

	      return p.data;
	   }
public class Node{
    private Object value;
    private String key;
    /*if ( key.compareTo( this.key ) > 0 )
    {
        if ( right != null )
        {
            right.put( key, value );
        }
        else
        {
            right = new BSTNode( key, value );
        }*/
	private int data;
	Node left; 
	Node right;
	public Node(int value)
	{
		data=value;
	}

}
//breadth first search 
	public void levelOrderQueue() {
		Queue<Node> q = new LinkedList<Node>();
		if (root == null)
			return;
		q.add(root);
		while (!q.isEmpty()) {
			Node n = (Node) q.remove();
			System.out.print(" " + n.data);
			if (n.left != null)
				q.add(n.left);
			if (n.right != null)
				q.add(n.right);
		}
	}
	//Depth first searh 
	public void DFS() {
		Stack<Node> s = new Stack<Node>();
		s.add(root);
		while (!s.isEmpty()) {
			Node x = s.pop();
			if(x.right!=null) s.add(x.right);
			if(x.left!=null) s.add(x.left);			
			System.out.print(" " + x.data);
		}
	}
public static void main(String arg[]){
	Tree tree= new Tree();
	tree.insert(5); 
	tree.insert(2);
	tree.insert(10);
	tree.insert(8); 
	tree.insert(9);
	tree.insert(1);
	System.out.println(tree.search(2));
	System.out.println(tree.search(11));
  tree.DFS();
  System.out.println(tree.search(11));
  tree.levelOrderQueue();
	

}
}
