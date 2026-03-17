package Searching;

import java.util.*;

public class BellmanFord {

    static int[] bellmanFord(int[][] edges, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 1; i <= V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Negative cycle detected!");
                return null;
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                {0, 1, 1},
                {0, 2, 4},
                {1, 2, -2},
                {1, 3, 3},
                {2, 3, 2}
        };

        int[] distances = bellmanFord(edges, V, 0);

        if (distances != null) {
            for (int i = 0; i < distances.length; i++) {
                System.out.println("Node 0 -> Node " + i + " = " + distances[i]);
            }
        }
    }
}
