
public class BuySellStock {
	
	 /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
	public int maxProfitDPSlow(int [] prices, int K) {
if (K == 0 || prices.length == 0) {
			return 0;
		}
		int [][] T = new int [K+1][prices.length];
		
		for (int i=1; i<T.length; i++) {
			for (int j=1; j<T[0].length; j++) {
				int maxVal = 0;
				for (int m=0; m<j; m++) {
					maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i-1][m]);
				}
				T[i][j] = Math.max(T[i][j-1], maxVal);
			}
		}
		
		return T[K][prices.length-1];
	}
	
	/**
     * This is faster method which does optimization on slower method
     * Time complexity here is O(K * number of days)
     *
     * Formula is
     * T[i][j] = max(T[i][j-1], prices[j] + maxDiff)
     * maxDiff = max(maxDiff, T[i-1][j] - prices[j])
     */
	
	public int maxProfitDP(int [] prices, int K) {
		if (K == 0 || prices.length == 0) {
			return 0;
		}
		int [][] T = new int [K+1][prices.length];
		
		for (int i=1; i<T.length; i++) {
			int maxDiff = -prices[0];
			for (int j=1; j<T[0].length; j++) {
				T[i][j] = Math.max(T[i][j-1], prices[j] + maxDiff);
				maxDiff = Math.max(maxDiff, T[i-1][j] - prices[j]);
			}
		}
		return T[K][prices.length-1];
	}
	
	/**
	 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
	 * design an algorithm to find the maximum profit.
	 */
	public int maxProfitOneTransaction(int prices []) {
		int profit = 0;
		int minElement = Integer.MAX_VALUE;
		for (int i=0; i<prices.length; i++) {
			profit = Math.max(profit, prices[i] - minElement);
			minElement = Math.min(minElement, prices[i]);
		}
		return profit;
	}

	/**
	 * Design an algorithm to find the maximum profit. 
	 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
	 */
	public int maxProfitUnlimitedTransaction(int [] prices) {
		int profit = 0;
		for (int i=1; i<prices.length; i++) {
			int diff = prices[i] - prices[i-1];
			if (diff > 0) {
				profit += diff;
			}
		}
		return profit;
	}

	public static void main(String[] args) {
		BuySellStock BS = new BuySellStock();
		
		int [] prices = {2, 5, 7, 1, 4, 3, 1, 3};
		int K = 3;
		System.out.println("max profit k transaction: " + BS.maxProfitDPSlow(prices, K));
		System.out.println("max profit k transaction - DP fast: " + BS.maxProfitDPSlow(prices, K));
		
		//1 transaction buy & sell stock algorithm
		K = 1;
		System.out.println("max profit with 1 transaction - DP: " + BS.maxProfitDP(prices, K));
		System.out.println("max profit with 1 transaction: " + BS.maxProfitOneTransaction(prices));
		
		//unlimited transaction - buy and sell stocks.
		System.out.println("max profit - unlimited transactions: " + BS.maxProfitUnlimitedTransaction(prices));
		
	}
}
