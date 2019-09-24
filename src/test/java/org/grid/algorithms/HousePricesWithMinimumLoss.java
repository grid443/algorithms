package org.grid.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/minimum-loss/problem
 *
 * Lauren has a chart of distinct projected prices for a house over the next several years.
 * She must buy the house in one year and sell it in another, and she must do so at a loss.
 * She wants to minimize her financial loss.
 *
 * For example, the house is valued at price = [20, 15, 8, 2, 12] over the next n = 5 years.
 * She can purchase the home in any year, but she must resell the house at a loss in one of
 * the following years. Her minimum loss would be incurred by purchasing in year 2
 * at price[1] = 15 and reselling in year 5 at price[4] = 12.
 *
 * Find and print the minimum amount of money Lauren must lose if she buys the house
 * and resells it within the next n years.
 *
 * Note: It's guaranteed that a valid answer exists.
 *
 * Constraints
 * - 2 < = n <= 10^5
 * - 1 <= price[i] <= 10^16
 * - All the prices are distinct.
 * - A valid answer exists.
 */
public class HousePricesWithMinimumLoss {

    @Test
    public void test() {
        long[] housePrices = {20, 15, 8, 2, 12};
        int minLoss = minimumLoss(housePrices);
        int minLossInefficient = minimumLossInefficient(housePrices);
        Assert.assertEquals(3, minLoss);
        Assert.assertEquals(minLoss, minLossInefficient);
    }

    private int minimumLoss(long[] prices) {
        int minimumLoss = Integer.MAX_VALUE;
        Map<Long, Integer> positions = new HashMap<>();
        long[] sortedPrices = new long[prices.length];
        System.arraycopy(prices, 0, sortedPrices, 0, prices.length);
        Arrays.sort(sortedPrices);

        for (int i = 0; i < prices.length; i++) {
            positions.put(prices[i], i);
        }

        for (int i = 1; i < prices.length; i++) {
            long secondPrice = sortedPrices[i];
            long firstPrice = sortedPrices[i - 1];

            long secondPosition = positions.get(secondPrice);
            long firstPosition = positions.get(firstPrice);
            int loss = (int) (secondPrice - firstPrice);
            if (loss < minimumLoss && secondPosition < firstPosition) {
                minimumLoss = loss;
            }
        }

        return minimumLoss;
    }

    private int minimumLossInefficient(long[] prices) {
        int minimumLoss = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                long firstPrice = prices[i];
                long secondPrice = prices[j];
                int loss = (int) (firstPrice - secondPrice);
                if (loss <= 0) {
                    continue;
                }
                if (loss < minimumLoss) {
                    minimumLoss = loss;
                }
            }
        }
        return minimumLoss;
    }
}
