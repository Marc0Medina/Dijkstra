import java.util.Arrays;

public class DijkstraAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, INF);
        distance[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != INF &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        printSolution(distance);
    }

    private static int minDistance(int[] distance, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < distance.length; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printSolution(int[] distance) {
        System.out.println("Distancia más corta");
        for (int i = 0; i < distance.length; i++) {
            System.out.println("Hacia el vértice " + i + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 4, 0, 0},
                {1, 0, 2, 0, 0, 0},
                {0, 2, 0, 0, 5, 0},
                {4, 0, 0, 0, 1, 3},
                {0, 0, 5, 1, 0, 1},
                {0, 0, 0, 3, 1, 0}
        };

        dijkstra(graph, 0);
    }
}