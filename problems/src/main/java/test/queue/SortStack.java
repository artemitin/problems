package test.queue;

import java.util.*;

public class SortStack {
    //push pop peek isEmpty
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        new Random().ints(10, 0, 100)
                .distinct()
                .forEach(stack::push);

        System.out.println(stack);

        Stack<Integer> sorted = sortStack(stack);

        System.out.println(sorted);
    }

    private static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> result = new Stack<>();

        while(!stack.isEmpty()) {
            Integer topUnsorted = stack.pop();

            while(!result.isEmpty() && result.peek() > topUnsorted) {
                stack.push(result.pop());
            }
            result.push(topUnsorted);
        }

        return result;
    }
}
