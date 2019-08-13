package datastructures;

import java.util.Iterator;

public class LinkedList<E> implements IList<E> {

    private int size;
    private Node root;
    private Node tail;

    public LinkedList() {
        size = 0;
        root = null;
        tail = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        Node iterator = root;
        root = null;
        tail = null;
        while (iterator != null) {
            Node temp = iterator;
            temp.prev = null;
            temp.item = null;
            iterator = iterator.next;
            temp.next = null;
        }
        size = 0;
    }

    //region "Add element"
    @Override
    public void addFirst(E item) {
        Node newNode = new Node(item);
        if (root == null) {
            newNode.prev = null;
            newNode.next = null;
            root = newNode;
            tail = newNode;
        } else {
            newNode.prev = null;
            newNode.next = root;
            root = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E item) {
        Node newNode = new Node(item);
        if (root == null) {
            newNode.prev = null;
            newNode.next = null;
            root = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public boolean add(E item) {
        addLast(item);
        return true;
    }
    //endregion

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(this);
    }

    private class Node {

        E item;
        Node prev;
        Node next;

        Node(E item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }

        Node(E item, Node prev, Node next) {
            this(item);
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<E> {

        private final LinkedList<E> linkedList;
        private Node iterator;
        private int idx;

        LinkedListIterator(LinkedList<E> linkedList) {
            this.linkedList = linkedList;
            this.iterator = linkedList.root;
            this.idx = -1;
        }

        @Override
        public boolean hasNext() {
            return ++idx < linkedList.size;
        }

        @Override
        public E next() {
            if (idx > 0) iterator = iterator.next;
            return iterator.item;
        }
    }
}
