package datastructures;

public interface ICollection<E> extends Iterable<E> {
    boolean add(E item);
    int size();
}
