package krusty_krab.krusty_krab.domain;

import java.sql.Time;
import java.util.List;

public class Event {

	private String name;
	private Time date;
	private String location;
	private List<String> activies;
	private int rating;
	private double cost;
	
	public Event() {
	}
	
	public Event(String name, Time date, String location, List<String> activities, int rating, double cost) {
		this.name = name;
		this.date = date;
		this.location = location;
		this.activies = activities;
		this.rating = rating;
		this.cost = cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Time date() {
		return this.date;
	}
	
	public String location() {
		return this.location;
	}
	
	public List<String> getActivities() {
		return this.activies;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public double getCost() {
		return this.cost;
	}
}
