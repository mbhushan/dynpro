
public class StringInterleavingCheck {

	public static void main(String[] args) {
		StringInterleavingCheck sic = new StringInterleavingCheck();
		
		String s1 = "aaby";
		String s2 = "aacd";
		String s3 = "aaacabdy";
		
		System.out.println("is interleaved: " + sic.isInterleaved(s1.toCharArray(), s2.toCharArray(), s3.toCharArray()));
		System.out.println("is interleaved: " + sic.isInterleaved(s1.toCharArray(), s2.toCharArray(), "aaacadyb".toCharArray()));

	}
	
	public boolean isInterleaved(char [] s1, char [] s2, char [] s3) {
		int row = s1.length;
		int col = s2.length;
		boolean [][] T = new boolean[row+1][col+1];
		
		T[0][0] = true;
		for (int j=1; j<=col; j++) {
			if (s3[j-1] == s2[j-1]) {
				T[0][j] = true;
			}
		}
		for (int i=1; i<=row; i++) {
			if (s3[i-1] == s1[i-1]) {
				T[i][0] = true;
			}
		}
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if (s3[i+j-1] == s1[i-1]) {
					T[i][j] = T[i-1][j];
				} else if (s3[i+j-1] == s2[j-1]) {
					T[i][j] = T[i][j-1];
				} else {
					T[i][j] = false;
				}
			}
		}
		
		return T[row][col];
	}
}
