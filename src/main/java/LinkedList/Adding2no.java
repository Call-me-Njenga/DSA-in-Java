package LinkedList;

public class Adding2no {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) { sum += l1.data; l1 = l1.next; }
            if (l2 != null) { sum += l2.data; l2 = l2.next; }

            carry = sum / 10;
            current.next = new Node(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data);
            if (node.next != null) System.out.print(" -> ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Number 1: 342  stored as  2 -> 4 -> 3
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        // Number 2: 465  stored as  5 -> 6 -> 4
        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        System.out.print("List 1 (342 reversed): "); printList(l1);
        System.out.print("List 2 (465 reversed): "); printList(l2);

        Node result = addTwoNumbers(l1, l2);

        System.out.print("Result (807 reversed): "); printList(result);
    }
}
