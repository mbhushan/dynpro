/*
‘.’ Matches any single character.
‘*’ Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch(“aa”,”a”) → false
isMatch(“aa”,”aa”) → true
isMatch(“aaa”,”aa”) → false
isMatch(“aa”, “a*”) → true
isMatch(“aa”, “.*”) → true
isMatch(“ab”, “.*”) → true
isMatch(“aab”, “c*a*b”) → true
 */

public class RegexMatch {
	public static void main(String[] args) {
		RegexMatch rm = new RegexMatch();

		System.out.println(rm.isMatch("aa", "a"));
		System.out.println(rm.isMatch("aa", "aa"));
		System.out.println(rm.isMatch("aaa", "aa"));
		System.out.println(rm.isMatch("aa", "a*"));
		System.out.println(rm.isMatch("aa", ".*"));
		System.out.println(rm.isMatch("ab", ".*"));
		System.out.println(rm.isMatch("aab", "c*a*b"));
	}

	public boolean isMatch(String S, String P) {
		if (S == null || P == null) {
			return false;
		}

		int sLen = S.length();
		int pLen = P.length();

		boolean[][] T = new boolean[sLen + 1][pLen + 1];

		T[0][0] = true;
		
		 //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (P.charAt(i-1) == '*') {
                T[0][i] = T[0][i - 2];
            }
        }


		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j <= pLen; j++) {
				if (S.charAt(i - 1) == P.charAt(j - 1)
						|| P.charAt(j - 1) == '.') {
					T[i][j] = T[i - 1][j - 1];
				} else if (P.charAt(j - 1) == '*') {
					T[i][j] = T[i][j-2];
					if ((S.charAt(i - 1) == P.charAt(j - 2)) || (P.charAt(j - 2) == '.')) {
						T[i][j] = T[i][j] || T[i - 1][j];
					}
				} else {
					T[i][j] = false;
				}
			}
		}

		return T[sLen][pLen];
	}
}
