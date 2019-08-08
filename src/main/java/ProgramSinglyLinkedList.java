import datastructures.SinglyLinkedList;

import java.util.Arrays;


public class ProgramSinglyLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        System.out.println("Is list empty: " + list.isEmpty());

        list.add(3);
        list.add(5);
        list.add(7);
        list.add(0);
        list.add(6);
        list.add(1);

        Integer[] arr = { 11, 13 };
        list.addAll(Arrays.asList(arr));

        Integer[] arr2 = { 3, 5, 11, 6};
        System.out.println("Contains {3, 5, 11, 6}: " + list.containsAll(Arrays.asList(arr2)));

        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("Is list empty: " + list.isEmpty());
        System.out.println("List size: " + list.size());
        System.out.println("List contains 3: " + list.contains(3));
        System.out.println("List contains -1: " + list.contains(-1));

        list.clear();
        System.out.println(list.isEmpty());
        System.out.println(list.size());
    }
}
