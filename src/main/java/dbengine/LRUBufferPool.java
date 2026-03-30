package dbengine;
import java.util.*;

/**
 * ============================================================
 *  LRUBufferPool.java
 *  Package : com.dbengine.algorithms
 *  Class   : LRUBufferPool
 * ============================================================
 *
 *  Algorithm : LRU Buffer Pool — Least-Recently-Used Page Cache
 *
 *  Used by the DB engine as the:
 *    Buffer Manager   (InnoDB buffer pool, PostgreSQL shared_buffers,
 *                      SQL Server buffer pool, Oracle SGA)
 *
 *  ── Why It Exists ─────────────────────────────────────────
 *  Disk I/O is ~1,000× slower than RAM access.
 *  The buffer pool keeps frequently-accessed pages in memory
 *  to avoid expensive disk reads.
 *
 *  A "page" is the unit of I/O (typically 4 KB or 16 KB).
 *  Every table read or write goes through the buffer pool.
 *
 *  ── LRU Eviction Policy ───────────────────────────────────
 *  When the pool is full and a new page must be loaded:
 *    • Evict the page that was LEAST RECENTLY USED.
 *    • This assumes recently-used pages are more likely to be
 *      needed again ("temporal locality").
 *
 *  ── Data Structures for O(1) Operations ──────────────────
 *  Naïve LRU (scan a list) costs O(n) per access — too slow
 *  for a busy database.  Instead, we combine:
 *
 *    HashMap<pageId → Node>
 *      Gives O(1) lookup: "Is this page in the pool?"
 *
 *    Doubly-Linked List  (MRU head ←→ ... ←→ LRU tail)
 *      Gives O(1) reorder: move the accessed node to the front.
 *      Gives O(1) eviction: always evict from the tail.
 *
 *  Together, both GET and PUT are O(1) amortised.
 *
 *  ── Operations ────────────────────────────────────────────
 *  getPage(id)        Cache hit  → return data, move to MRU.
 *                     Cache miss → return -1 (load from disk).
 *  loadPage(id,data)  Load page into pool.
 *                     If pool full → evict LRU page first.
 *  isDirty / flush    (tracked but not written to disk here)
 *
 *  ── Complexity ────────────────────────────────────────────
 *  Time  : O(1)          per get and per load
 *  Space : O(capacity)   fixed pool size set by DBA
 * ============================================================
 */
public class LRUBufferPool {

    // ── Internal doubly-linked list node ─────────────────────
    private static class PageNode {
        int     pageId;
        int     data;
        boolean dirty;      // true = page was modified, must flush to disk
        PageNode prev, next;

        PageNode(int pageId, int data) {
            this.pageId = pageId;
            this.data   = data;
            this.dirty  = false;
        }

        @Override
        public String toString() {
            return "Page[" + pageId + ", data=" + data
                    + (dirty ? ", DIRTY" : "") + "]";
        }
    }

    // ── Pool state ────────────────────────────────────────────
    private final int                    capacity;
    private final Map<Integer, PageNode> cache    = new LinkedHashMap<>();
    private final PageNode               head;    // MRU sentinel (dummy)
    private final PageNode               tail;    // LRU sentinel (dummy)

    // Statistics
    private int hits   = 0;
    private int misses = 0;
    private int evictions = 0;

    public LRUBufferPool(int capacity) {
        this.capacity = capacity;
        head = new PageNode(-1, -1);   // head.next = most recently used
        tail = new PageNode(-1, -1);   // tail.prev = least recently used
        head.next = tail;
        tail.prev = head;
    }

    // =========================================================
    //  getPage(pageId)
    //  Returns the page data on HIT, -1 on MISS.
    //  On hit, the page is promoted to the MRU (front) position.
    // =========================================================

    public int getPage(int pageId) {
        if (!cache.containsKey(pageId)) {
            misses++;
            System.out.println("  MISS  pageId=" + pageId
                    + "  → must load from disk");
            return -1;                               // caller must loadPage()
        }
        hits++;
        PageNode node = cache.get(pageId);
        moveToFront(node);                           // promote to MRU
        System.out.println("  HIT   " + node
                + "  | pool order: " + poolOrder());
        return node.data;
    }

    // =========================================================
    //  loadPage(pageId, data)
    //  Brings a page from disk into the buffer pool.
    //  If the pool is at capacity, the LRU page is evicted first.
    // =========================================================

