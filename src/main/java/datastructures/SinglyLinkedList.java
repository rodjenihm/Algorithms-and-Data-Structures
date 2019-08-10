package datastructures;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class SinglyLinkedList<E> implements Iterable<E> {
    private Node root = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(E element) {
        if (element == null) throw new NullPointerException();

        for (E e : this) {
            if (element == e) return true;
        }

        return false;
    }

    public boolean contains(E element, Comparator<E> c) {
        if (element == null) throw new NullPointerException();

        for (E e : this) {
            if (c.compare(element, e) == 0) return true;
        }

        return false;
    }

    public boolean containsAll(Collection<E> collection) {
        boolean result = true;

        for (E element : collection) {
            result &= contains(element);
        }

        return result;
    }

    public boolean containsAll(Collection<E> collection, Comparator<E> c) {
        boolean result = true;

        for (E element : collection) {
            result &= contains(element, c);
        }

        return result;
    }

    public void clear() {
        Node iterator = root;
        root = null;
        size = 0;
        while (iterator != null) {
            Node temp = iterator;
            iterator = iterator.next;
            temp.next = null;
        }
    }

    public boolean add(E element) {
        if (element == null) throw new NullPointerException();

        Node node = new Node(element, null);

        if (root != null) tail.next = node;
        else root = node;

        tail = node;
        size++;
        return true;
    }

    public boolean addAll(Collection<E> collection) {
        if (collection == null) throw new NullPointerException("Source collection is null");
        if (collection.stream().anyMatch(Objects::isNull))
            throw new NullPointerException("source collection contains null element");

        for (E element : collection) {
            add(element);
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator(root);
    }

    class SinglyLinkedListIterator implements Iterator<E> {
        Node current;

        SinglyLinkedListIterator(Node root) {
            this.current = root;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class Node {
        E element;
        Node next;

        Node() {
        }

        Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
