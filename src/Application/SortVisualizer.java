package Application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.*;

import AbstractClasses.SortAlgorithm;
import SortAlgs.BogoSort;
import SortAlgs.BubbleSort;
import SortAlgs.Heap;
import SortAlgs.InsertionSort;
import SortAlgs.MergeSort;
import SortAlgs.QuickSort;
import SortAlgs.SelectionSort;
import SortAlgs.TreeSort;

/**
 * <h1>The main driver of the program</h1> Runs the UI, and handles the order of
 * all sorting algorithms that are supposed to go in order Also handles the
 * animation speed, UI scaling, etc...
 * <p>
 */
public class SortVisualizer extends JPanel {
	private static final long serialVersionUID = 1L;

	public static Integer[] arrToSort;
	// RGB value for each rectangle
	public static int[] barColors;

	// The window
	public static JFrame frame;

	public static SortVisualizer sortManager;

	// Shouldnt do more than 1000
	public static int arraySize = 250;

	// Animation stuff
	public static double animationSpeed = 0.001;
	// Delay in seconds after each soritng algorithm finishes before it moves on
	public final static double DELAY_AFTER_SORT = 1;
	private boolean sortFinished = false;
	// The higher it is, the faster the red colors will go away
	public final int COLOR_DEGRADE_SPEED = 32;
	
	// Recommended to keep at 3
	private final int BAR_WIDTH = 3;

	public String currentSortName = "";
	public boolean currentSortInPlace;
	public boolean currentSortStable;

	SortVisualizer(int size) {
		arrToSort = new Integer[size];
		barColors = new int[size];

		addElements(arrToSort);
		shuffleArray(arrToSort);
	}
	
	/**
	 * <h1>Handles the order on which sorting algorithm goes first</h1>
	 * Change the order of the sorting algorithms by just moving around the method calls...
	 * <p>
	 */
	public void startSortingAlgorithms() {
		runHeapSort();
		runQuickSort();
		runMergeSort();
		runSelectionSort();
		runTreeSort();
		runBubbleSort();
		runInsertionSort();
		runBogoSort();
	}


