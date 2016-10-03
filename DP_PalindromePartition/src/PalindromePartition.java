 /*
* Given a string s, partition s such that every substring of the partition is a palindrome.
* Return the minimum cuts needed for a palindrome partitioning of s.
* https://leetcode.com/problems/palindrome-partitioning-ii/
*/

public class PalindromePartition {
	
	public static void main(String [] args) {
		PalindromePartition PP = new PalindromePartition();
		String string = "abcbm";
		//string = "abcde";
		int minCut = PP.palindromePartitionMinCut(string);
		System.out.println("min cut: " + minCut);
		System.out.println("min cut str: " + PP.minCut(string));
	}

	public int palindromePartitionMinCut(String string) {
		if (string == null) {
			return 0;
		}
		
		int len = string.length();
		
		if (len < 1) {
			return 0;
		}
		
		int [][] T = new int[len][len];
		boolean [][] P = new boolean[len][len];
		// zero splits required for individual character strings.
		for (int i=0; i<len; i++) {
			T[i][i] = 0;
			P[i][i] = true;
		}
		
		for (int l=2; l<=len; l++) {
			for (int i=0; i<len - (l-1);i++) {
				int j = i + (l-1);
				
				// If L is 2, then we just need to compare two characters. Else
	            // need to check two corner characters and value of P[i+1][j-1]
	            if (l == 2)
	                P[i][j] = (string.charAt(i) == string.charAt(j));
	            else
	                P[i][j] = (string.charAt(i) == string.charAt(j)) && P[i+1][j-1];
	            
				//if (isPalindrome(string, i, j)) {
				if (P[i][j]) {
					T[i][j] = 0;
					continue;
				}
				T[i][j] = Integer.MAX_VALUE;
				for (int k=i; k<j; k++) {
					T[i][j] = Math.min(T[i][j], T[i][k] + T[k+1][j] + 1);
				}
			}
		}
		
		
		return T[0][len-1];
	}
	
	
	public int minCut(String str){
        if (str.length() == 0) {
            return 0;
        }

        int[] cut = new int[str.length()];
        boolean isPal[][] = new boolean[str.length()][str.length()];
        for (int i = 1; i < str.length(); i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == str.charAt(j) && (i <= j + 1 || isPal[i - 1][j + 1])) {
                    isPal[i][j] = true;
                    min = Math.min(min, j == 0 ? 0 : 1 + cut[j - 1]);
                }
            }
            cut[i] = min;
        }
        return cut[str.length() - 1];
    }

	
	 private  boolean isPalindrome(String str, int r, int t) {
	        while(r < t) {
	            if (str.charAt(r) != str.charAt(t)) {
	                return false;
	            }
	            r++;
	            t--;
	        }
	        return true;
	    }
	
}
