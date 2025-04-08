package com.example.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Graph {

    private List<Edges> edges = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private int vertex;

    public Graph(int vertex) {
        this.edges = new ArrayList<>();
        this.vertex = vertex;
    }

    public void addEdge(int origin, int destiny, int weight) {
        edges.add(new Edges(origin, destiny, weight));
    }

    public List<Edges> getEdges() {
        return edges;
    }

    public int getVertex() {
        return vertex;
    }

    public int[] bellmanFord(int source, Map<Integer, String> indexToName) {
        int[] distance = new int[vertex];
        int[] predecessor = new int[vertex];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);
        distance[source] = 0;

        for (int i = 1; i < vertex; i++) {
            for (Edges edge : edges) {
                int u = edge.getOrigin();
                int v = edge.getDestiny();
                int weight = edge.getWeight();

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    predecessor[v] = u;
                }
            }
        }

        for (Edges edge : edges) {
            int u = edge.getOrigin();
            int v = edge.getDestiny();
            int weight = edge.getWeight();

            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Ciclo negativo detectado!");

                int current = v;
                for (int i = 0; i < vertex; i++) {
                    current = predecessor[current];
                }

                List<Integer> cycle = new ArrayList<>();
                int start = current;
                do {
                    cycle.add(current);
                    current = predecessor[current];
                } while (current != start && !cycle.contains(current));
                cycle.add(start);
                Collections.reverse(cycle);

                System.out.print("Ciclo encontrado entre os pontos: ");
                for (int i = 0; i < cycle.size(); i++) {
                    int node = cycle.get(i);
                    System.out.print(indexToName.get(node));
                    if (i < cycle.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println("\nEsse ciclo permite reduzir indefinidamente o custo total.");

                return distance;
            }
        }

        return distance;
    }

}
