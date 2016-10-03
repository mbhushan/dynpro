import java.util.Arrays;
import java.util.Comparator;

class Job {
	int start;
	int end;
	int profit;
	
	public Job(int start, int end, int profit) {
		this.start = start;
		this.end = end;
		this.profit = profit;
	}
}
public class WeightedJobScheduling {
	
	public int maxProfit(Job [] jobs) {
		int [] T = new int[jobs.length];
		Arrays.sort(jobs, new FinishTimeComparator());
		
		T[0] = jobs[0].profit;
		for (int i=1; i<jobs.length; i++) {
			T[i] = Math.max(T[i-1], jobs[i].profit);
			for (int j=i-1; j>=0; j--) {
				if(jobs[j].end <= jobs[i].start){
					T[i] = Math.max(T[i], jobs[i].profit + T[j]);
					break;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int val: T) {
			if (val > max) {
				max = val;
			}
		}
		return max;
	}

	public static void main(String [] args) {
		Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,6);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        
        WeightedJobScheduling WJS = new WeightedJobScheduling();
        System.out.println("max profit from job scheduling: " + WJS.maxProfit(jobs));
        
		
	}
}

class FinishTimeComparator implements Comparator<Job> {

	@Override
	public int compare(Job o1, Job o2) {
		return o1.end - o2.end;
	}
	
}
