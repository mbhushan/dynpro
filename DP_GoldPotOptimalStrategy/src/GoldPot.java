import java.util.Arrays;


/*
N pots, each with some number of gold coins, are arranged in a line. You are playing a game against another player. 
You take turns picking a pot of gold. You may pick a pot from either end of the line, remove the pot, and keep the gold pieces. 
The player with the most gold at the end wins. Develop a strategy for playing this game.
*/

public class GoldPot {

	public static void main(String[] args) {
		GoldPot gp = new GoldPot();
		
		int pots[] = {3,1,5,6,2,9,3};
		//int [] pots = {3, 9, 1, 2};
		
		gp.optimalStrategy(pots);
	}
	
	public void optimalStrategy(int [] pots) {
		int size = pots.length;
		Move [][] T = new Move[size][size];
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				T[i][j] = new Move(0,0,0);
			}
		}
		
		for (int i=0; i<size; i++) {
			T[i][i].first = pots[i]; //= new Move(pots[i], 0, i);
			T[i][i].pick = i;
		}
		
		for (int len=2; len<=size; len++) {
			for (int i=0; i<=size-len; i++) {
				int j = i + len - 1;
				
				if (T[i+1][j].second + pots[i] > T[i][j-1].second + pots[j]) {
					T[i][j].first = T[i+1][j].second + pots[i] ;
					T[i][j].second = T[i+1][j].first;
					T[i][j].pick = i;
				} else {
					T[i][j].first = T[i][j-1].second + pots[j];
					T[i][j].second = T[i][j-1].first;
					T[i][j].pick = j;
				}
			}
		}
		
		System.out.println("final move values: " + T[0][size-1].toString());
		
		System.out.println("moves are: ");
		int i = 0;
		int j = size - 1;
		String player = "P1";
		for (int k=0; k<size; k++) {
			int index = T[i][j].pick;
			player = k%2 == 0 ? "P1" : "p2";
			System.out.println( player + "=> " + pots[index] + "; index: " + index);
			if (index == j) {
				--j;
			} else {
				++i;
			}
		}
	}
}

class Move {
	int first;
	int second;
	int pick;
	
	public Move(int first, int second, int pick) {
		this.first = first;
		this.second = second;
		this.pick = pick;
		
	}
	
	public String toString() {
		String buff = "[" + this.first + ", " + this.second + "]";
		return buff;
	}
}
