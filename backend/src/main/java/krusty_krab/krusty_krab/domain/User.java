package krusty_krab.krusty_krab.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NodeEntity
public class User {

    @GraphId
    private Long id;
    private String username;
    private List<String> visitedEvents = new ArrayList();
    private Map<String, Float> eventToRating = new HashMap<String, Float>();
    private List<ItineraryItem> itin = new ArrayList<ItineraryItem>();

    public User() {}

    public User(String username, List<String> visitedEvents, Map<String, Float> eventToRating, List<ItineraryItem> itin) {
        this.username = username;
        this.visitedEvents = visitedEvents;
        this.eventToRating = eventToRating;
        this.itin = itin;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getVisitedEvents() {
        return visitedEvents;
    }

    public Map<String, Float> getEventToRating() {
        return eventToRating;
    }
    public List<ItineraryItem> getItin() {
        return itin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVisitedEvents(List<String> visitedEvents) {
        this.visitedEvents = visitedEvents;
    }

    public void setEventToRating(Map<String, Float> eventToRating) {
        this.eventToRating = eventToRating;
    }

    public void setItin(List<ItineraryItem> itin) {
        this.itin = itin;
    }
}
