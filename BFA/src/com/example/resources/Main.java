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

    public static void loadExample(int option, Map<String, Integer> nameToIndex, Map<Integer, String> indexToName, Graph graph) {
        String[] names;
        List<String> edges = new ArrayList<>();

        switch (option) {
            case 2 -> {
                // Exemplo normal
                names = new String[]{"A", "B", "C", "D"};
                for (int i = 0; i < names.length; i++) {
                    nameToIndex.put(names[i], i);
                    indexToName.put(i, names[i]);
                }
                edges.add("A -> B (4)");
                graph.addEdge(0, 1, 4);

                edges.add("A -> C (2)");
                graph.addEdge(0, 2, 2);

                edges.add("C -> B (1)");
                graph.addEdge(2, 1, 1);

                edges.add("B -> D (5)");
                graph.addEdge(1, 3, 5);

                edges.add("C -> D (8)");
                graph.addEdge(2, 3, 8);
            }

            case 3 -> {
                // Exemplo com ciclo negativo
                names = new String[]{"X", "Y", "Z"};
                for (int i = 0; i < names.length; i++) {
                    nameToIndex.put(names[i], i);
                    indexToName.put(i, names[i]);
                }
                edges.add("X -> Y (1)");
                graph.addEdge(0, 1, 1);

                edges.add("Y -> Z (-1)");
                graph.addEdge(1, 2, -1);

                edges.add("Z -> X (-1)");
                graph.addEdge(2, 0, -1);
            }

            case 4 -> {
                // Exemplo com ponto inacessível
                names = new String[]{"P", "Q", "R", "S"};
                for (int i = 0; i < names.length; i++) {
                    nameToIndex.put(names[i], i);
                    indexToName.put(i, names[i]);
                }
                edges.add("P -> Q (3)");
                graph.addEdge(0, 1, 3);

                edges.add("Q -> R (2)");
                graph.addEdge(1, 2, 2);
            }
        }

        System.out.println("\n=== Grafo carregado ===");
        System.out.println("Pontos:");
        for (String name : nameToIndex.keySet()) {
            System.out.println("- " + name);
        }

        System.out.println("\nRotas:");
        for (String edge : edges) {
            System.out.println("- " + edge);
        }
        System.out.println();
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        clearScreen();

        Map<String, Integer> nameToIndex = new HashMap<>();
        Map<Integer, String> indexToName = new HashMap<>();

        System.out.println("==== ROTAS COM PEDÁGIOS (BELLMAN-FORD) ====");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Criar grafo manualmente");
        System.out.println("2. Exemplo: rotas normais");
        System.out.println("3. Exemplo: ciclo negativo");
        System.out.println("4. Exemplo: pontos inacessíveis");
        System.out.print("Opção: ");
        int option = in.nextInt();
        in.nextLine();

        clearScreen();

        @SuppressWarnings("UnusedAssignment")
        int vertexQtd = 0;
        @SuppressWarnings("UnusedAssignment")
        Graph graph = null;

        if (option == 1) {
            System.out.print("Informe a quantidade de pontos (cidades, cruzamentos etc): ");
            vertexQtd = in.nextInt();
            in.nextLine();

            for (int i = 0; i < vertexQtd; i++) {
                System.out.print("Nome do ponto " + (i + 1) + ": ");
                String name = in.nextLine().trim();
                nameToIndex.put(name, i);
                indexToName.put(i, name);
            }

            System.out.print("Informe a quantidade de rotas entre os pontos: ");
            int edgeQtd = in.nextInt();
            in.nextLine();

            graph = new Graph(vertexQtd);

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
        } else if (option >= 2 && option <= 4) {
            vertexQtd = switch (option) {
                case 2 ->
                    4;
                case 3 ->
                    3;
                case 4 ->
                    4;
                default ->
                    0;
            };
            graph = new Graph(vertexQtd);
            loadExample(option, nameToIndex, indexToName, graph);
        } else {
            System.out.println("Opção inválida.");
            in.close();
            return;
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
        int[] distances = graph.bellmanFord(sourceIndex, indexToName);

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
