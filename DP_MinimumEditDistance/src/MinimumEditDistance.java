
public class MinimumEditDistance {
	
	/**
     * Uses recursion to find minimum edits
     */
    public int recEditDistance(char[]  str1, char str2[], int len1,int len2){
        
        if(len1 == str1.length){
            return str2.length - len2;
        }
        if(len2 == str2.length){
            return str1.length - len1;
        }
        return Math.min(recEditDistance(str1, str2, len1 + 1, len2 + 1) + str1[len1] == str2[len2] ? 0 : 1, 
        		Math.min(recEditDistance(str1, str2, len1, len2 + 1) + 1, recEditDistance(str1, str2, len1 + 1, len2) + 1));
    }

	public int minimumEditDistance(char []A, char []B) {
		int lenA = A.length;
		int lenB = B.length;
		int [][] T = new int[lenA+1][lenB+1];
		
		for (int i=0; i<=lenA; i++) {
			T[i][0] = i;
		}
		for (int i=0; i<=lenB; i++) {
			T[0][i] = i;
		}
		
		for (int i=1; i<=lenA; i++) {
			for (int j=1; j<=lenB; j++) {
				if (A[i-1] == B[j-1]) {
					T[i][j] = T[i-1][j-1];
				} else {
					T[i][j] = Math.min(Math.min(T[i-1][j], T[i][j-1]), T[i-1][j-1]) + 1;
				}
			}
		}
		printActualEdits(T, A, B);
		return T[lenA][lenB];
	}
	
	/**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while(true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i-1] == str2[j-1]) {
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j-1] + 1){
                System.out.println("Edit " + str2[j-1] + " in B to " + str1[i-1] + " in A");
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j] + 1) {
                System.out.println("Delete in A: " + str1[i-1]);
                i = i-1;
            } else if (T[i][j] == T[i][j-1] + 1){
                System.out.println("Delete in B: " + str2[j-1]);
                j = j -1;
            } else {
            	System.out.println("something went wrong!");
            	break;
            }

        }
    }
	
	public static void main(String [] args) {
		MinimumEditDistance MED = new MinimumEditDistance();
		String A = "azced";
        String B = "abcdef";
        System.out.println("min edit distance: " + MED.minimumEditDistance(A.toCharArray(), B.toCharArray()));
       // System.out.println("min edit recursive: " + MED.recEditDistance(A.toCharArray(), B.toCharArray(), A.length(), B.length()));
	}
}
