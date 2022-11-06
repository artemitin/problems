package test.math;

/**
 * Given the bottom-left and top-right coordinates of two axis-aligned rectangles,
 * check if the two rectangles overlap.
 * Return true if they overlap, otherwise, return false
 */
public class DetectRectangleOverlap {

    public static void main(String[] args) {
        int[] rect1 = {0, 0, 2, 2};
        int[] rect2 = {1, 1, 3, 3};
        boolean result = isRectangleOverlap(rect1, rect2);
        System.out.println(result);
    }

    public static Boolean isRectangleOverlap(int[] rec1, int[] rec2) {
//        xb1 yb1 xt1 yt1
//        xb2 yb2 xt2 yt2
//        overlap:
//        xb2 < xt1 && yb2 < yt1 || xb2 < xt1 && yb2 < yt1

        return rec2[0] < rec1[2] && rec2[1] < rec1[3];
    }

}
