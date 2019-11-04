package edu.olezha.sandbox.collections;

import java.util.Stack;

public class DeleteMiddleElementOfStackWithoutUsingDataStructure {

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        for (int i = 1; i < 8; i++)
            stack.push(Character.forDigit(i, 10));

        System.out.println(stack);

        do {
            deleteMiddleElement(stack);
            System.out.println(stack);
        }
        while (!stack.isEmpty());
    }

    private static void deleteMiddleElement(Stack<Character> stack) {
        if (stack == null || stack.isEmpty())
            return;

        int middle = stack.size() / 2;

        Stack<Character> top = new Stack<>();
        while (middle > 0) {
            top.push(stack.pop());
            middle--;
        }

        stack.pop();

        while (top.size() > 0)
            stack.push(top.pop());
    }
}
