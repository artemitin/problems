package test.misc;

import java.util.List;

public class ClosestMeetingPoint {

    public static Point shortestDistanceTravelled(int gridSize, List<Point> startingPoints) {
        Point minPoint = new Point(0, 0);
        double sum = Integer.MAX_VALUE;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double localSum = 0;
                for (Point p : startingPoints) {
                    localSum += Math.sqrt(Math.pow(p.getX() - i, 2) + Math.pow(p.getY() - j, 2));
                }
                if (localSum < sum) {
                    sum = localSum;
                    minPoint.setX(i);
                    minPoint.setY(j);
                }
            }
        }
        return minPoint;
    }
}

class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
};