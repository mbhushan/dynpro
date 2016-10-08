/*
Given a 2D matrix matrix, find the sum of the elements inside the 
rectangle defined by its upper left corner (row1, col1) and lower 
right corner (row2, col2).
Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

public class RangeQuery {
	
	private int [][] T;

	public static void main(String[] args) {
		RangeQuery rq = new RangeQuery();
		
		int [][] matrix = {
		          {3, 0, 1, 4, 2},
		          {5, 6, 3, 2, 1},
		          {1, 2, 0, 1, 5},
		          {4, 1, 0, 1, 7},
		          {1, 0, 3, 0, 5}
		};
		
		rq.init(matrix);
		
		System.out.println("range query sum: " + rq.sumRegion(2, 1, 4, 3));
		System.out.println("range query sum: " + rq.sumRegion(1, 1, 2, 2));
		System.out.println("range query sum: " + rq.sumRegion(1, 2, 2, 4));
	}
	
	public int sumRegion(int row1, int col1, int row2, int col2) {
	
		int sum = T[row2][col2];
		if (row1 > 0 && col1 > 0) {
			sum  = sum - T[row2][col1-1] - T[row1-1][col2] + T[row1-1][col1-1];
		} else if (row1 == 0 && col1 > 0) {
			sum = sum - T[row2][col1-1];
		} else if (col1 == 0 && row1 > 0) {
			sum = sum - T[row1-1][col2];
		}
		
		return sum;
			
	}
	
	public void init(int [][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		T = new int[row][col];
		T[0][0] = matrix[0][0];
		
		//populate first row
		for (int i=1; i<col; i++) {
			T[0][i] = T[0][i-1] + matrix[0][i];
		}
		
		//populate first col.
		for (int j=1; j<row; j++) {
			T[j][0] = T[j-1][0] + matrix[j][0];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				T[i][j] = T[i-1][j] + T[i][j-1] + matrix[i][j] - T[i-1][j-1];
			}
		}
		
		printMatrix(T);
	}
	
	private void printMatrix(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
	}
}
