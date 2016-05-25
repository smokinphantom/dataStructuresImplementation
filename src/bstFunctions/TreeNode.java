package bstFunctions;

public class TreeNode<Key,Value> {

	Key key;
	Value value;
	TreeNode left;
	TreeNode right;
	TreeNode next;
	
	public TreeNode(Key key,Value value){
		this.key = key;
		this.value = value;
	}

}
