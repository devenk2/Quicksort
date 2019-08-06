package cmsc351s19;

import java.util.Arrays;
import java.util.Random;

//import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int n = 6;
		int k = 2;
		
//		System.out.println("Random array generation:");
//		Random random = new Random(); 
//		PermutationGenerator pg = new PermutationGenerator(random);
		
		
		//int[] array = pg.nextPermutation(n);
		
		int[] array = {10,50,30,20,80,40};
		System.out.println(Arrays.toString(array));
		
		// Create a MartianOracle
		ModifiedQuicksorter sorter = new ModifiedQuicksorter(k);
		// Read n elements
		
		sorter.sort(array);
		// Print the array and statistics
		for(int i = 0; i < n; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
		System.out.printf("Total number of comparisons: %d%n", sorter.getComparisons());
		System.out.printf("Total number of moves: %d%n", sorter.getMoves());
		
		System.out.println();
		
		
	}

}
