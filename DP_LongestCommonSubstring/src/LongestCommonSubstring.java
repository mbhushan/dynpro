
public class LongestCommonSubstring {

	public int longestCommonSubstring(char[] A, char[] B) {
		int lenA = A.length;
		int lenB = B.length;
		int [][] T = new int[lenA+1][lenB+1];
		int max = 0;
		for (int i=1; i<=lenA; i++) {
			for (int j=1; j<=lenB; j++) {
				if (A[i-1] == B[j-1]) {
					T[i][j] = T[i-1][j-1] + 1;
					if (max < T[i][j]) {
						max = T[i][j];
					}
				}
			}
		}
		return max;
	}
	
	public int lcsRec(char str1[], char str2[], int pos1, int pos2,
			boolean checkEqual) {
		if (pos1 == -1 || pos2 == -1) {
			return 0;
		}
		if (checkEqual) {
			if (str1[pos1] == str2[pos2]) {
				return 1 + lcsRec(str1, str2, pos1 - 1, pos2 - 1, true);
			} else {
				return 0;
			}
		}
		int r1 = 0;
		if (str1[pos1] == str2[pos2]) {
			r1 = 1 + lcsRec(str1, str2, pos1 - 1, pos2 - 1, true);
		}
		return Math.max(
				r1,
				Math.max(lcsRec(str1, str2, pos1 - 1, pos2, false),
						lcsRec(str1, str2, pos1, pos2 - 1, false)));
	}
	
	public static void main(String [] args) {
		LongestCommonSubstring LCS = new LongestCommonSubstring();
		String A = "abcdef";
		String B = "zcdemf";
		int max = LCS.longestCommonSubstring(A.toCharArray(), B.toCharArray());
		System.out.println("longest common substring: "  + max);
		System.out.println("lcs recursive: " + LCS.lcsRec(A.toCharArray(), B.toCharArray(), A.length()-1, 
				B.length() - 1, false));
	}
}
