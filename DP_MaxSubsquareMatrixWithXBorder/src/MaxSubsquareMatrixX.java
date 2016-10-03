
public class MaxSubsquareMatrixX {

	public static void main(String[] args) {
		MaxSubsquareMatrixX ms = new MaxSubsquareMatrixX();
		char[][] M = { 
				{ 'O', 'O', '0', 'O', 'X' },
				{ 'X', '0', 'X', 'X', 'X' },
				{ 'X', '0', 'X', '0', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' }, 
				{ 'O', '0', 'X', 'X', 'X' }, 
			};
		System.out.println("max subsquare: " + ms.maxSubsquareMatrix(M));
		
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
		
		System.out.println("max subsquare: " + ms.maxSubsquareMatrix(input));
		System.out.println("max subsquare: " + ms.maxSubsquareMatrix(input1));
		System.out.println("max subsquare: " + ms.maxSubsquareMatrix(input2));
		
	}
	
	public int maxSubsquareMatrix(char [][] M) {
		int row = M.length;
		int col = M[0].length;
		int maxSquare = 1;
		
		Node [][] T = new Node[row][col];
		
		if (M[0][0] == 'X') {
			T[0][0] = new Node(1, 1);
		} else {
			T[0][0] = new Node(0, 0);
		}
		
		for (int i=1; i<col; i++) {
			Node node = new Node(0,0);
			if (M[0][i] == 'X') {
				node.horizontal = 1 + T[0][i-1].horizontal;
				node.vertical = 1;
			}
			T[0][i] = node;
		}
		
		for (int i=1; i<row; i++) {
			Node node = new Node(0,0);
			if (M[i][0] == 'X') {
				node.vertical = 1 + T[i-1][0].vertical;
				node.horizontal = 1;
			}
			T[i][0] = node;
		}
		
		for (int i=1; i<row; i++) {
			for (int j=1 ; j<col; j++) {
				Node node = new Node(0, 0);
				if (M[i][j] == 'X') {
					node.horizontal = T[i][j-1].horizontal + 1;
					node.vertical = T[i-1][j].vertical + 1;
				}
				T[i][j] = node;
			}
		}

		
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				System.out.print(T[i][j].toString() + "\t");
			}
			System.out.println();
		}
		

		for (int i=row-1; i>0; i--) {
			for (int j=col-1; j>0; j--) {
				Node node = T[i][j];
				int range = Math.min(node.horizontal, node.vertical);
				for (int k=range; k>1; k--) {
					if (T[i][j-k+1].vertical >= k && T[i-k+1][j].horizontal >= k) {
						maxSquare = Math.max(maxSquare, k);
						break;
					}
				}
			}
		}
		
		return maxSquare;
	}
}

class Node {
	int horizontal;
	int vertical;
	
	public Node (int h, int v) {
		this.horizontal = h;
		this.vertical = v;
	}
	
	public String toString() {
		return "[H,V: " + this.horizontal + "," + this.vertical + "]";
	}
}