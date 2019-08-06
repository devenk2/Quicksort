package cmsc351s19;

import java.util.Arrays;

public class ModifiedQuicksorter {

	/* number of pivots that will be used */
	protected int m_k;
	/* number of comparisons performed */
	protected int m_comparisons = 0;
	/* number of moves performed */
	protected int m_moves = 0;
	
	/**
	 * constructor
	 * @param k The number of pivots, guaranteed to be at least 1
	 */
	public ModifiedQuicksorter(int k) {
		m_k = k;
		
		/* TODO: Add your initialization if any */
	}
	
	/**
	 * Perform an in-place insertion sort on array[1...n], both end-points inclusive
	 * All the comparisons and moves COUNT in this function
	 * You should implement an insertion sort WITH sentinel here
	 * @param array array to sort
	 * @param n number of elements in the array
	 */
	public void insertionSort(int[] array, int n) {
		//System.out.println("Inside insertion sort: ");
		int[] temp = new int [n+1];
		temp[0] = Integer.MIN_VALUE;
		m_moves += 1;
		for (int i = 1; i < temp.length; i++) {
			temp[i] = array[i-1];
		}
		//System.out.println(Arrays.toString(temp));
		for (int i = 1; i <= n; i++) {
			int t = temp[i];
			int j = i - 1;
			while (t < temp[j]) {
				m_comparisons++;
				temp[j+1] = temp[j];
				m_moves++;
				j = j - 1;
			} 
			m_comparisons++;
			temp[j+1] = t;
			m_moves += 1;
		}
		//System.out.println(Arrays.toString(temp));
		for (int i = 0; i < array.length; i++) {
			array[i] = temp[i+1];
		}
	}
	
	/**
	 * Perform an in-place insertion sort on array[p...q], both end-points inclusive
	 * This function extracts array[p...q] and maps it into an array [0, array[p], array[p+1], ..., array[q]],
	 * then it calls the overloaded insertionSort on the extracted array
	 * Moves happened in this function DOESN't count towards m_moves
	 * 
	 * You should NOT modify this function
	 * You should ALWAYS call this version of insertion sort in your other function
	 * @param array array to sort
	 * @param p starting point
	 * @param q ending point
	 */
	public void insertionSort(int[] array, int p, int q) {
		// Sanity check
		if(p >= q) {
			return;
		}
		// Create a new array. Size is one more than the number of elements to accommodate sentinel
		int[] extractedArray = new int[q - p + 2];
		// Copy array[p...q] to extractedArray[1...(q-p+1)]
		System.arraycopy(array, p, extractedArray, 1, q - p + 1);
		// Make the call
		insertionSort(extractedArray, q - p + 1);
		// Copy back
		System.arraycopy(extractedArray, 1, array, p, q - p + 1);
	}
	
	/**
	 * Partition array[p...q], both end-points inclusive
	 * You should sort the last m_k elements in a[p...q], and then use them as pivots to partition the array in-place.
	 * You should then return the indices of those pivots in an array, in increasing order, which SHOULD have size exactly m_k
	 * @param array array to partition
	 * @param p starting point
	 * @param q ending point
	 * @return the dices of partitioned pivots
	 */
	public int[] partition(int[] array, int p, int q) {
//		System.out.println(Arrays.toString(array));
//		System.out.println("New partition");
//		System.out.println("m_k = " + m_k);
//		System.out.println("p = " + p);
//		System.out.println("q = " + q);
		int[] copy = Arrays.copyOfRange(array, q+1 - m_k, q+1);
//		System.out.println(Arrays.toString(copy));
//		System.out.println("After copy creation");
		insertionSort(copy, m_k);
//		System.out.println(Arrays.toString(copy));
//		System.out.println("After insertion");
//		System.out.println("arr[q+1 - m_k] = " + array[q+1 - m_k]);
		for (int i = q+1 - m_k; i < q+1; i++) {
			array[i] = copy[i- (q+1 - m_k)];
		}
//		System.out.println(Arrays.toString(array));
//		System.out.println("New array after insertion");
		int low = p;
		int[] pivotPlaces = new int [m_k];
		int pivX = 0;
		for (int m = q+1 - m_k; m < q+1; m++) {
			//System.out.println("Starting partition with " + array[m] + " as pivot:");
			int pivot = array[m];
			int i = (low - 1);
			for (int j = low; j < m; j++) {
				if (array[j] <= pivot) {
					i++;
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;	
					m_moves++;
				}
				m_comparisons += 1;
			}
//			System.out.println("Array before sift: ");
//			System.out.println(Arrays.toString(array));
			
			int temp = array[i+1];
			array[i+1] = array[m];
			array[m] = temp;
			m_moves++;
			low = i+2;
			pivotPlaces[pivX] = i+1;
			pivX++;
//			System.out.println("Array after sift: ");
//			System.out.println(Arrays.toString(array));
		}
		return pivotPlaces;
	}
	
	/**
	 * Sort array[p...q] in-place using modified quicksort, both end-points inclusive
	 * @param array array to sort
	 * @param p starting point
	 * @param q ending point
	 */
	public void sort(int[] array, int p, int q) {
		if (q+1 - p > 2*m_k) { 
			int[] pivs = partition(array, p, q);
			sort(array, p, pivs[0]-1);
			for(int i = 0; i < pivs.length-1; i++) {
				sort(array, pivs[i]+1, pivs[i+1]-1);
			}
			sort(array, pivs[pivs.length-1]+1, q);
		} else if (q+1 - p > 1) {
			int[] copy = Arrays.copyOfRange(array, p, q+1);
			insertionSort(copy, copy.length);
			for (int i = p; i < q+1; i++) {
				array[i] = copy[i - p];
			}
		}
	}
	
	/**
	 * Sort array in-place using modified quicksort
	 * This function just calls overloaded version of sort
	 * You should NOT modify this function
	 * @param array array to sort
	 */
	public void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}
	
	/**
	 * Get the number of comparisons up to now
	 * You should not change this function
	 * @return the number of comparisons
	 */
	final public int getComparisons() {
		return m_comparisons;
	}
	
	/**
	 * Get the number of moves up to now
	 * You should not change this function
	 * @return the number of moves
	 */
	final public int getMoves() {
		return m_moves;
	}

}
