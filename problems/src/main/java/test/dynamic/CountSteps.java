package test.dynamic;
/*894. All Possible Full Binary Trees.*/

import test.MeasureUtil;

public class CountSteps {

    int[] memo;

    public static void main(String[] args) {
        CountSteps main = new CountSteps();
        Object result = MeasureUtil.runMeasured(() -> main.climbStairs(5));
        System.out.println(result);
    }

    public int climbStairs(int n) {
        memo = new int[n + 1];
        return countSteps(n);
    }

    private int countSteps(int n) {
        if (n <= 1) {
            return 1;
        }
        int steps1 = memo[n - 1] == 0 ? countSteps(n - 1) : memo[n - 1];
        int steps2 = memo[n - 2] == 0 ? countSteps(n - 2) : memo[n - 2];
        int steps = steps1 + steps2;
        memo[n] = steps;
        return steps;
    }
}
