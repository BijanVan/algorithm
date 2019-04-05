package com.bijansoft;

import java.util.Objects;

class Misc {

    private static class ListNode<T> {
        T data;
        ListNode<T> next;

        @Override
        public String toString() {
            ListNode<T> node = this;
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.data);
                if (node.next != null)
                    sb.append("->");
                node = node.next;
            }

            return sb.toString();
        }
    }

    private static ListNode<Integer> sumListInteger(ListNode<Integer> num1,
            ListNode<Integer> num2) {
        int num1Int = listToInt(num1);
        int num2Int = listToInt(num2);
        return intoList(num1Int + num2Int);
        // return intoListReverse(num1Int + num2Int);
    }

    private static int listToInt(ListNode<Integer> num) {
        Objects.requireNonNull(num);

        int exp10 = 1;
        int value = 0;
        ListNode<Integer> iter = num;
        while (iter != null) {
            value += iter.data * exp10;
            exp10 *= 10;
            iter = iter.next;
        }

        return value;
    }

    // private static ListNode<Integer> intoList(int num) {

    // }

    private static ListNode<Integer> intoListReverse(int num) {
        ListNode<Integer> node = new ListNode<>();
        node.data = num % 10;
        int div = num / 10;
        if (div > 0) {
            node.next = intoListReverse(div);
        } else
            node.next = null;

        return node;
    }

    public static void main(String[] args) {
        ListNode<Integer> digit3 = new ListNode<>();
        digit3.data = 3;
        digit3.next = null;
        ListNode<Integer> digit2 = new ListNode<>();
        digit2.data = 2;
        digit2.next = digit3;
        ListNode<Integer> digit1 = new ListNode<>();
        digit1.data = 1;
        digit1.next = digit2;

        ListNode<Integer> digit6 = new ListNode<>();
        digit6.data = 6;
        digit6.next = null;
        ListNode<Integer> digit5 = new ListNode<>();
        digit5.data = 5;
        digit5.next = digit6;
        ListNode<Integer> digit4 = new ListNode<>();
        digit4.data = 4;
        digit4.next = digit5;

        // System.out.println(listToInt(digit4));

        ListNode<Integer> sum = sumListInteger(digit1, digit4);
        System.out.println(sum);

    }
}
