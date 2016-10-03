
/* Given a sequence of words, and a limit on the number of characters that can be put 
 * in one line (line width). Put line breaks in the given sequence such that the 
 * lines are printed neatly
 * 
 * Solution:
 * Badness - We define badness has square of empty spaces in every line. So 2 empty space
 * on one line gets penalized as 4 (2^2) while 1 each empty space on 2 lines gets
 * penalized as 2(1 + 1). So we prefer 1 empty space on different lines over 2 empty space on
 * one line.
 * 
 * For every range i,j(words from i to j) find the cost of putting them on one line. If words 
 * from i to j cannot fit in one line cost will be infinite. Cost is calculated as square of
 * empty space left in line after fitting words from i to j.
 * 
 * Then apply this formula to get places where words need to be going on new line.
 * minCost[i] = minCost[j] + cost[i][j-1]
 * Above formula will try every value of j from i to len and see which one gives minimum 
 * cost to split words from i to len.
 * 
 * Space complexity is O(n^2)
 * Time complexity is O(n^2)
 * 
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
*/
public class TextJustification {
	
	public static void main(String [] args) {
		String [] words = {"shreyansh", "bhushan", "loves", "to", "play", "cricket", "in", "his", "free", "time" };
		int width = 20;
		TextJustification TJ = new TextJustification();
		String result = TJ.justify(words, width);
		System.out.println("printing justified string");
		System.out.println(result);
	}

	public String justify(String [] words, int width) {
		int len = words.length;
		int [][] cost = new int[len][len];
		
		//calculate cost of putting words from i to j in one line, if words dont fit then we put Integer.MAX_VALUE
		for (int i=0; i<len; i++) {
			cost[i][i] = width - words[i].length();
			for (int j=i+1; j<len; j++) {
				cost[i][j] = cost[i][j-1] - words[j].length() - 1; 
			}
		}
		
		for(int i=0; i < words.length; i++){
            for(int j=i; j < words.length; j++){
                if(cost[i][j] < 0){
                    cost[i][j] = Integer.MAX_VALUE;
                }else{
                    cost[i][j] = (int)Math.pow(cost[i][j], 2);
                }
            }
        }
		
		//minCost from i to len is found by trying
        //j between i to len and checking which
        //one has min value
        int minCost[] = new int[len];
        int result[] = new int[len];
        for(int i = len-1; i >= 0 ; i--){
            minCost[i] = cost[i][len-1];
            result[i] = len;
            for(int j=len-1; j > i; j--){
                if(cost[i][j-1] == Integer.MAX_VALUE){
                    continue;
                }
                if(minCost[i] > minCost[j] + cost[i][j-1]){
                    minCost[i] = minCost[j] + cost[i][j-1];
                    result[i] = j;
                }
            }
        }
        int i = 0;
        int j;
        
        System.out.println("Minimum cost is " + minCost[0]);
        System.out.println("\n");
        //finally put all words with new line added in 
        //string buffer and print it.
        StringBuilder builder = new StringBuilder();
        do{
            j = result[i];
            for(int k=i; k < j; k++){
                builder.append(words[k] + " ");
            }
            builder.append("\n");
            i = j;
        }while(j < words.length);
        
        return builder.toString();
	}
}
