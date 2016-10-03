
public class LongestPalindromicSubsequence {
	
	public int lpsDP(char [] A) {
		int len = A.length;
		int [][] T = new int[len][len];
		for (int i=0; i<len; i++) {
			T[i][i] = 1;
		}
		
		for (int w=2; w<=len; w++) {
			for (int i=0; i<len-w+1; i++) {
				int j = i + w - 1;
				if (w == 2 && A[i] == A[j]) {
					T[i][j] = 2 ;
				} else if (A[i] == A[j]) {
					T[i][j] = T[i + 1][j-1] + 2;
				} else {
					T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
				}
			}
		}
		return T[0][len-1];
	}

	public int lpsRec(char [] A, int start, int len) {
		if (len == 1) {
			return 1;
		}
		if (len == 0) {
			return 0;
		}
		if (A[start] == A[start + len-1]) {
			return 2 + lpsRec(A, start+1, len-2);
		} else {
			return Math.max(lpsRec(A, start+1, len-1), lpsRec(A, start, len-2));
		}
	}
	
	public static void main(String [] args) {
		LongestPalindromicSubsequence LPS = new LongestPalindromicSubsequence();
		String A = "agbdba";
		System.out.println("longest palindromic seq: " + LPS.lpsRec(A.toCharArray(), 0, A.length()));
		System.out.println("DP LPS: " + LPS.lpsDP(A.toCharArray()));
		
	}
}
