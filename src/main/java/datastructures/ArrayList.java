package datastructures;

import datastructures.interfaces.ICollection;
import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

import java.util.Iterator;

public class ArrayList<E> implements IList<E> {

    //region "Private fields"
    private final static int defaultCapacity = 10;
    private Object[] elements;
    private int capacity;
    private int size;
    //endregion

    //region "Constructors"
    public ArrayList() {
        this(0);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("ArrayList capacity illegal: " + initialCapacity);
        if (initialCapacity == 0) {
            elements = new Object[defaultCapacity];
            capacity = defaultCapacity;
        } else {
            elements = new Object[initialCapacity];
            capacity = initialCapacity;
        }
        size = 0;
    }
    //endregion

    //region "Private methods"
    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    private boolean isArrayFull() {
        return size >= capacity;
    }

    private void allocateNewArray() {
        capacity <<= 1;
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        for (int idx = 0; idx < size; idx++)
            elements[idx] = null;
        elements = newArray;
    }
    //endregion

    //region "Public methods"
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elements[index] == null)
                    return index;
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elements[index]))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int idx = size - 1; idx >= 0; idx--) {
                if (elements[idx] == null)
                    return idx;
            }
        } else {
            for (int idx = size - 1; idx >= 0; idx--) {
                if (o.equals(elements[idx]))
                    return idx;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (isIndexValid(index))
            return (E) elements[index];
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public void clear() {
        for (int idx = 0; idx < elements.length; idx++)
            elements[idx] = null;
        size = 0;
    }

    @Override
    public boolean add(E item) {
        if (isArrayFull())
            allocateNewArray();
        elements[size++] = item;
        return true;
    }

    @Override
    public boolean addAll(ICollection<? extends E> c) {
        for (E item : c)
            add(item);
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] output = new Object[size];
        System.arraycopy(elements, 0, output, 0, size);
        return output;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator(this);
    }

    //endregion
    private class ArrayListIterator implements IListIterator<E> {

        private Object[] elements;
        private int index;

        ArrayListIterator(ArrayList arrayList) {
            this.elements = arrayList.elements;
            index = -1;
        }

        @Override
        public boolean hasNext() {
            return index + 1 < size;
        }

        @Override
        public E next() {
            return (E) elements[++index];
        }
    }
}
