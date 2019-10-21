package krusty_krab.krusty_krab.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    List<ItineraryItem> itin = new ArrayList();

    public Itinerary() {}

    public Itinerary(List<ItineraryItem> itin) {
        this.itin = itin;
    }

    private List<Event> getEvents(Time date, Time startTime, Time endTime, String location, float maxDist, List<String> activities, float budget){
        List<Event> events = new ArrayList();

        List<String> activ = new ArrayList();
        activ.add("c");
        Time time = new Time(1);
        //String name, Time date, String location, List<String> activities, int rating, float price, String title, Time startTime, Time endTime
        Event e1 = new Event("a", time, "b", activ, 1, 1.0f, "Museum", new Time(2), new Time(4));
        Event e2 = new Event("a", time, "b", activ, 1, 1.0f, "Museum", new Time(2), new Time(4));

        events.add(e1);
        events.add(e2);

        return events;
    }

    private Event getNextBestEvent(Time date, Time startTime, Time endTime, String curLoc, float maxDist, List<String>activities, float budget){
        //3 rating, 5 time between events, 1 maxDist, 1 budget
        List<Event> events = this.getEvents(date, startTime, endTime, curLoc, maxDist, activities, budget);
        Event bestEvent = events.get(0);

        for(Event e: events){
            if(e.getScore(startTime, curLoc) > bestEvent.getScore(startTime, curLoc)){bestEvent = e;}
        }
        return bestEvent;
    }
}
