package test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimalKSum {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9));
        List<Integer> tempList = new ArrayList<>();
        for (int element : list) {
            if (element % 2 == 0) {
                tempList.add(element);
            }
        }
        list.removeAll(tempList);
        System.out.println(list);
//        System.out.println(minimalKSum(new int[]{1, 2}, 10));
//        System.out.println(minimalKSum(new int[]{100}, 2));
//        System.out.println(minimalKSum(new int[]{1, 4, 25, 10}, 2));
//        System.out.println(minimalKSum(new int[]{647,2,529,588,688,157,261}, 96187219));
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

class Parent {
    void foo() {
        System.out.println("Super");
    }
}

class Child extends Parent {
    void foo() {
        System.out.println("Super");
    }
}