	/**
	 * <h1>Creates and shows the main GUI</h1>
	 * 
	 * @param s This is the reference to this main driver class
	 */
	public void createAndShowGui(SortVisualizer s) {
		frame = new JFrame("Sort Visualizer");

		// Will close the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(s);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		sortManager = new SortVisualizer(arraySize);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				sortManager.createAndShowGui(sortManager);
			}
		};
		EventQueue.invokeLater(r);

		// Starting the sort
		sortManager.startSortingAlgorithms();
	}

	/**
	 * Set the scaled size of the GUI depending on the array's length
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(arrToSort.length * BAR_WIDTH + 100, arrToSort.length + 125);
	}

	/**
	 * <h1>Creates the graphics</h1>
	 * 
	 * This method overrides the main one and it basically repaints the entire GUI
	 * each time it is called. It also draws information about the sorting
	 * algorithm, and as well as create the background
	 * <p>
	 * Can get called by the method repaint();
	 * <p>
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);

		// For background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// UI info
		g.setColor(Color.green);
		g.drawString("Algorithm Name: " + currentSortName, 5, 15);
		g.setColor(Color.orange);

		String accessTotal = String.format("%,d", SortAlgorithm.totalAccesses);
		String swapTotal = String.format("%,d", SortAlgorithm.totalSwaps);
		String comparisonTotal = String.format("%,d", SortAlgorithm.totalComparisons);
		String inPlace = (currentSortInPlace) ? "Yes" : "No";
		String stable = (currentSortStable) ? "Yes" : "No";

		g.drawString("Accesses: " + accessTotal, 5, 35);
		g.drawString("Swaps: " + swapTotal, 5, 55);
		g.drawString("Comparisons: " + comparisonTotal, 5, 75);
		g.drawString("In-Place: " + inPlace, 175, 35);
		g.drawString("Stable: " + stable, 175, 55);

		g.setColor(Color.YELLOW);
		g.drawString("Number of Elements: " + arraySize, 300, 35);

		int x = 0, y, width = BAR_WIDTH, height;

		for (int i = 0; i < arrToSort.length; i++) {
			x += width;
			y = frame.getSize().height - 45;
			height = arrToSort[i];

			if (sortFinished)
				graphics.setColor(new Color(255 - barColors[i], 255, 255 - barColors[i]));
			else
				graphics.setColor(new Color(255, 255 - barColors[i], 255 - barColors[i]));

			graphics.fillRect(x, y - height, width, height);

			barColors[i] -= (barColors[i] > 0) ? COLOR_DEGRADE_SPEED : 0;

			barColors[i] = (barColors[i] < 0) ? 0 : barColors[i];
		}
	}

	/**
	 * <h1>Reset and prepares for the next sorting algorithm</h1>
	 */
	public void reset() {
		sortFinished = true;
		playSortedAnimation();
		SortAlgorithm.wait(DELAY_AFTER_SORT);

		sortFinished = false;
		shuffleArray(arrToSort);
		resetColorVals(barColors);
		repaint();

		SortAlgorithm.resetStats();
	}

	/**
	 * <h1>Adds elements to an array</h1>
	 */
	public void addElements(Integer[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
	}

	/**
	 * <h1>Reset the bar's RGB values</h1>
	 */
	public void resetColorVals(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		repaint();
	}

	/**
	 * <h1>Shuffles the array</h1>
	 */
	public void shuffleArray(Integer[] arr) {
		Random rand = new Random();
		int randIndex;

		for (int i = 0; i < arr.length; i++) {
			randIndex = rand.nextInt(arr.length - 1);

			Integer temp = arr[i];
			arr[i] = arr[randIndex];
			arr[randIndex] = temp;

			barColors[i] = 250;
			barColors[randIndex] = 250;

			repaint();
			SortAlgorithm.wait(animationSpeed);
		}
	}

	public Integer[] addRandomElements(Integer[] arr) {
		Random rand = new Random();

		for (int i = 0; i < arr.length; i++) {
			// arr2[i] = i+1;
			arr[i] = rand.nextInt(arr.length) + 1;
		}

		return arr;
	}

	/**
	 * <h1>Plays the sorted animation</h1> When a sorting algorithm is done sorting,
	 * there will be an animation played to show that it is done sorting
	 * <p>
	 */
	private void playSortedAnimation() {
		resetColorVals(barColors);
		for (int i = 0; i < barColors.length; i++) {
			barColors[i] = 250;

			repaint();
			SortAlgorithm.wait(animationSpeed);
		}
		resetColorVals(barColors);
	}

	/**
	 * <h1>Set's the UI's texts</h1>
	 */
	private void setUiText(SortAlgorithm s) {
		currentSortName = s.getName();
		currentSortInPlace = s.isInPlace();
		currentSortStable = s.isStable();
	}

	private void runInsertionSort() {
		InsertionSort insertionSort = new InsertionSort();
		setUiText(insertionSort);

		insertionSort.runSort(arrToSort);
		reset();
	}

	private void runBubbleSort() {
		BubbleSort bubbleSort = new BubbleSort();
		setUiText(bubbleSort);

		bubbleSort.runSort(arrToSort);
		reset();
	}

	private void runSelectionSort() {
		SelectionSort selectionSort = new SelectionSort();
		setUiText(selectionSort);

		selectionSort.runSort(arrToSort);
		reset();
	}

	private void runQuickSort() {
		QuickSort quickSort = new QuickSort();
		setUiText(quickSort);

		quickSort.runSort(arrToSort, 0, arrToSort.length - 1);
		reset();
	}

	private void runMergeSort() {
		MergeSort mergeSort = new MergeSort();
		setUiText(mergeSort);

		mergeSort.runSort(arrToSort, 0, arrToSort.length - 1);
		reset();
	}

	private void runHeapSort() {
		Heap heap = new Heap();
		setUiText(heap);

		heap.runHeapSort(arrToSort);
		reset();
	}
	
	private void runTreeSort() {
		TreeSort<Integer> treeSort = new TreeSort<Integer>();
		setUiText(treeSort);
		
		//treeSort.potato();
		treeSort.runTreeSort();
		reset();
	}

	private void runBogoSort() {
		BogoSort bogoSort = new BogoSort();
		setUiText(bogoSort);

		bogoSort.runSort(arrToSort);
		reset();
	}

}