class Cell {
	int hori = 0;
	int vert = 0;
}

public class MatrixSubsquareX {

	public int findSubSquare(char [][] input) {
		int row = input.length;
		int col = input[0].length;
		Cell[][] T = new Cell[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				T[i][j] = new Cell();
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (input[i][j] == 'X') {
					if (i == 0 && j == 0) {
						T[i][j].hori = 1;
						T[i][j].vert = 1;
					} else if (i == 0) {
						T[i][j].hori = T[i][j - 1].hori + 1;
						T[i][j].vert = 1;
					} else if (j == 0) {
						T[i][j].vert = T[i - 1][j].vert + 1;
						T[i][j].hori = 1;
					} else {
						T[i][j].hori = T[i][j - 1].hori + 1;
						T[i][j].vert = T[i - 1][j].vert + 1;
					}
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(T[i][j].vert + "," + T[i][j].hori + "  ");
			}
			System.out.println();
		}

		// start iterating from bottom right corner and find min of hori or ver
		// at every cell.
		// If this is greater than 1 then see if you can find a number between
		// this min and 1
		// such that on left's ver and top's hori is greater greater than or
		// equal to k.
		int max = 1;
		for (int i = T.length - 1; i >= 0; i--) {
			for (int j = T[0].length - 1; j >= 0; j--) {
				if (T[i][j].vert == 0 || T[i][j].vert == 1 || T[i][j].hori == 1) {
					continue;
				}
				int min = Math.min(T[i][j].vert, T[i][j].hori);
				int k = 0;
				for (k = min; k > 1; k--) {
					if (T[i][j - k + 1].vert >= k && T[i - k + 1][j].hori >= k) {
						break;
					}
				}
				if (max < k) {
					max = k;
				}
			}
		}

		return max;

	}

	public static void main(String[] args) {
		MatrixSubsquareX MS = new MatrixSubsquareX();
		char[][] input = { 
				{ 'X', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'X', 'X', 'X', 'X', 'O', 'O' },
				{ 'X', 'X', 'X', 'X', 'X', 'O' },
				{ 'X', 'O', 'O', 'X', 'X', 'O' },
				{ 'X', 'O', 'X', 'X', 'X', 'O' } };

		char[][] input1 = { 
				{ 'O', 'O', 'O', 'O', 'O', 'X' },
				{ 'O', 'X', 'O', 'X', 'X', 'X' },
				{ 'O', 'X', 'O', 'X', 'O', 'X' },
				{ 'O', 'X', 'X', 'X', 'X', 'X' },
				{ 'O', 'O', 'O', 'O', 'O', 'O' }, };

		char[][] input2 = { 
				{ 'O', 'O', 'X', 'O', 'X' },
				{ 'O', 'X', 'X', 'O', 'X' },
				{ 'O', 'X', 'O', 'X', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' }, 
				{ 'O', 'X', 'X', 'X', 'O' }, };
		
		System.out.println("max subsquare with X border: " + MS.findSubSquare(input));
		System.out.println("max subsquare with X border: " + MS.findSubSquare(input1));
		System.out.println("max subsquare with X border: " + MS.findSubSquare(input2));
	}
	
}
