package edu.olezha.sandbox.algo.graph;

public class DFS {

    public static void main(String[] args) {
        dfs(Graph.mock(), 2);
        dfs(Graph.mock(), 0);
    }

    // Print traversal from a given source
    static void dfs(Graph graph, int s) {
        boolean[] visited = new boolean[graph.v];
        dfs(graph, s, visited);
        System.out.println();
    }

    private static void dfs(Graph graph, int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s + " ");

        for (int n : graph.adj[s]) {
            if (!visited[n]) {
                dfs(graph, n, visited);
            }
        }
    }
}
