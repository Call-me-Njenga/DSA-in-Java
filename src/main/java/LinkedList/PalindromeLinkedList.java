package LinkedList;

public class PalindromeLinkedList {

    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Step 1 — find middle using fast and slow pointer
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2 — reverse the second half
        Node secondHalf = reverse(slow);
        Node copy = secondHalf; // save to restore later

        // Step 3 — compare first half and reversed second half
        Node first = head;
        while (secondHalf != null) {
            if (first.data != secondHalf.data) return false;
            first      = first.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    static Node reverse(Node head) {
        Node prev = null, current = head;
        while (current != null) {
            Node next    = current.next;
            current.next = prev;
            prev         = current;
            current      = next;
        }
        return prev;
    }

    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Palindrome: 1 -> 2 -> 3 -> 2 -> 1
        Node h1 = new Node(1);
        h1.next = new Node(2);
        h1.next.next = new Node(3);
        h1.next.next.next = new Node(2);
        h1.next.next.next.next = new Node(1);
        System.out.print("List: "); printList(h1);
        System.out.println("Palindrome? " + isPalindrome(h1));

        // Not palindrome: 1 -> 2 -> 3
        Node h2 = new Node(1);
        h2.next = new Node(2);
        h2.next.next = new Node(3);
        System.out.print("\nList: "); printList(h2);
        System.out.println("Palindrome? " + isPalindrome(h2));
    }
}
