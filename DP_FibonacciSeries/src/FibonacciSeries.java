
public class FibonacciSeries {
	
	public int fibonacciIter(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		int a = 0;
		int b = 1;
		for (int i=2; i<=n; i++) {
			int sum = a + b;
			a = b;
			b = sum;
		}
		
		return b;
	}

	public int fibonacci(int n) {
		if (n==0 || n==1) {
			return n;
		}
		return fibonacci(n-1) + fibonacci(n-2);
	}

	public static void main(String[] args) {
		FibonacciSeries FS = new FibonacciSeries();
		for (int i=1; i<40; i++) {
			System.out.print(FS.fibonacciIter(i) + " ");
		}
		System.out.println();
		for (int i=1; i<40; i++) {
			System.out.print(FS.fibonacci(i) + " ");
		}
		System.out.println();
		
	}
}
