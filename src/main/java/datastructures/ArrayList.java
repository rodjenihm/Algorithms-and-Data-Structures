package datastructures;

import datastructures.interfaces.ICollection;
import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

import java.util.NoSuchElementException;


public class ArrayList<E> extends AbstractList<E> implements IList<E> {

    //region "Private fields"
    private final static int defaultCapacity = 10;
    private Object[] elements;
    private int capacity;
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

    public ArrayList(ICollection<? extends E> c) {
        this();
        addAll(c);
    }
    //endregion

    //region "Private methods"
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
        if (!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        elements[index] = element;
        return element;
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
    public boolean remove(Object o) {
        int indexToRemove = indexOf(o);
        if (indexToRemove == -1)
            return false;
        System.arraycopy(elements, indexToRemove + 1, elements, indexToRemove, size - 1 - indexToRemove);
        size--;
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] output = new Object[size];
        System.arraycopy(elements, 0, output, 0, size);
        return output;
    }

    @Override
    public IListIterator<E> listIterator(int index) {
        if (!isValidIteratorPosition(index))
            throw new IndexOutOfBoundsException();
        return new ArrayListIterator(this, index);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            allocateNewArray();
            capacity = minCapacity;
        }
    }
    //endregion

    private class ArrayListIterator implements IListIterator<E> {

        private Object[] elements;
        private int size;
        private int idxNext;
        private int idxPrev;

        ArrayListIterator(ArrayList arrayList, int index) {
            this.elements = arrayList.elements;
            size = arrayList.size;
            idxNext = index;
            idxPrev = index - 1;
        }

        @Override
        public boolean hasNext() {
            return idxNext < size;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return (E) elements[idxNext++];
        }

        @Override
        public boolean hasPrevious() {
            return idxPrev > -1;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            return (E) elements[idxPrev--];
        }
    }
}
