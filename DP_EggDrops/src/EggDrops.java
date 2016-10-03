
public class EggDrops {
	
	public int eggDropDP(int floors, int eggs) {
		int [][]T = new int[eggs+1][floors+1];
		for (int i=0; i<=floors; i++) {
			T[1][i] = i;
		}
		
		for (int e=2; e<=eggs; e++) {
			for (int f=1; f<=floors; f++) {
				T[e][f] = Integer.MAX_VALUE;
				for (int k=1; k<=f; k++) {
					int val = 1 + Math.max(T[e-1][k-1], T[e][f-k]);
					if (val < T[e][f]) {
						T[e][f] = val;
					}
				}
			}
		}
		return T[eggs][floors] ;
	}
	
	public int calculateRecursive(int eggs, int floors){
        if(eggs == 1){
            return floors;
        }
        if(floors == 0){
            return 0;
        }
        int min = 1000;
        for(int i=1; i <= floors; i++){
            int val = 1 + Math.max(calculateRecursive(eggs-1, i-1),calculateRecursive(eggs, floors-i));
            if(val < min){
                min = val;
            }
        }
        return min;
    }

	public int eggDropsRec(int floors, int eggs) {
		if (eggs == 1) {
			return floors;
		}
		
		if (floors == 0) {
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		for (int i=1; i<=floors; i++) {
			int val = 1 + Math.max(eggDropsRec(i-1, eggs-1), eggDropsRec(floors-i, eggs));
			if (val < min) {
				min = val;
			}
		}
		return min;
	}
	
	public static void main(String [] args) {
		EggDrops EG = new EggDrops();
		int floors = 100;
		int eggs = 20;
		for (int e=2; e<=eggs; e++) {
			System.out.println("# of egg drops: " + EG.eggDropDP(floors, e));
			//System.out.println("# of egg drops: " + EG.eggDropsRec(floors, e));
			//System.out.println("# of egg drops: " + EG.calculateRecursive(e, floors));
		}
	}
}
