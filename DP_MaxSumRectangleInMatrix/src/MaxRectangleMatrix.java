
public class MaxRectangleMatrix {

	public static void main(String[] args) {
		MaxRectangleMatrix mrm = new MaxRectangleMatrix();
		
		//int [] A = {-2, -3, 4, -1, -2, 1, 5, -3};
		//int [] A = {2, 0, 2, -3};
		//KadaneResult kr = mrm.kadane(A);
		//System.out.println(kr.toString());
		
        int [][] M = {
        		{ 2,  1, -3, -4,  5},
                { 0,  6,  3,  4,  1},
                { 2, -2, -1,  4, -5},
                {-3,  3,  1,  0,  3}
                };
        Result result = mrm.maxRectangleInMatrix(M);
        System.out.println(result.toString());
		
	}
	
	public Result maxRectangleInMatrix(int [][] M) {
		
		int maxSum = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		int up = 0;
		int down = 0;
		
		int row = M.length;
		int col = M[0].length;
		
		for (int left = 0; left<col; left++) {
			
			int [] aux = new int[row];
			
			for (int right = left; right<col; right++) {
			
				for (int i=0; i<row; i++) {
					aux[i] += M[i][right]; 
				}
				
				KadaneResult kr = kadane(aux);
				if (kr.maxSum > maxSum) {
					maxSum = kr.maxSum;
					up = kr.start;
					down = kr.end;
					leftIndex = left;
					rightIndex = right;
				}
			}
		}
		
		Result result = new Result(maxSum, leftIndex, rightIndex, up, down);
		return result;
	}
	
	public KadaneResult kadane(int [] A) {
		int maxSum = 0;
		int start = 0;
		int end = 0;
		int runningSum = 0;
		
		for (int i=0; i<A.length; i++) {
			runningSum += A[i];
			if (runningSum > maxSum) {
				maxSum = runningSum;
				end = i;
			}
			if (runningSum < 0) {
				runningSum = 0;
				start = i+1;
			}
		}
		
		KadaneResult result = new KadaneResult(maxSum, start, end);
		return result;
	}
}

class Result {
	int maxSum;
	int left;
	int right;
	int up;
	int down;
	
	public Result(int maxSum, int left, int right, int up, int down) {
		this.maxSum = maxSum;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("[max sum: " + maxSum + ", ");
		sb.append("left index: " + left + ", ");
		sb.append("right index: " + right + ", ");
		sb.append("up index: " + up + ", ");
		sb.append("down index: " + down + "]");
		
		return sb.toString();
	}
}

class KadaneResult {
	int maxSum;
	int start;
	int end;
	
	public KadaneResult(int maxSum, int start, int end) {
		this.maxSum = maxSum;
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		String result = "[maxSum: " + maxSum +", start Index: " + start + ", end index: " + end +"]";
		return result;
	}
}
