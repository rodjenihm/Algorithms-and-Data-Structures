import datastructures.List;

import java.util.Iterator;

public class Program {
    public static void main(String[] args) {
        List<Integer> list = new List<Integer>();
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(0);
        list.add(6);
        list.add(1);

        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("List size: " + list.size());
    }
}
