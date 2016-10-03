
public class BinaryStringNonConsecutive1s {
	
	public int getStrings(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		int a = 0;
		int b = 1;
		for (int i=2; i<=n ; i++) {
			int sum = a + b;
			a = b;
			b = sum;
		}
		return b;
	}

	public static void main(String[] args) {
		BinaryStringNonConsecutive1s BS = new BinaryStringNonConsecutive1s();
		for (int i=1; i<=10; i++) {
              System.out.println("# of binary strings with non-consecutive 1s: " + BS.getStrings(i));
		}
	}
}
