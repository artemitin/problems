package test.arrays;

/**
 * Given a list of stock prices, find the maximum profit with a single buy or sell activity.
 */
public class StockBuySellToMaximizeProfit {

    public static void main(String[] args) {
        int[] arr = {7, 5, 1, 3, 6, 4};
//        int[] arr = {8, 6, 5, 4, 3, 2, 1};
        Tuple<Integer, Integer> buySellStockPrices = findBuySellStockPrices(arr);
        System.out.println(buySellStockPrices);
    }

    public static Tuple<Integer, Integer> findBuySellStockPrices(int[] stockNums) {
        // Return None when stock list is empty or less than 2
        if (stockNums == null || stockNums.length < 2) {
            return null;
        }

        int currentBuy = stockNums[0];
        int globalSell = stockNums[1];
        int currentProfit;
        int globalProfit = globalSell - currentBuy;

        // Looping over stocks to find best buy and selling price
        for (int i = 1; i < stockNums.length; i++) {
            currentProfit = stockNums[i] - currentBuy;

            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
                globalSell = stockNums[i];
            }

            // We will always maximise margin relative to the lowest stock price we can find
            // So whenever we find a stock price lower than the current buying price,
            // we adopt it as the current buying price
            if (currentBuy > stockNums[i]) {
                currentBuy = stockNums[i];
            }
        }

        // Tuple having buy price and sell price
        return new Tuple<>(globalSell - globalProfit, globalSell);
    }
}

class Tuple<X, Y> {
    public X x;
    public Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}