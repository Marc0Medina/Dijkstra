import java.util.Arrays;
import java.util.Scanner;

public class DijkstraAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int source, int target, char[] vertices) {
        int n = graph.length;
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(distance, INF);
        Arrays.fill(previous, -1);
        distance[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(distance, visited);
            if (u == -1) break;

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != INF &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    previous[v] = u;
                }
            }
        }

        printPath(distance, previous, source, target, vertices);
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

    private static void printPath(int[] distance, int[] previous, int source, int target, char[] vertices) {
        if (distance[target] == INF) {
            System.out.println("No hay camino del vértice " + vertices[source] + " al vértice " + vertices[target]);
            return;
        }

        System.out.println("Distancia mínima de " + vertices[source] + " a " + vertices[target] + ": " + distance[target]);

        System.out.print("Camino: ");
        printRoute(previous, target, vertices);
        System.out.println();
    }

    private static void printRoute(int[] previous, int vertex, char[] vertices) {
        if (vertex == -1) {
            return;
        }
        printRoute(previous, previous[vertex], vertices);
        System.out.print(vertices[vertex] + " ");
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

        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F'};

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el vértice inicial (A-F): ");
        char start = Character.toUpperCase(scanner.next().charAt(0));
        System.out.print("Ingrese el vértice final (A-F): ");
        char end = Character.toUpperCase(scanner.next().charAt(0));

        int startIndex = start - 'A';
        int endIndex = end - 'A';

        if (startIndex < 0 || startIndex >= vertices.length || endIndex < 0 || endIndex >= vertices.length) {
            System.out.println("Vértices inválidos. Use letras de A a F.");
        } else {
            dijkstra(graph, startIndex, endIndex, vertices);
        }

        scanner.close();
    }
}
