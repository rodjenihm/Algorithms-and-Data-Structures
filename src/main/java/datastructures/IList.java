package datastructures;

public interface IList<E> extends ICollection<E> {
    int indexOf(Object o);
    int lastIndexOf(Object o);
    Object[] toArray();
    E get(int index);
}
