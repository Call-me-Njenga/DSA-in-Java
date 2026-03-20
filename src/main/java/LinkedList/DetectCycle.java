package LinkedList;

public class DetectCycle {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Floyd's cycle detection — O(n) time, O(1) space
    static boolean hasCycle(Node head) {
        if (head == null) return false;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }

    public static void main(String[] args) {

        // List WITH cycle: 1 -> 2 -> 3 -> 4 -> 5 -> (back to 3)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3; // cycle: 5 points back to 3

        System.out.println("List with cycle:    hasCycle = " + hasCycle(n1));

        // List WITHOUT cycle: 1 -> 2 -> 3 -> null
        Node h2 = new Node(1);
        h2.next = new Node(2);
        h2.next.next = new Node(3);

        System.out.println("List without cycle: hasCycle = " + hasCycle(h2));
    }
}