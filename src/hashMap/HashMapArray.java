package hashMap;

import java.util.LinkedList;
import java.util.Queue;

public class HashMapArray<K,V> {
	
	private static final int CAPACITY = 8;
	private int size=0;
	private K keyA[];
	private V valueA[];
	
	public HashMapArray(){
		keyA = (K[]) new Object[CAPACITY];
		valueA = (V[]) new Object[CAPACITY];
	}
	
	public void put(K key,V value){
		
		if(size>keyA.length) 
			resize(keyA.length*2);
		
		keyA[size]=key;
		valueA[size]=value;
		size++;
		
	}
	
	public V get(K key){
		for(int i=0;i<keyA.length;i++)
			if(key.equals(keyA[i])) return valueA[i];
		
		return null;
		
		
	}
	
	private void resize(int newSize) {
		K[] resizedKeyArr = (K[]) new Object[newSize];
		V[] resizedValueArr = (V[]) new Object[newSize];
		for(int i=0;i<keyA.length;i++){
			resizedKeyArr[i]=keyA[i]; 
			resizedValueArr[i]=valueA[i];
		}
		keyA = resizedKeyArr;
		valueA = resizedValueArr;	
	}
	
	public void delete(K key){
		for(int i=0;i<keyA.length;i++){
			if(key.equals(keyA[i])){
				keyA[i]=keyA[size-1];
				valueA[i]=valueA[size-1];
				keyA[size-1]=null;
				valueA[size-1]= null;
				size--;
				if(size==(keyA.length/4) && size>0 )
					resize(keyA.length/2);
				return;
			}
			
		}
	}

	public int size(){
		return size;
	}
	
	public Iterable<K> keys(){
		Queue<K> qu = new LinkedList<K>(); 
		for(int i=0;i<keyA.length;i++){
			qu.add(keyA[i]);
		}
		return qu;
		
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMapArray<String, String> hs = new HashMapArray<String, String>();
		hs.put("Mahmood", "Rushda");
		hs.put("Jinesh", "Janet");
		hs.put("Aditya", "Garima");
		hs.put("Sharif", "kim");
		
		System.out.println(hs.get("Jinesh"));
		System.out.println(hs.get("Mahmood"));
		
		hs.delete("Sharif");
		
		for(String a : hs.keys())
		{
			System.out.println(a+"-");
		}
		

	}

}
