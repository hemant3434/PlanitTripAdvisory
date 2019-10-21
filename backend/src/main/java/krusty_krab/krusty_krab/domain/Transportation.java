package krusty_krab.krusty_krab.domain;

import java.sql.Time;

public class Transportation extends ItineraryItem {

    private Time time;
    private float distance;

    public Transportation() {
    }

    public Transportation(Time time, float distance, String title, float price, Time startTime, Time endTime) {
        super("Transportation", title, price, startTime, endTime);
        this.time = time;
        this.distance = distance;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
