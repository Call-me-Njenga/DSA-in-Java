package LinkedList;

public class MergeTwoSortedLists {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Merge two sorted lists — O(n + m) time, O(1) space
    static Node mergeLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail  = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
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

        // List 1: 1 -> 3 -> 5 -> 7
        Node l1 = new Node(1);
        l1.next = new Node(3);
        l1.next.next = new Node(5);
        l1.next.next.next = new Node(7);

        // List 2: 2 -> 4 -> 6 -> 8
        Node l2 = new Node(2);
        l2.next = new Node(4);
        l2.next.next = new Node(6);
        l2.next.next.next = new Node(8);

        System.out.print("List 1:  "); printList(l1);
        System.out.print("List 2:  "); printList(l2);

        Node merged = mergeLists(l1, l2);

        System.out.print("Merged:  "); printList(merged);
    }
}
