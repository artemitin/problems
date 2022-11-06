package test.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPerimeterIslandTest {

    @Test
    void test() {
        Integer[][] matrix = {
                {0,1,0,0,0},
                {1,1,1,1,0},
                {0,0,1,0,0},
                {1,1,1,1,1},
                {0,0,1,0,1}
        };
        int result = FindPerimeterIsland.islandPerimeter(matrix);
        assertEquals(28, result);
    }

    @Test
    void test2() {
        Integer[][] matrix = {
                {0, 0},
                {0, 0}
        };
        int result = FindPerimeterIsland.islandPerimeter(matrix);
        assertEquals(0, result);
    }

    @Test
    void test3() {
        Integer[][] matrix = {};
        int result = FindPerimeterIsland.islandPerimeter(matrix);
        assertEquals(0, result);
    }
}
