package dbengine;

import java.util.*;

/**
 * ============================================================
 *  BTreeSearch.java
 *  Package : com.dbengine.algorithms
 *  Class   : BTreeSearch
 * ============================================================
 *
 *  Algorithm : B-Tree Search — Balanced M-Way Index Tree
 *
 *  Used by the DB engine for:
 *    PRIMARY KEY index       — fast single-row lookup
 *    UNIQUE constraint       — duplicate check on insert
 *    Range scans             — WHERE age BETWEEN 20 AND 40
 *    Clustered index         — rows stored in B-Tree leaf order
 *
 *  ── What Is a B-Tree? ─────────────────────────────────────
 *  A self-balancing tree where every node can hold many keys
 *  (not just 2 like a BST).  Each internal node with k keys
 *  has exactly k+1 child pointers.  All leaves sit at the
 *  same depth — guaranteeing uniform lookup time.
 *
 *  Min-degree t means:
 *    Every non-root node has  ≥ (t-1)  keys   and ≥ t children.
 *    Every node has           ≤ (2t-1) keys   and ≤ 2t children.
 *
 *  ── Search Algorithm ──────────────────────────────────────
 *  Starting at the root:
 *    1. Scan the current node's keys left-to-right.
 *    2. If  key == node.keys[i]   → FOUND, return node + pos.
 *    3. If  key  < node.keys[i]   → descend into child[i].
 *    4. If  key  > all keys       → descend into rightmost child.
 *    5. If we reach a leaf and still no match → NOT FOUND.
 *
 *  ── Insert Algorithm ──────────────────────────────────────
 *  Always insert into a leaf.  If the target node is full
 *  (2t-1 keys), SPLIT it: promote the middle key to the parent
 *  and create two half-filled children.  Splits propagate
 *  upward; if the root splits, tree height grows by 1.
 *
 *  ── Range Scan ────────────────────────────────────────────
 *  Find the start key with binary-style descent, then do an
 *  in-order traversal collecting keys until the end bound.
 *
 *  ── Complexity ────────────────────────────────────────────
 *  Time  : O(t · log_t n)   — height = log_t(n), scan per node = t
 *  Space : O(n)             — all n keys stored in nodes
 * ============================================================
 */
public class BTreeSearch {

    // ── B-Tree node ───────────────────────────────────────────
    static class BTreeNode {
        int         t;           // minimum degree
        int[]       keys;        // key array, size = 2t-1 max
        int         numKeys;     // actual number of keys in node
        BTreeNode[] children;    // child pointers, size = 2t max
        boolean     isLeaf;

        BTreeNode(int t, boolean isLeaf) {
            this.t        = t;
            this.isLeaf   = isLeaf;
            this.keys     = new int[2 * t - 1];
            this.children = new BTreeNode[2 * t];
            this.numKeys  = 0;
        }

        // ── Search within this subtree ─────────────────────────
        SearchResult search(int key, int depth) {
            int i = 0;

            // Advance past all keys smaller than the search key
            while (i < numKeys && key > keys[i]) i++;

            System.out.println("   Depth " + depth
                    + " | Node keys " + nodeKeysString()
                    + "  →  probe index " + i);

            if (i < numKeys && keys[i] == key) {
                System.out.println("   ✔  Key " + key + " FOUND at depth " + depth
                        + ", node position " + i);
                return new SearchResult(true, depth, i);
            }

            if (isLeaf) {
                System.out.println("   ✘  Key " + key + " NOT FOUND (leaf reached)");
                return new SearchResult(false, -1, -1);
            }

            return children[i].search(key, depth + 1);
        }

        // ── Range scan: collect keys in [low, high] in-order ──
        void rangeScan(int low, int high, List<Integer> output) {
            int i = 0;
            while (i < numKeys) {
                if (!isLeaf) {
                    children[i].rangeScan(low, high, output);
                }
                if (keys[i] >= low && keys[i] <= high) {
                    output.add(keys[i]);
                }
                i++;
            }
            if (!isLeaf) {
                children[numKeys].rangeScan(low, high, output);
            }
        }

