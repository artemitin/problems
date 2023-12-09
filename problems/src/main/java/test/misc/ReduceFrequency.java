package test.misc;

import java.util.Arrays;

public class ReduceFrequency {
    public static void main(String[] args) {
        boolean result = new ReduceFrequency().equalFrequency("abcc");
        System.out.println(result);
    }

    public boolean equalFrequency(String word) {
        int[] freqs = new int[26];
        for (int i = 0; i < word.length(); i++) {
            freqs[word.charAt(i) - 'a']++;
        }

        Arrays.sort(freqs);

        int firstNon0 = last0(freqs) + 1;

        if (freqs[firstNon0] == 1 && allEquals(freqs, firstNon0 + 1, freqs.length, freqs[firstNon0 + 1])) {
            return true;
        }

        if (allEquals(freqs, firstNon0, freqs.length - 1, freqs[freqs.length - 1] - 1)) {
            return true;
        }

        return false;
    }

    private int last0(int[] arr) {
        int x = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i;
            } else {
                break;
            }
        }
        return x;
    }

    private boolean allEquals(int[] arr, int from, int to, int k) {
        for (int i = from; i < to; i++) {
            if (arr[i] != k) {
                return false;
            }
        }
        return true;
    }
}
