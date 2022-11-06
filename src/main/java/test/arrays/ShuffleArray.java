package test.arrays;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an integer array nums, write an algorithm to shuffle the array randomly. All permutations of the array should be equally likely.
 * <p>
 * Implement three functions of the Solution class:
 * <p>
 * Initializes the object with the integer array nums.
 * Resets the array to its original configuration and returns it.
 * Returns a random shuffling of the array.
 */
public class ShuffleArray {

    private int[] original;
    Random rand;

    public ShuffleArray(int[] nums) {
        original = nums;
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        // TODO: Write - Your - Code
        int[] shuffled = new int[original.length];
        System.arraycopy(original, 0, shuffled, 0, original.length);
        int current = 0;
        while (current < shuffled.length - 1) {
            rand = new Random();
            int randomUnshuffled = rand.nextInt(shuffled.length - current) + current;
            int temp = shuffled[current];
            shuffled[current] = shuffled[randomUnshuffled];
            shuffled[randomUnshuffled] = temp;

            current++;
        }

        return shuffled;
    }

    /* DO NOT CHANGE THE TEST HARNESS CODE BELOW */

    static void print(int[] input) {
        String toPrint = "[";
        for (int j : input) {
            toPrint += j + ", ";
        }
        System.out.print(toPrint.substring(0, toPrint.length() - 2) + "]");
    }

    static void print(double[] input) {
        String toPrint = "[";
        for (double v : input) {
            toPrint += v + ", ";
        }
        System.out.print(toPrint.substring(0, toPrint.length() - 2) + "]");
    }

    static void updateFreqCount(int[] shuffled, int[][] allShuffles, int[] shuffleCounts) {
        for (int i = 0; i < allShuffles.length; i++) {
            if (Arrays.equals(allShuffles[i], shuffled)) {
                shuffleCounts[i]++;
                return;
            }
        }
    }

    static void calcFrequencies(int[] shuffleCounts, double[] shuffleFrequencies,
                                double totalTries) {
        for (int i = 0; i < shuffleFrequencies.length; i++) {
            shuffleFrequencies[i] = shuffleCounts[i] / totalTries * 100.0;
        }
    }

    public static void main(String[] args) {

        int[] numsToShuffle = {1, 2, 3};
        int totalTries = 1200;
        ShuffleArray sol = new ShuffleArray(numsToShuffle);

        int[][] allShuffles = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
        int[] shuffleCounts = {0, 0, 0, 0, 0, 0};
        double[] shuffleFrequencies = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        int[] shuffled;
        for (int call = 0; call < totalTries; call++) {
            shuffled = sol.shuffle();
            updateFreqCount(shuffled, allShuffles, shuffleCounts);
        }
        double totalTriesdble = totalTries * 1.0;
        calcFrequencies(shuffleCounts, shuffleFrequencies, totalTriesdble);

        System.out.print("Input array: ");
        print(numsToShuffle);
        System.out.print(", shuffled " + totalTries + " times.\n\n");

        System.out.println("Permutation | Occurrences | Frequency");
        for (int i = 0; i < allShuffles.length; i++) {
            System.out.print(Arrays.toString(allShuffles[i]));
            String sf = String.format("%3d", shuffleCounts[i]);
            System.out.println("   |  \t" + sf + " times | "
                    + String.format("%.02f", shuffleFrequencies[i]) + "%");
        }
    }
}
