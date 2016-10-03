/*
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented
 * by array nums. You are asked to burst all the balloons. If the you burst balloon i you will
 * get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst,
 * the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Time complexity O(n^3)
 * Space complexity O(n^2)
 */

public class BalloonBursting {

	public static void main(String[] args) {
		BalloonBursting bb = new BalloonBursting();
		
		int [][] A = {
				{3, 1, 5, 8},
				{2, 4, 3, 5}
		};
		
		for (int i=0; i<A.length; i++) {
			int result = bb.maxCoinsFromBalloon(A[i]);
			System.out.println("max coins: " + result);
		}
	}
	
	public int maxCoinsFromBalloon(int [] A) {
		int size = A.length;
		Cell [][] T = new Cell[size][size];
		
		for (int i=0; i<size; i++) {
			if (i == 0) {
				T[i][i] = new Cell(A[i] * A[i+1], i);
			} else if (i == size-1) {
				T[i][i] = new Cell(A[i] * A[i-1], i);
			} else {
				T[i][i] = new Cell(A[i-1] * A[i] * A[i+1], i);
			}
		}
		
		for (int len=2; len<=size; len++) {
			for (int i=0; i<=size-len; i++) {
				int j = i + len -1;
				
				int max = 0;
				for (int k=i; k<=j; k++) {
					int val = 0;
					if (k == 0) {
						if (j+1 < size) {
							val = 1 * A[k] * A[j+1];
						} else {
							val = 1 * A[k] * 1;
						}
						val += T[k+1][j].value;
					} else if (k == size-1) {
						if (i-1 >= 0) {
							val = A[i-1] * A[k] * 1;
						} else {
							val = 1 * A[k] * 1;
						}
						val += T[i][k-1].value;
					} else {
						int left = i-1 >= 0 ? A[i-1] : 1;
						int right = j+1 <= size-1? A[j+1]: 1;
						int x = k-1>=i ? T[i][k-1].value : 0;
						int y = k+1 <= j ? T[k+1][j].value : 0;
								
						val = x +  left * A[k] * right + y;
					}
					if (val > max) {
						max = val;
						T[i][j] = new Cell(max, k);
					}
				}
			}
		}
		System.out.println("DP matrix: ");
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (T[i][j] == null) {
					System.out.print("null" + " ");
					continue;
				}
				System.out.print(T[i][j].toString() + " ");
			}
			System.out.println();
		}
		return T[0][size-1].value;
	}
	
}

class Cell {
	int value;
	int index;
	
	public Cell(int value, int index) {
		this.value = value;
		this.index = index;
	}
	
	public String toString() {
		return "[" + this.value + ", " + this.index + "]";
	}
}
