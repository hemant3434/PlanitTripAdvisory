package krusty_krab.krusty_krab.domain;

import java.sql.Time;

public class ItineraryItem {

    private String type;
    private String title;
    private float price;
    private Time startTime;
    private Time endTime;

    public ItineraryItem() {
    }

    public ItineraryItem(String type, String title, float price, Time startTime, Time endTime) {
        this.type = type;
        this.title = title;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
