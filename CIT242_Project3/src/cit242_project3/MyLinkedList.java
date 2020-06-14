package cit242_project3;

import java.util.Arrays;

// myLinkedList extend comparable in order to quapare elements in the ordered list and add the new one in the right position
public final class MyLinkedList<E extends Comparable<E>> {

    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    // Create an empty list
    public MyLinkedList() {
    }

    /*
     * Create a list from an array of objects with the new add function methode that only loops once
     */
    public MyLinkedList(E[] objects) {
        addUpdated(objects);
    }

    /*
     * Return the head element in the list
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /*
     * Return the last element in the list
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /*
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node

        if (head != null) {
            if ((head.element).equals(newNode.element)) { // check if the object equals to the previous one increment nodeCount
                head.addNodeCount();
            } else {
                newNode.next = head; // link the new node with the head 
                head = newNode; // head points to the new node
                size++; // Increase list size
            }
        } else {
            newNode.next = head; // link the new node with the head 
            head = newNode; // head points to the new node
            size++; // Increase list size
        }
        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }
    }

    /*
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            if ((tail.element).equals(newNode.element)) {
                tail.addNodeCount();
            } else {
                tail.next = newNode; // Link the new with the last node
                tail = newNode; // tail now points to the last node
                size++; // Increase size

            }
        }

    }

    /*
     * Add a new element at the specified index in this list. The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);// next = Algiers
            (current.next).next = temp;
            size++;
        }
    }

    // Methode that get an arrays of objecta and add it to the linked list
    public void addUpdated(E[] objects) {
        Arrays.sort(objects); // sort array

        for (int i = 0; i < objects.length; i++) {
            if (i == 0) {
                addFirst(objects[i]);
            } else {

                if (!tail.element.equals(objects[i])) {

                    addLast(objects[i]);
                } else {
                    tail.addNodeCount();
                }
            }
        }
    }

    // Methode that inserts an element in the already ordered list
    public void addUpdated(E e) {
        Node<E> current2 = head;
        Node<E> temp = new Node<>(e);
        int i = 0;
        while ((temp.element).compareTo(current2.next.element) >= 0 && i < size) {
            if (i > 1) {
                current2 = current2.next;
            }
            i++;
        }
        if ((current2.element).equals(temp.element)) {
            current2.addNodeCount();
        } else {
            if (i == 0) {
                addFirst(e);
            } else if (i >= size) {
                addLast(e);
            } else {

                temp = current2.next;
                current2.next = new Node<>(e);
                (current2.next).next = temp;
                size++;
            }
        }
    }

    /*
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            E temp = head.element;
            if (head.getNodeCount() == 1) {

                head = head.next;
                size--;
                if (head == null) {
                    tail = null;
                }
            } else {
                head.decrementNodeCount();
            }
            return temp;
        }
    }

    /*
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        switch (size) {
            case 0:
                return null;
            case 1: {
                E temp = head.element;
                if (head.getNodeCount() == 1) {

                    head = tail = null;
                    size = 0;
                } else {
                    head.decrementNodeCount();
                }
                return temp;

            }
            default: {

                Node<E> current = head;

                for (int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                E temp = tail.element;

                if (current.next.getNodeCount() == 1) {
                    tail = current;
                    tail.next = null;
                    size--;
                } else {
                    current.next.decrementNodeCount();
                }
                return temp;
            }
        }
    }

    /*
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;

        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            if ((previous.next).getNodeCount() == 1) {
                Node<E> current = previous.next;
                previous.next = current.next;
                size--;
                return current.element;

            } else {
                previous.next.decrementNodeCount();
            }
            return previous.element;
        }
    }

    @Override
    /*
     * Override toString() to return elements in the list
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element).append("<").append(current.getNodeCount()).append(">");

            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /*
     * Clear the list
     */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    /*
     * Return true if this list contains the element e
     */
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(get(i))) {
                return true;
            }
        }
        return false;
    }

    /*
     * Return the element at the specified index
     */
    public E get(int index) {
        if (index > size) {
            return null;
        }
        int count = 0;
        Node<E> node = head;
        while (count != index) {
            node = node.next;
            count++;
        }
        return node.element;
    }

    /*
     * Return the index of the head matching element in this list. Return -1 if
     * no match.
     */
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Return the index of the last matching element in this list. Return -1 if
     * no match.
     */
    public int lastIndexOf(E e) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(get(i))) {
                index = i;
            }
        }
        return index;
    }

    /*
     * Replace the element at the specified position in this list with the
     * specified element.
     */
    public E set(int index, E e) {
        return null;
    }

    /*
     * Override iterator() defined in Iterable
     */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator
            implements java.util.Iterator<E> {

        private Node<E> current = head; // Current index 

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            // Left as an exercise
        }
    }

    private static class Node<E> {

        E element;
        Node<E> next;
        int nodeCount = 1;

        public Node(E element) {
            this.element = element;
        }

        public void addNodeCount() {
            nodeCount++;
        }

        public void decrementNodeCount() {
            nodeCount--;
        }

        public int getNodeCount() {
            return nodeCount;
        }

    }

    /*
     * Return the number of elements in this list
     */
    public int size() {
        return size;
    }
}
