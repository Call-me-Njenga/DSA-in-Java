package LinkedList;

public class DoublyLinkedList {

    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;

    // Add to tail — O(1) with tail pointer
    void addToTail(int data) {
        Node newNode = new Node(data);
        if (head == null) { head = tail = newNode; return; }
        newNode.prev = tail;
        tail.next    = newNode;
        tail         = newNode;
    }

    // Add to head — O(1)
    void addToHead(int data) {
        Node newNode = new Node(data);
        if (head == null) { head = tail = newNode; return; }
        newNode.next = head;
        head.prev    = newNode;
        head         = newNode;
    }

    // Delete first matching node — O(n) search, O(1) delete
    void delete(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;

                return;
            }
            current = current.next;
        }
        System.out.println(data + " not found");
    }

    // Forward traversal — head to tail using next
    void printForward() {
        Node current = head;
        System.out.print("Forward:  null <- ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" <-> ");
            current = current.next;
        }
        System.out.println(" -> null");
    }

    // Backward traversal — tail to head using prev
    void printBackward() {
        Node current = tail;
        System.out.print("Backward: null <- ");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) System.out.print(" <-> ");
            current = current.prev;
        }
        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.addToTail(10);
        list.addToTail(20);
        list.addToTail(30);
        list.addToHead(5);

        list.printForward();
        list.printBackward();

        System.out.println("\nAfter deleting 20:");
        list.delete(20);
        list.printForward();
        list.printBackward();
    }
}

