import java.util.ArrayList;


/*
There is a particular sequence only uses the numbers 1, 2, 3, 4 and no two adjacent numbers are the same.
Write a program that given n1 1s, n2 2s, n3 3s, n4 4s will output the number of such sequences using all these numbers.
Output your answer modulo 1000000007 (10^9 + 7).

https://miafish.wordpress.com/2015/03/10/how-many-particular-sequence-without-adjacent/
*/

public class AdjacentNumbers {

	public static void main(String[] args) {
		AdjacentNumbers an = new AdjacentNumbers();
		
		int n1 = 2;
		int n2 = 2;
		int n3 = 3;
		int n4 = 3;
		
		int result = an.sequence(n1, n2, n3, n4) % 1000000007;
		System.out.println("result: " + result);
		
		System.out.println("DP result: " + an.Sequence2(n1, n2, n3, n4));
		
	}
	
	 public int Sequence2(int n1, int n2, int n3, int n4) {
         int[][][][] dp1 = new int[n1 + 1][ n2 + 1][ n3 + 1][ n4 + 1];
         int[][][][] dp2 = new int[n1 + 1][ n2 + 1][ n3 + 1][ n4 + 1];
         int[][][][] dp3 = new int[n1 + 1][ n2 + 1][ n3 + 1][ n4 + 1];
         int[][][][] dp4 = new int[n1 + 1][ n2 + 1][ n3 + 1][ n4 + 1];

         int MOD = 1000000007;
         dp1[1][0][0][0] = 1;
         dp2[1][0][0][0] = 1;
         dp3[1][0][0][0] = 1;
         dp4[1][0][0][0] = 1;

         for (int i = 0; i <= n1; i++)
         {
             for (int j = 0; j <= n2; j++)
             {
                 for (int k = 0; k <= n3; k++)
                 {
                     for (int l = 0; l <= n4; l++)                                        {                             
                         if (i + j + k + l > 1)
                         {
                             if (i > 0) dp1[i][j][k][l] = dp2[i - 1][ j][ k][ l] + dp3[i - 1][ j][ k][ l] + dp4[i - 1][ j][ k][ l] % MOD;
                             if (j > 0) dp2[i][j][k][l] = dp1[i][ j - 1][ k][ l] + dp3[i][ j - 1][ k][ l] + dp4[i][ j - 1][ k][ l] % MOD;
                             if (k > 0) dp3[i][j][k][l] = dp2[i][ j][ k - 1][ l] + dp1[i][ j][ k - 1][ l] + dp4[i][ j][ k - 1][ l] % MOD;
                             if (l > 0) dp4[i][j][k][l] = dp2[i][ j][ k][ l - 1] + dp3[i][ j][ k][ l - 1] + dp1[i][ j][ k][ l - 1] % MOD;
                         }
                     }
                 }

             }
         }
         return dp1[n1][ n2][ n3][ n4] + dp2[n1][ n2][ n3][ n4] + dp3[n1][ n2][ n3][ n4] + dp4[n1][ n2][ n3][ n4] % MOD;
     }
	public int sequence(int n1, int n2, int n3, int n4) {
		return sequence(n1, n2, n3, n4, new ArrayList<Integer>());
	}
	
	private int sequence(int n1, int n2, int n3, int n4, ArrayList<Integer> list) {
		if (n1 == 0 && n2 == 0 && n3 == 0 && n4 ==0) {
			System.out.println(list.toString());
			return 1;
		}
		
		int count = 0;
		
		if ((list.isEmpty()) || (list.size() > 0 && n1 > 0 && list.get(list.size()-1) != 1)) {
			list.add(1);
			count += sequence(n1-1, n2, n3, n4, list);
			list.remove(list.size()-1);
		}
		if ((list.isEmpty()) || (list.size() > 0 && n2 > 0 && list.get(list.size()-1) != 2)) {
			list.add(2);
			count += sequence(n1, n2-1, n3, n4, list);
			list.remove(list.size()-1);
		}
		if ((list.isEmpty()) || (list.size() > 0 && n3 > 0 && list.get(list.size()-1) != 3)) {
			list.add(3);
			count += sequence(n1, n2, n3-1, n4, list);
			list.remove(list.size()-1);
		}
		if ((list.isEmpty()) || (list.size() > 0 && n4 > 0 && list.get(list.size()-1) != 4)) {
			list.add(4);
			count += sequence(n1, n2, n3, n4-1, list);
			list.remove(list.size()-1);
		}
		
		return count;
	}
}
