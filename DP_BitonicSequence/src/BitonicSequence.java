
public class BitonicSequence {

	public int longestBitonicSequence(int [] A) {
		int max = 0;
		int len = A.length;
		int [] left = new int[len];
		int [] right = new int[len];
		
		for (int i=0; i<len; i++) {
			left[i] = 1;
			right[i] = 1;
		}
		
		for (int i=1; i<len; i++) {
			for (int j=0; j<i; j++) {
				if (A[i] > A[j]) {
					left[i] = Math.max(left[i], left[j] + 1);
				}
			}
		}
		
		for (int i=len-2; i>=0; i--) {
			for (int j=len-1; j>i; j--) {
				if (A[i] > A[j]) {
					right[i] = Math.max(right[i], right[j] + 1);
				}
			}
		}
		
		for (int i=0; i<len; i++) {
			if (left[i]+right[i]-1 > max) {
				max = left[i] + right[i] -1;
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		BitonicSequence BS = new BitonicSequence();
		int [] A = {2, -1, 4, 3, 5, -1, 3, 2};
		System.out.println("max bitonic sequence: " + BS.longestBitonicSequence(A));
	}
}
