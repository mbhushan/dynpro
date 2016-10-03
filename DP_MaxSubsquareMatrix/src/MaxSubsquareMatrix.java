
public class MaxSubsquareMatrix {

	public static void main(String[] args) {
		MaxSubsquareMatrix msm = new MaxSubsquareMatrix();
		int M[][] = {
				{0,1,1,0,1},
				{1,1,1,0,0},
				{1,1,1,1,0},
				{1,1,1,0,1}
			};
		msm.maxSubsquareMatrix(M);
	}
	
	public void maxSubsquareMatrix(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		
		int [][] T = new int[row][col];
		
		System.arraycopy(M[0], 0, T[0], 0, M[0].length);
		for (int i=1; i<row; i++) {
			T[i][0] = M[i][0];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				int minValue = min(T[i-1][j], T[i-1][j-1], T[i][j-1]);
				T[i][j] = minValue + 1;
			}
		}
		
		System.out.println("max subsquare matrix of all 1s: " + T[row-1][col-1]);
	}
	
	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
}
