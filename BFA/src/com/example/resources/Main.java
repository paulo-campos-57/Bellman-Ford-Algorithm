package com.example.resources;

import java.io.IOException;
import java.util.*;

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
        Scanner in = new Scanner(System.in);
        clearScreen();

        Map<String, Integer> nameToIndex = new HashMap<>();
        Map<Integer, String> indexToName = new HashMap<>();

        System.out.println("==== ROTAS COM PEDÁGIOS (ALGORITMO DE BELLMAN-FORD) ====");
        System.out.print("Informe a quantidade de pontos (cidades, cruzamentos etc): ");
        int vertexQtd = in.nextInt();
        in.nextLine(); // limpa buffer

        for (int i = 0; i < vertexQtd; i++) {
            System.out.print("Nome do ponto " + (i + 1) + ": ");
            String name = in.nextLine().trim();
            nameToIndex.put(name, i);
            indexToName.put(i, name);
        }

        System.out.print("Informe a quantidade de rotas entre os pontos: ");
        int edgeQtd = in.nextInt();
        in.nextLine();

        Graph graph = new Graph(vertexQtd);

        for (int i = 0; i < edgeQtd; i++) {
            System.out.println("\nRota " + (i + 1) + ":");
            System.out.print("Ponto de origem: ");
            String origin = in.nextLine().trim();
            System.out.print("Ponto de destino: ");
            String destiny = in.nextLine().trim();
            System.out.print("Preço do pedágio (pode ser negativo se for bônus): ");
            int weight = in.nextInt();
            in.nextLine();

            if (!nameToIndex.containsKey(origin) || !nameToIndex.containsKey(destiny)) {
                System.out.println("Erro: Um dos pontos informados não existe!");
                i--;
                continue;
            }

            graph.addEdge(nameToIndex.get(origin), nameToIndex.get(destiny), weight);
        }

        System.out.print("\nInforme o ponto de partida: ");
        String sourceName = in.nextLine().trim();

        if (!nameToIndex.containsKey(sourceName)) {
            System.out.println("Erro: ponto de partida inválido!");
            in.close();
            return;
        }

        int sourceIndex = nameToIndex.get(sourceName);

        System.out.println("\nCalculando menores custos a partir de " + sourceName + "...\n");
        int[] distances = graph.bellmanFord(sourceIndex);

        System.out.println("\n==== Resultados ====");
        for (int i = 0; i < vertexQtd; i++) {
            String destino = indexToName.get(i);
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Para " + destino + " -> Inacessível");
            } else {
                System.out.println("Para " + destino + " -> Custo total: " + distances[i]);
            }
        }

        in.close();
    }
}
