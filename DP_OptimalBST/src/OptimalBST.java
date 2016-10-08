
public class OptimalBST {
	
	
	public int optimalBSTDP(int [] A, int [] F) {
		int n = A.length;
		int [][] T = new int[n][n];
		
		for (int i=0; i<n; i++) {
			T[i][i] = F[i];
		}
		
		for (int w=2; w <= n; w++) {
			for (int i=0; i<=n-w; i++) {
				int j = i + w - 1;
				T[i][j] = Integer.MAX_VALUE;
				int sum = getSum(F, i, j);
				
				for(int k=i; k <= j; k++){
                    int val = sum + (k-1 < i ? 0 : T[i][k-1]) +
                           (k+1 > j ? 0 : T[k+1][j]) ;
                    if(val < T[i][j]){
                        T[i][j] = val;
                    }
               }
			}
		}
		return T[0][n-1];
	}
	
	 private int getSum(int freq[], int i, int j){
	        int sum = 0;
	        for(int x = i; x <= j; x++){
	            sum += freq[x];
	        }
	        return sum;
	    }
	 

	
	public int optimalBST(int [] A, int [] F) {
		int low = 0;
		int high = A.length - 1;
		int level = 1;
		return optimalBSTUtil(A, F, low, high, level);
	}
	
	private int optimalBSTUtil(int [] A, int [] F, int low, int high, int level) {
		if (low > high) {
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		for (int i=low; i<=high; i++) {
			int val = optimalBSTUtil(A, F, low, i-1, level+1) + 
					optimalBSTUtil(A, F, i+1, high, level+1) + level*F[i];
			if (val < min) {
				min = val;
			}
		}
		return min;
	}
	
	public static void main(String [] args) {
		int [] A = {10, 12, 20, 35, 46};
        int [] F = {34, 8, 50, 21, 16};
        OptimalBST OBT = new OptimalBST();
        System.out.println("optimal BST value: " + OBT.optimalBST(A, F));
        System.out.println("optimal BST value - DP Solution: " + OBT.optimalBSTDP(A, F));
	}
}
