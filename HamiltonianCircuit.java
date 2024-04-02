import java.io.*;
import java.util.*;

class Graph {
    int V;
    int[][] graph;
    Graph(int V) {
        this.V = V;
        graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = 0;
            }
        }
    }
    public void addEdge(int u, int v)   {
        graph[u][v] = graph[v][u] = 1;
    }
}

class HamiltonianCircuit {
    public static void main(String[] args) {
        // Graph graph = new Graph(5);
        // graph.addEdge(0, 1);
        // graph.addEdge(1, 2);
        // graph.addEdge(0, 3);
        // graph.addEdge(1, 4);
        // graph.addEdge(1, 3);
        // graph.addEdge(3, 4);
        // graph.addEdge(2, 4);

        // isHamiltonian(graph);

        Scanner input = new Scanner(System.in);
        System.out.print("Enter no. of vertices for a graph : ");
        int V = input.nextInt();
        Graph graph = new Graph(V);
        System.out.println("Enter inputs for graph (u, v)");
        int u, v;
        do {
            u = input.nextInt();
            v = input.nextInt();
            if (u != -1 && v != -1)
                graph.addEdge(u, v); 
        }while (u != -1 && v != -1);

        isHamiltonian(graph);
    }

    public static void isHamiltonian(Graph graph)  {
        int V = graph.V;
        int[] path = new int[V];
        for (int i = 0; i < V; i++) 
            path[i] = -1;
        path[0] = 0;
        if (hamiltonian(graph, path, 1) == false)   {
            System.out.println("No solution exists");
            return;
        }
        System.out.println("Resultant hamiltonian cycle");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " -> ");
        }
        System.out.println(path[0]);
        return;
    }
    public static boolean hamiltonian(Graph graph, int[] path, int pos) {
        int V = graph.V;
        if (pos == V)   {
            if (graph.graph[path[pos-1]][path[0]] == 1)
                return true;
            else    
                return false;
        }
        for (int i = 1; i < V; i++) {
            if (isSafe(i, graph, path, pos))    {
                path[pos] = i;
                if (hamiltonian(graph, path, pos+1))
                    return true;
                path[pos] = -1;
            }
        }
        return false;
    }
    public static boolean isSafe(int i, Graph graph, int[] path, int pos)    {
        if (graph.graph[path[pos-1]][i] == 0)
            return false;
        for (int x = 0; x < pos; x++)   {
            if (path[x] == i)
                return false;
        }
        return true;
    }
}
