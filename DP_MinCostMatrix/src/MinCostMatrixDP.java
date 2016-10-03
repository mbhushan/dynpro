

/*
Given a 2 dimensional matrix, find minimum cost path to reach bottom 
right from top left provided you can only from down and right.
*/

public class MinCostMatrixDP {

	public static void main(String[] args) {
		MinCostMatrixDP mcm = new MinCostMatrixDP();
		
		int M[][] = {
				{1,2,3},
				{4,8,2},
				{1,5,3},
				{6,2,9}
			};
		System.out.println("min cost: " + mcm.minCost(M));
	}
	
	public int minCost(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		int [][] T = new int[row][col];
		
		T[0][0] = M[0][0];
		for (int i=1; i<col; i++) {
			T[0][i] = T[0][i-1] + M[0][i];
		}
		
		for (int i=1; i<row; i++) {
			T[i][0] = T[i-1][0] + M[i][0];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				int val = Math.min(T[i-1][j], T[i][j-1]);
				T[i][j] = val + M[i][j];
			}
		}

		return T[row-1][col-1];
	}
}
