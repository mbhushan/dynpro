import java.util.Arrays;


public class MinJumpToReachEnd {
	
	public static void main(String [] args) {
		int [] A = {1,3,5,3,2,2,6,1,6,8,9};
		int [] result = new int[A.length];
		
		MinJumpToReachEnd MJ = new MinJumpToReachEnd();
		int minJumps = MJ.minJump(A, result);
		System.out.println("min jumps: " + minJumps);
		System.out.println("result: ");
		System.out.println(Arrays.toString(result));
	}

	public int minJump(int [] A, int [] result) {
		int len = A.length;
		int [] jump = new int[len];
		
		jump[0] = 0;
		for (int i=1; i<len; i++) {
			jump[i] = Integer.MAX_VALUE;
		}
		
		for (int i=1; i<len; i++) {
			for (int j=0; j<i; j++) {
				if (A[j] + j >= i) {
					if (jump[i] > jump[j]+1) {
						jump[i] = jump[j]+1;
						result[i] = j;
					}
				}
			}
		}
		return jump[len-1];
	}
}
