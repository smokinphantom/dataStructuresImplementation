package trie;

public class TrieBasic<Value> {

	private TrieNode root= new TrieNode();

	
	public void put(String key, Value val){
		root = put(root,key,val,0);
	}

	private TrieNode put(TrieNode x, String key, Value val, int d) {
		if(x==null) x= new TrieNode();
		if(d==key.length()){x.value=val; return x;}
		char c = key.charAt(d);
		x.next[c]=put(x.next[c],key,val,d+1);
		return x;
	}
	
	public Value get(String key){
		TrieNode x= get(root, key, 0);
		if(x==null) return null;
		return (Value) x.value;
	}
	
	//Check if node is null, if yes return null, check is key length = d if yes return value, 
	// if no get char, recurse into the function for checking next link
	private TrieNode get(TrieNode x, String key, int d) {
		if(x==null) 
			{
				System.out.println(key+":no value found");
				return null;
			}
		if(d==key.length()) return x;
		char c= key.charAt(d);
		x = get(x.next[c],key,d+1);
		return x;
	}
	
	public void delete(String key){
		 root=delete(root, key, 0);	
	}
	
	//recursively find the value of the node, set it to null then check whther the value is null and if it is null
	//set next link of the character to null
	private TrieNode delete(TrieNode x, String key, int d) {
		if(x==null) return null;
		if(d==key.length()) 
		{
			x.value=null;
			return x;
		}
		char c= key.charAt(d);
		delete(x.next[c],key,d+1);
		if(x.value==null)
			x.next[c]=null;
		return x;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrieBasic<Integer> tr = new TrieBasic<Integer>();
		tr.put("hat", 0);
		tr.put("hater", 1);
		tr.put("haleys", 2);
		
		tr.put("sen", 4);
		tr.put("serious", 5);
		tr.put("senorita", 6);
		tr.put("seminar", 9);
		
		System.out.println(tr.get("senorita"));
		tr.delete("serious");
		System.out.println(tr.get("serious"));
	}

}
