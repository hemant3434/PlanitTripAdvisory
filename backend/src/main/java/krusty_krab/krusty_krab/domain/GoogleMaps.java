package krusty_krab.krusty_krab.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleMaps {

    private List<String> visitedEvents = new ArrayList();

    public GoogleMaps() {

    }

    public Transportation getTransportation(String loc1, String loc2, Time startTime){

        if(loc1.equals("Union Station") && loc2.equals("Ripley's Aquarium")){
            return new Transportation(10, "Walk", 0, startTime, startTime.add(new Time(0, 0, 0, 0, 15, true)), new Time(0, 0, 0, 0, 15, true));
        }
        if(loc1.equals("Union Station") && loc2.equals("CN Tower")){
            return new Transportation(15, "Bus", 3, startTime, startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true));
        }
        if(loc1.equals("Spadina") && loc2.equals("Ripley's Aquarium")){
            return new Transportation(100, "Bus", 20, startTime, startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true));
        }
        if(loc1.equals("Spadina") && loc2.equals("CN Tower")){
            return new Transportation(5, "Walk", 0, startTime, startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true));
        }

        return new Transportation();
    }

    public List<Event> getEvents(Time startTime, Time endTime, String curLoc, String location, float maxDist, List<String> activities, float budget){
        List<Event> events = new ArrayList();

        Event e1 = new Event("Ripley's Aquarium", "Ripley's Aquarium", "aquarium", 5, 20, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true));
        Event e2 = new Event("CN Tower", "CN Tower", "lookout", 4, 40, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true));


        //events.add(e2);

        if(!getVisitedEvents().contains(e1.getLocation())){
            events.add(e1);
            this.visitedEvents.add(e1.getLocation());
        }
        if(!getVisitedEvents().contains(e2.getLocation())){
            events.add(e2);
            this.visitedEvents.add(e2.getLocation());
        }

        return events;
    }

    public List<String> getVisitedEvents() {
        return visitedEvents;
    }

    public void setVisitedEvents(List<String> visitedEvents) {
        this.visitedEvents = visitedEvents;
    }
}
