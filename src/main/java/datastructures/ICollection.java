package datastructures;

public interface ICollection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean add(E item);
}
