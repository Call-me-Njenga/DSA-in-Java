import java.util.Collections;
import java.util.PriorityQueue;

public class HeapPriorityQueue {

    public static void main(String[] args) {

        // -------------------------------------------------------
        // MIN HEAP — smallest element always at top
        // Java's default PriorityQueue is a min heap
        // -------------------------------------------------------
        System.out.println("=== Min Heap ===");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(50);
        minHeap.offer(20);
        minHeap.offer(40);

        System.out.println("Peek (min): " + minHeap.peek()); // always shows smallest

        System.out.print("Poll order (ascending): ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // removes smallest each time
        }
        System.out.println();

        // -------------------------------------------------------
        // MAX HEAP — largest element always at top
        // Use Collections.reverseOrder() to flip the comparison
        // -------------------------------------------------------
        System.out.println("\n=== Max Heap ===");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);
        maxHeap.offer(40);

        System.out.println("Peek (max): " + maxHeap.peek()); // always shows largest

        System.out.print("Poll order (descending): ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // removes largest each time
        }
        System.out.println();

        // -------------------------------------------------------
        // REAL USE CASE — find Kth largest element
        // Keep a min heap of size K
        // If heap grows beyond K, remove the smallest
        // What remains is the K largest elements — root is Kth largest
        // -------------------------------------------------------
        System.out.println("\n=== Kth Largest Element ===");
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        PriorityQueue<Integer> kHeap = new PriorityQueue<>();
        for (int num : nums) {
            kHeap.offer(num);
            if (kHeap.size() > k) kHeap.poll(); // remove smallest to keep top K
        }

        System.out.println("Array: [3,2,1,5,6,4]");
        System.out.println("K = " + k);
        System.out.println("Kth largest = " + kHeap.peek()); // root of min heap = Kth largest
    }
}
