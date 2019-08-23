package datastructures;

import datastructures.interfaces.IList;

public abstract class AbstractList<E> implements IList<E> {
    protected int size;

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
}
