package SortAlgs;

import AbstractClasses.SortAlgorithm;
import Application.SortVisualizer;

public class TreeSort<E extends Comparable<E>> extends SortAlgorithm{
	private Node<E> root;
	
	public TreeSort() {
		this.name = "Tree Sort";
		this.inPlace = false;
		this.stable = true;
		
		root = null;
	}
	
	public class Node<E extends Comparable<E>> {
		E data;
		Node<E> right, left;

		public Node(E data) {
			this.data = data;
			this.right = null;
			this.left = null;
		}
	}
	
	public void runTreeSort() {
		// Convert array to binary tree
		for (int i = 0; i < SortVisualizer.arrToSort.length; i++) {
			insert((E) SortVisualizer.arrToSort[i]);
		}
		
		sortInOrder(root);
	}
	
	int index = 0;
	public void sortInOrder(Node<E> node) {

		if (node != null) {
			sortInOrder(node.left);
			SortVisualizer.arrToSort[index] = (Integer) node.data;
			SortVisualizer.barColors[index] = 250;
			
			index++;
			totalAccesses++;
			
			SortVisualizer.sortManager.repaint();
			wait(SortVisualizer.animationSpeed);
			
			sortInOrder(node.right);
		}
	}
	
	public void insert(E data) {
		root = insert(data, root);
	}

	public <E extends Comparable<E>> Node<E> insert(E data, Node<E> node) {
		if (node == null) {
			node = new Node<E>(data);
			return node;
		}

		if (node.data.compareTo(data) > 0) {
			if (node.left != null)
				node.left = insert(data, node.left);
			else
				node.left = new Node<E>(data);
		} else if (node.data.compareTo(data) < 0) {
			if (node.right != null)
				node.right = insert(data, node.right);
			else
				node.right = new Node<E>(data);
		}
		
		return node;
	}
	
	public boolean search(E target) {
		return search(target, root);
	}

	public <E extends Comparable<E>> boolean search(E target, Node<E> node) {
		if (node.data.compareTo(target) == 0)
			return true;

		if (node.left == null && node.right == null)
			return false;

		if (node.data.compareTo(target) > 0) {
			return search(target, node.left);
		} else if (node.data.compareTo(target) < 0) {
			return search(target, node.right);
		}
		return false;
	}

	public void printPostOrder(Node<E> node) {
		if (node != null) {
			printInOrder(node.left);
			printInOrder(node.right);
			System.out.print(node.data + " ");
		}
	}

	public void printPreOrder(Node<E> node) {
		if (node != null) {
			System.out.print(node.data + " ");
			printInOrder(node.left);
			printInOrder(node.right);
		}

	}

	public void printInOrder(Node<E> node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.data + " ");
			printInOrder(node.right);
		}
	}
}
