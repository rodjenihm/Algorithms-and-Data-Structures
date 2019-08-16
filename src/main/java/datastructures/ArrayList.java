package datastructures;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayList<E> implements IList<E> {

    //region "Private fields"
    private static Object[] emptyArray = {};
    private Object[] elements ;
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
        if (initialCapacity > 0)
            elements = new Object[initialCapacity];
        else
            elements = emptyArray;
        capacity = initialCapacity;
        size = 0;
    }
    //endregion

    //region "Private methods"

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
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public void clear() {

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
