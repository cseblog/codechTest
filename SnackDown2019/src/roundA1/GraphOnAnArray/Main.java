package roundA1.GraphOnAnArray;

import java.io.IOException;
import java.util.*;

public class Main {

    static int __gcd(int a, int b) {
        // Everything divides 0
        if (a == 0 || b == 0) {
            return 0;
        }

        // base case
        if (a == b) {
            return a;
        }

        // a is greater
        if (a > b) {
            return __gcd(a - b, b);
        }

        return __gcd(a, b - a);
    }

    // function to check and print if
    // two numbers are co-prime or not
    static boolean isCoprime(int a, int b) {
        return __gcd(a, b) == 1;
    }

    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            // Reading input
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<Integer[]> tcs = new ArrayList<>();
            while (numOfTestcases > 0) {
                int arraySize = Integer.valueOf(r.readLine());
                String[] line3 = r.readLine().split(" ");
                Integer[] data = Arrays.stream(line3).map(Integer::valueOf).toArray(a -> new Integer[line3.length]);
                tcs.add(data);
                numOfTestcases--;
            }

            //
            for (Integer[] test : tcs) {
                processing(test);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
//        processing(new Integer[]{2, 4, 6, 8, 10});

    }

    private static void processing(Integer[] test) {
        int size = test.length;
        int[][] graph = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = i == j ? 0 : isCoprime(test[i], test[j]) ? 1 : 0;
            }
        }
        primMST(graph, test);

    }


    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    static int minKey(int key[], Boolean mstSet[], int size) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < size; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    static void printMST(int parent[], int n, int graph[][]) {
//        System.out.println("Edge \tWeight");

        for (int i = 1; i < graph[0].length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" +
                    graph[i][parent[i]]);
        }
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    static void primMST(int graph[][], Integer[] test) {
        int V = graph[0].length;
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet, V);

            // Add the picked vertex to the MST Set
            if (u > 0) {
                mstSet[u] = true;
            }

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

            // graph[u][v] is non zero only for adjacent vertices of m
            // mstSet[v] is false for vertices not yet included in MST
            // Update the key only if graph[u][v] is smaller than key[v]
            {
                if (graph[u][v] != 0 && !mstSet[v] &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // print the constructed MST
//        printMST(parent, V, graph);
        List<Integer> set = new ArrayList<>();
        for (int i = 1; i < graph[0].length; i++) {
            if (graph[i][parent[i]] <= 0) {
                set.add(parent[i]);
                set.add(i);
            }
        }
        if (set.size() == 0) {
            System.out.println(0);
            for (int i = 0; i < V; i++) {
                System.out.print(test[i] + " ");
            }
            System.out.println();
        }
        else {
            System.out.println(1);
            Integer number = set.get(0);
            for (int i = 0; i < V; i++) {
                System.out.print((i == number ? 47 : test[i]) + " ");
            }
            System.out.println();
        }
    }
}
