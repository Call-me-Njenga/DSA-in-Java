package LinkedList;

public class FindMiddleNode {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Fast and slow pointer — O(n) time, O(1) space
    static Node findMiddle(Node head) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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

        // Odd length list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("List:               "); printList(head);
        System.out.println("Middle (odd list):  " + findMiddle(head).data);

        System.out.println();

        // Even length list: 1 -> 2 -> 3 -> 4
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);

        System.out.print("List:               "); printList(head2);
        System.out.println("Middle (even list): " + findMiddle(head2).data);
    }
}
