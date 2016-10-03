import java.util.ArrayList;
import java.util.Collections;


/*
Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of 
the given array such that the intgers in the subsequence are sorted in increasing order. 
For example, if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), 
if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) and 
if the input array is {10, 5, 4, 3}, then output should be 10
*/

public class MaxSumIncreasingSubsequence {

	public static void main(String[] args) {
		MaxSumIncreasingSubsequence ms = new MaxSumIncreasingSubsequence();
		
		int [] A = {1, 101, 2, 3, 100, 4, 5};
		ms.maxSumIncreasingSubsequence(A);
	}
	
	public void maxSumIncreasingSubsequence(int [] A) {
		int size = A.length;
		int [] T = new int[size]; //Stores the max sum at any index.
		int [] R = new int[size]; //stores the final result.
		
		for (int i=0; i<size; i++) {
			T[i] = A[i];
			R[i] = i;
		}
		
		for (int i=1; i<size; i++) {
			for (int j=0; j<i; j++) {
				if (A[i] > A[j]) {
					//T[i] = Math.max(T[i], T[j] + A[i]);
					if (T[j] + A[i] > T[i]) {
						T[i] = T[j] + A[i];
						R[i] = j;
					}
				}
			}
		}
		
		int maxSum = 0;
		int index = 0;
		for (int i=0; i<size; i++) {
			if (T[i] > maxSum) {
				maxSum = T[i];
				index = i;
			}
		}
		System.out.println("max sum: " + maxSum);
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (index != R[index]) {
			//System.out.print(A[index] + " ");
			result.add(A[index]);
			index = R[index];
		}
		//System.out.println(A[index]);
		result.add(A[index]);
		Collections.reverse(result);
		System.out.println(result.toString());
	}
}
