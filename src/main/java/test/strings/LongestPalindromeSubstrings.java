package test.strings;

/*
Given a string of characters, find and return the longest palindromic substring within the input string.
 */
public class LongestPalindromeSubstrings {

    public static void main(String[] args) {
        String result = longestPalindromicSubstring("abbcde");
        System.out.println(result);
    }

    // This function receives input and returns palindromes list
    public static String longestPalindromicSubstring(String s) {
        // Return an empty string if the original string itself
        // is empty or null
        if (s == null || s.isEmpty())
            return "";

        // Initializing start and end indexes of resultant substring
        // as zero
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // left side comparison
            int oddLength = findPalindromeLength(s, i, i);
            int evenLength = findPalindromeLength(s, i, i + 1);
            int maxLength = Math.max(oddLength, evenLength);

            if (maxLength > end - start) {
                // Updating start and end indexes of palindromic substring
                start = i - (maxLength - 1) / 2;
                end = i + maxLength / 2;
            }
        }//0 1 2 3 4 5
        // a b b b d e

        // Returning the longest palindromic substring
        return s.substring(start, end + 1);
    }

    private static int findPalindromeLength(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
