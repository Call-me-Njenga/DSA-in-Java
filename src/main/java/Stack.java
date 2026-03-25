public class Stack {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node top;
    int size = 0;

    // Push — add to top — O(1)
    void push(int data) {
        Node newNode  = new Node(data);
        newNode.next  = top;
        top           = newNode;
        size++;
    }

    // Pop — remove from top — O(1)
    int pop() {
        if (top == null) {
            System.out.println("Stack is empty!");
            return -1;
        }
        int removed = top.data;
        top         = top.next;
        size--;
        return removed;
    }

    // Peek — view top without removing — O(1)
    int peek() {
        if (top == null) return -1;
        return top.data;
    }

    boolean isEmpty() { return top == null; }
    int size()        { return size; }

    void print() {
        Node current = top;
        System.out.print("Stack (top→bottom): ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack s = new Stack();

        s.push(10);
        s.push(20);
        s.push(30);

        s.print();
        System.out.println("Peek:   " + s.peek());
        System.out.println("Pop:    " + s.pop());
        System.out.println("Pop:    " + s.pop());
        s.print();
        System.out.println("Size:   " + s.size());
        System.out.println("Empty?  " + s.isEmpty());
    }
}
