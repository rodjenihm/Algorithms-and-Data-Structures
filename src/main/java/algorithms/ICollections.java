package algorithms;

import datastructures.interfaces.ICollection;

import java.util.Comparator;
import java.util.Iterator;

public class ICollections {

    //region "max"
    public static <T extends Comparable> T max(ICollection<T> c) {
        Iterator<T> it = c.iterator();
        T max = it.next();
        while (it.hasNext()) {
            T t = it.next();
            if (t.compareTo(max) > 0)
                max = t;
        }
        return max;
    }

    public static <T> T max(ICollection<T> c, Comparator<T> comp) {
        Iterator<T> it = c.iterator();
        T max = it.next();
        while (it.hasNext()) {
            T t = it.next();
            if (comp.compare(t, max) > 0)
                max = t;
        }
        return max;
    }
    //endregion

    //region "min"
    public static <T extends Comparable> T min(ICollection<T> c) {
        Iterator<T> it = c.iterator();
        T min = it.next();
        while (it.hasNext()) {
            T t = it.next();
            if (t.compareTo(min) < 0)
                min = t;
        }
        return min;
    }

    public static <T> T min(ICollection<T> c, Comparator<T> comp) {
        Iterator<T> it = c.iterator();
        T min = it.next();
        while (it.hasNext()) {
            T t = it.next();
            if (comp.compare(t, min) < 0)
                min = t;
        }
        return min;
    }
    //endregion
}
