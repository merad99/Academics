package com.merribiAdil.ArrayList;

import java.util.Arrays;

public interface SimpleArrayList {

	/**
	 * Returns the number of elements in this list. If this list contains more
	 * than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at
	 * least one element <tt>e</tt>
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	boolean contains(Object o);

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * <p>
	 * The returned array will be "safe" in that no references to it are
	 * maintained by this list. (In other words, this method must allocate a new
	 * array even if this list is backed by an array). The caller is thus free
	 * to modify the returned array.
	 *
	 * <p>
	 * This method acts as bridge between array-based and SimpleArrayList-based
	 * APIs.
	 *
	 * @return an array containing all of the elements in this list in proper
	 *         sequence
	 * @see Arrays#asList(Object[])
	 */
	Object[] toArray();

	/**
	 * Appends the specified element to the end of this list
	 *
	 * @param e
	 *            element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link SimpleArrayList#add})
	 */
	boolean add(Object e);

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present (optional operation). If this list does not contain the
	 * element, it is unchanged.
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	boolean remove(Object o);

	/**
	 * Returns <tt>true</tt> if this list contains all of the elements of the
	 * specified SimpleArrayList.
	 *
	 * @param c
	 *            SimpleArrayList to be checked for containment in this list
	 * @return <tt>true</tt> if this list contains all of the elements of the
	 *         specified SimpleArrayList
	 */
	boolean containsAll(SimpleArrayList c);

	/**
	 * Appends all of the elements in the specified SimpleArrayList to the end
	 * of this list, in the order that they are returned by the specified
	 * SimpleArrayList.
	 *
	 * @param c
	 *            SimpleArrayList containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 */
	boolean addAll(SimpleArrayList c);

	/**
	 * Inserts all of the elements in the specified SimpleArrayList into this
	 * list at the specified position. Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (increases
	 * their indices). The new elements will appear in this list in the order
	 * that they are returned by the specified SimpleArrayList's iterator. The
	 * behavior of this operation is undefined if the specified SimpleArrayList
	 * is modified while the operation is in progress.
	 *
	 * @param index
	 *            index at which to insert the first element from the specified
	 *            SimpleArrayList
	 * @param c
	 *            SimpleArrayList containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 */
	boolean addAll(int index, SimpleArrayList c);

	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified SimpleArrayList.
	 *
	 * @param c
	 *            SimpleArrayList containing elements to be removed from this
	 *            list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 */
	boolean removeAll(SimpleArrayList c);

	/**
	 * Retains only the elements in this list that are contained in the
	 * specified SimpleArrayList. In other words, removes from this list all of
	 * its elements that are not contained in the specified SimpleArrayList.
	 *
	 * @param c
	 *            SimpleArrayList containing elements to be retained in this
	 *            list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 */
	boolean retainAll(SimpleArrayList c);

	/**
	 * Removes all of the elements from this list (optional operation). The list
	 * will be empty after this call returns.
	 *
	 */
	void clear();

	/**
	 * Compares the specified object with this list for equality. Returns
	 * <tt>true</tt> if and only if the specified object is also a list, both
	 * lists have the same size, and all corresponding pairs of elements in the
	 * two lists are <i>equal</i>. In other words, two lists are defined to be
	 * equal if they contain the same elements in the same order. This
	 * definition ensures that the equals method works properly across different
	 * implementations of the <tt>SimpleArrayList</tt> interface.
	 *
	 * @param o
	 *            the object to be compared for equality with this list
	 * @return <tt>true</tt> if the specified object is equal to this list
	 */
	@Override
	boolean equals(Object o);

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 */
	Object get(int index);

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element (optional operation).
	 *
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	void set(int index, Object element);

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 */
	void add(int index, Object element);

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	 Object remove(int index);

	// Search Operations

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	int indexOf(Object o);

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	int lastIndexOf(Object o);

	/**
	 * Returns a SimpleArrayList of the portion of this list between the
	 * specified <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.
	 * (If <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list
	 * is empty.)
	 * 
	 *
	 * @param fromIndex
	 *            low endpoint (inclusive) of the subList
	 * @param toIndex
	 *            high endpoint (exclusive) of the subList
	 * @return a view of the specified range within this list
	 */
     //	Object[] subList(int fromIndex, int toIndex);
	SimpleArrayList subList(int fromIndex, int toIndex);

	/**
	 * Returns a string of all the items within the list delineated with a
	 * comma. So you can simply understand what is within this SimpleArrayList.
	 * If nothing is in the list an empty string should be returned.
	 * 
	 * @return A string indicating the items within the list.
	 */
	@Override
	String toString();
	
	void augmentArraySize();

}
