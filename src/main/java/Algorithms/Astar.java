package Algorithms;

import java.util.*;

public class Astar {

    public static class Node {
        int x, y;
        double g, h, f;
        Node parent;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.f = 0;
            this.parent = null;
        }
    }

    static double heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static List<Node> findPath(boolean[][] grid, int startX, int startY,
                                      int goalX,  int goalY) {
        int rows = grid.length;
        int cols = grid[0].length;

        Node[][] nodes = new Node[rows][cols];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                nodes[r][c] = new Node(c, r);

        PriorityQueue<Node> openList = new PriorityQueue<>(
                Comparator.comparingDouble(n -> n.f)
        );

        Set<Node> closedSet = new HashSet<>();

        Node startNode = nodes[startY][startX];
        Node goalNode  = nodes[goalY][goalX];

        startNode.g = 0;
        startNode.h = heuristic(startNode, goalNode);
        startNode.f = startNode.g + startNode.h;

        openList.add(startNode);

        while (!openList.isEmpty()) {

            Node current = openList.poll();

            if (current.x == goalNode.x && current.y == goalNode.y) {
                return buildPath(current);
            }

            closedSet.add(current);

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int dir = 0; dir < 4; dir++) {
                int nx = current.x + dx[dir];
                int ny = current.y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= cols || ny >= rows) continue;
                if (grid[ny][nx]) continue;

                Node neighbor = nodes[ny][nx];

                if (closedSet.contains(neighbor)) continue;

                double newG = current.g + 1;

                if (newG < neighbor.g || neighbor.g == 0) {
                    neighbor.g = newG;
                    neighbor.h = heuristic(neighbor, goalNode);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = current;
                    openList.add(neighbor);
                }
            }
        }

        System.out.println("No path found!");
        return new ArrayList<>();
    }

    static List<Node> buildPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;

        while (current != null) {
            path.add(current);
            current = current.parent;
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        boolean[][] grid = {
                {false, false, false, false, false},
                {false, true,  true,  false, false},
                {false, false, true,  false, false},
                {false, false, true,  true,  false},
                {false, false, false, false, false}
        };

        List<Node> path = findPath(grid, 0, 0, 4, 4);

        if (!path.isEmpty()) {
            System.out.println("Path found! Steps: " + (path.size() - 1));
            System.out.print("Route: ");
            for (Node n : path) {
                System.out.print("(" + n.x + "," + n.y + ") ");
            }
            System.out.println();
        }
    }
}
