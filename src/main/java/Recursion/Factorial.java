package Recursion;

public class Factorial {

    // Recursive — O(n) time, O(n) space (call stack)
    static long factorialRec(int n) {
        if (n == 0 || n == 1) return 1; // base case
        return n * factorialRec(n - 1); // recursive case
    }

    // Iterative — O(n) time, O(1) space (no call stack)
    static long factorialIter(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + "! = " + factorialRec(i) + " (recursive)  " + factorialIter(i) + " (iterative)");
        }
    }
}
