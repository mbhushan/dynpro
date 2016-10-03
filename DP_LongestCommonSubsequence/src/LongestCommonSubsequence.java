
public class LongestCommonSubsequence {

	
	public static void main(String [] args) {
		String A = "computerscience";
		String B = "programmingandcoding";
		
		LongestCommonSubsequence LCS = new LongestCommonSubsequence();
		System.out.println("A: " + A );
		System.out.println("B: " + B );
		System.out.println("lcs: " + LCS.lcs(A, B));
		System.out.println("LCS DP:" + LCS.lcsDP(A, B));
	}
	
	public int lcsDP(String A, String B) {
		char [] X = A.toCharArray();
		char [] Y = B.toCharArray();
		int lenX = X.length;
		int lenY = Y.length;
		int [][] T = new int[lenX+1][lenY+1];
		int max = 0;
		for (int i=1; i<T.length; i++) {
			for (int j=1; j<T[0].length; j++) {
				if (X[i-1] == Y[j-1]) {
					T[i][j] = 1 + T[i-1][j-1];
				} else {
					T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
				}
				if (T[i][j] > max) {
					max = T[i][j];
				}
			}
		}
		return max;
	}
	
	public int lcs(String A, String B) {
		if (A == null || B == null) {
			return 0;
		}
		return lcsUtil(A.toCharArray(), B.toCharArray(), 0, 0);
	}
	
	private int lcsUtil(char [] A, char [] B, int lenA, int lenB) {
		if (lenA == A.length || lenB == B.length) {
			return 0;
		}
		
		if (A[lenA] == B[lenB]) {
			return 1 + lcsUtil(A, B, lenA+1, lenB+1);
		} else {
			return Math.max(lcsUtil(A, B, lenA+1, lenB), lcsUtil(A, B, lenA, lenB+1));
		}
	}
	
}
