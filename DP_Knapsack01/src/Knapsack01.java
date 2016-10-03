import java.util.HashMap;
import java.util.Map;

/*
  * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * Time complexity - O(W*total items)
 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 
 	        | 0	if i = 0 or w = 0
c[i,w]  =	| c[i-1, w]	if wi ≥ 0
 	        | max [vi + c[i-1, w-wi], c[i-1, w]}	if i>0 and w ≥  wi

 */
public class Knapsack01 {
	
	
	class Index {
		int remainingWeight;
		int remainingItems;
		
		@Override
		public boolean equals(Object o) {
			if (this == o) { return true; }
			if (o == null || getClass() != o.getClass() ) {
				return false;
			}
			Index index = (Index)o;
			
			if (remainingWeight != index.remainingWeight) {
				return false;
			}
			return remainingItems == index.remainingItems;
		}
		
		@Override
		public int hashCode() {
			int result = remainingWeight;
			result = 31 * result + remainingItems;
			return result;
		}
	}
	
	public int knapsackRecursiveMemo(int [] values, int [] weights, int W) {
		HashMap<Index, Integer> hmap = new HashMap<Index, Integer>();
		return knapsackRecMemoUtil(values, weights, W, values.length, 0, hmap);
	}
	
	private int knapsackRecMemoUtil(int [] values, int [] weights, int remainingWeight, int totalItems, int currentItem, 
			HashMap<Index, Integer> hmap) {
		if(currentItem >= totalItems || remainingWeight <= 0) {
            return 0;
        }
		
		//fom a key based on remainingWeight and remainingCount
        Index key = new Index();
        key.remainingItems = totalItems - currentItem -1;
        key.remainingWeight = remainingWeight;

        //see if key exists in map. If so then return the maximumValue for key stored in map.
        if(hmap.containsKey(key)) {
            return hmap.get(key);
        }
        int maxValue;
        
      //if weight of item is more than remainingWeight then try next item by skipping current item
        if(remainingWeight < weights[currentItem]) {
            maxValue = knapsackRecMemoUtil(values, weights, remainingWeight, totalItems, currentItem + 1, hmap);
        } else {
            //try to get maximumValue of either by picking the currentItem or not picking currentItem
            maxValue = Math.max(values[currentItem] + knapsackRecMemoUtil(values, weights, remainingWeight - weights[currentItem], totalItems, currentItem + 1, hmap),
            		knapsackRecMemoUtil(values, weights, remainingWeight, totalItems, currentItem + 1, hmap));
        }
        //memoize the key with maxValue found to avoid recalculation
        hmap.put(key, maxValue);
        return maxValue;
	}
	
	public static void main(String [] args) {
		int [] weights = {1, 3, 4, 5};
		int [] values = {1, 4, 5, 7};
		int W = 7;
		
		Knapsack01 K = new Knapsack01();
		System.out.println("knapsack optimal value: " + K.knapsack01DP(values, weights, W));
		System.out.println("knapsack optimal value recursive with memoization: " + K.knapsackRecursiveMemo(values, weights, W));
	}

	public int knapsack01DP(int [] values, int [] weights, int W) {
		int [][] T = new int[values.length+1][W+1];
		
		for (int i=0; i<=values.length; i++) {
			for (int j=0; j<=W; j++) {
				if (i == 0 || j ==0) {
					T[i][j] = 0;
					continue;
				}
				if (j - weights[i-1] >= 0) {
					T[i][j] = Math.max(T[i-1][j], values[i-1] + T[i-1][j - weights[i-1]]);
				} else {
					T[i][j] = T[i-1][j];
				}
			}
		}
		return T[values.length][W];
	}
}
