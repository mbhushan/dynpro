import java.util.Arrays;


public class BuySellStock {

	public static void main(String[] args) {
		BuySellStock bss = new BuySellStock();
		
		int [][] A = {
				{2, 5, 7, 1, 4, 3, 1, 3},
				{1, 4, 5, 7, 6, 3, 2, 9}
		};
		
		for (int i=0; i<A.length; i++) {
			System.out.println("input array: ");
			System.out.println(Arrays.toString(A[i]));
			System.out.println("max profit with 1 transaction: " + bss.maxProfitOneTransaction(A[i]));
			System.out.println("max profit with unlimited transaction: " + bss.maxProfitUnlimitedTransaction(A[i]));
			System.out.println("max profit with 2 transactions: " + bss.maxProfitTwoTransactions(A[i]));
			System.out.println("max profit with k (=2) transaction: " + bss.maxProfitKTransactions(A[i], 2));
			System.out.println("max profit with k (=3) transaction: " + bss.maxProfitKTransactions(A[i], 3));
			System.out.println();
		}
	}
	
	
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
	 */
	
	public int maxProfitKTransactions(int [] prices, int k) {
		int [][] T = new int[k+1][prices.length];
		
		//populate first row with 0
		for (int i=0; i<prices.length; i++) {
			T[0][i] = 0;
		}
		//populate first col with 0 - since you can only buy and profit would be zero
		for (int i=0; i<=k; i++) {
			T[i][0] = 0;
		}
		
		for (int i=1; i<=k; i++) {
			int maxDiff = -prices[0];
			for (int j=1; j<prices.length; j++) {
				//not transacting on jth day
				int v1 = T[i][j-1];
				int v2 = prices[j] + maxDiff; //alternate: v2 = prices[j] - prices[m] + T[i-1][m] for m=0 ... j-1
				T[i][j] = Math.max(v1, v2);
				maxDiff = Math.max(maxDiff, T[i-1][j] - prices[j]);
			}
		}
		
		return T[k][prices.length-1];
	}
	
	
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
	 */
	public int maxProfitTwoTransactions(int [] prices) {
		int maxProfit = 0;
		
		int [] left = new int[prices.length];
		int [] right = new int[prices.length];
		
		//get the max profit from left to right
		left[0] = prices[0];
		int min = prices[0];
		for (int i=1; i<prices.length; i++) {
			min = Math.min(min, prices[i]);
			left[i] = Math.max(left[i-1], prices[i] - min);
		}
		
		//get the max profit right to left
		right[0] = prices[prices.length-1];
		int max = prices[prices.length-1];
		for (int i=prices.length-2; i>=0; i--) {
			max = Math.max(max, prices[i]);
			right[i] = Math.max(right[i+1], max - prices[i]);
		}
		
		for (int i=0; i<prices.length; i++) {
			maxProfit = Math.max(maxProfit, left[i] + right[i]);
		}
		
		return maxProfit;
	}
	
	
	/*
	 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
	 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 */
	public int maxProfitUnlimitedTransaction(int [] prices) {
		int profit = 0;
		
		for (int i=1; i<prices.length; i++) {
			if (prices[i] > prices[i-1]) {
				profit += (prices[i] - prices[i-1]);
			}
		}
		return profit;
	}
	
	
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
	 * design an algorithm to find the maximum profit.
	 */
	public int maxProfitOneTransaction(int [] prices) {
		int profit = 0;
		int min = prices[0];
		
		for (int i=1; i<prices.length; i++) {
			profit = Math.max(profit, prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		
		return profit;
	}
}
