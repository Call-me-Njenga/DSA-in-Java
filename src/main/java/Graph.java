import java.util.*;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    private int vertices;
    private List<List<Integer>> adj;

    Graph(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adj.add(new ArrayList<>());
    }

    // Add undirected edge between src and dest
    void addEdge(int src, int dest) {
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }

    // BFS — level by level using a Queue — O(V+E)
    void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // DFS — go deep first using Recursion — O(V+E)
    void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        dfsRec(start, visited);
    }

    void dfsRec(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor])
                dfsRec(neighbor, visited);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);

        //        0
        //       / \
        //      1   2
        //     / \ / \
        //    3  4 5  6

        System.out.print("BFS (level by level): "); g.bfs(0); System.out.println();
        System.out.print("DFS (deep first):      "); g.dfs(0); System.out.println();

        // Expected:
        // BFS: 0 1 2 3 4 5 6
        // DFS: 0 1 3 4 2 5 6
    }
}