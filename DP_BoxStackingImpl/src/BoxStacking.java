import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
 * Given different dimensions and unlimited supply of boxes for each dimension, stack boxes
 * on top of each other such that it has maximum height but with caveat that length and width
 * of box on top should be strictly less than length and width of box under it. You can
 * rotate boxes as you like.
 * 
 * 1) Create all rotations of boxes such that length is always greater or equal to width
 * 2) Sort boxes by base area in non increasing order (length * width). This is because box
 * with more area will never ever go on top of box with less area.
 * 3) Take T[] and result[] array of same size as total boxes after all rotations are done
 * 4) Apply longest increasing subsequence type of algorithm to get max height.
 * 
 * If n number of dimensions are given total boxes after rotation will be 3n.
 * So space complexity is O(n)
 * Time complexity - O(nlogn) to sort boxes. O(n^2) to apply DP on it So really O(n^2)
*/

public class BoxStacking {
	
	public static void main(String[] args) {
		BoxStacking bs = new BoxStacking();
		
		int [][] input = {
				{3, 2, 5},
				{1, 2, 4}
		};
		int ans = bs.maxHeight(input);
		System.out.println("max height: " + ans);
		
	}
	
	public int maxHeight(int [][] input) {
		Box [] boxes = createBoxes(input);
		
		Arrays.sort(boxes, new BoxComparator());
		printBoxes(boxes);
		
		int [] height = new int[boxes.length];
		int [] result = new int[boxes.length];
		
		for (int i=0; i<boxes.length; i++) {
			height[i] = boxes[i].height;
			result[i] = i;
		}
		
		for (int i=1; i<boxes.length; i++) {
			for (int j=0; j<i; j++) {
				if ((boxes[i].length < boxes[j].length) && (boxes[i].width < boxes[j].width)) {
					height[i] = Math.max(height[i],  + height[j]);
					result[i] = j;
				}
			}
		}
		
		int maxHeight = 0;
		int index = 0;
		for (int i=0; i<height.length; i++) {
			if (height[i] > maxHeight) {
				maxHeight = height[i];
				index = i;
			}
		}
		ArrayList<Box> boxList = new ArrayList<Box>();
		boxList.add(boxes[index]);
		while (index != result[index]) {
			index = result[index];
			boxList.add(boxes[index]);
		}
		
		System.out.println("list of final boxes are: ");
		for (Box box: boxList) {
			System.out.println(box.toString());
		}
		return maxHeight;
	}
	
	public void printBoxes(Box [] boxes) {
		for (Box B: boxes) {
			System.out.println(B.toString());
		}
	}
	
	public Box [] createBoxes(int [][] input) {
		Box [] boxes = new Box[input.length*3];
		
		int index = 0;
		for (int i=0; i<input.length; i++) {
			int [] aux = input[i];
			int len = Math.max(aux[0], aux[1]);
			int wid = Math.min(aux[0], aux[1]);
			int ht = aux[2];
			boxes[index++] = new Box(len, wid, ht);
			
			len = Math.max(aux[1], aux[2]);
			wid = Math.min(aux[1], aux[2]);
			ht = aux[0];
			boxes[index++] = new Box(len, wid, ht);
			
			len = Math.max(aux[2], aux[0]);
			wid = Math.min(aux[2], aux[0]);
			ht = aux[1];
			boxes[index++] = new Box(len, wid, ht);
		}
		return boxes;
	}
}

class Box {

	int length;
	int width;
	int height;
	
	public Box(int length, int width, int height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[length: " + length + ", ");
		sb.append("width: " + width + ", ");
		sb.append("height: " + height + "]");
		return sb.toString();
	}
}

class BoxComparator implements Comparator<Box> {

	@Override
	public int compare(Box o1, Box o2) {
		int baseArea1 = o1.length * o1.width;
		int baseArea2 = o2.length * o2.width;
		
		if (baseArea1 > baseArea2) {
			return -1;
		} else if (baseArea1 < baseArea2) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
