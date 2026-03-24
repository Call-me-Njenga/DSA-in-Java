package Queue;

public class BinarySearchTree {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data  = data;
            this.left  = null;
            this.right = null;
        }
    }

    Node root;

    // INSERT — O(log n) average
    // If value < current node go left, if > go right
    // Keep going until we find an empty spot
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node node, int data) {
        if (node == null) return new Node(data); // empty spot — place it here
        if (data < node.data)      node.left  = insertRec(node.left,  data);
        else if (data > node.data) node.right = insertRec(node.right, data);
        return node; // data == node.data means duplicate — ignore
    }

    // SEARCH — O(log n) average
    // Same logic as insert — go left or right until found or null
    boolean search(int data) {
        return searchRec(root, data);
    }

    boolean searchRec(Node node, int data) {
        if (node == null)       return false; // reached end — not found
        if (data == node.data)  return true;  // found!
        if (data < node.data)   return searchRec(node.left,  data);
        return                         searchRec(node.right, data);
    }

    // INORDER TRAVERSAL — left, root, right
    // Visits nodes in ASCENDING order — a BST superpower
    void inorder() {
        System.out.print("Inorder (sorted): ");
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node node) {
        if (node == null) return;
        inorderRec(node.left);           // visit left subtree first
        System.out.print(node.data + " "); // then current node
        inorderRec(node.right);          // then right subtree
    }

    // FIND MIN — keep going left until no more left children
    int findMin() {
        if (root == null) return -1;
        Node current = root;
        while (current.left != null) current = current.left;
        return current.data;
    }

    // FIND MAX — keep going right until no more right children
    int findMax() {
        if (root == null) return -1;
        Node current = root;
        while (current.right != null) current = current.right;
        return current.data;
    }

    // HEIGHT — longest path from root to a leaf
    int height(Node node) {
        if (node == null) return 0;
        int leftH  = height(node.left);
        int rightH = height(node.right);
        return 1 + Math.max(leftH, rightH);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        //        50
        //       /  \
        //      30   70
        //     / \   / \
        //    20  40 60  80

        bst.inorder();
        System.out.println("Search 40:  " + bst.search(40));
        System.out.println("Search 99:  " + bst.search(99));
        System.out.println("Min:        " + bst.findMin());
        System.out.println("Max:        " + bst.findMax());
        System.out.println("Height:     " + bst.height(bst.root));
    }
}
