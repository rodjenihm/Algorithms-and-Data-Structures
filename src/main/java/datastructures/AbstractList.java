package datastructures;

import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

import java.util.Iterator;

public abstract class AbstractList<E> implements IList<E> {
    protected int size;

    protected boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public IListIterator<E> listIterator() {
        return listIterator(0);
    }
}
