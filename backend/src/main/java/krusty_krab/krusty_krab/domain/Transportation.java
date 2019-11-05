package krusty_krab.krusty_krab.domain;

public class Transportation extends ItineraryItem {

    public static final String WALK = "Walk", BIKE = "Bike", TRANSIT = "Transit", DRIVE = "Drive";
    
    private float distance;
    private String icon;
    private String subtitle;

    public Transportation() {
    }

    public Transportation(float distance, String title, float price, Time startTime, Time endTime, Time expectedLength, String icon, String subtitle) {
        super("transportation", title, price, startTime, endTime, expectedLength);
        this.distance = distance;
        this.icon = icon;
        this.subtitle = subtitle;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getIcon() {
        return icon;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
