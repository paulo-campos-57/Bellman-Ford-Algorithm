package com.example.resources;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(5); 
        
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 4, -4);
        graph.addEdge(2, 3, -3);
        graph.addEdge(2, 4, 9);
        graph.addEdge(3, 1, -2);
        graph.addEdge(4, 3, 7);

        graph.bellmanFord(0);
    }
}
