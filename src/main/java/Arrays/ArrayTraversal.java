package Arrays;

public class ArrayTraversal {


    // 1. FORWARD TRAVERSAL — visit every element left to right

    static void forwardTraversal(int[] arr) {
        System.out.println("=== Forward Traversal ===");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }


    // 2. BACKWARD TRAVERSAL — visit every element right to left
    //    Start at last index (arr.length - 1), count down to 0

    static void backwardTraversal(int[] arr) {
        System.out.println("=== Backward Traversal ===");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }


    // 3. FIND MAX — traverse and keep track of biggest value seen
    //    Start by assuming first element is max, then update
    //    whenever a bigger value is found

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }


    // 4. SEARCH ELEMENT — traverse and check each element
    //    Stop immediately with break when target is found
    //    Returns index if found, -1 if not found

    static int searchElement(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }


    // MAIN — runs all four traversal types

    public static void main(String[] args) {
        int[] arr = {15, 8, 42, 3, 27, 11, 36};

        // Forward
        forwardTraversal(arr);

        System.out.println();

        // Backward
        backwardTraversal(arr);

        System.out.println();

        // Find max
        int max = findMax(arr);
        System.out.println("=== Find Max ===");
        System.out.println("Maximum value = " + max);

        System.out.println();

        // Search
        System.out.println("=== Search Element ===");
        int index = searchElement(arr, 42);
        if (index != -1) System.out.println("Found 42 at index " + index);
        else System.out.println("42 not found");

        int index2 = searchElement(arr, 99);
        if (index2 != -1) System.out.println("Found 99 at index " + index2);
        else System.out.println("99 not found in array");
    }
}
