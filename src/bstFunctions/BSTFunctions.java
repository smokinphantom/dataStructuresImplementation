package bstFunctions;

import graph.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class BSTFunctions<Key extends Comparable<Key>,Value>{

	static Node parent = null;
	int k=0;
	static int level=0;
	static int nodeVal=0;
	Node ans = null;
	private Node root;
	private TreeNode<Character,Integer> rootS;

	public BSTFunctions(Key key, Value val){
		rootS = new TreeNode(key,val);
	}

	//put acc to sedgewick
	public void put(Key key, Value val){
		rootS = put(rootS,key,val);
	}
	private TreeNode put(TreeNode x, Key key, Value val) {
		if(x==null) return new TreeNode(key,val);
		int cmp = key.compareTo((Key) x.key);
		if(cmp<0)
			x.left=put(x.left, key, val);
		else if(cmp>0)
			x.right=put(x.right, key, val);
		else
			x.value = val;

		return x;
	}

	//search sedgewick
	public Value get(Key key){
		TreeNode x = rootS;
		while(x!=null){
			int cmp = key.compareTo((Key) x.key);
			if(cmp<0) x=x.left;
			else if(cmp>0) x=x.right;
			else return (Value) x.value;
		}
		return null;
	}

	// sedgewick inorder traversal
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		inorder(rootS,q);
		return q;
	}

	private void inorder(TreeNode x,Queue que)
	{
		if(x==null) return;

		inorder(x.left,que);
		que.enqueue(x.key);
		inorder(x.right,que);
	}

	//recursive implementation of minimum element according to sedgewick
	public Key minR(){
		TreeNode c = minR(rootS);
		return (Key) c.key;
	}

	private TreeNode minR(TreeNode root){
		if(root==null) 
			return null;
		if(root.left==null) 
			return root;
		return minR(root.left);
	}

	//recursive implementation of maximum element according to sedgewick
	public Key maxR(){
		TreeNode c = maxR(rootS);
		return (Key) c.key;
	}

	private TreeNode maxR(TreeNode root){
		if(root==null) 
			return null;
		if(root.right==null) 
			return root;
		return maxR(root.right);
	}

	//recursive implementation of floor according to sedgewick
	public TreeNode floorR(Key key){
		TreeNode x = floorR(rootS,key);
		return x;
	}

	private TreeNode floorR(TreeNode x, Key key) {
		if(x==null) return null;

		int cmp = key.compareTo((Key) x.key);

		if(cmp==0) return x;         //if key is same as x key
		if(cmp<0) return floorR(x.left,key); //if comparator value is less, key is greater than x key 

		TreeNode t = floorR(x.right,key); //we check this condition to know if there exists a smaller key greater than x
		if(t!=null) return t;
		else return x;

	}
	
	//recursive implementation of ceiling according to sedgewick
		public TreeNode ceilR(Key key){
			TreeNode x = ceilR(rootS,key);
			return x;
		}

		private TreeNode ceilR(TreeNode x, Key key) {
			if(x==null) return null;

			int cmp = key.compareTo((Key) x.key);

			if(cmp==0) return x;         //if key is same as x key
			if(cmp>0) return ceilR(x.right,key); //if comparator value is less, key is greater than x key 

			TreeNode t = ceilR(x.left,key); //we check this condition to know if there exists a smaller key greater than x
			if(t!=null) return t;
			else return x;

		}
	
	

	public Character max(){
		if(rootS==null) return null;
		while(rootS.right!=null){
			rootS=rootS.right;
		}
		return rootS.key;
	}

	public void insertNode(Node node,int value)
	{
		//start here...
		if(value < node.value)
		{
			if(node.left!=null)
				insertNode(node.left,value); //ok?yes
			else 
				node.left = new Node(value);//do same for right
		}

		if(value > node.value)
		{
			if(node.right!=null)
				insertNode(node.right,value);
			else
				node.right = new Node(value);
		}

	}

	public void inorder(Node node)
	{
		if(node==null) return;

		inorder(node.left);
		System.out.println("Inorder:"+node.value);
		inorder(node.right);
	}

	/**
	 * inorder traversal iterative
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		ArrayList<Integer> lst = new ArrayList<Integer>();

		if(root == null)
			return lst; 

		Stack<TreeNode> stack = new Stack<TreeNode>();
		//define a pointer to track nodes
		TreeNode p = root;

		while(!stack.empty() || p != null){

			// if it is not null, push to stack
			//and go down the tree to left
			if(p != null){
				stack.push(p);
				p = p.left;

				// if no left child
				// pop stack, process the node
				// then let p point to the right
			}else{
				TreeNode t = stack.pop();
				lst.add((Integer) t.value);
				p = t.right;
			}
		}

		return lst;
	}

	public void preorder(Node node)
	{
		if(node!=null)
		{
			System.out.println("Preorder:"+node.value);
			preorder(node.left);
			preorder(node.right);
		}
		else
			return;

	}

	public void postOrder(Node node)
	{
		if(node!=null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.println("PostOrder" +
					":"+node.value);
		}

	}

	public Node findMin(Node node)
	{

		while(node.left!=null)
		{
			node = node.left;

		}
		return node;


	}

	public Node findMax(Node node)
	{
		//int min=0;
		if(node!=null)
		{
			findMax(node.right);
			if(node.right==null)
				return node;
		}
		return null;

	}

	public static Node findParent(Node root,Node value)//first learn this...findparent..ok
	{

		if(value.value<root.value && root.left.value==value.value){
			level++;
			parent = root;}
		//System.out.println("the parent of the node is:"+root.value);	
		else if(value.value>root.value && root.right.value == value.value)
		{level++;
		parent = root;}
		//System.out.println("the parent of the node is:"+root.value);	
		else if(value.value<root.value){
			level++;
			findParent(root.left,value);
		}

		else if(value.value>root.value){
			level++;
			findParent(root.right,value);
		}


		return parent;

	}

	public void deleteNode(Node root, Node nodeToRemove1)
	{
		Node nodeToRemove=null;
		Node parent = findParent(root, nodeToRemove1);

		if(parent.value<nodeToRemove1.value)
			nodeToRemove = parent.right;
		else
			nodeToRemove = parent.left;

		System.out.println("the parent of node to be removed is:"+parent.value);
		if(nodeToRemove.left==null && nodeToRemove.right==null)
		{
			/*if(parent.left == null)
					parent.right=null;
				else parent.left = null;*/
			if(nodeToRemove==parent.right)
				parent.right=null;
			else
				parent.left=null;

		}
		else if((nodeToRemove.left!=null  && nodeToRemove.right== null) || (nodeToRemove.left == null && nodeToRemove.right!=null))
		{
			if(nodeToRemove.left!=null && nodeToRemove.right==null)
			{
				if(parent.right==nodeToRemove)
				{
					parent.right=nodeToRemove.left;
					nodeToRemove = null;
				}
				else if(parent.left==nodeToRemove)
				{
					parent.left = nodeToRemove.left;
					nodeToRemove = null;
				}
			}
			else if(nodeToRemove.left==null && nodeToRemove.right!=null)
			{
				if(parent.right==nodeToRemove)
				{
					parent.right=nodeToRemove.right;
					nodeToRemove = null;
				}
				else if(parent.left==nodeToRemove)
				{
					parent.left = nodeToRemove.right;
					nodeToRemove = null;
				}
			}

		}
		else if(nodeToRemove.left!=null && nodeToRemove.right!=null)
		{
			Node min=null;
			Node MinParent=null;

			if(nodeToRemove.value<parent.value){
				min = findMin(nodeToRemove.right);
				int temp = min.value;
				MinParent = findParent(parent.left.right, min);
				parent.left.value=temp;	
			}	
			else{
				min = findMin(nodeToRemove.right);
				int temp = min.value;
				MinParent = findParent(parent, min);
				parent.right.value=temp;	
			}

			if(min.value<MinParent.value)
				MinParent.left=null;
			else
				MinParent.right=null;

		}

	}

	/**
	 * Lowest common ancestor in a bst.
	 * @param root
	 * @param val1
	 * @param val2
	 * @return
	 */
	public Node findLowestCommonAncestor(Node root, int val1, int val2)
	{
		if(val1 < root.value && val2 < root.value)
		{
			findLowestCommonAncestor(root.left, val1, val2);
		}
		else if (val1> root.value && val2 > root.value)
		{
			findLowestCommonAncestor(root.right, val1, val2);
		}

		return root;
	}

	public void findksmallest(Node root, int val)
	{
		if(root!=null && val>0){

			findksmallest(root.left, val);
			k++;
			if(val==k){
				System.out.println("the 3rd smallest value is:"+root.value);
				return;
			}
			findksmallest(root.right, val);
		}
	}

	public Node findKlargest(Node root, int val)
	{
		if(root == null || val<= 0)
			return null;

		findKlargest(root.right, val);
		k++;
		if(val==k)
		{
			ans = root;
			//System.out.println("the "+val+" largest number is:"+root.value);
		}
		findKlargest(root.left, val);

		return ans;

	}

	public void cousinOfNode(Node root,int present_level, int level, Node parent){

		if(root!=null){

			if(parent!=root)
				cousinOfNode(root.left,present_level+1, level, parent);
			if(present_level==level)
				System.out.println("cousin is:"+root.value);
			if(parent!=root)
				cousinOfNode(root.right, present_level+1, level, parent);

		}
	}


	private static int checkHeight(Node root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = checkHeight(root.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = checkHeight(root.right);
		if (rightHeight == -1) {
			return -1;
		}

		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return -1;
		}
		else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	/*
	 * check if a bst is balanced/ not
	 */
	public boolean isBalanced(Node root) {
		if (checkHeight(root) == -1) {
			return false;
		} else {
			return true;
		}
	}


	/*
	 * Algorithm to create a bst with minimal heigh from a sorted array
	 */
	Node MinimalHeightBST(int[] arr){
		return MinimalHeightBST(arr,0,arr.length-1);
	}


	private Node MinimalHeightBST(int[] arr, int i, int j) {

		if(i>j){
			return null;
		}
		else{
			int mid = (i+j)/2;
			Node n = new Node(arr[mid]);
			n.left = MinimalHeightBST(arr, i, mid-1);
			n.right = MinimalHeightBST(arr, mid+1, j);

			return n;
		}

	}

	public ArrayList<LinkedList<Node>> findLevelLinkList(Node root) 
	{
		int level = 0;

		ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();

		LinkedList<Node> list = new LinkedList<Node>();

		list.add(root);

		result.add(level, list);

		while (true) 
		{
			list = new LinkedList<Node>();
			for (int i = 0; i < result.get(level).size(); i++) 
			{
				Node n = (Node) result.get(level).get(i);

				if (n != null) 
				{
					if(n.left != null) list.add(n.left);
					if(n.right!= null) list.add(n.right);
				}
			}
			if (list.size() > 0) 
			{
				result.add(level + 1, list);
			} 
			else 
			{
				break;
			}

			level++;
		}
		return result;
	} 

	/**
	 * Given a binary tree, return the level order traversal of its 
	 * nodes' values. (ie, from left to right, level by level).
	 * For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(Node root) {
		int level = 0;

		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Node> NodeList = new ArrayList<Node>();

		NodeList.add(root);
		list.add(root.value);

		result.add(level, list);

		while (true) 
		{
			list = new ArrayList<Integer>();
			ArrayList<Node> NodeTempList = NodeList;
			NodeList = new ArrayList<Node>();
			for (int i = 0; i < NodeTempList.size(); i++) 
			{
				Node n = (Node) NodeTempList.get(i);

				if (n != null) 
				{
					if(n.left != null)
					{
						list.add(n.left.value);
						NodeList.add(n.left);
					}
					if(n.right!= null) 
					{
						list.add(n.right.value);
						NodeList.add(n.right);
					}
				}
			}
			if (list.size() > 0) 
			{
				result.add(level + 1, list);
			} 
			else 
			{
				break;
			}

			level++;
		}
		return result;
	}
	/*
	 * Implement a function to check if a binary tree is a bst
	 */
	private boolean checkBST(Node n, double min, double max) {
		if (n == null) {
			return true;
		}
		if(n.value <= min || n.value >= max)
			return false;

		if (!checkBST(n.left, min, n.value) ||
				!checkBST(n.right, n.value, max)) {
			return false;
		}
		return true;
	}

	public boolean checkBST(Node n) {
		return checkBST(n, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	/*
	 * Find the next node(in-order successor of a given node in a bst)
	 */
	public static Node inorderSucc(Node n, Node root) { 
		if (n == null) return null;

		Node p = findParent(root, n);
		// Found right children -> return left most node of right subtree //if this is a right subtree or root
		if (p == null || n.right != null) { 
			return leftMostChild(n.right); 
		} else {  //if it is a left subtree
			Node q = n;
			Node x = findParent(root, q);
			// Go up until we�re on left instead of right
			while (x != null && x.left != q) {
				q = x;
				x = findParent(root, x);
			}
			return x;
		}  
	} 

	public static Node leftMostChild(Node n) {
		if (n == null) {
			return null;
		}
		while (n.left != null) {
			n = n.left; 
		}
		return n; 
	}

	/**
	 * Given a binary tree where all the right nodes are leaf nodes, 
	 * flip it upside down and turn it into a tree with left leaf nodes. 
	 */
	public static Node FlipTree ( Node root )
	{
		if (root == null)
			return null;

		// Working base condition
		if( root.left == null && root.right == null) 
		{
			return root;
		}

		Node newRoot = FlipTree(root.left);

		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;

		return newRoot;
	}


	public void connect(Node root) {  
		Node first = root, cur = root;  
		while (cur != null) {  
			if (cur.left == null && cur.right == null) break;  

			if (cur.left != null)  cur.left.next = cur.right;  
			if (cur.next != null) {  
				if (cur.right != null)  cur.right.next = cur.next.left;  
				cur = cur.next;  
			} else {  
				cur = first.left;  
				first = cur;  
			}  
		}  
	} 

	/**
	 * Given two binary trees, write a function to check if they are equal or not.
	 * Two binary trees are considered equal if they are structurally identical 
	 * and the nodes have the same value.
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(Node p, Node q) {

		if(p==null && q==null)
			return true;

		if(p!=null && q!=null){
			return p.value==q.value && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
		}
		return false;

	}

	/**
	 * Invert a binary tree || mirror image of a binary tree
	 * @param root
	 * @return
	 */
	public Node invertTree(Node root) {
		if(root!=null){
			Node temp = root.left;
			root.left=root.right;
			root.right = temp;

			invertTree(root.left);
			invertTree(root.right);

		}
		return root;

	}

	/**
	 * Given a binary tree, return all root-to-leaf paths.
	 * For example, given the following binary tree:
   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:
["1->2->5", "1->3"]
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(Node root) {
		List<String> result = new ArrayList<String>();
		if (root == null) {
			return result;
		}
		findPaths(root, result, root.value + "");
		return result;
	}

	private void findPaths(Node root, List<String> result, String cur) {
		if (root.left == null && root.right == null) {
			result.add(cur);
			return;
		}
		if (root.left != null) {
			findPaths(root.left, result, cur + "->" + root.left.value);
		}
		if (root.right != null) {
			findPaths(root.right, result, cur + "->" + root.right.value);
		}
	}

	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 * For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3

	 * @param root
	 * @return
	 */
	public boolean isSymmetric(Node root) {
		if(root==null)
			return true;

		return mirrorEquals(root.left,root.right);
	}

	boolean mirrorEquals(Node left, Node right) {
		if (left == null || right == null) return left == null && right == null;
		return left.value == right.value && mirrorEquals(left.left, right.right) && mirrorEquals(left.right, right.left);
	}

	public int NodeSearch(Node root,int x)
	{
		while(root!=null)
		{
			if(root.value>x)
				root=root.left;
			else if(root.value<x)
				root=root.right;
			else return root.value;
		}
		return (Integer) null;


	}



	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		 // TODO Auto-generated method stub
		 int minimal[] = {1,3,4,5,6,7,8,9,10};
		 Node root = new Node(32);//root of bst
		 Node root1 = new Node(32);
		 Node test45 = new Node(45);
		 //code to build a Binary tree
		 /*Node root1 = new Node(1);
		root1.left = new Node(2);
		root1.right = new Node(3);
		root1.left.left = new Node(4);
		root1.left.right = new Node(5);
		root1.left.left.left = new Node(6);
		root1.left.left.right = new Node(7);*/
		 BSTFunctions as = new BSTFunctions('s',13);
		 /*as.insertNode(root, 21);
		as.insertNode(root, 11);
		as.insertNode(root, 39);
		as.insertNode(root, 36);
		as.insertNode(root, 26);
		as.insertNode(root, 25);
		as.insertNode(root, 28);
		as.insertNode(root, 37);
		as.insertNode(root, 44);*/
		 as.insertNode(root, 12);
		 as.insertNode(root, 45);
		 as.insertNode(root, 23);
		 as.insertNode(root, 4);
		 as.insertNode(root, 56);
		 //as.insertNode(root, 57);
		 //as.insertNode(root, 55);
		 as.insertNode(root, 40);
		 //as.insertNode(root, 24);
		 //as.insertNode(root, 42);
		 //as.insertNode(root, 41);

		 as.insertNode(root1, 12);
		 as.insertNode(root1, 45);
		 as.insertNode(root1, 23);
		 as.insertNode(root1, 4);
		 as.insertNode(root1, 56);
		 as.insertNode(root1, 40);

		 as.put('e',5);
		 as.put('a',1);
		 as.put('r',18);
		 as.put('h',8);
		 as.put('m',13);
		 as.put('c',3);
		 as.put('x',23);

		 System.out.println(as.keys());
		 System.out.println(as.minR());
		 System.out.println(as.maxR());
		 System.out.println(as.floorR('g'));
		 System.out.println(as.ceilR('q'));
		 //as.inorder(root);
		 //System.out.println("the value after adding is:"+nodeVal);
		 as.preorder(root);
		 as.postOrder(root);
		 //Node z=as.findMin(root);
		 //System.out.println("the min value is: "+z.value);
		 as.findMax(root);
		 //as.inorder(root);
		 //System.out.println("the parent of 23 is:"+as.findParent(root, new Node(23)).value+level);
		 //Node parent = findParent(root, new Node(40));
		 //as.cousinOfNode(root, 0, level, parent);
		 //as.deleteNode(root, new Node(23));
		 //System.out.println(as.isBalanced(root));
		 //as.inorder(root);
		 //do inoder ok
		 //as.findLowestCommonAncestor(root, 11, 28);
		 as.findksmallest(root, 3);
		 //Node z2 = as.findKlargest(root, 5);
		 //System.out.println("the kth largest val: "+z2.value);
		 //as.isBalanced(root);
		 //as.checkBST(root);
		 //Node z1 = inorderSucc(as.findKlargest(root, 5),root);
		 //System.out.println("The inorder successor is: "+z1.value);
		 //as.findNthSmallest(root, 2);
		 System.out.println("_______________++++");
		 //Node newNode = FlipTree(root1);
		 //Node a = as.MinimalHeightBST(minimal);
		 //as.inorder(a);
		 /*ArrayList<LinkedList<Node>> resultant = as.findLevelLinkList(root);		
		for(LinkedList<Node> a: resultant){
			for(int i=0;i<a.size();i++){
				System.out.print(a.get(i).value+"--");
			}System.out.println("\n");
		}*/
		 //as.connect(root);
		 //Boolean ans = as.isSameTree(root, root1);
		 //System.out.println(ans);
		 //as.invertTree(root);
		 //as.isBalanced(root);
		 /*List<List<Integer>> resultant = as.levelOrder(root);
		for(List<Integer> a: resultant){
			for(int i=0;i<a.size();i++){
				System.out.print(a.get(i)+"--");
			}System.out.println("\n");
		}*/
		 //as.deleteNode(root, test45);

		 //System.out.println(as.NodeSearch(root, 4));
		 //as.checkBST(root);
	 }

}
