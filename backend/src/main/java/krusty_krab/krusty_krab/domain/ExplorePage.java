package krusty_krab.krusty_krab.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExplorePage {

	private ArrayList<Event> events;
	private List<Event> allEvents;
	
	public ExplorePage() {
		events = new ArrayList<Event>();
		allEvents = GoogleMaps.getEventsFromMongo();
	}
	
	public void generateExplorePage() {
		Random random = new Random();
		ArrayList<Event> newEvents = new ArrayList<Event>();
		while (newEvents.size() < 10) {
			Event tempEvent = allEvents.get(random.nextInt());
			if (!events.contains(tempEvent) && !newEvents.contains(tempEvent)) {
				newEvents.add(tempEvent);
			}
		}
		events = newEvents;
	}	
}
