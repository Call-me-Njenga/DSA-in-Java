public class Knapsack {

    // 0/1 Knapsack — O(n × W) time and space
    // dp[i][w] = max value using first i items with capacity w
    static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Build a 2D table — rows=items, cols=weight capacities
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                // Option 1: don't take item i
                dp[i][w] = dp[i-1][w];

                // Option 2: take item i (only if it fits)
                if (weights[i-1] <= w) {
                    int valueWithItem = values[i-1] + dp[i-1][w - weights[i-1]];

                    // Take the better option
                    dp[i][w] = Math.max(dp[i][w], valueWithItem);
                }
            }
        }

        return dp[n][capacity]; // answer is bottom-right cell
    }

    public static void main(String[] args) {
        int[] weights  = {1, 3, 4, 5};
        int[] values   = {1, 4, 5, 7};
        int   capacity = 7;

        System.out.println("Weights:  [1, 3, 4, 5]");
        System.out.println("Values:   [1, 4, 5, 7]");
        System.out.println("Capacity: " + capacity);
        System.out.println("Max value: " + knapsack(weights, values, capacity));
        // Expected: 9 (items with weight 3+4=7, value 4+5=9)
    }
}