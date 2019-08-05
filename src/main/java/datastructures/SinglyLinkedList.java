package datastructures;

import java.util.Iterator;

public class SinglyLinkedList<Type> implements Iterable<Type> {
    private Node root = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public void add(Type value) {
        Node node = new Node(value, null);

        if (root != null) tail.next = node;
        else root = node;

        tail = node;
        size++;
    }

    public Iterator<Type> iterator() {
        return new SinglyLinkedListIterator(root);
    }

    class SinglyLinkedListIterator implements Iterator<Type>{
        Node current;

        SinglyLinkedListIterator (Node root) {
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

        Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
