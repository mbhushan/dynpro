
public class SubsetSum {
	
	public boolean equalPartition(int [] A) {
		int len = A.length;
		int sum = 0;
		for (int i=0; i<len; i++) {
			sum += A[i];
		}
		if (sum%2 != 0) {
			return false;
		}
		int total = sum/2;
		boolean [][] T = new boolean[len+1][total+1];
		for (int i=0; i<=len; i++) {
			T[i][0] = true;
		}
		
		for (int i=1; i<=len; i++) {
			for (int j=1; j<=total; j++) {
				if (j-A[i-1] >= 0) {
					T[i][j] = T[i-1][j] || T[i-1][j - A[i-1]];
				} else {
					T[i][j] = T[i-1][j];
				}
			}
		}
		return T[len][total];
		
	}

	public boolean subsetSum(int [] A, int total) {
		int len = A.length;
		boolean [][] T = new boolean[len+1][total+1];
		for (int i=0; i<=len; i++) {
			T[i][0] = true;
		}
		
		for (int i=1; i<=len; i++) {
			for (int j=1; j<=total; j++) {
				if (j-A[i-1] >= 0) {
					T[i][j] = T[i-1][j] || T[i-1][j - A[i-1]];
				} else {
					T[i][j] = T[i-1][j];
				}
			}
		}
		return T[len][total];
	}
	
	public static void main(String [] args) {
		
			SubsetSum SS = new SubsetSum();
			int [] A = {2, 3, 7, 8};
			int total = 11;
			System.out.println("has subset sum: " + SS.subsetSum(A, total));
			System.out.println("has subset sum: " + SS.subsetSum(A, 22));
			
			int [] B = {1, 3, 5, 5, 2, 1, 1, 6};
			System.out.println("equal partition: " + SS.equalPartition(B));
	}
}
