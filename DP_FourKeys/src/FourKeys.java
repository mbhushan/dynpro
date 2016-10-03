import java.util.Arrays;

/*
Imagine you have a special keyboard with the following keys:

A
Ctrl+A
Ctrl+C
Ctrl+V
where CTRL+A, CTRL+C, CTRL+V each acts as one function key for “Select All”, “Copy”, and “Paste” operations respectively.

If you can only press the keyboard for N times (with the above four keys), please write a program to produce maximum numbers of A. 
If possible, please also print out the sequence of keys.

That is to say, the input parameter is N (No. of keys that you can press), the output is M (No. of As that you can produce).

http://articles.leetcode.com/ctrla-ctrlc-ctrlv/
*/

public class FourKeys {
	
	public static void main(String[] args) {
		FourKeys fk = new FourKeys();
		int n = 27;

		for (int i=1; i<=n ;i++) {
			System.out.println(i + ": " + fk.maxAs(i));
		}
	}
	
	public  int maxAs(int n) {
		
		 // the correct answer for N = 7 is 7. This is simply because the sequence of { CTRL+A, CTRL+C, CTRL+V } 
		 // does not double the previous text. Try on notepad or any other editor.
		if (n <= 7) {
			return n;
		}
		
		int [] T = new int[n+1];
		
		for (int i=0; i<=7; i++ ) {
			T[i] = i;
		}
		
		//We only have to consider up to 4 consecutive pastes, since select, copy, paste x 5 is equivalent to 
		// select, copy, paste, select, copy, paste and the latter is better since it leaves us with more in the clipboard.
		int mark = 5;
		for (int i=8; i<=n; i++) {
			for (int k=i-mark; k<=i; k++) {
				T[i] = Math.max(T[i], (k-3)*(i-k+1));
			}
			int p = 6; //since after 4 pastes you can do better by increasing buffer.
			for (int k=i-p; k<=i && p>2; k++, --p) {
				T[i] = Math.max(T[i], T[k]*(p-2));
			}
		}
		
		//System.out.println(Arrays.toString(T));
		return T[n];
	}
}
