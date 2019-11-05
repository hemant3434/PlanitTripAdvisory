  package krusty_krab.krusty_krab.domain;

import java.util.List;

public class Event extends ItineraryItem{

	private String location;
	private double longitude;
	private double latitude;
	private String activity;
	private int rating;
	private String image;
	private String description;
	private String id;
	
	public Event() {
	}
	
	public Event(String title, String location, double latitude, double longitude, String activity, int rating, float price, Time startTime, Time endTime, Time expectedLength, String image, String description, String id) {
		super("event", title, price, startTime, endTime, expectedLength);
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.activity = activity;
		this.rating = rating;
		this.image = image;
		this.description = description;
		this.id = id;
	}

	// Computes a score for an event, based on its rating, price, wait time until it opens, and distance needed to travel
	public float getScore(Time curTime, String curLoc, GoogleMaps gm, float maxDist, float budget, User user, List<String> methodsOfTrans){
	    //Importance of each factor of an event quantified by a weight integer
		int ratingWeight = 3;
		int userRatingWeight = 3;
	    int waitTimeWeight = 5;
	    int maxDistWeight = 1;
	    int budgetWeight = 1;

	    //Upper bound of factors
	    int ratingMax = 5;
	    int waitTimeMax = 360;
	    float maxDistMax = maxDist;
	    float budgetMax = budget;

	    //gets transportation object, if user were to travel from their current location to this event
		Transportation transp = null;
		transp = gm.getTransportation(curLoc, getId(), curTime, methodsOfTrans);

		float rating, userRating, waitTime, dist, price;
        rating = this.getRating();

        userRating = user.getActivityRating(this.getActivity());

        //If the start time of the event is before the current time, then the user does not have to wait for the event to open
        if(this.getStartTime().toMinutes() - transp.getEndTime().toMinutes() < curTime.toMinutes()){
            waitTime = 0;
        }
        // otherwise, how long user needs to wait is computed
        else{
            waitTime = this.getStartTime().toMinutes() - curTime.toMinutes();
        }

        dist = transp.getDistance();
        price = this.getPrice();

        //Following floats incremented to avoid divide by zero error
		waitTime++;
		dist++;
		price++;

        /*System.out.println(rating/ratingMax*ratingWeight);
		System.out.println(userRating/ratingMax*userRatingWeight);
		System.out.println(1/waitTime/waitTimeMax*waitTimeWeight);
		System.out.println(1/dist/maxDistMax*maxDistWeight);
		System.out.println(1/price/budgetMax*budgetWeight);*/

        //Puts each factor on a scale between 0 and 1 (higher the better) by dividing by the upper bound, then multiplying by the weight
        return rating/ratingMax*ratingWeight + userRating/ratingMax*userRatingWeight + 1/waitTime/waitTimeMax*waitTimeWeight + 1/dist/maxDistMax*maxDistWeight + 1/price/budgetMax*budgetWeight;

    }

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getActivity() {
		return activity;
	}

	public int getRating() {
		return rating;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}
}
