/*
Find all possible interpretations of an array of digits
Consider a coding system for alphabets to integers where ‘a’ is represented as 1, ‘b’ as 2, .. ‘z’ as 26. Given an array of digits (1 to 9) as input, write a function that prints all valid interpretations of input array.

Examples

Input: {1, 1}
Output: ("aa", 'k") 
[2 interpretations: aa(1, 1), k(11)]

Input: {1, 2, 1}
Output: ("aba", "au", "la") 
[3 interpretations: aba(1,2,1), au(1,21), la(12,1)]

Input: {9, 1, 8}
Output: {"iah", "ir"} 
[2 interpretations: iah(9,1,8), ir(9,18)]
*/
public class DigitArrayInterpretation {

	char [] cmap = new char[27];
	String st = "abcdefghijklmnopqrstuvwxyz";
	public DigitArrayInterpretation() {
		for (int i=1; i<cmap.length; i++) {
			cmap[i] = st.charAt(i-1);
		}
	}
	public static void main(String[] args) {
		DigitArrayInterpretation da = new DigitArrayInterpretation();
		int [] digits = {1,2,1};
		System.out.println("possible interpretations: " + da.possibleInterpretations(digits));
	}
	
	public int possibleInterpretations(int [] digits) {
		if (digits == null || digits.length < 1) {
			return 0;
		}
		return possibleInterpretations(digits, 0, new StringBuffer());
	}
	
	private int possibleInterpretations(int [] digits, int index, StringBuffer sb) {
		if (index == digits.length) {
			System.out.println(sb.toString());
			return 1;
		}
		
		int count = 0;
		sb.append(cmap[digits[index]]);
		count += possibleInterpretations(digits, index+1, sb);
		sb.deleteCharAt(sb.length()-1);
		
		if (index+1 < digits.length) {
			int num = digits[index]*10 + digits[index+1];
			if (num>=10 && num <= 26) {
				sb.append(cmap[num]);
				count += possibleInterpretations(digits, index+2, sb);
				sb.deleteCharAt(sb.length()-1);
			}
		}
		return count;
	}
	
	
}
