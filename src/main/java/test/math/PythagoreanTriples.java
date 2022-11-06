package test.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array, find all Pythagorean triples. а^2 + b^2 = c^2
 */
public class PythagoreanTriples {

    public static void main(String[] args) {
        int[] arr = {3,-1, 2, 5, 4};
        List<int[]> result = findPythagoreanTriples(arr);
        System.out.println(result);
    }

    static List<int[]> findPythagoreanTriples(int[] arr) {
        List<int[]> triples = new ArrayList<>();

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    if (arr[i] * arr[i] + arr[j] * arr[j] == arr[k] * arr[k]) {
                        triples.add(new int[]{arr[i], arr[j], arr[k]});
                    }
                }
            }
        }
        return triples;
    }

}
