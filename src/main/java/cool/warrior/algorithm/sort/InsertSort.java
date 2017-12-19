package cool.warrior.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {

	void sort(int[] arr, int n) {
		for (int i=1;i<n;i++) {
			if (arr[i] < arr[i-1]) {
				int popValue = arr[i];//弹出值
				int emptyIndex = i;
				do {
					arr[emptyIndex] = arr[emptyIndex-1];
					emptyIndex--;
				} while (emptyIndex>0 && popValue<arr[emptyIndex-1]);
				arr[emptyIndex] = popValue;
			}
		}
	}
	
	public static void main(String[] args) {
		Random rdm = new Random();
		int[] arr = new int[20];
		for (int i=0;i<arr.length;i++) {
			arr[i] = rdm.nextInt(100);
		}
		System.out.println("生成数组=" + Arrays.toString(arr));
		
		InsertSort o = new InsertSort();
		o.sort(arr, arr.length);
		System.out.println("排序后="+Arrays.toString(arr));
	}
}
