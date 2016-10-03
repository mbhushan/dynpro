import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class WordBreak {
	
	/**
     * Prints all the words possible instead of just one combination.
     * Reference
     * https://leetcode.com/problems/word-break-ii/
     */
	public List<String> wordBreakTopDown(String str) {
		Map<Integer, List<String>> dp = new HashMap<Integer, List<String>>();
		
		int max = 0;
		for (String word: dict) {
			max = Math.max(max, word.length());
		}
		return wordBreakTopDownUtil(str, dp, 0, max);
	}
	
	private List<String> wordBreakTopDownUtil(String str, Map<Integer, List<String>> dp, int start, int max) {
		if (start == str.length()) {
			return Collections.singletonList("");
		}
		if (dp.containsKey(start)) {
			return dp.get(start);
		}
		List<String> words = new ArrayList<String>();
		for (int i=start; i<start+max && i<str.length(); i++) {
			String newWord = str.substring(start, i+1);
			if (!dict.contains(newWord)) {
				continue;
			}
			List<String> result = wordBreakTopDownUtil(str, dp, i+1, max);
			for (String r : result) {
				String space = r.length() == 0 ? "" : " ";
				words.add(newWord + space + r);
			}
		}
		dp.put(start, words);
		return words;
	}
	
	public String breakWordDP(String str) {
		
		int len = str.length();
		int [][] T = new int[len][len];
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				T[i][j] = -1; //indicates word between i & j cant be split
			}
		}
		
		for (int l=1; l<=len; l++) {
			for (int i=0; i<len-l+1; i++) {
				int j = l - 1 + i;
				String subStr = str.substring(i, j+1);
				if (dict.contains(subStr)) {
					T[i][j] = i;
					continue;
				}
				//find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true 
				for (int k=i+1; k<=j; k++) {
					 if(T[i][k-1] != -1 && T[k][j] != -1) {
	                        T[i][j] = k;
	                        break;
	                 }
				}
			}
		}
		
		if(T[0][len-1] == -1){
	            return null;
	    }
		
		//split of words is possible
		StringBuffer buff = new StringBuffer();
		int i=0; int j = len-1;
		while (i < j) {
			int k = T[i][j];
			if (i == k) {
				buff.append(str.substring(i, j+1));
				break;
			}
			buff.append(str.substring(i, k) + " ");
			i = k;
		}
		return buff.toString();
	}
	
	private HashSet<String> dict ;
	private String [] words = {
			"indi", "ans", "to","am", "i", "where", "we", "are", "indians", "bangalore", "coders", "looking", "work", "like", "code", "free", "time"
	};
	
	WordBreak() {
		dict = new HashSet<String>();
		Collections.addAll(dict, words);
	}
	
	public static void main(String [] args) {
		WordBreak WB = new WordBreak();
		String str = "weindiansliketocode";
		String result = WB.breakWord(str.toCharArray(), 0);
		System.out.println("After word break:");
		System.out.println(result);
		System.out.println("DP word break:");
		System.out.println(WB.breakWordDP(str));
		
		System.out.println("top down approach - printing all combinations");
		List<String> words = WB.wordBreakTopDown(str);
		for (String r: words) {
			System.out.println(r);
		}
		
	}
	
	public String breakWord(char [] str, int low) {
		StringBuffer sb = new StringBuffer();
		for (int i=low; i<str.length; i++) {
			sb.append(str[i]);
			if (dict.contains(sb.toString())) {
				String result = breakWord(str, i+1);
				if (result != null) {
					return sb.toString() + " " + result;
				}
			}
		}
		if (dict.contains(sb.toString())) {
			return sb.toString();
		}
		return null;
	}
}
