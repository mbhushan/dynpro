
/**
 * http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 */

public class PathsInMatrix {
	
	public int countMatrixPathsDP(int row, int col) {
		int [][] T = new int[row][col];
		
		for (int i=0; i<row; i++) {
			T[i][0] = 1;
		}
		
		for (int i=0; i<col; i++) {
			T[0][i] = 1;
		}
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				T[i][j] = T[i-1][j] + T[i][j-1];
			}
		}
		return T[row-1][col-1];
	}
	
	public int countMatrixPaths(int row, int col) {
		if (row == 1 || col == 1) {
			return 1;
		}
		
		return countMatrixPaths(row-1, col) + countMatrixPaths(row, col-1);
	}

	public static void main(String[] args) {
		PathsInMatrix PM = new PathsInMatrix();
		int row = 3;
		int col = 3;
		System.out.println("count matrix paths: " + PM.countMatrixPaths(row, col));
		System.out.println("count matrix paths DP: " + PM.countMatrixPathsDP(row, col));
	}
}
