package SortAlgs;

import AbstractClasses.SortAlgorithm;
import Application.SortVisualizer;

public class ShellSort extends SortAlgorithm {
	public ShellSort() {
		this.name = "Shell Sort";
		this.inPlace = true;
		this.stable = false;
	}
	
	public <T extends Comparable<T>> void runSort(T[] arr) {
		int n = arr.length; 
		  
		// Start with a gap
        for (int gap = n/2; gap > 0; gap /= 2) 
        { 
        	// Loop over the array starting at the gap index and comparing elements to the left of the selected element by the gap
            for (int i = gap; i < n; i++) 
            { 
               
            	T temp = arr[i]; 
                totalAccesses++;
  
                // shift earlier gap-sorted elements up until 
                // the correct location for a[i] is found 
                int j;
                for (j = i; j >= gap && arr[j - gap].compareTo(temp) > 0; j -= gap) {
                    arr[j] = arr[j - gap]; 
                    
                    SortVisualizer.barColors[j] = 250;

                	totalSwaps++;
                	totalAccesses+=3;
                	totalComparisons++;
                	SortVisualizer.sortManager.repaint();
                	wait(SortVisualizer.animationSpeed);
                }
 
                // switch the temp obj to this.
                arr[j] = temp; 
                totalAccesses++;
                totalSwaps++;
            } 
        } 
	}
}
