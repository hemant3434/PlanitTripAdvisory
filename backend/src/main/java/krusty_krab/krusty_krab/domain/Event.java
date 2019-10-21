package krusty_krab.krusty_krab.domain;

import java.sql.Time;
import java.util.List;

public class Event extends ItineraryItem{

	private String name;
	private Time date;
	private String location;
	private List<String> activities;
	private int rating;
	
	public Event() {
	}
	
	public Event(String name, Time date, String location, List<String> activities, int rating, float price, String title, Time startTime, Time endTime) {
		super("Event", title, price, startTime, endTime);
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

	public float getScore(Time startTime, String location){
	    int ratingWeight = 3;
	    int waitTimeWeight = 5;
	    int maxDistWeight = 1;
	    int budgetWeight = 1;

        float rating, waitTime, maxDist, budget;
        rating = this.getRating();
        if(this.getStartTime().before(startTime)){
            waitTime = 0;
        }
        else{
            waitTime = this.getStartTime().getTime()-startTime.getTime();
        }

        return ratingWeight*rating + waitTimeWeight*waitTime + maxDistWeight*maxDist + budgetWeight*budget;

    }

}
