import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/*
 * Given an array of non negative integers where each element represents the max 
 * number of steps that can be made forward from that element. Write a function to 
 * return the minimum number of jumps to reach the end of the array from first element
 */

public class MinimumJumps {

	public static void main(String[] args) {
		MinimumJumps mj = new MinimumJumps();
		
		int [][] input = {
				{1,3,5,3,2,2,6,1,6,8,9},
				{2,3,1,1,2,4,2,0,1,1},
				{2,3,1,1,4}
		};
		
		for (int i=0; i<input.length; i++) {
			System.out.println("input array: ");
			System.out.println(Arrays.toString(input[i]));
			System.out.println("min jumps: " + mj.minJumps(input[i]));
			System.out.println();
		}
		
		
	}
	
	public int minJumps(int [] A) {
		
		int [] jumpNums = new int[A.length];
		int [] jumpIndex = new int[A.length];
		
		for (int i=0; i<A.length; i++) {
			jumpIndex[i] = i;
		}
		Arrays.fill(jumpNums, Integer.MAX_VALUE);

		jumpNums[0] = 0;
		
		for (int i=1; i<A.length; i++) {
			for (int j=0; j<i; j++) {
				if (A[j] + j >= i) {
					if (jumpNums[i] > 1 + jumpNums[j]) {
						jumpNums[i] =  1 + jumpNums[j];
						jumpIndex[i] = j;
					}
				}
			}
		}
		System.out.println("jump numbers: ");
		System.out.println(Arrays.toString(jumpNums));
		System.out.println("jump indexes: ");
		System.out.println(Arrays.toString(jumpIndex));
		
		System.out.println("actual jumps: ");
		ArrayList<Integer> jumps = new ArrayList<Integer>();
		int index = A.length-1;

		while (index != jumpIndex[index]) {
			jumps.add(index);
			index = jumpIndex[index];
		}
		jumps.add(index);
		Collections.reverse(jumps);
		System.out.println(jumps.toString());
		
		
		return jumpNums[A.length-1];
	}
}
