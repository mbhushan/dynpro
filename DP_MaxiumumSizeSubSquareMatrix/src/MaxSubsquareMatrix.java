
public class MaxSubsquareMatrix {

	public int maxSubsquareMatrix(int [][] M) {
		int max = 1;
		int row = M.length;
		int col = M[0].length;
		int [][] T = new int[row][col];
		
		for (int i=0; i<row; i++) {
			T[i][0] = M[i][0];
		}
		
		for (int i=0; i<col; i++) {
			T[0][i] = M[0][i];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				if (M[i][j] == 0) {
					continue;
				}
				T[i][j] = 1 + Math.min(T[i-1][j-1], Math.min(T[i-1][j], T[i][j-1]));
				if (T[i][j] > max) {
					max = T[i][j];
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		MaxSubsquareMatrix MSM = new MaxSubsquareMatrix();
		
		int [][] M =  {{0,1,1,0,1},{1,1,1,0,0},{1,1,1,1,0},{1,1,1,0,1}};
		System.out.println("max subsquare matrix size: " + MSM.maxSubsquareMatrix(M));
	}
}
