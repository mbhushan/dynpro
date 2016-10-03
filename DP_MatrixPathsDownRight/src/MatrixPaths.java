
/*
Given a 2 dimensional matrix, how many ways you can reach bottom right from top left provided you can only move down and right.
*/

public class MatrixPaths {

	public static void main(String[] args) {
		MatrixPaths mp = new MatrixPaths();
		
		int row = 3;
		int col = 3;
		System.out.println("total ways: " + mp.numPaths(row, col));
	}
	
	public int numPaths(int row, int col) {
		int [][] T = new int[row][col];
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (i == 0 || j == 0) {
					T[i][j] = 1;
				} else {
					T[i][j] = T[i][j-1] + T[i-1][j];
				}
			}
		}
		
		return T[row-1][col-1];
	}
}
