package krusty_krab.krusty_krab.domain;

import java.util.List;

public class Transportation {

    private double time;
    private String distance;
    private double cost;

    public Transportation() {
    }

    public Transportation(double time, String distance, double cost) {
        this.time = time;
        this.distance = distance;
        this.cost = cost;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
