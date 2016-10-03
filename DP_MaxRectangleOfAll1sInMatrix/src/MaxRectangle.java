import java.util.Stack;


/*
 * Given a 2D matrix of 0s and 1s. Find largest rectangle of all 1s
 * in this matrix.
 */
public class MaxRectangle {

	public static void main(String[] args) {
		MaxRectangle mr = new MaxRectangle();
		 int M [][] = {
				 {1,1,1,0},
                 {1,1,1,1},
                 {0,1,1,0},
                 {0,1,1,1},
                 {1,0,0,1},
                 {1,1,1,1}
                 };
		 
		 System.out.println("max area: " + mr.maxRectangle(M));
	}
	
	public int maxRectangle(int [][] M) {
		int col = M[0].length;
		int row = M.length;
		
		int [] T = new int[col];
		
		int maxArea = 0;
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (M[i][j] == 0) {
					T[j] = 0;
				} else {
					T[j] += 1;
				}
			}
			int area = maxRectangleHistogram(T);
			maxArea = Math.max(maxArea, area);
		}
		
		return maxArea;
		
	}
	
private int maxRectangleHistogram(int [] A) {
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int maxArea = 0;
		//stack.push(0);
		int i = 0;
		for (i=0; i < A.length; i++ ) {
			if (stack.isEmpty() || A[i] >= A[stack.peek()]) {
				stack.push(i);
			} else {
				while (!stack.isEmpty() && A[i] < A[stack.peek()]) {
					int top = stack.pop();
					int len = A[top];
					int area = 0;
					if (stack.isEmpty()) {
						area = len * i;
					} else {
						area = len * (i - stack.peek()-1);
					}
					maxArea = Math.max(maxArea, area);
				}
				stack.push(i);
			}
		}
		
		while (!stack.isEmpty()) {
			int top = stack.pop();
			int len = A[top];
			int area = 0;
			if (stack.isEmpty()) {
				area = len * i;
			} else {
				area = len * (i - stack.peek()-1);
			}
			maxArea = Math.max(maxArea, area);
		}
		
		return maxArea;
	}
}
