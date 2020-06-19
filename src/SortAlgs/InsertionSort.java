package SortAlgs;

import AbstractClasses.SortAlgorithm;

public class InsertionSort extends SortAlgorithm {
	public InsertionSort() {
		this.name = "Insertion Sort";
		this.inPlace = true;
		this.stable = true;
	}

	public <T extends Comparable<T>> void runSort(T[] arr) {
		int key = 0;
		int j = 0;
		for (int i = 1; i < arr.length; i++) {
			key = i;
			j = i - 1;

			while (j >= 0 && arr[key].compareTo(arr[j]) < 0) {
				totalAccesses += 2;
				totalComparisons+=2;

				swap(arr, j, key);
				key = j;
				j--;
			}
		}
	}
}
