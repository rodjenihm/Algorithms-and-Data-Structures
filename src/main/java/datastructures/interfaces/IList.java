package datastructures.interfaces;

public interface IList<E> extends ICollection<E> {
    int indexOf(Object o);
    int lastIndexOf(Object o);
    E get(int index);
    E set(int index, E element);
    IListIterator<E> listIterator();
    IListIterator<E> listIterator(int index);
}
