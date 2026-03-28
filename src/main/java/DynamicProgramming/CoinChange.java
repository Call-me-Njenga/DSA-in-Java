package DynamicProgramming;

import java.util.Arrays;

public class CoinChange {

    // Coin Change — O(amount × coins) time, O(amount) space
    // dp[i] = minimum coins needed to make amount i
    static int coinChange(int[] coins, int amount) {
        // Fill table with amount+1 (represents infinity — impossible)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        // Base case — 0 coins needed to make amount 0
        dp[0] = 0;

        // For each amount from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Try every coin
            for (int coin : coins) {
                // If coin fits and using it gives fewer coins — update
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If still amount+1 it means impossible
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println("=== Test 1 ===");
        int[] coins1 = {1, 5, 6, 9};
        int amount1  = 11;
        System.out.println("Coins:  " + Arrays.toString(coins1));
        System.out.println("Amount: " + amount1);
        System.out.println("Min coins: " + coinChange(coins1, amount1));

        System.out.println("\n=== Test 2 ===");
        int[] coins2 = {1, 2, 5};
        int amount2  = 11;
        System.out.println("Coins:  " + Arrays.toString(coins2));
        System.out.println("Amount: " + amount2);
        System.out.println("Min coins: " + coinChange(coins2, amount2));

        System.out.println("\n=== Test 3 — impossible ===");
        int[] coins3 = {2};
        int amount3  = 3;
        System.out.println("Coins:  " + Arrays.toString(coins3));
        System.out.println("Amount: " + amount3);
        System.out.println("Min coins: " + coinChange(coins3, amount3));
    }
}
