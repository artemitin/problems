package test.arrays;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Given an array of integers nums, sort it in ascending order using the quicksort algorithm.
 */
public class BirthdayParadox {

    public static void main(String[] args) {
        int team = 50;
        int trials = 1000;
        int nHasSameBirthdays = 0;

        for (int i = 0; i < trials; i++) {
            if (hasSameBirthday(team)) {
                nHasSameBirthdays++;
            }
        }

        System.out.printf("team of %s had the same birthdays %s times out of %s", team, nHasSameBirthdays, trials);

    }

    private static boolean hasSameBirthday(int team) {
        Random r = new Random();
        Set<String> set = new HashSet<>(team);
        for (int i = 0; i < team; i++) {
            int day = r.nextInt(30) + 1;
            int month = r.nextInt(12) + 1;
            String date = day + "-" + month;
            set.add(date);
        }
        return set.size() < team;
    }
}
