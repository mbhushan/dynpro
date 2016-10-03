/*
Find minimum possible size of array with given rules for removing elements
Given an array of numbers and a constant k, minimize size of array with following rules for removing elements.

Exactly three elements can be removed at one go.
The removed three elements must be adjacent in array, i.e., arr[i], arr[i+1], arr[i+2]. 
And the second element must be k greater than first and third element must be k greater than second, 
i.e., arr[i+1] – arr[i] = k and arr[i+2]-arr[i+1] = k.

Example:

Input: arr[] = {2, 3, 4, 5, 6, 4}, k = 1
Output: 0
We can actually remove all elements. 
First remove 4, 5, 6 => We get {2, 3, 4}
Now remove 2, 3, 4   => We get empty array {}

Input:  arr[] = {2, 3, 4, 7, 6, 4}, k = 1
Output: 3
We can only remove 2 3 4
*/

public class MinimizeArraySize {
	
	public static void main(String[] args) {
		MinimizeArraySize ma = new MinimizeArraySize();
		
		int [][] A = {
				{2, 3, 4, 5, 6, 4},
				{2, 3, 4, 7, 6, 4}
		};
		int k = 1;
		for (int i=0; i<A.length; i++) {
			System.out.println("min array size: " + ma.minimizeArray(A[i], k));
		}
	}
	
	public int minimizeArray(int [] A, int k) {
		if (A == null || A.length < 3) {
			return 0;
		}
		int low = 0;
		int high = A.length;
		int [][] dp = new int[A.length][A.length];
		
		return minimizeArrayUtil(A, dp, low, high-1, k);
		
	}
	
	private int minimizeArrayUtil(int [] A, int [][] dp, int low, int high, int k) {
		if (low > high) {
			return 0;
		}
		if (dp[low][high] != 0) {
			return dp[low][high];
		}
		
		if (high-low+1 < 3) {
			return high-low+1;
		}
		
		// Initialize result as the case when first element is
	    // separated (not removed using given rules)
		int result = 1 + minimizeArrayUtil(A, dp, low+1, high, k);
		
		for (int i=low+1; i<=high-1; i++) {
			for (int j=i+1; j<=high; j++) {
				// Check if this triplet follows the given rules of
	            // removal. And elements between 'low' and 'i' , and
	            //  between 'i' and 'j' can be recursively removed.
				if (A[i] == (A[low] + k) && (A[j] == (A[low] + 2*k)) && (minimizeArrayUtil(A, dp, low+1, i-1, k) == 0) &&
						(minimizeArrayUtil(A, dp, i+1, j-1, k) == 0)) {
					result = Math.min(result, minimizeArrayUtil(A, dp, j+1, high, k));
				}
			}
		}
		dp[low][high] = result;
		return dp[low][high];
	}
}
