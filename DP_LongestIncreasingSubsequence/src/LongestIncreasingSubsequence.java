
public class LongestIncreasingSubsequence {

	public int LISRecursive(int [] A) {
		int maxLen = 0;
		int len = A.length;
		for (int i=0; i<len-1; i++) {
			int currLen = LISRecUtil(A, i+1, A[i]);
			if (currLen > maxLen) {
				maxLen = currLen;
			}
		}
		return maxLen + 1;
	}
	
	private int LISRecUtil(int [] A, int index, int lastNum) {
		if (index == A.length) {
			return 0;
		}
		int t1 = 0;
		if (A[index] > lastNum) {
			t1 = 1 + LISRecUtil(A, index+1, A[index]);
		}
		int t2 = LISRecUtil(A, index+1, lastNum);

		return Math.max(t1, t2); 
	}
	
	public static void main(String [] args) {
		LongestIncreasingSubsequence LIS = new LongestIncreasingSubsequence();
		int [] A = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
		System.out.println("LIS: " + LIS.LISRecursive(A));
	}
}
