package edu.olezha.sandbox.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void main(String[] args) {
        bfs(Graph.mock(), 2);
        bfs(Graph.mock(), 0);
    }

    // Print traversal from a given source
    static void bfs(Graph graph, int s) {
        boolean[] visited = new boolean[graph.v];

        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int n : graph.adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        System.out.println();
    }
}
