package datastructures;

import datastructures.interfaces.ICollection;
import datastructures.interfaces.IList;
import datastructures.interfaces.IListIterator;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<E> implements IList<E> {
    //region "Private fields"
    private ArrayList<E> list;
    //endregion

    //region "Constructors"
    public Stack() {
        list = new ArrayList<>();
    }

    public Stack(int capacity) {
        list = new ArrayList<>(capacity);
    }
    //endregion

    //region "Public methods"
    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
    }

    @Override
    public IListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public IListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public int size() {
        return list.size;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean add(E item) {
        return list.add(item);
    }

    @Override
    public boolean addAll(ICollection<? extends E> c) {
        return list.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public E push(E item) {
        list.add(item);
        return item;
    }

    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.get(size() - 1);
    }

    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.remove(size() - 1);
    }
    //endregion
}
