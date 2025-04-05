package com.example.resources;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void pause() {
        System.out.println("Pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadExampleGraph(Graph graph) {
        System.out.println("Carregando exemplo de rotas com pedágios e bônus...");

        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, -2);
        graph.addEdge(2, 1, -1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, -2);
        graph.addEdge(4, 5, 3);

        System.out.println("Grafo de exemplo carregado com sucesso!");
        pause();
    }

    public static void printGraph(Graph graph, int vertexQtd) {
        System.out.println("\n=== Lista de Vértices ===");
        for (int i = 0; i < vertexQtd; i++) {
            System.out.println("Vértice: " + i);
        }

        System.out.println("\n=== Lista de Arestas ===");
        List<Edges> edges = graph.getEdges();
        for (Edges edge : edges) {
            System.out.println("Origem: " + edge.getOrigin()
                    + ", Destino: " + edge.getDestiny()
                    + ", Peso: " + edge.getWeight());
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        clearScreen();
        Scanner in = new Scanner(System.in);

        System.out.println("==== ALGORITMO DE BELLMAN-FORD ====");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Criar grafo manualmente");
        System.out.println("2. Usar problema de exemplo (rotas com pedágios e bônus)");
        System.out.print("Opção: ");
        int option = in.nextInt();
        clearScreen();

        Graph graph;
        int vertexQtd;

        if (option == 1) {
            System.out.print("Informe a quantidade de vértices: ");
            vertexQtd = in.nextInt();
            System.out.print("Informe a quantidade de arestas: ");
            int edgeQtd = in.nextInt();
            clearScreen();

            graph = new Graph(vertexQtd);

            for (int i = 0; i < edgeQtd; i++) {
                System.out.println("Aresta: " + (i + 1));
                System.out.print("Informe o vértice de origem: ");
                int originVertex = in.nextInt();
                System.out.print("Informe o vértice de destino: ");
                int destinyVertex = in.nextInt();
                System.out.print("Informe o peso da aresta: ");
                int vertexWeight = in.nextInt();
                graph.addEdge(originVertex, destinyVertex, vertexWeight);
                System.out.println("");
                System.out.println("Aresta " + (i + 1) + ":");
                System.out.println("Vértice de origem: " + originVertex);
                System.out.println("Vértice de destino: " + destinyVertex);
                System.out.println("Peso da aresta: " + vertexWeight);
                pause();
                clearScreen();
            }

            int source;
            while (true) {
                System.out.print("Informe o vértice de origem para o algoritmo de Bellman-Ford: ");
                source = in.nextInt();

                if (source >= 0 && source < vertexQtd) {
                    break;
                } else {
                    System.out.println("Erro: Vértice de origem inválido! Insira um número entre 0 e " + (vertexQtd - 1));
                }
            }

            clearScreen();
            graph.bellmanFord(source);
        } else {
            vertexQtd = 6;
            graph = new Graph(vertexQtd);
            loadExampleGraph(graph);
            clearScreen();
            printGraph(graph, vertexQtd);
            System.out.print("Informe o ponto de partida do vértice: ");
            int source = in.nextInt();
            graph.bellmanFord(source);
        }

        in.close();
    }
}
