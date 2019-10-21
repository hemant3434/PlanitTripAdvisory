package krusty_krab.krusty_krab.domain;

public class Transportation extends ItineraryItem {

    private float distance;

    public Transportation() {
    }

    public Transportation(float distance, String title, float price, Time startTime, Time endTime, Time expectedLength) {
        super("Transportation", title, price, startTime, endTime, expectedLength);
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
