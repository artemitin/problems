package test.strings;

/*
Given a string of characters, find and return the longest palindromic substring within the input string.
 */
public class FindPalindrome {

    public static void main(String[] args) {
        boolean result = isPalindrome(12321);
        System.out.println(result);
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        return (x == reverse) || (x == reverse / 10);
    }
}
