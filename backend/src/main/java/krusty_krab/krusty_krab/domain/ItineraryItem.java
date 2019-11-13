package krusty_krab.krusty_krab.domain;

public class ItineraryItem {

    private String type;
    private String title;
    private float price;
    private Time startTime;
    private Time endTime;
    private Time expectedLength;

    public ItineraryItem() {
    }

    public ItineraryItem(String type, String title, float price, Time startTime, Time endTime, Time expectedLength) {
        this.type = type;
        this.title = title;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedLength = expectedLength;
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

    public Time getExpectedLength() {
        return expectedLength;
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

    public void setExpectedLength(Time expectedLength) {
        this.expectedLength = expectedLength;
    }
}
