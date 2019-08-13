package datastructures;

public interface IList<E> extends ICollection<E> {
    void addFirst(E item);
    void addLast(E item);
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