        String nodeKeysString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < numKeys; i++) {
                if (i > 0) sb.append(", ");
                sb.append(keys[i]);
            }
            sb.append("]");
            return sb.toString();
        }
    }

    // ── Search result wrapper ─────────────────────────────────
    static class SearchResult {
        boolean found;
        int     depth;
        int     position;

        SearchResult(boolean found, int depth, int position) {
            this.found    = found;
            this.depth    = depth;
            this.position = position;
        }
    }

    // ── B-Tree container ──────────────────────────────────────
    static class BTree {
        BTreeNode root;
        int       t;

        BTree(int t) {
            this.t    = t;
            this.root = new BTreeNode(t, true);
        }

        // Public search entry point
        SearchResult search(int key) {
            System.out.println("Searching for key: " + key);
            return root.search(key, 0);
        }

        // Public range scan entry point
        List<Integer> rangeScan(int low, int high) {
            List<Integer> output = new ArrayList<>();
            root.rangeScan(low, high, output);
            Collections.sort(output);   // ensure ascending order
            return output;
        }

        // ── INSERT ────────────────────────────────────────────
        void insert(int key) {
            BTreeNode r = root;
            if (r.numKeys == 2 * t - 1) {
                // Root is full — grow the tree upward
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = r;
                splitChild(s, 0, r);
                root = s;
                insertNonFull(s, key);
            } else {
                insertNonFull(r, key);
            }
        }

        private void insertNonFull(BTreeNode node, int key) {
            int i = node.numKeys - 1;
            if (node.isLeaf) {
                // Shift keys right to make room, then insert
                while (i >= 0 && key < node.keys[i]) {
                    node.keys[i + 1] = node.keys[i];
                    i--;
                }
                node.keys[i + 1] = key;
                node.numKeys++;
            } else {
                // Find the correct child to descend into
                while (i >= 0 && key < node.keys[i]) i--;
                i++;
                if (node.children[i].numKeys == 2 * t - 1) {
                    splitChild(node, i, node.children[i]);
                    if (key > node.keys[i]) i++;
                }
                insertNonFull(node.children[i], key);
            }
        }

        private void splitChild(BTreeNode parent, int i, BTreeNode fullChild) {
            BTreeNode newChild = new BTreeNode(t, fullChild.isLeaf);
            newChild.numKeys = t - 1;

            // Copy right half of fullChild into newChild
            for (int j = 0; j < t - 1; j++)
                newChild.keys[j] = fullChild.keys[j + t];
            if (!fullChild.isLeaf)
                for (int j = 0; j < t; j++)
                    newChild.children[j] = fullChild.children[j + t];
            fullChild.numKeys = t - 1;

            // Shift parent's children and keys right
            for (int j = parent.numKeys; j >= i + 1; j--)
                parent.children[j + 1] = parent.children[j];
            parent.children[i + 1] = newChild;
            for (int j = parent.numKeys - 1; j >= i; j--)
                parent.keys[j + 1] = parent.keys[j];

            // Promote the middle key to parent
            parent.keys[i] = fullChild.keys[t - 1];
            parent.numKeys++;
        }
    }

    // =========================================================
    //  MAIN — demonstration
    // =========================================================

    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println("  B-TREE SEARCH  (min-degree t = 3)");
        System.out.println("  Simulating a PRIMARY KEY index on employee_id");
        System.out.println("=====================================================\n");

        BTree tree = new BTree(3);   // each node holds 2–5 keys

        int[] employeeIds = { 10, 20, 5, 6, 12, 30, 7, 17,
                3,  25, 40, 35, 15, 22, 8 };

        System.out.print("Inserting employee IDs: ");
        for (int id : employeeIds) {
            System.out.print(id + " ");
            tree.insert(id);
        }
        System.out.println("\n");

        // ── Point lookups ────────────────────────────────────
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("POINT SEARCH: employee_id = 17");
        System.out.println("─────────────────────────────────────────────────────");
        SearchResult r1 = tree.search(17);
        System.out.println("Result: " + (r1.found
                ? "FOUND  (depth=" + r1.depth + ", pos=" + r1.position + ")"
                : "NOT FOUND"));

        System.out.println();
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("POINT SEARCH: employee_id = 99  (does not exist)");
        System.out.println("─────────────────────────────────────────────────────");
        SearchResult r2 = tree.search(99);
        System.out.println("Result: " + (r2.found ? "FOUND" : "NOT FOUND"));

        // ── Range scan ───────────────────────────────────────
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("RANGE SCAN: WHERE employee_id BETWEEN 10 AND 25");
        System.out.println("─────────────────────────────────────────────────────");
        List<Integer> rangeResult = tree.rangeScan(10, 25);
        System.out.println("Matching IDs: " + rangeResult);

        System.out.println("\nB-Tree Search completed successfully.");
    }
}

