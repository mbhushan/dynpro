import java.util.Arrays;


/*
Given a string, how many minimum splits would it take so that each partition after split is a palindrome
*/

public class PalindromicPartition {

	public static void main(String[] args) {
		PalindromicPartition pp = new PalindromicPartition();
		
		String [] st = {"abcbm", "aabb", "ababcbxyx"};
		
		for (int i=0; i<st.length; i++) {
			int minPartition = pp.palindromicPartition(st[i].toCharArray());
			System.out.println("input: " + st[i]);
			System.out.println("min partition: " + minPartition);
			System.out.println();
		}
		
//		String [] S = {"aba", "abba", "acd"};
//		for (int i=0; i<S.length; i++) {
//			System.out.println("is palindrome: " + pp.isPalindrome(S[i].toCharArray(), 0, S[i].length()-1));
//		}
		
	}
	
	public int palindromicPartition(char [] input) {
		int size = input.length;

		int [][] T = new int[size][size];

		for (int i=0; i<size; i++) {
			Arrays.fill(T[i], Integer.MAX_VALUE);
			T[i][i] = 0;
		}
		
		for (int len=1; len<=size; len++) {
			for (int i=0; i<size-len+1; i++) {
				int j = i + len - 1;
				if (isPalindrome(input, i, j)) {
					T[i][j] = 0;
					continue;
				}
				int val = Integer.MAX_VALUE;
				for (int k=i+1; k<=j; k++) {
					val = Math.min(val, 1 + (T[i][k-1] + T[k][j]));
				}
				T[i][j] = val;
			}
		}
//		for (int i=0; i<size; i++) {
//			for (int j=0; j<size; j++) {
//				if (T[i][j] == Integer.MAX_VALUE) {
//					System.out.print("-1" + " ");
//					continue;
//				}
//				System.out.print(T[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return T[0][size-1];
	}
	
	private boolean isPalindrome(char [] A, int low, int high) {
		while (low < high) {
			if (A[low] != A[high]) {
				return false;
			} 
			++low;
			--high;
		}
		return true;
	}
}
