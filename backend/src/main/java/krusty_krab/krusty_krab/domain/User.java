package krusty_krab.krusty_krab.domain;

import java.util.*;

public class User {

    private Long id;
    private String username;
    private List<String> visitedEvents = new ArrayList();
    private Map<String, Integer> eventRatings = new HashMap<>();
    private Itinerary itinerary = new Itinerary();

    public User() {}

    public User(String username, List<String> visitedEvents, Map<String, Integer> eventRatings, Itinerary itinerary) {
        this.username = username;
        this.visitedEvents = visitedEvents;
        this.eventRatings = eventRatings;
        this.itinerary = itinerary;
    }

    //Returns the user's average rating for all events of the same activity as the one that is given
    public float getActivityRating(String activity){
        float sum = 0;
        float numEvents = 0;
        List<String> activities;
        for(String ratedEvent:eventRatings.keySet()){
            Event e = GoogleMaps.getEventByID(ratedEvent);
            activities = Arrays.asList(e.getActivity().split(", "));
            if(activities.contains(activity)){
                sum += eventRatings.get(ratedEvent);
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
        for(String key:eventRatings.keySet()){
            if(key.equals(e.getId())){
                return eventRatings.get(key);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVisitedEvents(List<String> visitedEvents) {
        this.visitedEvents = visitedEvents;
    }

    public Map<String, Integer> getEventRatings() {
        return eventRatings;
    }

    public void setEventRatings(Map<String, Integer> eventRatings) {
        this.eventRatings = eventRatings;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }


}
