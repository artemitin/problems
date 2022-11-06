package test.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given a null-terminated string, remove all the white spaces (tabs or spaces) present in the string.
 */
public class RemoveWhitespaces {

    public static void main(String[] args) {
        String result = removeWhiteSpaces("All greek to me. ".toCharArray());
        System.out.println(result);
    }

    static String removeWhiteSpaces(char[] s) {
        int read = 0;
        int write = 0;

        while (read < s.length) {
            if (!Character.isWhitespace(s[read])) {
                s[write++] = s[read];
            }
            read++;
        }

        return String.valueOf(Arrays.copyOfRange(s, 0, write));
    }
}
