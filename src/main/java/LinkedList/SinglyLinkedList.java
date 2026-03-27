package LinkedList;

public class SinglyLinkedList {

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;

    // Add at end
    void add(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return; //if list is empty:make new node the first node
        }

        Node temp = head;// Start from the beginning
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Add at beginning
    void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Delete a node
    void delete(int data) {
        if (head == null) return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    // Search for a value
    boolean search(int data) {
        Node temp = head;

        while (temp != null) {
            if (temp.data == data) return true;
            temp = temp.next;
        }

        return false;
    }

    // Print the list
    void print() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    // Reverse the list (IMPORTANT)
    void reverse() {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
    }

    // Main method (testing)
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.addFirst(40);

        System.out.print("List: ");
        list.print();

        list.delete(20);
        System.out.print("After delete: ");
        list.print();

        System.out.println("Search 30: " + list.search(30));

        list.reverse();
        System.out.print("Reversed: ");
        list.print();
    }
}