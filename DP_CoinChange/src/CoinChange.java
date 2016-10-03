
/*
* Given a total and coins of certain denominations find number of ways total
* can be formed from coins assuming infinity supply of coins
*/

public class CoinChange {

	public int coinChangeDP(int [] coins, int total) {
		int len = coins.length;
		int [] T = new int[total+1];
		int [] R = new int[total+1];
		T[0] = 0;

		for (int i=1; i <= total; i++) {
			T[i] = Integer.MAX_VALUE - 1;
			R[i] = -1;
		}
		
		for (int j=0; j<coins.length; j++) {
			for (int i=1; i<= total; i++) {
				if (i >= coins[j]) {
					if (T[i-coins[j]]+1 < T[i]) {
						T[i] = 1 + T[i - coins[j]];
						R[i] = j;
					}
				}
			}
		}
		
		printCoins(R, coins);
		return T[total];
	}
	
	private void printCoins(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.println("Solution not possible!");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins for Total: ");
        while ( start != 0 ) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.println();
    }
	
	public static void main(String [] args) {
		int total = 15;
		int [] coins = {3,4,6,7,9};
		CoinChange CC = new CoinChange();
		System.out.println("coin change: " + CC.coinChangeDP(coins, total));
		
	}
	
}
