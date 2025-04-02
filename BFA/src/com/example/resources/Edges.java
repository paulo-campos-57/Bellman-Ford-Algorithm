package com.example.resources;

public class Edges {

    private int origin;
    private int destiny;
    private int weight;

    public Edges(int origin, int destiny, int weight) {
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestiny() {
        return destiny;
    }

    public void setDestiny(int destiny) {
        this.destiny = destiny;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
