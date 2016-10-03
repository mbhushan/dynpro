import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


/*
Given a string and a dictionary, 
return true if string can be split into multiple words such that each word is in dictionary. 
If not return false
 * e.g weloveprogramming -> we love programming
 * e.g youareawesome -> you are awesome
*/

public class WordBreakProblem {
	
	private HashSet<String> hset = new HashSet<String>();
	private String [] dict = {"we", "awe", "you", "are", "is", "live", "love", "awesome", "some", "programming", "pro", "gramming"};
	
	public WordBreakProblem() {
		Collections.addAll(hset, dict);
	}

	public static void main(String[] args) {
		WordBreakProblem wb = new WordBreakProblem();
		
		String [] st = {"weloveprogramming", "youareawesome", "programmingawesome"};
		for (int i=0; i<st.length; i++) {
			wb.wordBreak(st[i].toCharArray());
		}
		
	}
	
	public void wordBreak(char [] input) {
		int index = 0;
		StringBuffer sb = new StringBuffer();
		ArrayList<String> result = new ArrayList<String>();
		wordBreak(input, index, sb, result);
	}
	
	private void wordBreak(char [] input, int index, StringBuffer sb, ArrayList<String> result) {
		if (index >= input.length) {
			StringBuffer buff = new StringBuffer();
			int count = 0;
			for (String st: result) {
				//System.out.print(st + " ");
				sb.append(st + " ");
				count += st.length();
			}
			if (count == input.length)
				System.out.println(sb.toString());
			return;
		}
		
		for (int i=index; i<input.length; i++) {
			sb.append(input[i]);
			String str = sb.toString();
			if (hset.contains(str)) {
				result.add(str);
				wordBreak(input, i+1, new StringBuffer(), result);
				result.remove(result.size()-1);
				wordBreak(input, i+1, sb, result);
			}
		}
	}
}
