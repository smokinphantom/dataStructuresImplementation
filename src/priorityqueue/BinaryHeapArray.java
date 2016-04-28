package priorityqueue;

public class BinaryHeapArray<K extends Comparable<K>> {

	private int size=0;
	private K pq[];
	
	//
	public BinaryHeapArray(int capacity) {
		pq = (K[]) new Comparable[capacity+1];
	}
	
	public void insert(K key){
		pq[++size]=key;
		swim(size);
	}
	
	//we insert element at bottom and we want to reheap to adjust the element location. check if k>1 and k/2 i.e parent is less than
	//child to insert, if it is exchange it with parent and update the position
	private void swim(int k) {
		while(k>1 && less(k/2,k)){
			exch(k,k/2);
			k=k/2;
		}
		
	}

	//class is k extends comparable as we use compareto
	private boolean less(int i, int k) {
		
		return pq[i].compareTo(pq[k]) <0;
	}

	private boolean isEmpty(){
		return size==0;
	}
	
	//delmax returns max and deletes it. take topmost val, exchange the lowest with topmost, sink it for reheap, null the 
	//size+1.
	public K delMax(){
		K max = pq[1];
		exch(1,size--);
		sink(1);
		pq[size+1]=null;
		return max;
	}
	
	private void exch(int i, int j){
		K key = pq[i];
		pq[i]=pq[j];
		pq[j]=key;
	}
	
	//Since 2k and 2k+1 are children of k, and we navigate downwards, check if 2k< size, check, which child is bigger 
	//and if the bigger child is greater than parent, exchange it and decrement the value of k to child exchanged.
	private void sink(int k){
		while(2*k<=size){
			int j=2*k;
			if(j<size && less(j,j+1) ) j++;
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryHeapArray<Integer> bh = new BinaryHeapArray<Integer>(10);
		bh.insert(4);
		bh.insert(3);
		bh.insert(5);
		bh.insert(6);
		bh.insert(1);
		bh.insert(2);
		bh.insert(7);
		bh.insert(8);
		
		System.out.println("max number is:"+bh.delMax());
		System.out.println("max number is:"+bh.delMax());
		System.out.println("max number is:"+bh.delMax());
	}

}
