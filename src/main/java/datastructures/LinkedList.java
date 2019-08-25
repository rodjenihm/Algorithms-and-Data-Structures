package datastructures;

import datastructures.interfaces.ICollection;
import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

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

    private void linkFirst(E item) {
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
    }

    private void linkLast(E item) {
        Node newNode = new Node(item);
        if (root == null) {
            linkFirst(item);
        } else {
            newNode.prev = tail;
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        }
    }

    private void unlinkFirst() {
        Node temp = root.next;
        root.prev = null;
        root.next = null;
        if (temp != null)
            temp.prev = null;
        else
            tail = null;
        root = temp;
    }

    private void unlinkLast() {
        Node temp = tail.prev;
        if (temp == null) {
            unlinkFirst();
        } else {
            temp.next = null;
            tail.prev = null;
            tail = temp;
        }
    }

    private void unlink(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
        node.item = null;
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
        linkFirst(item);
        size++;
    }

    public void addLast(E item) {
        linkLast(item);
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

    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        E item = root.item;
        unlinkFirst();
        size--;
        return item;
    }

    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        E item = tail.item;
        unlinkLast();
        size--;
        return item;
    }

    @Override
    public boolean remove(Object o) {
        int indexToRemove = indexOf(o);
        if (indexToRemove == -1)
            return false;
        if (indexToRemove == 0)
            unlinkFirst();
        else if (indexToRemove == size - 1)
            unlinkLast();
        else
            unlink(nodeAtIndex(indexToRemove));
        size--;
        return true;
    }

    @Override
    public E remove(int index) {
        if (!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        E item;
        if (index == 0) {
            item = root.item;
            unlinkFirst();
        } else if (index == size - 1) {
            item = tail.item;
            unlinkLast();
        } else {
            Node toRemove = nodeAtIndex(index);
            item = toRemove.item;
            unlink(toRemove);
        }
        size--;
        return item;
    }

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
        if (!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        return nodeAtIndex(index).item;
    }

    @Override
    public E set(int index, E element) {
        if (!isValidIndex(index))
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
        if (!isValidIteratorPosition(index))
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

        LinkedListIterator(LinkedList<E> linkedList, int index) {
            this.linkedList = linkedList;
            iteratorPrev = null;
            iteratorNext = linkedList.root;
            while (index-- > 0) {
                iteratorPrev = iteratorNext;
                iteratorNext = iteratorNext.next;
            }
        }

        @Override
        public boolean hasNext() {
            return iteratorNext != null;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E item = iteratorNext.item;
            iteratorPrev = iteratorNext;
            iteratorNext = iteratorNext.next;
            return item;
        }

        @Override
        public boolean hasPrevious() {
            return iteratorPrev != null;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            E item = iteratorPrev.item;
            iteratorNext = iteratorPrev;
            iteratorPrev = iteratorPrev.prev;
            return item;
        }
    }
}
