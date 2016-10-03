
public class StringInterleaving {
	
	
	public boolean isInterleavedDP(String S1, String S2, String S3) {
		char [] A = S1.toCharArray();
		char [] B = S2.toCharArray();
		char [] C = S3.toCharArray();
		
		boolean [][] T = new boolean[A.length+1][B.length+1];
		
		if (A.length + B.length != C.length) {
			return false;
		}
		
		for (int i=0; i<=A.length; i++) {
			for (int j=0; j<=B.length; j++) {
				int k = i + j -1;
				if (i == 0 && j == 0) {
					T[i][j] = true;
				} else if (i == 0) {
					if (B[j-1] == C[k]) {
						T[i][j] = T[i][j-1];
					}
				} else if (j ==0) {
					if (A[i-1] == C[k]) {
						T[i][j] = T[i-1][j];
					}
				} else {
					T[i][j] = (A[i-1] == C[k] ? T[i-1][j]: false) || (B[j-1] == C[k] ? T[i][j-1] : false);
				}
			}
		}
		return T[A.length][B.length];
		
	}

	public static void main(String[] args) {
		StringInterleaving SI = new StringInterleaving();
		String S1 = "aab";
		String S2 = "axy";
		String S3 = "aaxaby";
		boolean ans = SI.isInterleaved(S1.toCharArray(), S2.toCharArray(), S3.toCharArray(), 0, 0, 0);
		System.out.println("is interleaved: " + ans);
		System.out.println("is interleaved DP: " + SI.isInterleavedDP(S1, S2, S3));
		S3 = "abaaxy";
		ans = SI.isInterleaved(S1.toCharArray(), S2.toCharArray(), S3.toCharArray(), 0, 0, 0);
		System.out.println("is interleaved: " + ans);
		System.out.println("is interleaved DP: " + SI.isInterleavedDP(S1, S2, S3));
	}
	
	public boolean isInterleaved(char [] S1, char [] S2, char [] S3, int p1, int p2, int p3) {
		if (p1 == S1.length && p2 == S2.length && p3 == S3.length) {
			return true;
		}
		if (p3 == S3.length) {
			return false;
		}
		
		return (p1 < S1.length && S1[p1] == S3[p3] && isInterleaved(S1, S2, S3, p1+1, p2, p3+1)) 
				|| (p2 < S2.length && S2[p2] == S3[p3] && isInterleaved(S1, S2, S3, p1, p2+1, p3+1));
	}
}
