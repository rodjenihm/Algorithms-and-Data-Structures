package datastructures;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayList<E> implements IList<E> {

    //region "Private fields"
    private final static Object[] emptyArray = {};
    private Object[] elements;
    private int capacity;
    private int size = 0;
    //endregion

    //region "Constructors"
    public ArrayList() {
        this(0);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("ArrayList capacity illegal: " + initialCapacity);
        if (initialCapacity > 0)
            elements = new Object[initialCapacity];
        else
            elements = emptyArray;
        capacity = initialCapacity;
        size = 0;
    }
    //endregion

    //region "Private methods"
    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }
    //endregion

    //region "Public methods"
    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public E get(int index) {
        if (!isIndexValid(index))
            throw new IndexOutOfBoundsException();
        return (E)elements[index];
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
        return false;
    }

    @Override
    public void clear() {
        for (int idx = 0; idx < elements.length; idx++)
            elements[idx] = null;
        size = 0;
    }

    @Override
    public boolean add(E item) {
        return false;
    }

    @Override
    public boolean addAll(ICollection<? extends E> c) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
    //endregion
}
