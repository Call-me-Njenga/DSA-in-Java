package LinkedList;

public class ReverseLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverse(Node head) {
        Node prev    = null;
        Node current = head;
        Node next    = null;

        while (current != null) {
            next         = current.next;
            current.next = prev;
            prev         = current;
            current      = next;
        }

        return prev;
    }

    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("Before: "); printList(head);
        head = reverse(head);
        System.out.print("After:  "); printList(head);
    }
}
