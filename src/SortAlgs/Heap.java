package SortAlgs;
import AbstractClasses.SortAlgorithm;
import Application.SortVisualizer;

// Currenty is a max heap
public class Heap extends SortAlgorithm {
	int size;
	int capacity;

	public Integer[] heapArr;

	public Heap(int capacity) {
		this.name = "Heap Sort";
		this.inPlace = true;
		this.stable = false;

		this.capacity = capacity;
		this.size = 0;
		heapArr = new Integer[capacity];
	}

	// constructor that uses the main array
	public Heap() {
		this.name = "Heap Sort";
		this.inPlace = true;
		this.stable = false;

		this.capacity = SortVisualizer.arraySize;
		this.size = 0;

		heapArr = new Integer[capacity];
	}

	public void runHeapSort(Integer[] arr) {
		for (Integer i : arr) {
			insert(i);
		}

		int i = size -1;
		while (i > 0) {
			this.heapArr[i] = pop();
			
			SortVisualizer.arrToSort = this.heapArr;
			
			SortVisualizer.barColors[i] = 250;
			
			SortVisualizer.sortManager.repaint();
			wait(SortVisualizer.animationSpeed);
			
			i--;
		}
	}

	public Integer peek() {
		if (size == 0)
			return null;
		return heapArr[0];
	}

	public Integer pop() {
		if (size == 0)
			return null;

		Integer data = heapArr[0];
		heapArr[0] = heapArr[size - 1];

		size--;
		heapifyDown();

		return data;
	}

	public void insert(Integer data) {
		if (size < capacity) {
			heapArr[size] = data;

			size++;
			heapifyUp();
		}
	}

	private void swap(int index1, int index2) {
		Integer temp = heapArr[index1];
		heapArr[index1] = heapArr[index2];
		heapArr[index2] = temp;
		
		SortVisualizer.barColors[index1] = 250;
		SortVisualizer.barColors[index2] = 250;
		
		totalSwaps++;
		SortVisualizer.sortManager.repaint();
		wait(SortVisualizer.animationSpeed);
	}

	private void heapifyUp() {
		int index = size - 1;

		while (hasParent(index) && heapArr[getParentIndex(index)] < heapArr[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);

			totalAccesses += 2;
			totalComparisons++;
		}
	}

	private void heapifyDown() {
		int index = 0;

		while (hasLeftChild(index)) {
			int largestChild = getLeftChildIndex(index);

			if (hasRightChild(index) && heapArr[getRightChildIndex(index)] > heapArr[getLeftChildIndex(index)]) {
				largestChild = getRightChildIndex(index);

				totalAccesses += 2;
				totalComparisons++;
			}

			if (heapArr[index] > heapArr[largestChild]) {
				totalAccesses += 2;
				totalComparisons++;
				break;
			} else {
				swap(index, largestChild);
				index = largestChild;
			}
		}
	}

	public void printHeap() {
		for (Integer i : heapArr) {
			System.out.print(i + " ");
		}

		System.out.println();
	}

	private boolean hasLeftChild(int index) {
		return (getLeftChildIndex(index) < size);
	}

	private boolean hasRightChild(int index) {
		return (getRightChildIndex(index) < size);
	}

	private boolean hasParent(int index) {
		return (getParentIndex(index) < size);
	}

	private int getRightChildIndex(int parentIndex) {
		return (parentIndex * 2) + 2;
	}

	private int getLeftChildIndex(int parentIndex) {
		return (parentIndex * 2) + 1;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}
}
