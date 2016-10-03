
public class RodCutting {

	public int rodCutRec(int [] prices, int len) {
		if (len <= 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for (int i=0; i<len; i++) {
			int val = prices[i] + rodCutRec(prices, len - (i+1));
			if (val > max) {
				max = val;
			}
		}
		return max;
	}
	
	public int rodCuttingDP(int [] prices, int len) {
		int [][] T = new int[prices.length+1][len+1];
		
		for (int i=0; i<prices.length; i++) {
			T[i][0] = 0;
		}
		for (int i=0; i<=len; i++) {
			T[0][i] = 0;
		}
		
		for (int i=1; i<=prices.length; i++) {
			for (int j=1; j<=len; j++) {
				if (i <= j) {
					T[i][j] = Math.max(prices[i-1] + T[i][j - i], T[i-1][j]);
				} else if (i > j) {
					T[i][j] = T[i-1][j];
				}
			}
		}
		return T[prices.length][len];
	}
	public static void main(String [] args) {
		RodCutting RC = new RodCutting();
		int [] prices = {2,5,7,8};
		int len = 4;
		
		System.out.println("max profit: " + RC.rodCutRec(prices, len));
		System.out.println("max profit DP: " + RC.rodCuttingDP(prices, 5));
	}
}
