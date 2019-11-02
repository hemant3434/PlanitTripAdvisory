package krusty_krab.krusty_krab.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Itinerary {
    private Time startTime;
    private Time endTime;
    private String home;
    private String location;
    private float maxDist;
    private List<String> activities;
    private float budget;
    private List<ItineraryItem> itin = new ArrayList<ItineraryItem>();
    private List<String> visitedEvents = new ArrayList<String>();
    private List<String> methodsOfTrans = new ArrayList<String>();
    public float minScore = 0.0f;
    public GoogleMaps gm = new GoogleMaps();

    public Itinerary() {
    }

    // Gets the next best event for the user to attend
    public Event getNextBestEvent() throws Exception{

        // Gets every event that satisfies the given filters
        List<Event> events = this.gm.getEvents(this.getItinCurTime(), this.getEndTime(), this.getItinCurLoc(), this.getLocation(), this.getItinDistLeft(), this.getActivities(), this.getItinBudgetLeft());

        // Gets event with highest score of all events received
        Event bestEvent = events.get(0);
        for(Event e: events){
            if((!this.getVisitedEvents().contains(e.getLocation())) && (e.getScore(this.getItinCurTime(), this.getItinCurLoc(), this.gm, this.getMaxDist(), this.getBudget()) > bestEvent.getScore(this.getItinCurTime(), this.getItinCurLoc(), this.gm, this.getMaxDist(), this.getBudget()))){
                bestEvent = e;
            }
        }
        // If no event exists that is not already in the itinerary, exception thrown
        if(this.getVisitedEvents().contains(bestEvent.getLocation())){
            throw new NoSuchElementException();
        }
        return bestEvent;
    }

    // Creates the itinerary
    public void createItinerary() throws Exception{
        // Starts at the specified start time, at the users home
        Time curTime = getStartTime();
        String curLoc = getHome();
        try{
            // Gets first event
            Event nextEvent = getNextBestEvent();

            // Loops until it runs out of events, or all events remaining has a score so low that they shouldn't be on the itinerary
            while(nextEvent.getScore(curTime, curLoc, this.gm, this.getMaxDist(), this.getBudget()) > minScore){
                // Gets transportation object from the next event and the current location of the user
                Transportation transp = this.gm.getTransportation(curLoc, nextEvent.getLocation(), curTime);
                // Transporation object to begin at the current time
                transp.setStartTime(curTime);
                curTime = curTime.add(transp.getExpectedLength());
                transp.setEndTime(curTime);
                //Sets next event to begin after the expected length of the transportation
                nextEvent.setStartTime(curTime);

                // Transportation and event objects are added to the itinerary
                this.itin.add(transp);
                this.itin.add(nextEvent);
                // Event added to list to indicate that it is already in the itinerary
                this.visitedEvents.add(nextEvent.getLocation());

                //Current time updated to after event is over, current location updated to event location
                curLoc = nextEvent.getLocation();
                curTime = curTime.add(nextEvent.getExpectedLength());
                nextEvent.setEndTime(curTime);
                // Gets next event
                nextEvent = getNextBestEvent();
            }
        }
        catch(NoSuchElementException e){}
        // Gets transportation object from last event, back to home, and adds it to the itinerary
        Transportation transp = this.gm.getTransportation(curLoc, getHome(), curTime);
        transp.setStartTime(curTime);
        this.itin.add(transp);
    }
    
    public void addEvent(Event newEvent) {
    	// must get a new Transportation item first
    	Transportation newTransportation = joinEvents((Event)itin.get(itin.size() - 1), newEvent);
    	itin.add(newTransportation);
    	itin.add(newEvent);
    	handleConflict(newEvent);

//		// Create new event
//		System.out.println("ADDING EVENT");
//		Event event = new Event("Hidden Leaf", "Land of Fire", "Go see the naruto", 5, 20,
//		        new Time(2019, 10, 25, 9, 30, true), new Time(2019, 10, 25, 10, 0, true),
//		        new Time(0, 0, 0, 0, 30, true), "Land of Fire", "Go");
//		itin.add(event);
//		handleConflict(event);
    }
    
    public void deleteEvent(Event event) {
    	// need to delete event
    	// iterate through the itinerary
    	for (int i = 0; i < itin.size() - 1; i++) {
    		ItineraryItem item = itin.get(i);
    		// if the event to be deleted matches the current iterated ItinItem
    		if (item instanceof Event && item.getTitle().equals(event.getTitle())) {
    			// first remove the transportation preceding it
    			itin.remove(i - 1);
    			// next remove the transportation succeeding it
    			itin.remove(i + 1);
    			// now remove the item itself;
    			itin.remove(i);
    			// now need to find a new transportation between the remaining events

    			// EDGE CASE 1: if the event is the first one
    			if (i == 1) {
    				// now find best transportation btwn next one and current location
    			} //else if (i == )
    		}
    	}
    }
    
    private void handleConflict(Event newEvent) {
	List<Event> movedEvents = new ArrayList<Event>();
	// Check if this event starts during an existing event	
	for (int i = itin.size()-1; i >= 1; i--) {
	    if (itin.get(i) instanceof Event && !itin.get(i).equals(newEvent)) {
		Event event = (Event) itin.get(i);
		
		long startTime = event.getStartTime().toMinutes();
		long endTime = event.getEndTime().toMinutes();
		long newEventStart = newEvent.getStartTime().toMinutes();
		long newEventEnd = newEvent.getEndTime().toMinutes();
		
		if (startTime <= newEventStart && newEventStart <= endTime || startTime <= newEventEnd && newEventEnd <= endTime) {
		    // There is a conflict so remove this event and transportations related to it
		    if (itin.get(i-1) instanceof Transportation) itin.remove(i-1); 
		    itin.remove(i-1);
		    if (itin.get(i-1) instanceof Transportation) itin.remove(i-1);
		    // Find an available place to move the event
		    Time newStartTime = findOpenTime(event);
		    if (!newStartTime.equals(null)) {
			// Re add the event
			event.setStartTime(newStartTime);
			event.setEndTime(newStartTime.add(event.getExpectedLength()));
			itin.add(event);
		    }
		}
	    }
	}
	
	// Reorganize the lists
	Collections.sort(itin, new Comparator<ItineraryItem>() {
	    @Override
	    public int compare(ItineraryItem i1, ItineraryItem i2) {
		return i1.getStartTime().isLessThan(i2.getStartTime()) ? -1 : 1;
	    }
	});
	
	// Add transportations where needed
	for (int i = itin.size()-1; i >= 0; i--) {
	    if (i == 0 & itin.get(i) instanceof Event) {
		itin.add(i, gm.getTransportation(home, ((Event) itin.get(i)).getLocation(), startTime));
	    } else if (itin.get(i) instanceof Event && itin.get(i-1) instanceof Event) {
		Event e1 = (Event) itin.get(i-1);
		Event e2 = (Event) itin.get(i);
		itin.add(i, gm.getTransportation(e1.getLocation(), e2.getLocation(), e1.getEndTime()));
	    }
	}
	
    }
    
    private Time findOpenTime(Event event) {
	// Required time will be time for the event as well as transportation
	for (int i = 0; i < itin.size(); i++) {
	    if (itin.get(i) instanceof Event && i != itin.size()-2) {
		Event curr = (Event) itin.get(i);
		Event next = (itin.get(i+1) instanceof Event) ? (Event) itin.get(i+1) : (Event) itin.get(i+2); // needs special case for last event
		
		// Google maps transportation doesn't work yet so this method won't work. This method works without travel time information
		Time travelToTime = gm.getTransportation(curr.getLocation(), event.getLocation(), curr.getEndTime()).getExpectedLength();
		Time travelFromTime = gm.getTransportation(event.getLocation(), next.getLocation(), next.getEndTime()).getExpectedLength();
		long travelTime = travelToTime.add(travelFromTime).toMinutes();
		long expectedTime = event.getExpectedLength().toMinutes();
		System.out.println("TIME: " + next.getStartTime().getDifference(curr.getEndTime()).toMinutes());
		System.out.println("EXPECTED TIME: " + expectedTime);
		if (next.getStartTime().getDifference(curr.getEndTime()).toMinutes() >= expectedTime + travelTime) {
		    // There is enough time for JUST the event
		    System.out.println("Found a start time");
		    return curr.getEndTime().add(travelToTime);
		}
	    }
	}
	// Unable to find an available time
	return null;
    }
    
    private Transportation joinEvents(Event startEvent, Event nextEvent) {
    	Transportation transportation = gm.getTransportation(startEvent.getLocation(), nextEvent.getLocation(), startEvent.getEndTime());
    	return transportation;
    }
    
    public List<String> getMethodsOfTrans() {
    	return this.methodsOfTrans;
    }
    
    public void addMethodsOfTrans(String transportation) {
    	methodsOfTrans.add(transportation);
    }
    
    public List<ItineraryItem> getItin() {
        return itin;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getHome() {
        return home;
    }

    public String getLocation() {
        return location;
    }

    public float getMaxDist() {
        return maxDist;
    }

    public List<String> getActivities() {
        return activities;
    }

    public float getBudget() {
        return budget;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaxDist(float maxDist) {
        this.maxDist = maxDist;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setItin(List<ItineraryItem> itin) {
        this.itin = itin;
    }

    public List<String> getVisitedEvents() {
        return visitedEvents;
    }

    public float getItinBudgetLeft() {
        float cost = 0;
        for(ItineraryItem i: getItin()){
            cost+=i.getPrice();
        }
        return this.getBudget() - cost;
    }

    public Time getItinCurTime(){
        if(this.getItin().size() == 0){
            return this.getStartTime();
        }
        else {
            return this.getItin().get(this.getItin().size() - 1).getEndTime();
        }
    }

    public String getItinCurLoc(){
        if(this.getItin().size() == 0){
            return this.getHome();
        }
        else{
            return ((Event)(this.getItin().get(this.getItin().size() - 1))).getLocation();
        }
    }

    public float getItinDistLeft(){
        float dist = 0;

        List<ItineraryItem> itin = this.getItin();
        //Loop excludes the first and last transportation events, as this should not be factored into the max distance
        for(int i = 1; i < itin.size()-1; i++){
            if(itin.get(i) instanceof Transportation){
                dist+=((Transportation)itin.get(i)).getDistance();
            }
        }
        return this.getMaxDist() - dist;
    }
}
