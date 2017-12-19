package cool.warrior.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序.
 * @author WYR
 */
public class QuickSort {
	
	int partition(int[] array, int lo, int hi) {
		int key = array[lo];
		while(lo<hi) {
			while(array[hi]>=key && hi>lo) {
				hi--;
			}
			array[lo] = array[hi];
			while(array[lo]<=key && hi>lo) {
				lo++;
			}
			array[hi] = array[lo];
		}
		array[hi] = key;
		return hi;
	}
	
	void sort(int[] array, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		
		int index = partition(array, lo, hi);
		sort(array, lo, index-1);
		sort(array, index+1, hi);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{3,1,5,7,2,4,9,6,10,8};
		QuickSort q = new QuickSort();
		q.sort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
