package Searching;

import java.util.*;

public class Dijkstra {

    static int[] dijkstra(int[][] graph) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        return dist;
    }

    static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 1, 0},
                {4, 0, 2, 1},
                {1, 2, 0, 5},
                {0, 1, 5, 0}
        };

        int[] distances = dijkstra(graph);

        for (int i = 0; i < distances.length; i++) {
            System.out.println("Node 0 -> Node " + i + " = " + distances[i]);
        }
    }
}
