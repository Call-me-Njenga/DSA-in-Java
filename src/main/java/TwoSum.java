import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // APPROACH 1: Brute Force — O(n²) time, O(1) space
    // Check every pair of numbers
    static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // APPROACH 2: HashMap — O(n) time, O(n) space
    // For each number, check if its complement already exists in the map
    // complement = target - current number
    static int[] twoSumHash(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If complement is already in map we found the pair
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Otherwise store current number and its index
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums   = {2, 7, 11, 15};
        int   target = 9;

        System.out.println("Array:  " + Arrays.toString(nums));
        System.out.println("Target: " + target);
        System.out.println("-------");
        System.out.println("Brute Force: " + Arrays.toString(twoSumBrute(nums, target)));
        System.out.println("HashMap:     " + Arrays.toString(twoSumHash(nums, target)));

        // Second test
        int[] nums2   = {3, 2, 4};
        int   target2 = 6;
        System.out.println("\nArray:  " + Arrays.toString(nums2));
        System.out.println("Target: " + target2);
        System.out.println("-------");
        System.out.println("Brute Force: " + Arrays.toString(twoSumBrute(nums2, target2)));
        System.out.println("HashMap:     " + Arrays.toString(twoSumHash(nums2, target2)));
    }
}
