package test.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string of characters, find and return the longest palindromic substring within the input string.
 */
public class RegexMatch {

    // Top down memoization
    // Memoization -> cache intermediate
    static Map<String, Boolean> cache = new HashMap<>();

    // marker i for text string and marker j for pattern string
    public static boolean dp(String text, String pattern, int i, int j) {
        String ijPair = String.valueOf(i) + String.valueOf(j);
        if (cache.containsKey(ijPair)) {
            return cache.get(ijPair);
        }

        // Base case 1
        // If i is out of bounds and j is out of bounds, then we have found our answer
        if (i >= text.length() && j >= pattern.length()) {
            return true;
        }

        // Base case 2
        // If i is not out of bounds but j is out of bound then return false,
        // there are some characters in text string that we haven't matched.
        if (j >= pattern.length()) {
            return false;
        }

        // The bool value of match will be True if there is a match otherwise it will be False
        boolean match = (i < text.length() && // For match, the ith index must be less than the
                // length of the text string
                (text.charAt(i) == pattern.charAt(j) || // match between text[i] and pattern[j]
                        pattern.charAt(j) == '.')); // current pattern character is the "."
        // wildcard, so any character in the text is
        // acceptable"


        // If the character present on index (j+1) in pattern string is a "*"
        if ((j + 1) < pattern.length() && pattern.charAt(j + 1) == '*') {
            // If either of them evaluates to True then we return true
            // Skipping "*" and recursively calling the dp function with the advanced indices
            Boolean bool1 = dp(text, pattern, i, j + 2);
            // use *, remember that we can only use the "*" if there is a match
            Boolean bool2 = (match && dp(text, pattern, i + 1, j));

            Boolean newBool = bool1 || bool2;
            if (cache.containsKey(ijPair)) {
                cache.replace(ijPair, newBool);
            } else {
                cache.put(ijPair, newBool);
            }
            return cache.get(ijPair);
        }

        // If we do not have a "*" character, then we are only looking for a match
        if (match) {
            if (cache.containsKey(ijPair)) {
                cache.replace(ijPair, dp(text, pattern, i + 1, j + 1));
            } else {
                cache.put(ijPair, dp(text, pattern, i + 1, j + 1));
            }
            return cache.get(ijPair);
        }

        // If there is neither a "*" nor a match, then we simply store False in cache
        if (cache.containsKey(ijPair)) {
            cache.replace(ijPair, false);
        } else {
            cache.put(ijPair, false);
        }

        return false;
    }

    public static boolean regxMatch(String text, String pattern) {
        cache.clear();
        return dp(text, pattern, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println("regxMatch(\"aa\", \"a\"): " + regxMatch("aa", "a"));
        System.out.println("regxMatch(\"aa\", \"a*\"): " + regxMatch("aa", "a*"));
        System.out.println("regxMatch(\"ab\", \".*\"): " + regxMatch("ab", ".*"));
        System.out.println("regxMatch(\"aab\", \"c*a*b\"): " + regxMatch("aab", "c*a*b"));
        System.out.println(
                "regxMatch(\"mississippi\", \"mis*is*p*.\"): " + regxMatch("mississippi", "mis*is*p*."));
    }
}
