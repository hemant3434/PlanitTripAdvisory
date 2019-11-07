package krusty_krab.krusty_krab.domain;

import krusty_krab.krusty_krab.controller.MainController;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.beans.factory.annotation.Autowired;

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
    private List<Float> eventRatings = new ArrayList();
    private List<ItineraryItem> itin = new ArrayList<ItineraryItem>();

    public User() {}

    public User(String username, List<String> visitedEvents, List<Float> eventRatings, List<ItineraryItem> itin) {
        this.username = username;
        this.visitedEvents = visitedEvents;
        this.eventRatings = eventRatings;
        this.itin = itin;
    }

    //Returns the user's average rating for all events of the same activity as the one that is given
    public float getActivityRating(String activity){
        float sum = 0;
        float numEvents = 0;
        for(int i=0; i < visitedEvents.size();i++){
            Event e = GoogleMaps.getEventByID(visitedEvents.get(i));
            if(e.getActivity().equals(activity)){
                sum += eventRatings.get(i);
                numEvents++;
            }
        }
        //If user has never participated in event before, returns neutral rating of 2.5/5
        if(numEvents == 0){
            return 2.5f;
        }
        else {
            return sum/numEvents;
        }
    }

    //Returns if user has visited a given event
    public boolean hasVisitedEvent(Event e){
        for(int i=0; i < visitedEvents.size();i++){
            if(visitedEvents.get(i).equals(e.getId())){
                return true;
            }
        }
        return false;
    }

    //Returns rating given by the user of a visited event
    public float getRating(Event e){
        for(int i=0; i < visitedEvents.size();i++){
            if(visitedEvents.get(i).equals(e.getId())){
                return eventRatings.get(i);
            }
        }
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getVisitedEvents() {
        return visitedEvents;
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

    public void setItin(List<ItineraryItem> itin) {
        this.itin = itin;
    }

    public List<Float> getEventRatings() {
        return eventRatings;
    }

    public void setEventRatings(List<Float> eventRatings) {
        this.eventRatings = eventRatings;
    }
}
