package SortAlgs;

import java.util.Random;

import AbstractClasses.SortAlgorithm;

public class QuickSort extends SortAlgorithm {
	public QuickSort() {
		this.name = "Quick Sort";
		this.stable = false;
		this.inPlace = true;
	}

	public <T extends Comparable<T>> void runSort(T[] arr, int start, int end) {
		if (start < end) {
			int partitionIndex = partition(arr, start, end);

			runSort(arr, start, partitionIndex - 1);
			runSort(arr, partitionIndex + 1, end);
		}
	}

	private <T extends Comparable<T>> int partition(T[] arr, int start, int end) {
		// Wil choose the last element as the pivot
		Random rand = new Random();
		int randIndex = start + rand.nextInt(end - start);
		T pivot = arr[randIndex];
		totalAccesses++;;

		int pivotIndex = start;

		// This is needed if we use a random pivot because the pivot should be moved to
		// the end of the array...
		swap(arr, randIndex, end);

		// Keep smaller elements of the pivot to the left, the bigger ones to the right
		// Basically swaps the smaller elements with the bigger elements so it is sorted
		// in a way
		for (int i = start; i < end; i++) {
			totalAccesses++;
			if (pivot.compareTo(arr[i]) > 0) {
				swap(arr, i, pivotIndex);
				pivotIndex++;

				totalComparisons++;
			}
		}

		// So the pivot is in the right place
		swap(arr, end, pivotIndex);

		return pivotIndex;
	}
}
