
public class MaxSumSubsequence {

	public int maxSumSubsequence(int [] A) {
		if (A == null) {
			return -1;
		}
		if (A.length < 1) {
			return -1;
		}
		int len = A.length;
		int [] T = new int[len];
		
		for (int i = 0; i < T.length; i++) {
            T[i] = A[i];
        }
		
		for(int i=1; i<len; i++) {
			for (int j=0; j<i; j++) {
				if (A[j] < A[i]) {
					T[i] = Math.max(T[i], T[j] + A[i]);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (Integer x: T) {
			if (x > max) {
				max = x;
			}
		}
		return  max;
	}
	public static void main(String [] args) {
		MaxSumSubsequence MSS = new MaxSumSubsequence();
		int A[] = {1, 101, 10, 2, 3, 100,4};
		int max = MSS.maxSumSubsequence(A);
		System.out.println("max subsequence sum: " + max);
		
		
	}
}
