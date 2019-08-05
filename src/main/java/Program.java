import datastructures.SinglyLinkedList;

public class Program {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
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
