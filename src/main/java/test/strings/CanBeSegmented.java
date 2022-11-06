package test.strings;

import java.util.Set;

/*
Given a string of characters, find and return the longest palindromic substring within the input string.
 */
public class CanBeSegmented {

    public static void main(String[] args) {
        boolean result = canSegmentString("applepie", Set.of(""));
        System.out.println(result);
    }

    public static boolean canSegmentString(String inputString, Set<String> dictionary) {
        for (int i = 1; i <= inputString.length(); ++i) {
            String first = inputString.substring(0, i);

            // check if the first part exists in the dictionary
            if (dictionary.contains(first)) {
                String second = inputString.substring(i);

                // check if the second part exists in the dictionary
                if (dictionary.contains(second)) {
                    return true;
                }

                // Recursive call
                if (canSegmentString(second, dictionary)) {
                    return true;
                }
            }
        }
        return false;
    }
}
