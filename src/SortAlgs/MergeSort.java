package SortAlgs;

import AbstractClasses.SortAlgorithm;
import Application.SortVisualizer;

public class MergeSort extends SortAlgorithm {
	public MergeSort() {
		this.name = "Merge Sort";
		this.inPlace = false;
		this.stable = true;
	}

	public void runSort(Integer[] arrToSort, int l, int r) {
		if (l < r) {
			// Find the middle point
			int mid = (l + r) / 2;

			// Sort first and second halves
			runSort(arrToSort, l, mid);
			runSort(arrToSort, mid + 1, r);

			// Merge the sorted halves
			merge(arrToSort, l, mid, r);

		}
	}

	public Integer[] merge(Integer[] arr, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i) {
			L[i] = arr[l + i];

			totalAccesses += 2;

			SortVisualizer.barColors[i] = 250;
			SortVisualizer.barColors[l + i] = 250;

			SortVisualizer.sortManager.repaint();
			wait(SortVisualizer.animationSpeed);

		}
		for (int j = 0; j < n2; ++j) {
			R[j] = arr[m + 1 + j];

			totalComparisons++;

			SortVisualizer.barColors[j] = 250;
			SortVisualizer.barColors[m + 1 + j] = 250;

			SortVisualizer.sortManager.repaint();
			wait(SortVisualizer.animationSpeed);
		}

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
				totalComparisons++;
				totalAccesses += 4;
			} else {
				arr[k] = R[j];
				j++;
				totalAccesses += 2;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			totalAccesses += 2;
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			totalAccesses += 2;
			j++;
			k++;
		}

		return arr;
	}
}