package com.example.resources;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Edges> edges = new ArrayList<>();
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

    public void bellmanFord(int source) {
        int[] distance = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        for (int i = 1; i < vertex; i++) {
            for (Edges edge : edges) {
                if (distance[edge.getOrigin()] != Integer.MAX_VALUE &&
                        distance[edge.getOrigin()] + edge.getWeight() < distance[edge.getDestiny()]) {
                    distance[edge.getDestiny()] = distance[edge.getOrigin()] + edge.getWeight();
                }
            }
        }

        for (Edges edge : edges) {
            if (distance[edge.getOrigin()] != Integer.MAX_VALUE &&
                    distance[edge.getOrigin()] + edge.getWeight() < distance[edge.getDestiny()]) {
                System.out.println("Ciclo negativo detectado!");
                return;
            }
        }

        System.out.println("Distâncias mínimas a partir do vértice " + source + ":");
        for (int i = 0; i < vertex; i++) {
            System.out.println("Para " + i + " -> " + (distance[i] == Integer.MAX_VALUE ? "Infinito" : distance[i]));
        }
    }

}
