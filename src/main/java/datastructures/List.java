package datastructures;

import java.util.Iterator;

public class List<Type> implements Iterable<Type> {
    private Node root = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public void add(Type value) {
        Node node = new Node();
        node.value = value;
        node.next = null;

        if (root != null) {
            tail.next = node;
            tail = node;
        } else {
            root = node;
            tail = node;
        }
        size++;
    }

    public Iterator<Type> iterator() {
        return new ListIterator(root);
    }

    class ListIterator implements Iterator<Type>{
        Node current;

        ListIterator (Node root) {
            this.current = root;
        }

        public boolean hasNext() {
            return  current != null;
        }

        public Type next() {
            Type value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class Node {
        Type value;
        Node next;
    }
}