    public void loadPage(int pageId, int data) {
        if (cache.containsKey(pageId)) {
            // Page already in pool — just update and promote
            PageNode node = cache.get(pageId);
            node.data = data;
            moveToFront(node);
            System.out.println("  UPDATE " + node
                    + "  | pool order: " + poolOrder());
            return;
        }

        if (cache.size() == capacity) {
            // Pool full — evict LRU page (node just before tail)
            PageNode lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.pageId);
            evictions++;
            System.out.println("  EVICT  " + lru
                    + (lru.dirty ? "  ← flushed to disk" : ""));
        }

        // Insert new page at front (MRU position)
        PageNode newNode = new PageNode(pageId, data);
        cache.put(pageId, newNode);
        insertAtFront(newNode);
        System.out.println("  LOAD   " + newNode
                + "  | pool order: " + poolOrder());
    }

    // =========================================================
    //  markDirty(pageId)
    //  Called when a page is modified (UPDATE / DELETE).
    //  Dirty pages must be flushed to disk before eviction.
    // =========================================================

    public void markDirty(int pageId) {
        PageNode node = cache.get(pageId);
        if (node != null) {
            node.dirty = true;
            System.out.println("  DIRTY  " + node);
        }
    }

    // =========================================================
    //  Doubly-Linked List helpers
    // =========================================================

    private void removeNode(PageNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(PageNode node) {
        node.next      = head.next;
        node.prev      = head;
        head.next.prev = node;
        head.next      = node;
    }

    private void moveToFront(PageNode node) {
        removeNode(node);
        insertAtFront(node);
    }

    // =========================================================
    //  Diagnostics
    // =========================================================

    /** Returns page IDs from MRU (left) to LRU (right). */
    public List<Integer> poolOrder() {
        List<Integer> order = new ArrayList<>();
        PageNode cur = head.next;
        while (cur != tail) {
            order.add(cur.pageId);
            cur = cur.next;
        }
        return order;
    }

    public void printStats() {
        System.out.println("\n── Buffer Pool Statistics ─────────────────────────");
        System.out.println("  Capacity  : " + capacity + " pages");
        System.out.println("  Occupancy : " + cache.size() + " / " + capacity);
        System.out.println("  Cache Hits: " + hits);
        System.out.println("  Misses    : " + misses);
        System.out.println("  Evictions : " + evictions);
        double hitRate = (hits + misses) == 0 ? 0
                : (hits * 100.0 / (hits + misses));
        System.out.printf("  Hit Rate  : %.1f%%%n", hitRate);
        System.out.println("  Pool order (MRU → LRU): " + poolOrder());
    }

    // =========================================================
    //  MAIN — demonstration
    // =========================================================

    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println("  LRU BUFFER POOL  (capacity = 4 pages)");
        System.out.println("  Simulating buffer manager reading table pages");
        System.out.println("=====================================================\n");

        LRUBufferPool pool = new LRUBufferPool(4);

        System.out.println("── Loading initial pages from disk ─────────────────");
        pool.loadPage(101, 1001);
        pool.loadPage(102, 1002);
        pool.loadPage(103, 1003);
        pool.loadPage(104, 1004);

        System.out.println("\n── Page 101 is accessed (SELECT) ───────────────────");
        pool.getPage(101);                  // HIT — moves 101 to MRU

        System.out.println("\n── New page 105 needed (pool is full) ──────────────");
        pool.loadPage(105, 1005);           // evicts LRU (page 102)

        System.out.println("\n── Trying to access evicted page 102 ───────────────");
        int data = pool.getPage(102);       // MISS — no longer in pool
        if (data == -1) {
            System.out.println("  Re-loading page 102 from disk ...");
            pool.loadPage(102, 1002);       // evicts next LRU (page 103)
        }

        System.out.println("\n── Marking page 104 as dirty (UPDATE statement) ────");
        pool.markDirty(104);

        System.out.println("\n── Access pattern: 101, 102, 104 ───────────────────");
        pool.getPage(101);
        pool.getPage(102);
        pool.getPage(104);

        System.out.println("\n── Loading page 106 (will evict dirty page 105) ────");
        pool.loadPage(106, 1006);

        pool.printStats();

        System.out.println("\nLRU Buffer Pool simulation completed successfully.");
    }
}
