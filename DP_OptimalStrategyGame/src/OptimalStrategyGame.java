
class Pair {
	int first, second;
	//int first=0;
	//int second=0;
	int pick=0;
	
	@Override
	public String toString() {
		return "[ " + first + ", " + second + ": " + pick + " ]";
	}
}

public class OptimalStrategyGame {
	
	public Pair [][] findMoves(int [] pots) {
		Pair [][] moves = new Pair[pots.length][pots.length];
		
		for (int i=0; i<pots.length; i++) {
			for (int j=0; j<pots.length; j++) {
				moves[i][j] = new Pair();
			}
		}
		
		for (int i=0; i<pots.length; i++) {
			moves[i][i].first = pots[i];
//			moves[i][i].second = 0;
			moves[i][i].pick = 	i;
		}
		
		for (int l=2; l<=pots.length; l++) {
			for (int i=0; i<=pots.length-l; i++) {
				int j = i + l-1;
				if ((pots[i] + moves[i+1][j].second) > (pots[j] + moves[i][j-1].second)) {
					moves[i][j].first = pots[i] + moves[i+1][j].second;
					moves[i][j].second = moves[i+1][j].first;
					moves[i][j].pick = i;
				} else {
					moves[i][j].first = pots[j] + moves[i][j-1].second;
					moves[i][j].second = moves[i][j-1].first;
					moves[i][j].pick = j;
					
				}
			}
		}
		return moves;
	}
	
	public void printMoves(int [] pots, Pair [][] moves) {
		int i = 0;
		int j = pots.length - 1;
		int step = 0;
		
		for (int k=0; k<pots.length; k++) {
			step = moves[i][j].pick;
			System.out.println("value: " + pots[step] + " " + "index: " + step + " ");
			if (step <= i) {
				++i;
			} else {
				--j;
			}
			
		}
	}
	
	public static void main(String[] args) {
		OptimalStrategyGame OS = new OptimalStrategyGame();
		 int pots[] = {3,1,5,6,2,9,3};
		 Pair [][] moves = OS.findMoves(pots);
		 System.out.println("moves from players first and second:");
		 OS.printMoves(pots, moves);
	}

}
