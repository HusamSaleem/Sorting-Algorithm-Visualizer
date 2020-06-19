package SortAlgs;
import java.util.Random;

import AbstractClasses.SortAlgorithm;

public class BogoSort extends SortAlgorithm {
	public BogoSort() {
		this.name = "Bogo Sort";
		this.inPlace = true;
		this.stable = false;
	}
	
	public <T extends Comparable<T>> void runSort(T[] arr) {
		Random rand = new Random();
		
		int index = rand.nextInt(arr.length);
		int index2 = rand.nextInt(arr.length);
		
		if (isSorted(arr))
			return;
		
		do {
			swap(arr, index, index2);
			
			index = rand.nextInt(arr.length);
			index2 = rand.nextInt(arr.length);
		} while (!isSorted(arr));
	}
	
	private <T extends Comparable<T>> boolean isSorted(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0) {
				totalComparisons++;
				totalAccesses+=2;
				
				return false;
			}
		}
		
		return true;
	}
}
