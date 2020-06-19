package SortAlgs;

import AbstractClasses.SortAlgorithm;

public class BubbleSort extends SortAlgorithm {
	public BubbleSort() {
		this.name = "Bubble Sort";
		this.stable = true;
		this.inPlace = true;
	}

	public <T extends Comparable<T>> void runSort(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				totalAccesses += 2;
				if (arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);

					totalComparisons++;
				}
			}
		}
	}

}
