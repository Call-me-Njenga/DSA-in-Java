package LinkedList;

public class SinglyLinkedList {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;
    int size = 0;

    // Add new node to the end — O(n)
    void addToEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) { head = newNode; size++; return; }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    // Insert new node at the front — O(1)
    void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Delete first node matching value — O(n)
    void delete(int data) {
        if (head == null) return;
        if (head.data == data) { head = head.next; size--; return; }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
        System.out.println(data + " not found in list");
    }

    // Search for a value — O(n)
    boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) return true;
            current = current.next;
        }
        return false;
    }

    // Print entire list
    void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Return number of nodes — O(1)
    int size() { return size; }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addToEnd(10);
        list.addToEnd(20);
        list.addToEnd(30);
        list.insertAtHead(5);

        System.out.print("List:           "); list.print();
        System.out.println("Size:           " + list.size());

        list.delete(20);
        System.out.print("After delete 20: "); list.print();

        System.out.println("Search 30:      " + list.search(30));
        System.out.println("Search 99:      " + list.search(99));
        System.out.println("Size:           " + list.size());
    }
}