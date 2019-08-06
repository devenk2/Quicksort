package cmsc351s19;

import java.util.Random;

public class PermutationGenerator {

	final private Random m_random;
	
	/**
	 * constructor. You should not modify this constructor
	 * @param random The randomness source
	 */
	public PermutationGenerator(Random random) {
		m_random = random;
	}
	
	/**
	 * Generate a new random permutation of {1, 2, 3, ... , n}
	 * @param n The size of the permutation
	 * @return The new random permutation
	 */
	public int[] nextPermutation(int n) {
		int[] result = new int [n];
		for (int i = n-1; i >= 0; i--) {
			result[i] = i+1;
		}
		
		for (int i = n-1; i > 0; i--) {
			int j = m_random.nextInt(i+1);
			
			int t = result[i];
			result[i] = result[j];
			result[j] = t;
			
		}
		return result;
	}

}
