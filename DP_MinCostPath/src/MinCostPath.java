
public class MinCostPath {
	
	public static void main(String [] args) {
		
		MinCostPath MC = new MinCostPath();
		
		int [][] matrix = {{1,3,5,8},
				{4,2,1,7},
				{4,3,2,3}};
		System.out.println("min cost path: " + MC.minCost(matrix));
	}

	public int minCost(int [][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		int [][] T = new int[row][col];
		
		int sum = 0;
		for (int i=0; i<col; i++) {
			T[0][i] = sum + matrix[0][i];
			sum = T[0][i];
		}
		sum = 0;
		for (int i=0; i<row; i++) {
			T[i][0] = sum + matrix[i][0];
			sum = T[i][0];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
//				T[i][j] = Math.min(T[i-1][j-1], Math.min(T[i][j-1], T[i][j-1]));
				//considering only right or down direction, not diagonally.
				T[i][j] =  Math.min(T[i][j-1], T[i-1][j]) + matrix[i][j];
			}
		}
		return T[row-1][col-1];
	}
}
