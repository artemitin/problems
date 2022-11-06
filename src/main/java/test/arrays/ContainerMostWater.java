package test.arrays;

import java.util.Arrays;

/**
 * Given an array of heights, which represents the heights of vertical lines drawn on a graph.
 * Find two lines that form a container that holds the most water when combined with the horizontal axis.
 * The height of this container will be the shorter of the two lines:
 */
public class ContainerMostWater {

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxWaterAreaContainer(arr);
        System.out.println(result);
    }

    static int maxWaterAreaContainer(int[] height) {
//        (i2-i1)*Min(h1, h2)
        int left = 0;
        int right = height.length - 1;
        int volume = -1;

        while (left != right) {
            volume = Math.max(volume, (right - left) * Math.min(height[right], height[left]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return volume;
    }
}
