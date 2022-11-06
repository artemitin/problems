package test.test;

import java.util.Arrays;
import java.util.Random;

/**
 * Dining philosophers
 */
public class Ttt {

    public static void main(String[] args) throws Exception {
        String randomString = new Random().ints(97, 123)
                .limit(10)
                .collect(StringBuilder::new, (sb, v) -> sb.append((char) v), StringBuilder::append).toString();

        System.out.println(randomString);

        int n = 5;
        for (int i = 0; i < n; i++) {
            System.out.println((i + 4) % 5);
            System.out.println((i + 4) & 4);
        }
    }

    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;

        for (int num : nums) {
            if (num <= k) {
                k++;
                sum += num;
            }
        }

        return (long) (1 + k) * k / 2 - sum;
    }
}