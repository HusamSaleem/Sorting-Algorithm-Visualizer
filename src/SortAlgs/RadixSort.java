package SortAlgs;
import java.util.Arrays;

import AbstractClasses.SortAlgorithm;
import Application.SortVisualizer;

public class RadixSort extends SortAlgorithm{
	public RadixSort() {
		this.name = "Radix Sort";
		this.inPlace = false;
		this.stable = true;
	}
	
	private int getMax(Integer[] arr) {
		int max = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		
		return max;
	}
	
	public void runSort(Integer[] arr) {
		int maxNum = getMax(arr);
		for (int i = 1; maxNum / i > 0; i*=10) {
			arr = countSort(arr, i);
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
	private Integer[] countSort(Integer[] arr, int index) {
		int[] bucket = {0,0,0,0,0,0,0,0,0,0};
		Integer[] result = new Integer[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			bucket[(arr[i] / index) % 10]++;
		}
		
		for (int i = 1; i < 10; i++) {
			bucket[i] += bucket[i - 1];
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			result[bucket[(arr[i] / index) % 10]  -1 ] = arr[i];
			bucket[(arr[i] / index) % 10]--;
		}
		
		return result;
	}
}
