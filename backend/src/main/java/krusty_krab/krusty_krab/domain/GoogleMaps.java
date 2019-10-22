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

        if(loc1.equals("union station") && loc2.equals("ripley's aquarium")){
            return new Transportation(10, "walk", 0, startTime, startTime.add(new Time(0, 0, 0, 0, 15, true)), new Time(0, 0, 0, 0, 15, true), "flight-takeoff", "15 minutes");
        }
        if(loc1.equals("union station") && loc2.equals("cn tower")){
            return new Transportation(15, "bus", 3, startTime, startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true), "flight-takeoff", "5 minutes");
        }
        if(loc1.equals("spadina") && loc2.equals("ripley's aquarium")){
            return new Transportation(100, "bus", 20, startTime, startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true), "flight-takeoff", "30 minutes");
        }
        if(loc1.equals("spadina") && loc2.equals("cn tower")){
            return new Transportation(5, "walk", 0, startTime, startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true), "flight-takeoff", "5 minutes");
        }

        return new Transportation();
    }

    public List<Event> getEvents(Time startTime, Time endTime, String curLoc, String location, float maxDist, List<String> activities, float budget){
        List<Event> events = new ArrayList();

        Event e1 = new Event("ripley's aquarium", "ripley's aquarium", "aquarium", 5, 20, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true), "../images/toronto.jpg", "There be fish");
        Event e2 = new Event("cn tower", "cn tower", "lookout", 4, 40, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true),"../images/toronto.jpg", "If Quebec is Canada's ass...");


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
