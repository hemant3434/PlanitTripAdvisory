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
    private String location;
    private double locationLat;
    private double locationLong;
    private String home;
    private double homeLat;
    private double homeLong;
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
    public Event getNextBestEvent(User user) throws Exception {

	// Converts remaining budget to an events price range, to see how expensive of
	// an event the itinerary can take
	float maxPriceRange = GoogleMaps.budgetToRange(this.getItinBudgetLeft());
	// Gets every event that satisfies the given filters
	List<Event> events = this.gm.getEvents(this.getItinCurTime(), this.getEndTime(), this.getItinCurLoc(),
		this.getLocation(), this.getItinDistLeft(), this.getActivities(), maxPriceRange);

	// Gets event with highest score of all events received
	Event bestEvent = events.get(0);
	float bestScore = 0;
	float curScore;
	// GoogleMaps.getEventByID("3").getScore(this.getItinCurTime(),
	// this.getItinCurLoc(), this.gm, this.getMaxDist(), this.getBudget(), user);
	// System.out.println("a");
	for (Event e : events) {
	    // System.out.println(e.getLocation());
	    if (!this.getVisitedEvents().contains(e.getLocation())) {
		curScore = e.getScore(this.getItinCurTime(), this.getItinCurLoc(), this.gm, this.getMaxDist(),
			this.getBudget(), user);
		if (curScore > bestScore) {
		    bestEvent = e;
		    bestScore = curScore;
		}
	    }
	}

	// If no event exists that is not already in the itinerary, exception thrown
	if (bestScore == 0) {
	    throw new NoSuchElementException();
	}
	return bestEvent;
    }

    // Creates the itinerary
    public void createItinerary(User user) throws Exception {
	// Starts at the specified start time, at the users home
	Time curTime = getStartTime();
	String curLoc = getHome();
	try {
	    // Gets first event
	    Event nextEvent = getNextBestEvent(user);

	    // Loops until it runs out of events, or all events remaining has a score so low
	    // that they shouldn't be on the itinerary
	    while (nextEvent.getScore(curTime, curLoc, this.gm, this.getMaxDist(), this.getBudget(), user) > minScore) {
		// Gets transportation object from the next event and the current location of
		// the user
		Transportation transp = this.gm.getTransportation(curLoc, nextEvent.getLocation(), curTime);
		// Transporation object to begin at the current time
		transp.setStartTime(curTime);
		curTime = curTime.add(transp.getExpectedLength());
		transp.setEndTime(curTime);
		// Sets next event to begin after the expected length of the transportation
		nextEvent.setStartTime(curTime);

		// Transportation and event objects are added to the itinerary
		this.itin.add(transp);
		this.itin.add(nextEvent);
		// Event added to list to indicate that it is already in the itinerary
		this.visitedEvents.add(nextEvent.getLocation());

		// Current time updated to after event is over, current location updated to
		// event location
		curLoc = nextEvent.getLocation();
		curTime = curTime.add(nextEvent.getExpectedLength());
		nextEvent.setEndTime(curTime);
		// Gets next event
		nextEvent = getNextBestEvent(user);
	    }
	} catch (NoSuchElementException e) {
	}
	// Gets transportation object from last event, back to home, and adds it to the
	// itinerary
	Transportation transp = this.gm.getTransportation(curLoc, getHome(), curTime);
	transp.setStartTime(curTime);
	this.itin.add(transp);
    }

    public void addEvent(Event newEvent) {
	// must get a new Transportation item first
//	Transportation newTransportation = joinEvents((Event) itin.get(itin.size() - 1), newEvent);
//	itin.add(newTransportation);
//	itin.add(newEvent);
//	handleConflict(newEvent);

	// Create new event
	System.out.println("ADDING EVENT");
//
//		Event event = new Event("Hidden Leaf", "Land of Fire", 49.2, 49.2, "Go see the naruto", 5, 20,
//		        new Time(2019, 10, 25, 9, 30, true), new Time(2019, 10, 25, 10, 0, true),
//		        new Time(0, 0, 0, 0, 30, true), "Land of Fire", "Go", "tempID");
//		itin.add(event);
//		handleConflict(event);

	Event e1 = new Event("ripley's aquarium", "ripley's aquarium", 43.2, 43.2, "aquarium", 5, 2,
		new Time(2019, 10, 25, 13, 0, true), new Time(2019, 10, 25, 14, 0, true),
		new Time(0, 0, 0, 1, 0, true), "toronto", "There be fish", "1");
	itin.add(e1);
	handleConflict(e1);
    }

    public void deleteEvent(Event event) {
	// need to delete event
	// iterate through the itinerary
	for (int i = 0; i < itin.size() - 1; i++) {
	    ItineraryItem item = itin.get(i);
	    // if the event to be deleted matches the current iterated ItinItem
	    if (item instanceof Event && ((Event) item).getId().equals(event.getId())) {
		Transportation fillerTrans = new Transportation();
		// remove the transportation before, event itself, then transportation after
		for (int j = 0; j < 3; j++) {
		    itin.remove(i - 1);
		}
		if (i == 1) {
		    // EDGE CASE 1: first event being deleted, must join home with next event
		    Event successorEvent = new Event();
		    successorEvent = (Event) itin.get(0);
		    fillerTrans = joinEvents(this.location, successorEvent.getLocation(), this.startTime);
		} else if (i == itin.size() - 2) {
		    // EDGE CASE 2: last event being deleted, must join last event with home
		    Event predecessorEvent = new Event();
		    predecessorEvent = (Event) itin.get(i - 2);
		    fillerTrans = joinEvents(predecessorEvent.getLocation(), this.location,
			    predecessorEvent.getEndTime());
		} else {
		    // ELSE: deleted event is inside the list
		    Event predecessorEvent = new Event();
		    predecessorEvent = (Event) itin.get(i - 2);
		    Event successorEvent = new Event();
		    successorEvent = (Event) itin.get(i - 1);
		    fillerTrans = joinEvents(predecessorEvent.getLocation(), successorEvent.getLocation(),
			    predecessorEvent.getEndTime());
		}
		itin.add(i - 1, fillerTrans);
		break;
	    }
	}
    }

    private void handleConflict(Event newEvent) {
	// Reorganize the lists
	Comparator<ItineraryItem> itinerarySorter = new Comparator<ItineraryItem>() {
	    @Override
	    public int compare(ItineraryItem i1, ItineraryItem i2) {
		return i1.getStartTime().isLessThan(i2.getStartTime()) ? -1 : 1;
	    }
	};
	// Check if this event starts during an existing event
	for (int i = itin.size() - 1; i >= 1; i--) {
	    if (itin.get(i) instanceof Event && !itin.get(i).equals(newEvent)) {
		Event event = (Event) itin.get(i);

		long startTime = event.getStartTime().toMinutes();
		long endTime = event.getEndTime().toMinutes();
		long newEventStart = newEvent.getStartTime().toMinutes();
		long newEventEnd = newEvent.getEndTime().toMinutes();

		if (startTime <= newEventStart && newEventStart <= endTime
			|| startTime <= newEventEnd && newEventEnd <= endTime) {
		    System.out.println("Moving: " + event.getTitle());
		    // There is a conflict so remove this event and transportations related to it
		    if (itin.get(i - 1) instanceof Transportation)
			itin.remove(i - 1);
		    itin.remove(i - 1);
		    if (itin.get(i - 1) instanceof Transportation)
			itin.remove(i - 1);

		    // Sort the itinerary
		    Collections.sort(itin, itinerarySorter);

		    // Finding new place for deleted event
		    Time newStartTime = findOpenTime(event);
		    if (newStartTime != null) {
			event.setStartTime(newStartTime);
			event.setEndTime(newStartTime.add(event.getExpectedLength()));
			itin.add(event);
		    }
		    
		}
	    }
	}
	Collections.sort(itin, itinerarySorter);
	
	// Add transportations where needed
	for (int i = itin.size()-1; i >= 0; i--) {
	    if (itin.get(i) instanceof Event) {
		Event currentEvent = (Event) itin.get(i);
		if (i == itin.size()-1) {
		    // Event at end of itinerary. Need to add transportation home
		    Transportation transortation = gm.getTransportation(currentEvent.getLocation(), home, currentEvent.getEndTime());
		    itin.add(transortation);
		} else if (i == 0) {
		    // Event at start of itinerary. Need to add transportation from home to the event
		    Transportation transportation = gm.getTransportation(home, currentEvent.getLocation(), startTime);
		    itin.add(transportation);
		}
		    // Check if surrounding ItineraryItems are Events
		    // Check previous
		    if (itin.get(i-1) instanceof Event) {
			// Need to add transportation from previous to current
			Event prev = (Event) itin.get(i-1);
			Transportation transportation = gm.getTransportation(prev.getLocation(), currentEvent.getLocation(), prev.getEndTime());
			itin.add(transportation);
		    }
		
	    }
	}
	
	// Final sort
	Collections.sort(itin, itinerarySorter); // Need Maps getLocation to work first

    }

    private Time findOpenTime(Event event) {
	// Required time will be time for the event as well as transportation
	for (int i = itin.size() - 1; i >= 0; i--) {
	    if (itin.get(i) instanceof Event) {
		Event currentEvent = (Event) itin.get(i);
		System.out.println(currentEvent.getTitle());
		if (i == itin.size() - 1 || i == itin.size() - 2) {
		    // The last event, check if there is time after the last event
		    Time travelToEvent = gm.getTransportation(currentEvent.getLocation(), event.getLocation(), currentEvent.getEndTime()).getExpectedLength();
		    Time travelFromEvent = gm.getTransportation(event.getLocation(), home, currentEvent.getEndTime()).getExpectedLength();
		    long totalTravel = travelToEvent.add(travelFromEvent).toMinutes();
		    long eventTime = event.getExpectedLength().toMinutes();
		    
		    // Check if time from end of current event to end of itinerary day is enough for the event and the travel time 
		    if (endTime.getDifference(currentEvent.getEndTime()).toMinutes() >= eventTime + totalTravel) {
			Time newStartTime = currentEvent.getEndTime().add(travelToEvent);
			System.out.println("New Start Time: " + newStartTime);
			return newStartTime;
		    }
		    
		    System.out.println("Total Travel Time Needed: " + totalTravel);
		}
	    }

	}
	// Unable to find an available time
	return null;
    }

    private Transportation joinEvents(String startLocation, String nextLocation, Time endTime) {
	Transportation transportation = gm.getTransportation(startLocation, nextLocation, endTime);
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
	for (ItineraryItem i : getItin()) {
	    cost += GoogleMaps.rangeToBudget(i.getPrice());
	}
	return this.getBudget() - cost;
    }

    public Time getItinCurTime() {
	if (this.getItin().size() == 0) {
	    return this.getStartTime();
	} else {
	    return this.getItin().get(this.getItin().size() - 1).getEndTime();
	}
    }

    public String getItinCurLoc() {
	if (this.getItin().size() == 0) {
	    return this.getHome();
	} else {
	    return ((Event) (this.getItin().get(this.getItin().size() - 1))).getLocation();
	}
    }

    public float getItinDistLeft() {
	float dist = 0;

	List<ItineraryItem> itin = this.getItin();
	// Loop excludes the first and last transportation events, as this should not be
	// factored into the max distance
	for (int i = 1; i < itin.size() - 1; i++) {
	    if (itin.get(i) instanceof Transportation) {
		dist += ((Transportation) itin.get(i)).getDistance();
	    }
	}
	return this.getMaxDist() - dist;
    }

    public void setVisitedEvents(List<String> visitedEvents) {
	this.visitedEvents = visitedEvents;
    }

    public void setMethodsOfTrans(List<String> methodsOfTrans) {
	this.methodsOfTrans = methodsOfTrans;
    }

    public double getLocationLat() {
	return locationLat;
    }

    public void setLocationLat(double locationLat) {
	this.locationLat = locationLat;
    }

    public double getLocationLong() {
	return locationLong;
    }

    public void setLocationLong(double locationLong) {
	this.locationLong = locationLong;
    }

    public double getHomeLat() {
	return homeLat;
    }

    public void setHomeLat(double homeLat) {
	this.homeLat = homeLat;
    }

    public double getHomeLong() {
	return homeLong;
    }

    public void setHomeLong(double homeLong) {
	this.homeLong = homeLong;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getHome() {
	return home;
    }

    public void setHome(String home) {
	this.home = home;
    }
}
