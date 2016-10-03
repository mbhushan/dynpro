
public class MaxProductSubarray {
	
	public int maxProductDP(int [] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		
		int maxLocal = A[0];
		int minLocal = A[0];
		int global = A[0];
		
		for (int i=1; i<A.length; i++) {
			int temp = maxLocal;
			maxLocal = max(A[i], A[i]*maxLocal, A[i]*minLocal);
			minLocal = min(A[i], A[i]*minLocal, A[i]*temp);
			global = Math.max(global, maxLocal);
		}
		return global;
	}
	
	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	private int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
	
	public int maxProductSlow(int [] A) {
		if (A.length <= 1) {
			return A[0];
		}
		int max = Integer.MIN_VALUE;
		for (int i=0; i<A.length; i++) {
			for (int j=0; j<A.length; j++) {
				if (i+j < A.length) {
					int product = calcProduct(A, i, j);
					max = Math.max(max, product);
				}
			}
		}
		return max;
	}
	
	private int calcProduct(int [] A, int i, int j) {
		int product = 1;
		for (int k=i; k<=j; k++) {
			product *= A[k];
		}
		return product;
	}

	public static void main(String[] args) {
		MaxProductSubarray MP = new MaxProductSubarray();
		
		int [] A = {2,3,-2,4};
		System.out.println("max product - slow version: " + MP.maxProductSlow(A));
		System.out.println("max product - DP version: " + MP.maxProductDP(A));
	}
}
