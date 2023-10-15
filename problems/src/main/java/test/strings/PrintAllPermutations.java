package test.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, print all possible permutations of the string.
 */
public class PrintAllPermutations {

    public static void main(String[] args) {
        List<String> result = permuteString("bad");
        System.out.println(result);
    }

    static List<String> permuteString(String input) {
        List<String> result = new ArrayList<>();
        if (input == null || input.length() == 0) {
            result.add("");
            return result;
        }
        if (input.length() == 1) {
            result.add(input);
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                List<String> tmp = permuteString(input.substring(0, i) + input.substring(i + 1));
                for (String s : tmp) {
                    result.add(c + s);
                }
            }
        }
        return result;
    }
}
