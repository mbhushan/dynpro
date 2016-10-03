import java.util.Arrays;


/*
Find longest bitonic subsequence in given array. Bitonic subsequence first increases then decreases.
*/

public class LongestBitonicSequence {
	
	public static void main(String[] args) {
		LongestBitonicSequence lbs = new LongestBitonicSequence();
		
		int [] A = {2, -1, 4, 3, 5, -1, 3, 2};
		
		lbs.longestBitonicSequence(A);
	}
	
	private void longestBitonicSequence(int [] A) {
		
		int [] leftToRight = new int[A.length];
		int [] rightToLeft = new int[A.length];
		
		Arrays.fill(leftToRight, 1);
		Arrays.fill(rightToLeft, 1);

		for (int i=1; i<A.length; i++) {
			for (int j=0; j<i; j++) {
				if (A[i] > A[j]) {
					leftToRight[i] = Math.max(leftToRight[i], leftToRight[j] + 1);
				}
			}
		}
		
		for (int i=A.length-2; i>=0; i--) {
			for (int j=A.length-1; j>i; j--) {
				if (A[i] > A[j]) {
					rightToLeft[i] = Math.max(rightToLeft[i], rightToLeft[j] + 1);
				}
			}
		}
		System.out.println("left to right array: ");
		System.out.println(Arrays.toString(leftToRight));
		System.out.println("right to left array: ");
		System.out.println(Arrays.toString(rightToLeft));
		int maxSeq = 1;
		for (int i=0; i<A.length; i++) {
			maxSeq = Math.max(maxSeq, leftToRight[i] + rightToLeft[i] - 1);
		}
		
		System.out.println("max bitonic sequence: " + maxSeq);
	}
}
