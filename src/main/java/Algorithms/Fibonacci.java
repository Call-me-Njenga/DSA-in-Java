package Algorithms;

public class Fibonacci {

    // Recursive approach
    public static int fib(int n) {
        // Base cases — stop here
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Recursive case — call itself with smaller inputs
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("fib(" + i + ") = " + fib(i));
        }
    }
}
