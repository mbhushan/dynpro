
public class StaircaseProblem {

	public static void main(String[] args) {
		StaircaseProblem sp = new StaircaseProblem();
		
		for (int i=1; i<=10; i++) {
			System.out.println("total ways for " + i + ": " + sp.staircase(i));
		}
		
	}
	
	public int staircase(int n) {
		if (n == 0 || n == 1) {
			return n;
		}

		int count = 0;
		count += staircase(n-1) + staircase(n-2);
		return count;
		
	}
}
