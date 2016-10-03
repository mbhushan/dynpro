package main.java.com.wc;

public class WildcardMatching {

	public static void main(String[] args) {
		WildcardMatching wm = new WildcardMatching();
		
		
		boolean ans = wm.wildcardMatch("aa","a");
		System.out.println(ans);
		ans = wm.wildcardMatch("aa","aa");
		System.out.println(ans);
		ans = wm.wildcardMatch("aaa","aa") ;
		System.out.println(ans);
		ans = wm.wildcardMatch("aa", "*") ;
		System.out.println(ans);
		ans = wm.wildcardMatch("aa", "a*") ;
		System.out.println(ans);
		ans = wm.wildcardMatch("ab", "?*") ;
		System.out.println(ans);
		ans = wm.wildcardMatch("aab", "c*a*b");
		System.out.println(ans);
		
	}
	
	public boolean wildcardMatch(String S, String P) {
		System.out.println("string: " + S + ", pattern: " + P);
		if (S == null || P == null) {
			return false;
		}
		int row = S.length();
		int col = P.length();
		boolean [][] T = new boolean[row+1][col+1];
		T[0][0] = true;
		for (int i=1; i<=col; i++) {
			if (P.charAt(i-1) == '*') {
				T[0][i] = T[0][i-1];
			}
		}
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if ((S.charAt(i-1) == P.charAt(j-1)) || (P.charAt(j-1) == '?')) {
					T[i][j] = T[i-1][j-1];
				} else if (P.charAt(j-1) == '*') {
					T[i][j] = T[i-1][j] || T[i][j-1];
				} else {
					T[i][j] = false;
				}
			}
		}
		
		return T[row][col];
	}
}
