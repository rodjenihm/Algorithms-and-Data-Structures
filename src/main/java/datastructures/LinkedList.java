package datastructures;

import datastructures.interfaces.ICollection;
import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractList<E> implements IList<E> {

    //region "Private fields"
    private Node root;
    private Node tail;
    //endregion

    //region "Constructors"
    public LinkedList() {
        size = 0;
        root = null;
        tail = null;
    }

    public LinkedList(ICollection<? extends E> c) {
        this();
        addAll(c);
    }
    //endregion

    //region "Private methods"
    private Node nodeAtIndex(int index) {
        if (index < size / 2) {
            Node iterator = root;
            for (int i = 0; i < index; i++)
                iterator = iterator.next;
            return iterator;
        } else {
            Node iterator = tail;
            for (int i = size - 1; i > index; i--)
                iterator = iterator.prev;
            return iterator;
        }
    }
    //endregion

    //region "Public methods"
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
            root.prev = newNode;
            root = newNode;
        }
        size++;
    }

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

    @Override
    public boolean addAll(ICollection<? extends E> c) {
        if (c == null)
            return false;
        for (E item : c)
            add(item);
        return true;
    }
    //endregion

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            Node iterator = root;
            while (iterator != null) {
                if (iterator.item == null)
                    return index;
                index++;
                iterator = iterator.next;
            }
        } else {
            Node iterator = root;
            while (iterator != null) {
                if (o.equals(iterator.item))
                    return index;
                index++;
                iterator = iterator.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o == null) {
            Node iterator = tail;
            while (iterator != null) {
                if (iterator.item == null)
                    return index;
                index--;
                iterator = iterator.prev;
            }
        } else {
            Node iterator = tail;
            while (iterator != null) {
                if (o.equals(iterator.item))
                    return index;
                index--;
                iterator = iterator.prev;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Object[] output = new Object[size];
        int idx = 0;
        Node iterator = root;
        while (iterator != null) {
            output[idx++] = iterator.item;
            iterator = iterator.next;
        }
        return output;
    }

    @Override
    public E get(int index) {
        if (!isIndexValid(index))
            throw new IndexOutOfBoundsException();
        return nodeAtIndex(index).item;
    }

    @Override
    public E set(int index, E element) {
        if (!isIndexValid(index))
            throw new IndexOutOfBoundsException();
        nodeAtIndex(index).item = element;
        return element;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    @Override
    public IListIterator<E> listIterator(int index) {
        if (!isIndexValid(index))
            throw new IndexOutOfBoundsException();
        return new LinkedListIterator(this, index);
    }
    //endregion

    private class Node {

        E item;
        Node prev;
        Node next;

        Node(E item) {
            this.item = item;
            prev = null;
            next = null;
        }

        Node(E item, Node prev, Node next) {
            this(item);
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListIterator implements IListIterator<E> {

        private final LinkedList<E> linkedList;
        private Node iteratorNext;
        private Node iteratorPrev;
        private int idx;

        LinkedListIterator(LinkedList<E> linkedList, int index) {
            this.linkedList = linkedList;
            idx = index;
            iteratorNext = linkedList.root;
            while (index-- > 0)
                iteratorNext = iteratorNext.next;
            iteratorPrev = iteratorNext.prev;
        }

        @Override
        public boolean hasNext() {
            return idx < linkedList.size;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E value = iteratorNext.item;
            iteratorPrev = iteratorNext;
            iteratorNext = iteratorNext.next;
            idx++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return idx > -1;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            E value = iteratorPrev.item;
            iteratorNext = iteratorPrev;
            iteratorPrev = iteratorPrev.prev;
            idx--;
            return value;
        }
    }
}
