import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2(K) -3	3
-5	  -10	1
10	   30  -5(P)

*/

public class DungeonSolver {

	public static void main(String[] args) {
		DungeonSolver ds = new DungeonSolver();

		int[][] dungeon = {
				{-2, -3, 3}, 
				{-5, -10, 1}, 
				{10, 30, -30}
				};
		int minHealth = ds.minKnightHealth(dungeon);
		System.out.println("min health: " + minHealth);
		
		int [][] D = {{-200}};
		minHealth = ds.minKnightHealth(D);
		System.out.println("min health: " + minHealth);
		
	}
	
	public int minKnightHealth(int [][] dungeon) {
		
		if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
			return 0;
		}
		int minHealth = 1;
		int row = dungeon.length;
		int col = dungeon[0].length;
		int [][] T = new int[row][col];
		T[0][0] = dungeon[0][0];
		
		//populate first row
		for (int i=1; i<col; i++) {
			T[0][i] = T[0][i-1] + dungeon[0][i];
		}
		
		//populate first col
		for (int i=1; i<row; i++) {
			T[i][0] = T[i-1][0] + dungeon[i][0];
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1; j<col; j++) {
				int down = dungeon[i][j] + T[i-1][j];
				int right = dungeon[i][j] + T[i][j-1];
				if (down >= 0 && right >= 0) {
					T[i][j] = Math.max(down, right);
				} else if (down < 0 && right < 0) {
					T[i][j] = -1 * Math.min(Math.abs(right), Math.abs(down));
				} else {
					T[i][j] = down > right ? down : right;
				}
			}
		}
		
		for (int i=0; i<row; i++) {
			System.out.println(Arrays.toString(T[i]));
		}
		
		
		int i=row-1;
		int j=col-1;
		ArrayList<Cell> path = new ArrayList<Cell>();
		
		
		while (i >=0 && j >= 0) {
			path.add(new Cell(i, j));
			int val = T[i][j];
			if (val < minHealth) {
				minHealth = val;
			}
			if (i-1>=0 && ((val - dungeon[i][j]) == T[i-1][j])) {
				i = i-1;
			} else {
				j = j-1;
			}
		}
		
		System.out.println("path is "); 
		Collections.reverse(path);
		for (Cell c: path) {
			System.out.print(c.toString() + " -> ");
		}
		System.out.println(" finish! ");
		if (minHealth <= 0) {
			minHealth = 1 - minHealth;
		}
		//System.out.println("min health: " + minHealth);
		
		return minHealth;
	}
}

class Cell {
	int row;
	int col;
	
	Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public String toString() {
		return "[" + this.row + "," + this.col + "]";
	}
}
