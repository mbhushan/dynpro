import java.util.Arrays;

import javax.swing.text.MaskFormatter;

/*
Given an array of positive number, find maximum sum subsequence such that elements in this subsequence are not adjacent to each other.
*/

public class MaxSumNonAdjacent {

	public static void main(String[] args) {
		MaxSumNonAdjacent ms = new MaxSumNonAdjacent();
		
		int [][] A = {
				{4, 1, 1, 4, 2, 1},
				{2, 5, 3, 1, 7},
				{ 2, 10, 13, 4, 2, 15, 10 },
				{5,  5, 10, 40, 50, 35},
				{5, 5, 10, 100, 10, 5},
				{3, 2, 7, 10}
		};
		
		for (int i=0; i<A.length; i++) {
			System.out.println("input array: ");
			System.out.println(Arrays.toString(A[i]));
			System.out.println("max sum non adjacent: " + ms.maxSumNonAdj(A[i]));
			System.out.println("max sum non adjacent recursive: " + ms.maxSumNonAdjRec(A[i]));
		}
	}
	
	public int maxSumNonAdj(int [] A) {
		int incl = A[0];
		int excl = 0;
		
		for (int i=1; i<A.length; i++) {
			int buff = incl;
			// A[i] is included
			incl = A[i] + excl;
			
			//A[i] is excluded
			excl = Math.max(buff, excl);
		}
		
		return Math.max(incl, excl);
	}
	
	public int maxSumNonAdjRec(int [] A) {
		return maxSumNonAdjRec(A, A.length-1);
	}
	
	public int maxSumNonAdjRec(int [] A, int index) {
		if (index == 0) {
			return A[0];
		}
		if (index == 1) {
			return Math.max(A[0], A[1]);
		}
		
		return Math.max(maxSumNonAdjRec(A, index-2) + A[index], maxSumNonAdjRec(A, index-1));
	}
}
