/*
 Given a string S and a string T, count the number of distinct subsequences of T in S.
 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the 
 characters without disturbing the relative positions of the remaining characters. 
 (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 Here is an example:
 S = "rabbbit", T = "rabbit"
 Return 3.
 */

public class DistinctSubsequences {
	public static void main(String[] args) {

		DistinctSubsequences ds = new DistinctSubsequences();

		String[] S = { "rabbbit", "abdacgblc", "ABCDE" };
		String[] T = { "rabbit", "abc", "ACE" };
		
		for (int i=0; i<S.length; i++) {
			System.out.println("strings are: " + S[i] + " and " + T[i]);
			System.out.println("distinct subsequences: " + ds.distinctSubsequences(S[i].toCharArray(), T[i].toCharArray()));
		}
	}
	
	public int distinctSubsequences(char [] S, char [] T) {
		if (S.length == 0 || T.length == 0) {
			return 0;
		}
		int row = T.length;
		int col = S.length;
		int [][] dp = new int[row+1][col+1];
		
		for (int i=0; i<col; i++) {
			dp[0][i] = 1;
		}
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if (T[i-1] == S[j-1]) {
					dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
				} else {
					dp[i][j] = dp[i][j-1];
				}
			}
		}
		return dp[row][col];
	}
}
