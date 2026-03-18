package Arrays;

public class ArrayAggregation {


    // 1. SUM — add every element together
    //    Accumulator starts at 0, adds each element as we go

    static int sum(int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return total;
    }


    // 2. AVERAGE — sum divided by number of elements
    //    Returns double so we keep the decimal point

    static double average(int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return (double) total / arr.length;
    }


    // 3. MAXIMUM — traverse keeping track of biggest seen
    //    Assume first element is max, update when bigger found

    static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }


    // 4. MINIMUM — same idea as max but flipped
    //    Assume first element is min, update when smaller found

    static int min(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    // 5. COUNT — number of elements in the array
    //    arr.length gives this directly in Java

    static int count(int[] arr) {
        return arr.length;
    }


    // 6. COUNT EVEN — count how many elements are even
    //    Use modulus % 2 == 0 to check if a number is even
    static int countEven(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }


    // 7. COUNT ODD — count how many elements are odd
    //    Use modulus % 2 != 0 to check if a number is odd
    static int countOdd(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                count++;
            }
        }
        return count;
    }

    // 8. COUNT GREATER THAN — count elements above a threshold
    //    Useful for: how many students scored above 50?

    static int countGreaterThan(int[] arr, int threshold) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > threshold) {
                count++;
            }
        }
        return count;
    }


    // MAIN — runs all aggregations on a sample marks array
    public static void main(String[] args) {

        // Student marks out of 100
        int[] marks = {85, 42, 96, 13, 67, 58, 74, 29};

        System.out.println("Marks: [85, 42, 96, 13, 67, 58, 74, 29]");
        System.out.println("----------------------------------------");

        System.out.println("Sum:              " + sum(marks));
        System.out.println("Average:          " + average(marks));
        System.out.println("Maximum:          " + max(marks));
        System.out.println("Minimum:          " + min(marks));
        System.out.println("Count:            " + count(marks));
        System.out.println("Count Even:       " + countEven(marks));
        System.out.println("Count Odd:        " + countOdd(marks));
        System.out.println("Count above 50:   " + countGreaterThan(marks, 50));
    }
}