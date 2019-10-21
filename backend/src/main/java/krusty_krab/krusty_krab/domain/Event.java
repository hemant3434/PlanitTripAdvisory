package krusty_krab.krusty_krab.domain;

import java.util.List;

public class Event extends ItineraryItem{

	private String name;
	private Time date;
	private String location;
	private List<String> activities;
	private int rating;
	public GoogleMaps gm = new GoogleMaps();
	
	public Event() {
	}
	
	public Event(String name, Time date, String location, List<String> activities, int rating, float price, String title, Time startTime, Time endTime, Time expectedLength) {
		super("Event", title, price, startTime, endTime, expectedLength);
		this.name = name;
		this.date = date;
		this.location = location;
		this.activities = activities;
		this.rating = rating;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Time getDate() {
		return this.date;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public List<String> getActivities() {
		return this.activities;
	}
	
	public int getRating() {
		return this.rating;
	}

	public float getScore(Time curTime, String curLoc){
	    int ratingWeight = 3;
	    int waitTimeWeight = 5;
	    int maxDistWeight = 1;
	    int budgetWeight = 1;

        float rating, waitTime, dist, price;
		Transportation transp = this.gm.getTransportation(curLoc, getLocation());

        rating = this.getRating();

        if(this.getStartTime().getDifference(transp.getTime()).isLessThan(curTime)){
            waitTime = 0;
        }
        else{
            waitTime = this.getStartTime().getDifference(curTime).toMinutes();
        }

        dist = transp.getDistance();

        price = this.getPrice();

        return ratingWeight*rating + waitTimeWeight*waitTime + maxDistWeight*dist + budgetWeight*price;

    }

}
