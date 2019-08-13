package datastructures;

public interface ICollection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    void clear();
    boolean add(E item);
}
