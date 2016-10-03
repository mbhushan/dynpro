import java.util.ArrayList;


public class LongestIncreasingPathMatrix {

	public static void main(String[] args) {
		LongestIncreasingPathMatrix lim = new LongestIncreasingPathMatrix();
		
		int [][] M = {
		                 {9,9,4},
		                 {6,6,8},
		                 {2,1,1}
				};
		int [][] N  = {
		        {3,4,5},
		        {3,2,6},
		        {2,2,1}
		        };
		lim.longestIncreasingPath(M);
		lim.longestIncreasingPath(N);
	}
	
	public void longestIncreasingPath(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		
		int [] max = new int[1];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				ArrayList<Integer> buff = new ArrayList<Integer>();
				longestIncreasingPath(M, i, j, buff, max); 
			}
		}
		System.out.println("max longest increasing subsequence: " + max[0]);

	}
	
	private void longestIncreasingPath(int [][] M, int r, int c, ArrayList<Integer> buff, int [] max) {
		buff.add(M[r][c]);	
		
		if (buff.size() > max[0]){
			System.out.println(buff.toString());
			max[0] = buff.size();
		}
		
		if (r-1 >= 0 && M[r-1][c] > buff.get(buff.size()-1)) {
			longestIncreasingPath(M, r-1, c, buff, max);
			buff.remove(buff.size()-1);
		}
		if (r+1 < M.length && M[r+1][c] > buff.get(buff.size()-1)) {
			longestIncreasingPath(M, r+1, c, buff, max);
			buff.remove(buff.size()-1);
		}
		if (c-1 >= 0 && M[r][c-1] > buff.get(buff.size()-1)){
			longestIncreasingPath(M, r, c-1, buff, max);
			buff.remove(buff.size()-1);
		}
		if (c+1 < M[0].length && M[r][c+1] > buff.get(buff.size()-1)) {
			longestIncreasingPath(M, r, c+1, buff, max);
			buff.remove(buff.size()-1);
		}
	}
}
