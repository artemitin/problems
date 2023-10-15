package test.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, print all possible permutations of the string.
 */
public class PrintAllPermutationsSwap {

    // This function will swap characters for every permutation
    static void swapChar(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    static void permuteStringRec(char[] input, int currentIndex, List<String> result) {
        // Return the only possible permutation for an empty String
        if(input.length == 0) {
            String addInput = "";
            result.add(addInput);
            return;
        }

        // Prevents adding duplicate permutations
        if (currentIndex == input.length - 1) {
            String addInput = new String(input);
            result.add(addInput);
            return;
        }

        for (int i = currentIndex; i < input.length; i++) {
            // swaps character for each permutation
            swapChar(input, currentIndex, i);

            // recursively calls itslef to find each permuation
            permuteStringRec(input, currentIndex + 1, result);

            // reversing swap to avoid duplicate permutations
            swapChar(input, currentIndex, i);
        }
    }

    static List<String> permuteString(String input) {
        List<String> result = new ArrayList<>();
        // Starts finding permuations from start of string
        permuteStringRec(input.toCharArray(), 0, result);
        return result;
    }

    public static void main(String[] args) {
        String[] inputs = {"ab", "bad", "abcd"};
        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            List<String> res = permuteString(input);
            System.out.println(
                    (i + 1) + ".   All permutations of " + input + ": [" + String.join(", ", res) + "]");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
    }
}
