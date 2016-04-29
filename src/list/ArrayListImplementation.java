package list;

import java.util.NoSuchElementException;

public class ArrayListImplementation<E> {

	private Object arr[];
	private int size=0;
	private static final int DEFAULT_CAPACITY = 3;

	public ArrayListImplementation(){
		arr = new Object[DEFAULT_CAPACITY];
	}
	
	public void add(E e){
		if(arr.length==size)
			resize(arr.length*2);
		arr[size++]=e;
	}
	
	public void remove(int i){
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		if(i>size) throw new ArrayIndexOutOfBoundsException();
		arr[i]=null;
		int temp=i;
		while(temp<size){
			arr[temp]=arr[temp+1];
			arr[temp+1] = null;
			temp++;	
		}
		size--;
		
		if(size>0 && size==arr.length/4) 
			resize(arr.length/2);
		
	}
	
	public Object get(int i){
		if(i<size)
			return arr[i];
		else 
			throw new ArrayIndexOutOfBoundsException();
		
	}
	
	private void resize(int i){
		
		Object temp[] = new Object[i];
		for(int j=0;j<size;j++)
		{
			temp[j] = arr[j];
		}
		arr=temp;
		
	}
	
	private boolean isEmpty(){
		return size==0;
	}
	
	public static void main(String[] args) {
		ArrayListImplementation<Integer> test = new ArrayListImplementation<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.remove(4);
		test.remove(2);
		test.remove(1);
		test.remove(0);
		
	}

}
