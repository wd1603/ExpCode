package cool.warrior.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序.
 * @author WYR
 *
 */
public class HeapSort {

	
	void adjustHeap(int[] num, int s, int t) {
		int i = s;
		int x = num[s];
		for (int j = 2 * i; j <= t; j = 2 * j) {
			if (j < t && num[j] < num[j + 1])
				j = j + 1;// 找出较大者把较大者给num[i]
			if (x > num[j])
				break;
			num[i] = num[j];
			i = j;
		}
		num[i] = x;
	}
	
	void sort(int[] array, int n) {
		int i;
		for (i=n/2;i>=1;i--) {
			adjustHeap(array, i, n);
		}
		for (i=n;i>1;i--) {
			array[0] = array[i];
			array[i] = array[1];
			array[1] = array[0];
			adjustHeap(array, 1, i-1);
		}
	}
	
	public static void main(String[] args) {
		Random rdm = new Random();
		int[] arr = new int[10];
		for (int i=0;i<arr.length;i++) {
			arr[i] = rdm.nextInt(100);
		}
		System.out.println("生成数组=" + Arrays.toString(arr));
		
		HeapSort h = new HeapSort();
		h.sort(arr, arr.length-1);
		System.out.println("排序后=" + Arrays.toString(arr));
	}
}
