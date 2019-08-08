package datastructures;

import java.util.EmptyStackException;

public class Stack<E> {
    private Node stackPointer = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean empty() {
        return stackPointer == null;
    }

    public E peek() {
        if (size == 0) throw new EmptyStackException();
        return stackPointer.element;
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();

        Node temp = stackPointer;
        stackPointer = stackPointer.next;
        E popped = temp.element;
        temp.next = null;
        size--;

        return popped;
    }

    public E push(E element) {
        Node node = new Node(element, null);

        if (stackPointer == null) stackPointer = node;
        else {
            node.next = stackPointer;
            stackPointer = node;
        }

        size++;
        return element;
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
