/*
Count number of binary strings without consecutive 1’s
Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.

Examples:

Input:  N = 2
Output: 3
// The 3 strings are 00, 01, 10

Input: N = 3
Output: 5
// The 5 strings are 000, 001, 010, 100, 101
This problem can be solved using Dynamic Programming. Let a[i] be the number of binary strings of length i 
which do not contain any two consecutive 1’s and which end in 0. Similarly, let b[i] be the number of such strings which end in 1. 
We can append either 0 or 1 to a string ending in 0, but we can only append 0 to a string ending in 1. 
This yields the recurrence relation:
a[i] = a[i - 1] + b[i - 1]
b[i] = a[i - 1] 
The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].
n = 1, count = 2  = fib(3)
n = 2, count = 3  = fib(4)
n = 3, count = 5  = fib(5)
n = 4, count = 8  = fib(6)
n = 5, count = 13 = fib(7)
 */

public class BinaryStrings {

	public static void main(String[] args) {
		BinaryStrings bs = new BinaryStrings();
		
		for (int i=0; i<=10; i++) {
			System.out.println("n=" + i + " :" + bs.binaryNoConsec1s(i));
		}
	}
	
	public int binaryNoConsec1s(int n) {
		if (n == 0 || n == 1) {
			return n == 0? 1: 2;
		}
		int x = n+1;
		int a = 1;
		int b = 1;
		for (int i=2; i<=x; i++) {
			int temp = b;
			b = a+b;
			a = temp;
		}
		return b;
	}
}
