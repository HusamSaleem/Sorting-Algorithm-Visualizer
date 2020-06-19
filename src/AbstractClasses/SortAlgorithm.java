package AbstractClasses;

import java.math.BigDecimal;

import Application.SortVisualizer;
import Interfaces.SortInterface;

/**
 * <h1>A sorting algorithm abstract class</h1>
 * <p>
 * Contains the base fundamentals of information and methods that sorting
 * algorithms use
 */
public abstract class SortAlgorithm implements SortInterface {
	protected String name;
	protected boolean inPlace;
	protected boolean stable;

	public static int totalSwaps = 0;
	public static int totalAccesses = 0;
	public static int totalComparisons = 0;

	/**
	 * <h1>Swaps elements</h1>
	 * 
	 * Swaps two array elements and then gives those indices a RGB value color
	 * <p>
	 * 
	 * @param arr    This is the array that will be swapping elements
	 * @param index1 This is the first index
	 * @param index2 This is the first index
	 * @return Nothing...
	 */

	public <T> void swap(T[] arr, int index1, int index2) {
		T temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;

		SortVisualizer.barColors[index1] = 250;
		SortVisualizer.barColors[index2] = 250;

		totalSwaps++;
		SortVisualizer.sortManager.repaint();
		wait(SortVisualizer.animationSpeed);
	}

	/**
	 * <h1>Prints the array</h1>
	 * 
	 * @param arr This is the array that will be printed
	 * @return Nothing...
	 */
	public <T> void printArray(T[] arr) {
		for (T t : arr) {
			System.out.print(t + " ");
		}
		System.out.println();
	}

	public final String getName() {
		return this.name;
	}

	public final boolean isInPlace() {
		return this.inPlace;
	}

	public final boolean isStable() {
		return this.stable;
	}

	/**
	 * <h1>Tells the program to wait</h1> Mainly used for animation to delay for the
	 * use of seeing whats going on
	 * <p>
	 * 
	 * @param seconds The amount of seconds to wait for
	 * @return Nothing...
	 */
	public static void wait(double seconds) {
		double startTime = System.currentTimeMillis();
		BigDecimal elapsedTime = new BigDecimal(System.currentTimeMillis() - startTime);
		BigDecimal elapsedSeconds = new BigDecimal(0);

		// More Precise
		BigDecimal secondsAsDecimal = new BigDecimal(seconds);

		while (elapsedTime.compareTo(secondsAsDecimal) < 0) {
			elapsedTime = new BigDecimal(System.currentTimeMillis() - startTime);
			elapsedSeconds = elapsedTime.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_DOWN);

			elapsedTime = elapsedSeconds;
		}
	}

	/**
	 * <h1>Reset Algorithm Stats</h1>
	 * <p>
	 * 
	 * @return Nothing...
	 */
	public static void resetStats() {
		totalSwaps = 0;
		totalAccesses = 0;
		totalComparisons = 0;
	}
}
