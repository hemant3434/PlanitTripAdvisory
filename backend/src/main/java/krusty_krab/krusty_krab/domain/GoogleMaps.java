package krusty_krab.krusty_krab.domain;

import java.util.ArrayList;
import java.util.List;

public class GoogleMaps {

    public GoogleMaps() {
    }

    public Transportation getTransportation(String loc1, String loc2){
        return new Transportation();
    }

    public List<Event> getEvents(Time startTime, Time endTime, String curLoc, String location, float maxDist, List<String> activities, float budget){
        List<Event> events = new ArrayList();

        List<String> activ = new ArrayList();
        activ.add("c");
        krusty_krab.krusty_krab.domain.Time time = new krusty_krab.krusty_krab.domain.Time(1);
        //String name, Time date, String location, List<String> activities, int rating, float price, String title, Time startTime, Time endTime
        Event e1 = new Event("a", time, "b", activ, 1, 1.0f, "Museum", new krusty_krab.krusty_krab.domain.Time(2), new krusty_krab.krusty_krab.domain.Time(4), new krusty_krab.krusty_krab.domain.Time(1));
        Event e2 = new Event("a", time, "b", activ, 1, 1.0f, "Museum", new krusty_krab.krusty_krab.domain.Time(2), new krusty_krab.krusty_krab.domain.Time(4), new krusty_krab.krusty_krab.domain.Time(1));

        events.add(e1);
        events.add(e2);

        return events;
    }

    public Event getEvent(String loc){
        return new Event();
    }
}
