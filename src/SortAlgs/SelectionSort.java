package SortAlgs;

import AbstractClasses.SortAlgorithm;

public class SelectionSort extends SortAlgorithm {
	public SelectionSort() {
		this.name = "Selection Sort";
		this.inPlace = true;
		this.stable = true;
	}

	public <T extends Comparable<T>> void runSort(T[] arr) {
		int minIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				totalAccesses += 2;

				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;

					totalComparisons++;
				}
			}

			swap(arr, i, minIndex);
		}
	}
}