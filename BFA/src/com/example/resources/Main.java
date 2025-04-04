package com.example.resources;

import java.io.IOException;
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

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        clearScreen();
        Scanner in = new Scanner(System.in);

        System.out.print("Informe a quantidade de vértices: ");
        int vertexQtd = in.nextInt();
        System.out.print("Informe a quantidade de arestas: ");
        int edgeQtd = in.nextInt();

        Graph graph = new Graph(vertexQtd);

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

        graph.bellmanFord(source);

        in.close();
    }
}
