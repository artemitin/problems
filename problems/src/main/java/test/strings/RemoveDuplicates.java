package test.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given a string that contains duplicate occurrences of characters,
remove the duplicate occurrences such that every character in the string appears only once.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        String result = removeDuplicates("abbabcddbabcdeedebc".toCharArray());
        System.out.println(result);
    }

    static String removeDuplicates(char[] str) {
        // return if string is null or length is equal to zero
        if (str == null || str.length == 0 || str[0] == '\0') {
            return "";
        }

        // Creates a hashset
        Set<Character> hashset = new HashSet<>();

        int writeIndex = 0;
        int readIndex = 0;

        // Iterates loop till end of strinng
        while (readIndex != str.length) {
            // Add current character in hashset if it's not in set before
            if (!hashset.contains(str[readIndex])) {
                hashset.add(str[readIndex]);

                // copy character at the readIndex location to the writeIndex location within the string
                str[writeIndex] = str[readIndex];
                writeIndex++;
            }
            readIndex++;
        }

        // Returns string without duplicates
        return String.valueOf(Arrays.copyOfRange(str, 0, writeIndex));
    }

    static String removeDuplicates2(char[] str) {
        Set<Character> uniques = new HashSet<>();

        int count = 0;
        char[] noDups = new char[str.length];
        for (char c : str) {
            if (!uniques.contains(c)) {
                uniques.add(c);
                noDups[count++] = c;
            }
        }

        return String.valueOf(noDups, 0, count);
    }

    // returns the remaining string without iterated characters
    static String removeDuplicatesN2(char[] str) {
        if (str == null || str.length == 0 || str[0] == '\0') {
            return "";
        }

        int writeIndex = 0;
        for (int i = 0; i < str.length; i++) {
            boolean found = false;

            // this loop checks if current character already exists,
            // if found it breaks the loop
            for (int j = 0; j < writeIndex; j++) {
                if (str[i] == str[j]) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                str[writeIndex] = str[i];
                writeIndex++;
            }
        }

        if (writeIndex != str.length) {
            // Returns string without duplicates
            return String.valueOf(Arrays.copyOfRange(str, 0, writeIndex));
        } else {
            return String.valueOf(str);
        }
    }
}
