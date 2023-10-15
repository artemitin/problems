package test.strings;

import java.util.ArrayList;
import java.util.List;

/*
Given a string, find all non-single letter substrings that are palindromes.
A palindrome is a word, phrase, number, or other sequence of characters that reads the same backwards as it reads forwards.
 */
public class FindPalindromeSubstrings {

    public static void main(String[] args) {
        List<String> result = findAllPalindromeSubstrings("aabbbaa");
        System.out.println(result);
    }

    // This function receives input and returns palindromes list
    public static List<String> findAllPalindromeSubstrings(String input) {

        List<String> palindromes = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            // left side comparison
            findPalindromesInSubString(input, i - 1, i + 1, palindromes);
            // right side comparison
            findPalindromesInSubString(input, i, i + 1, palindromes);
        }
        // TODO: Write - Your - Code
        return palindromes;
    }

    private static void findPalindromesInSubString(String input, int left, int right, List<String> result) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            result.add(input.substring(left, right + 1));
            left--;
            right++;
        }
    }
}
