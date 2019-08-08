import datastructures.Stack;

public class ProgramStack {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        System.out.println("Is stack empty? " + st.empty());
        st.push(4);
        st.push(3);
        System.out.println("Stack size: " + st.size());

        System.out.println("Stack peek: " + st.peek());
        System.out.println("Stack size after peek: " + st.size());
        System.out.println("Stack peek: " + st.pop());
        System.out.println("Stack size after pop: " + st.size());
        Integer lastOnTheStack = st.pop();
        System.out.println("Stack size after pop: " + st.size());
        System.out.println("Is stack empty? " + st.empty());
    }
}
