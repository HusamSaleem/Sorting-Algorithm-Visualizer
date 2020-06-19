package Interfaces;

/**
 * <h1>Sorting Algorithm Interface</h1>
 */
public interface SortInterface {
	public abstract <T> void swap(T[] arr, int index1, int index2);

	public abstract <T> void printArray(T[] arr);

	public abstract String getName();

	public abstract boolean isInPlace();

	public abstract boolean isStable();
}
