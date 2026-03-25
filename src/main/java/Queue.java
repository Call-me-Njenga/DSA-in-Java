public class Queue {
    // Node — same as linked list
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    Node front; // remove from here
    Node rear;  // add to here
    int size = 0;

    // Add to rear — O(1)
    void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) { front = rear = newNode; size++; return; }
        rear.next = newNode;
        rear = newNode;
        size++;
    }

    // Remove from front — O(1)
    int dequeue() {
        if (front == null) { System.out.println("Queue is empty!"); return -1; }
        int removed = front.data;
        front = front.next;
        if (front == null) rear = null; // queue is now empty
        size--;
        return removed;
    }

    // Peek at front without removing — O(1)
    int peek() {
        if (front == null) { System.out.println("Queue is empty!"); return -1; }
        return front.data;
    }

    boolean isEmpty() { return front == null; }
    int size()        { return size; }

    void print() {
        Node current = front;
        System.out.print("Queue (front -> rear): ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue q = new Queue();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.print();

        System.out.println("Peek:     " + q.peek());
        System.out.println("Dequeue:  " + q.dequeue());
        System.out.println("Dequeue:  " + q.dequeue());
        q.print();

        System.out.println("Size:     " + q.size());
        System.out.println("Empty?    " + q.isEmpty());
    }
}
