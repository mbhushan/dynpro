import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Given an array, find longest increasing subsequence in nlogn time complexity
 *
 * References
 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 * http://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/
 */

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		
		int [][] A = {
				{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10},
				{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},
				{2, 5, 3, 7, 11}
		};
		
		
		for (int i = 0; i < A.length; i++) {
			ArrayList<Integer> result = lis.longestIncSubseq(A[i]);
			System.out.println("LIS: " + result.size());
			for (Integer x : result) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	
	public ArrayList<Integer> longestIncSubseq(int [] A) {
		int len = A.length;
		int [] T  = new int[len];
		int [] R = new int[len];
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		Arrays.fill(R, -1);
		
		int low = 0;
		int high = 0;
		
		for (int i=1; i<A.length; i++) {
			if (A[i] > A[T[high]]) {
				++high;
				T[high] = i;
				R[i] = T[high-1];
			} else if ( A[i] < A[T[low]]) {
				T[low] = i;
			} else {
				int index = findCieling(A, T, A[i], low, high);
				T[index] = i;
				R[i] = T[index-1];
			}
		}
		
		int index = T[high];
		
		while (index != -1) {
			ans.add(A[index]);
			index = R[index];
		}
		Collections.reverse(ans);
		
		return ans;
	}
	
	private int findCieling(int [] A, int [] T, int k, int low, int high) {
		if (low > high) {
			return -1;
		}
		
		int mid = low + (high - low)/2;
		if (k < A[T[mid]] && k > A[T[mid-1]]) {
			return mid;
		} else if (k < A[T[mid]]) {
			return findCieling(A, T, k, low, mid-1);
		} else {
			return findCieling(A, T, k, mid+1, high);
		}
	}
}
