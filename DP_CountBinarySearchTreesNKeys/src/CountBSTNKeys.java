
public class CountBSTNKeys {

	public static void main(String[] args) {
		CountBSTNKeys bt = new CountBSTNKeys();
		
		int n = 10;
		
		for (int i=0; i<=n; i++) {
			System.out.println(i + ": " + bt.countBSTs(i));
		}
	}
	
	public int countBSTs(int n) {
		
		if (n == 0 || n == 1) {
			return 1;
		}
		
		int [] T = new int[n+1];
		T[0] = 1;
		T[1] = 1;
		
		for (int i=2; i<=n; i++) {
			for (int j=0; j<i; j++) {
				T[i] += (T[j] * T[i-j-1]);
			}
		}
		
		return T[n];
	}
}
