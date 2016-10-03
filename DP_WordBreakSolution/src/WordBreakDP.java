import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/*
Given a string and a dictionary, 
return true if string can be split into multiple words such that each word is in dictionary. 
If not return false
 * e.g weloveprogramming -> we love programming
 * e.g youareawesome -> you are awesome
*/

public class WordBreakDP {

	private HashSet<String> hset = new HashSet<String>();
	private String [] dict = {"we", "awe", "you", "are", "is", "live", "love", "awesome", "some", "programming", "pro", "gramming"};
	
	public WordBreakDP() {
		Collections.addAll(hset, dict);
	}


	public static void main(String[] args) {
		WordBreakDP wb = new WordBreakDP();

		String [] st = {"welovesome", "weloveprogramming", "youareawesome", "programmingawesome"};
		for (int i=0; i<st.length; i++) {
			wb.wordBreak(st[i]);
		}
	}
	
	public void wordBreak(String input) {
		int n = input.length();
		int [][] T = new int[n][n];
		
		for (int i=0; i<n; i++) {
			Arrays.fill(T[i], -1); //-1 indicates no split
		}
		
		for (int len=1; len<=n; len++) {
			for (int i=0; i<n-len+1; i++) {
				int j = i + len -1;
				String buff = input.substring(i, j+1);
				if (hset.contains(buff)) {
					T[i][j] = i;
					//System.out.println("buff: " + buff + ", i: " + i);
					continue;
				}
				
				//find a k between i+1 and j where there is a valid split.
				for (int k=i+1; k<=j; k++) {
					if (T[i][k-1] != -1 && T[k][j] != -1) {
						T[i][j] = k;
						break;
					}
				}
			}
		}
		
		if (T[0][n-1] != -1) {
			System.out.println("word break possible!");
		}
		
		int end = T[0][n-1];
		int start = 0;
		ArrayList<String> result = new ArrayList<String>();
		while (end != start) {
			result.add(input.substring(start, end));
			start = end;
			end = T[end][n-1];
			if (start == end) {
				result.add(input.substring(start, n));
			}
		}
		//Collections.reverse(result);
		System.out.println(result.toString());
	}
}
