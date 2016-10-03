
public class MatrixMultiplicationChain {
	
	public static void main(String [] args) {
		int [] M = {2, 3, 6, 4, 5};
		MatrixMultiplicationChain MMC = new MatrixMultiplicationChain();
		System.out.println("optimal cost to multiply matrices: " + MMC.matrixMulChainCost(M));
	}

	//Time Complexity: O(n^3)
	//Auxiliary Space: O(n^2)
	public int matrixMulChainCost(int [] M) {
		int len = M.length;
		
		/* m[i,j] = Minimum number of scalar multiplications needed
	       to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where 
	       dimension of A[i] is p[i-1] x p[i] */
	 
		int [][] T = new int[len][len];
		int value = 0;
		for (int n=2; n<len; n++) {
			for (int i=0; i<len-n; i++) {
				int j = i + n;
				T[i][j] = Integer.MAX_VALUE;
				for (int k=i+1; k<j; k++) {
					value = T[i][k] + T[k][j] + (M[i] * M[k] * M[j]);
					if (value < T[i][j]) {
						T[i][j] = value;
					}
				}
			}
		}
		return T[0][len-1];
	}
}
