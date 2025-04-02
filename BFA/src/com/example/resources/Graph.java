package com.example.resources;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Edges> edges = new ArrayList<>();
    private int vertex;

    public List<Edges> getEdges() {
        return edges;
    }

    public void setEdges(List<Edges> edges) {
        this.edges = edges;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }
}
