package test.arrays;

import java.util.*;

/**
 * Given an array of integers,
 * find the largest number that can be made by creating all possible permutations of these integers.
 * <p>
 * As the largest number formed can be very large, Return a string instead of an integer.
 */
public class Test {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        List<Integer> arrlist = new ArrayList<>();

        HashSet<String> set = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();


        HashMap<String, Integer> map = new HashMap<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        map.put("abc", 2);
        map.get("Abc");

//        System.out.println(reverse(1534236469));

        int cap = 5;
        System.out.println("cap = " + cap);
        for (int i = 0; i < 30; i++) {
//            System.out.println("i = " + i);
            System.out.println("i = " + i + "; i % cap = " + (i % cap) + ";  i & cap-1 = " + (i & (cap - 1)));
//            System.out.println("is even? " + ((i & 1)));
        }
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop > -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static boolean isPalindrome(int x) {
        // Special cases:
        // when x < 0, x is not a palindrome.
        // if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palindrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
