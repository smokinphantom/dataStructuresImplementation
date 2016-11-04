package sorting;

public class Sorting {
	
	//function to call from main
	public int[] QuickSort(int[] arr){
		qsort(arr,0,arr.length-1);
		return arr;
	}

	
	private void qsort(int[] arr, int lo, int hi) {
		
		if(hi<=lo) return;
		int j = partition(arr,lo,hi);
		qsort(arr,lo,j-1);
		qsort(arr,j+1,hi);
		
	}
	
	private int quickSelect(int[] arr, int k){
		int lo = 0, hi = arr.length-1;
		
		while(hi>lo){
			int j = partition(arr, lo, hi);
			if(j<k) lo = j+1;
			else if(j>k) hi = j-1;
			else return arr[k];
		}
		return arr[k];
	}

	//partitioning function
	private int partition(int[] arr, int lo, int hi) {
		
		int i=lo, j=hi+1,tmp;
		
		while(i<=j){
			while(arr[++i]<arr[lo]) //find an item on left to swap
				if(i==hi) break;
			
			while(arr[--j]>arr[lo]) // find an item on right to swap
				if(j==lo) break;
			
			if (i >= j) break;  //check if pointers cross
				exch(arr,i,j);	//swap
              	
		}
		exch(arr,lo,j); //swap j i.e after i and j have crossed path with pivot i.e lo
		return j;
	}
	
	
	//helper method to swap
	private void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int qsort[] = {4,2,7,9,1,0};
		
		Sorting st = new Sorting();
		/*for(int a : st.QuickSort(qsort)){
			System.out.println(a+" ");
		}*/
		st.quickSelect(qsort, 1);
		

	}

}
