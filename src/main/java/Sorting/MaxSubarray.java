package Sorting;

public class MaxSubarray {

    // Kadane's Algorithm — O(n) time, O(1) space
    // At each element decide: extend current subarray OR start fresh
    static int maxSubarray(int[] nums) {
        int currentSum = nums[0];
        int maxSum     = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // If adding current element to existing sum is worse
            // than starting fresh — restart from here
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update global max if current window is better
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Extended version — also returns the subarray indices
    static int[] maxSubarrayWithIndices(int[] nums) {
        int currentSum = nums[0], maxSum = nums[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart  = i;
            } else {
                currentSum += nums[i];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start  = tempStart;
                end    = i;
            }
        }
        return new int[]{maxSum, start, end};
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Max subarray sum: " + maxSubarray(nums));

        int[] result = maxSubarrayWithIndices(nums);
        System.out.println("Max sum: " + result[0]);
        System.out.println("From index " + result[1] + " to index " + result[2]);
        System.out.print("Subarray: [");
        for (int i = result[1]; i <= result[2]; i++) {
            System.out.print(nums[i] + (i<result[2]?", ":""));
        }
        System.out.println("]");
    }
}
