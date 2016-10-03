import java.util.Arrays;


public class SumQuery {

	public static void main(String[] args) {
		SumQuery sq = new SumQuery();
		
		int [][] M =  {
				{2,0,-3,4}, 
				{6, 3, 2, -1}, 
				{5, 4, 7, 3}, 
				{2, -6, 8, 1}
				};
		sq.sumQuery(M);
	}
	
	public void sumQuery(int [][] M) {
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
				T[i][j] = T[i-1][j] + T[i][j-1] + M[i][j] - T[i-1][j-1];
			}
		}
		
		System.out.println("printing the query matrix: ");
		for (int i=0; i<row; i++) {
			System.out.println(Arrays.toString(T[i]));
			//System.out.println();
		}
	}
}
