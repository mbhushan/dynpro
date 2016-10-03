
public class MaxAdjacentSum {

	public int maxSumRec(int [] A, int index) {
		if (index == 0) {
			return A[0];
		}
		if (index == 1) {
			return Math.max(A[0], A[1]);
		}
		return Math.max(maxSumRec(A, index-2) + A[index], maxSumRec(A, index-1)); 
	}
	
	public int maxSumDP(int [] A) {
		int excl = 0;
		int incl = A[0];
		
		for (int i=1; i<A.length; i++) {
			int temp = incl;
			incl = Math.max(excl+A[i], incl);
			excl = temp;
		}
		return incl;
	}
	
	public static void main(String[] args) {
		MaxAdjacentSum MAS = new MaxAdjacentSum();
		
		int [] A = {4, 1, 1, 4, 2, 1};
		int index = A.length -1;
		System.out.println("max non adjacent sum - recursive: " + MAS.maxSumRec(A, index));
		System.out.println("max non adjacent sum - DP: " + MAS.maxSumDP(A));
	}
	
}
