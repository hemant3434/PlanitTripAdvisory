package krusty_krab.krusty_krab.domain;

import java.util.List;

public class Event extends ItineraryItem{

	private String location;
	private String activity;
	private int rating;
	
	public Event() {
	}
	
	public Event(String title, String location, String activity, int rating, float price, Time startTime, Time endTime, Time expectedLength) {
		super("Event", title, price, startTime, endTime, expectedLength);
		this.location = location;
		this.activity = activity;
		this.rating = rating;
	}

	public float getScore(Time curTime, String curLoc, GoogleMaps gm, float maxDist, float budget){
	    int ratingWeight = 3;
	    int waitTimeWeight = 5;
	    int maxDistWeight = 1;
	    int budgetWeight = 1;

	    int ratingMax = 5;
	    int waitTimeMax = 360;
	    float maxDistMax = maxDist;
	    float budgetMax = budget;

        float rating, waitTime, dist, price;
		Transportation transp = gm.getTransportation(curLoc, getLocation(), curTime);

        rating = this.getRating();

        if(this.getStartTime().toMinutes() - transp.getEndTime().toMinutes() < curTime.toMinutes()){
            waitTime = 0;
        }
        else{
            waitTime = this.getStartTime().toMinutes() - curTime.toMinutes();
        }

        dist = transp.getDistance();

        price = this.getPrice();

        return ratingWeight/ratingMax*rating + 1/waitTimeWeight/waitTimeMax*waitTime + 1/maxDistWeight/maxDistMax*dist + 1/budgetWeight/budgetMax*price;

    }

	public String getLocation() {
		return location;
	}

	public String getActivity() {
		return activity;
	}

	public int getRating() {
		return rating;
	}
}
