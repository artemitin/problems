package test.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of 'n' elements, find their kth permutation.
 */
public class FindKthPermutation {

    public static void main(String[] args) {
        String result = getPermutation(4, 8);
        System.out.println(result);
    }

    static String getPermutation(int n, int k) {
        List<Character> v = new ArrayList<>();
        // Constructing the permutation number
        char c = '1';
        for (char i = 1; i <= n; i++) {
            v.add(c++);
        }

        // Finding factorial and storing it
        List<Integer> factorial = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                factorial.add(1);
            } else {
                factorial.add(i * factorial.get(i - 1));
            }
        }

        StringBuilder result = new StringBuilder();
        findKthPermutation(v, k, result, factorial);
        return result.toString();
    }

    static void findKthPermutation(List<Character> v, int k, StringBuilder result, List<Integer> factorial) {
        if (v.isEmpty()) {
            return;
        }
        int n = v.size();

        // factorial is number of permutations starting with first digit,
        // selected is the number of digits in permutation
        int selected = (k - 1) / factorial.get(n - 1);

        // Get the first digit from selected array
        result.append(v.get(selected));
        v.remove(selected);
        k = k - (factorial.get(n - 1) * selected);

        // Recursively calling itself
        findKthPermutation(v, k, result, factorial);
    }

}
