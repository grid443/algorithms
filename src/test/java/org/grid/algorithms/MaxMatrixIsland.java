package org.grid.algorithms;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 */
public class MaxMatrixIsland {
    @Test
    public void squareMatrixText() {
        int [][] squareMatrix = {
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0}
        };
        Assert.assertEquals(5, connectedCell(squareMatrix));
    }

    @Test
    public void rectangularMatrixText() {
        int [][] rectangularMatrix = {
                {0, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0},
        };
        Assert.assertEquals(7, connectedCell(rectangularMatrix));
    }

    private int connectedCell(int[][] matrix) {
        int maxCountCells = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int countCells = countIslandCells(matrix, i, j, 0);
                if (countCells > maxCountCells) {
                    maxCountCells = countCells;
                }
            }
        }

        return maxCountCells;
    }

    private int countIslandCells(int[][] matrix, int i, int j, int initialCount) {
        int value = matrix[i][j];
        if (value != 1) {
            return initialCount;
        }

        int count = initialCount + 1;

        //mark checked field
        matrix[i][j] = -1;

        int width = matrix[i].length;
        int height = matrix.length;

        int topI = i - 1;
        int topY = j;
        if (topI >= 0) {
            count = countIslandCells(matrix, topI, topY, count);
        }

        int topLeftI = i - 1;
        int topLeftY = j - 1;
        if (topLeftI >= 0 && topLeftY >= 0 && topLeftY < width) {
            count = countIslandCells(matrix, topLeftI, topLeftY, count);
        }

        int topRightI = i - 1;
        int topRightY = j + 1;
        if (topRightI >= 0 && topRightY < width) {
            count = countIslandCells(matrix, topRightI, topRightY, count);
        }

        int leftI = i;
        int leftY = j - 1;
        if (leftY >= 0 && leftY < width) {
            count = countIslandCells(matrix, leftI, leftY, count);
        }

        int rightI = i;
        int rightY = j + 1;
        if (rightY < width) {
            count = countIslandCells(matrix, rightI, rightY, count);
        }

        int bottomI = i + 1;
        int bottomY = j;
        if (bottomI < height) {
            count = countIslandCells(matrix, bottomI, bottomY, count);
        }

        int bottomLeftI = i + 1;
        int bottomLeftY = j - 1;
        if (bottomLeftI < height && bottomLeftY >= 0 && bottomLeftY < width) {
            count = countIslandCells(matrix, bottomLeftI, bottomLeftY, count);
        }

        int bottomRightI = i + 1;
        int bottomRightY = j + 1;
        if (bottomRightI < height && bottomRightY < width) {
            count = countIslandCells(matrix, bottomRightI, bottomRightY, count);
        }

        return count;
    }
}
