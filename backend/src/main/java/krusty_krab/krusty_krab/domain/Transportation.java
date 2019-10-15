package krusty_krab.krusty_krab.domain;

import java.sql.Time;

public class Transportation {

    private Time time;
    private String distance;
    private double cost;
    private String method;

    public Transportation() {
    }

    public Transportation(Time time, String distance, double cost) {
        this.time = time;
        this.distance = distance;
        this.cost = cost;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
