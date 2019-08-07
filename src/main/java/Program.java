import datastructures.SinglyLinkedList;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Program {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        System.out.println("Is list empty: " + list.isEmpty());

        list.add(3);
        list.add(5);
        list.add(7);
        list.add(0);
        list.add(6);
        list.add(1);

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
