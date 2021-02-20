
/**
* Defines a singly-linked list class
* @author Gerardo R. Padilla Jr. 
* @author Nhan Ha
* CIS 22C Lab 5
*/

import java.util.NoSuchElementException;

public class List<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition a new List object is created
	 */
	public List() {
		first = null;
		last = null;
		iterator = null;
		length = 0;
	}

	/**
	 * Instantiates a new List by copying another List
	 * 
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical but separate copy of
	 *                the List original
	 */
	public List(List<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {

		if (length == 0) {
			throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
		}
		return first.data;

	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition length is not to be equal to 0
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: List is Empty. No data to access!");
		}
		return last.data;
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/*
	 * returns the node pointed to by the iterator
	 * 
	 * @postcondition iterator shall be returned
	 * 
	 * @throws NullPointerException when precondition is violated
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator: iterator is set to null; ");
		}
		return iterator.data;
	}

	/*
	 * returns whether the iterator is null
	 * 
	 * @postcondition returns a boolean stating if the iterator is null
	 */
	public boolean offEnd() {
		return (iterator == null);
	}

	/**
	 * Determines whether two Lists have the same data in the same order
	 * 
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) { // Lists are same length
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition adds a method to the front of the list and increases length by
	 *                1
	 */
	public void addFirst(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition Adds a node to the end of the list and increases length by 1
	 */
	public void addLast(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			last.next = N;
			N.prev = last;
			last = N;
		}
		length++;
	}

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition length must not be equal to 0
	 * @postcondition deletes the node at the front of the list AND decreases length
	 *                by 1
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
		} else if (length == 1) {
			first = last = null;
		} else {
			first = first.next;
			first.prev = null;

		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition length is not zero
	 * @postcondition deletes the node at the back of the list AND decreases length
	 *                by 1
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: list is empty. Nothing to remove.");
		} else if (length == 1) {
			first = last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/*
	 * moves the iterator to the start of the list
	 * 
	 * @precondition length is greater than 0
	 * 
	 * @throws NoSuchElementException when precondition is violated
	 * 
	 * @postcondition Iterator is pointing to first
	 * 
	 */
	public void placeIterator() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("placeIterator: List does not have a valid node to point to");
		} else
			iterator = first;
	}

	/**
	 * removes the element pointed to by the iterator
	 * 
	 * @precondition length is not zero
	 * @postcondition deletes the node pointed to by the iterator, iterator becomes
	 *                null AND decreases length by 1
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeIterator() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeIterator: list is empty. Nothing to remove.");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}

		iterator = null;
	}

	/*
	 * addIterator: inserts an element after the node currently pointed to by the
	 * iterator
	 * 
	 * @precondition iterator !null ;
	 * 
	 * @throws NullPointerException when precondition is violated
	 * 
	 * @postcondition length incremented by 1; New node added after the iterator
	 * 
	 */
	public void addIterator(T data) throws NullPointerException {

		if (iterator == null) // !offEnd()
		{
			throw new NullPointerException("removeIterator: iterator does not point to valid Node");
		}
		if (iterator == last) {
			addLast(data);
		} else {
			Node nodeToInsert = new Node(data);
			Node afterIterator = iterator.next;

			nodeToInsert.prev = iterator;
			nodeToInsert.next = afterIterator;

			afterIterator.prev = nodeToInsert;
			iterator.next = nodeToInsert;
			length++;
		}

	}

	/*
	 * will advance the iterator one node in the list
	 * 
	 * @precondition iterator != null
	 * 
	 * @postcondition iterator will now be equal to the next node
	 * 
	 * @throws NullPointerException when precondition is violated
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null)
			throw new NullPointerException("advance iterator: iterator is off End ");
		else
			iterator = iterator.next;
	}

	/*
	 * will move the iterator back one node in the list
	 * 
	 * @precondition iterator != null
	 * 
	 * @postcondition iterator will now be equal to the next node
	 * 
	 * @throws NullPointerException when precondition is violated
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null)
			throw new NullPointerException("reverse iterator: iterator is off End ");
		else
			iterator = iterator.prev;
	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result + "\n";
	}

	/*
	 * prints the contents of the linked list to the screen in the format #:
	 * <element> followed by a newline
	 * 
	 */
	public void printNumberedList() {
		Node toPrint = first;

		int i = 1;
		if (toPrint == null) {
			System.out.println("* Empty List *");
		}
		while (toPrint != null) {
			System.out.println(i + ". " + toPrint.data);
			i++;
			toPrint = toPrint.next;
		}
		// System.out.println();
	}

	/**
	 * Points the iterator at first and then advances it to the specified index
	 * 
	 * @param index the index where the iterator should be placed
	 * @precondition 0 < index <= length
	 * @throws IndexOutOfBoundsException when precondition is violated
	 */
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException {
		if (0 > index || index >= length) {
			throw new IndexOutOfBoundsException("iteratorToIndex: Index is out of bounds");
		} else {
			placeIterator();
			for (int i = 0; i < index; i++) {
				advanceIterator();
			}
		}
	}

	/**
	 * Searches the List for the specified value using the linear search algorithm
	 * 
	 * @param value the value to search for
	 * @return the location of value in the List or -1 to indicate not found Note
	 *         that if the List is empty we will consider the element to be not
	 *         found
	 * @postcondition position of the iterator remains unchanged
	 */
	public int linearSearch(T value) {
		Node temp = first;
		int position = 1;

		while (temp != null) {
			if (temp.data.equals(value)) {
				return position;
			}
			temp = temp.next;
			position++;
		}

		return -1;
	}
}
