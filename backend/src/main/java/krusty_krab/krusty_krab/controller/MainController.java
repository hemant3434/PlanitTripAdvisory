package krusty_krab.krusty_krab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;
import com.mongodb.*;
import krusty_krab.krusty_krab.domain.*;
import krusty_krab.krusty_krab.mongo.EventConverter;
import krusty_krab.krusty_krab.mongo.MongoDBEventDAO;
import com.mongodb.MongoClient;
import krusty_krab.krusty_krab.mongo.MongoDBUserDAO;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

  String link = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjArePQmp"
      + "_lAhUmx4UKHSOLDy4QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.fanthatracks.com%2Fnews"
      + "%2Fmisc%2Fstar-wars-is-everywhere-021-hello-there%2F&psig=AOvVaw3rX7UoWqkc6toLU"
      + "iEGB24b&ust=1571261300777966";

  // Itinerary itin = new Itinerary();
  // Instantiated by register, remove instantiation for final product
  User user = new User();
  Itinerary itin = user.getItinerary();
  GoogleMaps gm = new GoogleMaps();
  MongoDBUserDAO mpd = new MongoDBUserDAO(new MongoClient());
  
  ExplorePage explorePage = new ExplorePage();

  @Autowired
  UserService userService;


  // Only Run this request once to fill the database with restaurants
  @GetMapping("/getDummy1")
  public ResponseEntity<?> getDummy1() {
    Time start = new Time(2019, 11, 15, 6, 00, true);
    Time end = new Time(2019, 11, 15, 24, 00, true);
    double lat = 43.645474;
    double ltd = -79.380922;
    float budget = 150f;
    float distance = 1f;

    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");

    List<String> trans = new ArrayList<String>();
    trans.add("Drive");
    trans.add("Transit");

    try {
      gm.initializeDatabase();
    } catch (ApiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //List<Event> events = gm.getEvents(start, end, lat, ltd, distance, activities, budget);
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/getDummy2")
  public ResponseEntity<?> getDummy2(@RequestBody Map<String, Object> body) throws Exception {

    Time start = new Time(2019, 11, 9, 5, 00, true);
    Time end = new Time(2019, 11, 9, 24, 00, true);
    double lat = 43.645474;
    double ltd = -79.380922;
    float budget = 150f;
    float distance = 20f;

    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");

    List<String> trans = new ArrayList<String>();
    trans.add("Drive");
    trans.add("Transit");

    itin.setStartTime(start);
    itin.setEndTime(end);
    itin.setMaxDist(distance);
    itin.setBudget(budget);
    itin.setLocationLat(lat);
    itin.setLocationLong(ltd);
    itin.setHome(GoogleMaps.getHomeLocation(43.7764, -79.2318));
    itin.setHomeLat(43.7764);
    itin.setHomeLong(-79.2318);
    itin.setMethodsOfTrans(trans);
    itin.setActivities(activities);

    // itin.createItinerary(this.user);
    /*List<Event> events = gm.getEvents(start, end, lat, ltd, distance, activities, budget);
    for (Event e : events) {
      System.out.println(e.getLocation());
      // mpd.createEvent(e);
    }
    return ResponseEntity.ok().body(null);*/
    return null;
  }

  @GetMapping("/getDummy3")
  public ResponseEntity<?> getDummy3(@RequestBody Map<String, Object> body) {
    User user = mpd.readUser("Jason");
    /*user.getVisitedEvents().add("ChIJk2BJvS3L1IkRqyDWmyiQhg8");
    user.getEventRatings().put("ChIJk2BJvS3L1IkRqyDWmyiQhg8", 5);
    mpd.updateUser(user);*/
    //Event e = GoogleMaps.getEventByID("ChIJk2BJvS3L1IkRqyDWmyiQhg8");
    Event e = new Event("ripley's aquarium", "ripley's aquarium", 43.2, 43.2, "aquarium", 0, 3,
            new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
            new Time(0, 0, 0, 2, 0, true), "toronto", "There be fish", "1");
    Time curTime = new Time(2019, 10, 25, 5, 00, true);
    String curLoc = "scarbs";
    float budget = 150f;
    float maxDist = 20f;
    List<String> methodsOfTrans = new ArrayList<String>();
    methodsOfTrans.add("Drive");
    methodsOfTrans.add("Transit");
    System.out.println(e.getScore(curTime, curLoc, gm, maxDist, budget, user, methodsOfTrans));
    curTime = new Time(2019, 10, 25, 8, 00, true);
    System.out.println(e.getScore(curTime, curLoc, gm, maxDist, budget, user, methodsOfTrans));
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("jason")
  public ResponseEntity<?> jason(@RequestBody Map<String, Object> body) {
    String[] eventActivities = {"a","b"};
    List<String> userActivities = new ArrayList<>();
    userActivities.add("a");
    userActivities.add("b");
    System.out.println(GoogleMaps.filterByActivity(eventActivities, userActivities));
    return ResponseEntity.ok().body(mpd.readUser("Jason").getEmail());
  }
  
  @GetMapping("/checkItinerary")
  public Boolean checkItinerary() {
    return !itin.getItin().isEmpty();
  }

  @PostMapping("/createItinerary")
  public ResponseEntity<?> createItinerary(@RequestBody Itinerary body) throws Exception {
    itin = new Itinerary();
    itin.setStartTime(body.getStartTime());
    itin.setEndTime(body.getEndTime());
    itin.setMaxDist(body.getMaxDist());
    itin.setBudget(body.getBudget());
    itin.setLocationLat(body.getLocationLat());
    itin.setLocationLong(body.getLocationLong());
    itin.setHome(GoogleMaps.getHomeLocation(body.getHomeLat(), body.getHomeLong()));
    itin.setHomeLat(body.getHomeLat());
    itin.setHomeLong(body.getHomeLong());
    List<String> trans = new ArrayList<>();
    trans.add("Transit");
    trans.add("Ride Services");
    List<String> activities = new ArrayList<>();
    activities.add("nature and parks");
    activities.add("malls");
    //itin.setMethodsOfTrans(trans);
    //itin.setActivities(activities);
    itin.setMethodsOfTrans(body.getMethodsOfTrans());
    itin.setActivities(body.getActivities());

    itin.createItinerary(this.user);

    user.setItinerary(itin);
    mpd.updateUser(user);

    return ResponseEntity.ok().body(itin.getItin());
  }

  @GetMapping("/getItinerary")
  public ResponseEntity<?> getItinerary(@RequestBody Itinerary body) throws Exception {
    itin.setStartTime(body.getStartTime());
    itin.setEndTime(body.getEndTime());
    itin.setMaxDist(body.getMaxDist());
    itin.setBudget(body.getBudget());
    itin.setLocationLat(body.getLocationLat());
    itin.setLocationLong(body.getLocationLong());
    itin.setHome(GoogleMaps.getHomeLocation(body.getHomeLat(), body.getHomeLong()));
    itin.setHomeLat(body.getHomeLat());
    itin.setHomeLong(body.getHomeLong());
    List<String> trans = new ArrayList<>();
    trans.add("Transit");
    trans.add("Ride Services");
    List<String> activities = new ArrayList<>();
    activities.add("nature and parks");
    activities.add("malls");
    itin.setMethodsOfTrans(trans);
    itin.setActivities(activities);

    itin.createItinerary(this.user);


    return ResponseEntity.ok().body(itin.getItin());
  }

  @GetMapping("/getItinerary2")
  public ResponseEntity<?> getItinerary2(@RequestBody Map<String, Object> body) throws Exception {

    // is this map needed?
    Map<String, Transportation> map = new HashMap<String, Transportation>();
    // map.put("hello", "there");
    // Sends dummy data for the user filters into the itinerary class

    GoogleMaps maps = new GoogleMaps();
    Time start = new Time(2019, 11, 9, 5, 00, true);
    Time end = new Time(2019, 11, 3, 20, 00, true);
    double lat = 43.7764;
    double ltd = -79.2318;
    float budget = 4;
    float distance = 1f;

    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");

    //maps.getEvents(start, end, lat, ltd, distance, activities, budget);

    List<String> methods = new ArrayList<String>();
    methods.add("Transit");
    methods.add("Drive");
    methods.add("Walk");

    List<Transportation> transp = new ArrayList<>();
    transp.add(maps.getTransportation("ChIJBQBqCVnQ1IkR33DiwY5Xeps", "ChIJS4nFwffQ1IkRY-oKD5E607I",
        start, methods));
    transp.add(maps.getTransportation("ChIJBQBqCVnQ1IkR33DiwY5Xeps", "ChIJNTJCxvvQ1IkRdi4-MQdLY0M",
        start, methods));
    transp.add(maps.getTransportation("ChIJS4nFwffQ1IkRY-oKD5E607I", "ChIJBQBqCVnQ1IkR33DiwY5Xeps",
        start, methods));
    transp.add(maps.getTransportation("ChIJS4nFwffQ1IkRY-oKD5E607I", "ChIJNTJCxvvQ1IkRdi4-MQdLY0M",
        start, methods));
    transp.add(maps.getTransportation("ChIJNTJCxvvQ1IkRdi4-MQdLY0M", "ChIJBQBqCVnQ1IkR33DiwY5Xeps",
        start, methods));
    transp.add(maps.getTransportation("ChIJNTJCxvvQ1IkRdi4-MQdLY0M", "ChIJS4nFwffQ1IkRY-oKD5E607I",
        start, methods));
    return ResponseEntity.ok().body(transp);
  }

  @GetMapping("/viewItinerary")
  public List<ItineraryItem> viewItinerary() {
    return user.getItinerary().getItin();
  }

  @GetMapping("/viewItinerary2")
  public ResponseEntity<?> viewItinerary2() {
    Time startTime = new Time(2019, 11, 9, 5, 00, true);
    Time endTime = new Time(2019, 11, 9, 24, 00, true);
    double lat = 43.645474;
    double ltd = -79.380922;
    float budget = 150f;
    float maxDist = 20f;

    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");

    List<String> trans = new ArrayList<String>();
    trans.add("Drive");
    trans.add("Transit");

    //List<Event> events = gm.getEvents(startTime, endTime, lat, ltd, maxDist, activities, budget);
    //return ResponseEntity.ok().body(events);
    return null;
  }
  // @GetMapping("/dummy1")
  // public void dummy1(@RequestBody )


  @GetMapping("getExploreEvents")
  public List<Event> getExploreEvents() {
    return GoogleMaps.getExploreEvents();
  }

  @PostMapping("/addEvent")
  public ResponseEntity<?> addEvent(@RequestBody Event event) {
    user.getItinerary().addEvent(event);
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/addEvent2")
  public ResponseEntity<?> addEvent2(@RequestBody Map<String, String> body) {
    Event e = GoogleMaps.getEventByID(body.get("eventId"));
    e.setStartTime(new Time(body.get("startTime")));
    user.getItinerary().addEvent(e);
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }
  /*
   * @PostMapping("/addEvent") public ResponseEntity<?> addEvent(@RequestBody Map<String, String>
   * body) { itin.addEvent(GoogleMaps.getEventByID(body.get("eventId"))); return
   * ResponseEntity.ok().build(); }
   */

  @PutMapping("/deleteEvent")
  public ResponseEntity<?> deleteEvent(@RequestBody Map<String, String> body) {
    String eventId = new String(body.get("eventId"));
    user.getItinerary().deleteEvent(eventId);
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/addRating")
  public ResponseEntity<?> addRating(@RequestBody Map<String, String> body) {
    user.getEventRatings().put(body.get("eventId"), Integer.parseInt(body.get("rating")));
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/deleteRating")
  public ResponseEntity<?> deleteRating(@RequestBody Map<String, String> body) {
    user.getEventRatings().remove(body.get("eventId"));
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/addVisitedEvent")
  public ResponseEntity<?> addVisitedEvent(@RequestBody Map<String, String> body) {
    user.getVisitedEvents().add(body.get("eventId"));
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/deleteVisitedEvent")
  public ResponseEntity<?> deleteVisitedEvent(@RequestBody Map<String, String> body) {
    user.getVisitedEvents().remove(body.get("eventId"));
    mpd.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/changeTime")
  public ResponseEntity<?> changeTime(@RequestBody Itinerary body) {
    // itin.setStartTime(body.getStartTime());
    itin.setEndTime(body.getEndTime());
    return ResponseEntity.ok().build();
  }

  @PutMapping("/changeLocation")
  public ResponseEntity<?> changeLocation(@RequestBody Itinerary body) {
    itin.setLocation(body.getLocation());
    return ResponseEntity.ok().build();
  }

  @PutMapping("/changeMaxBudget")
  public ResponseEntity<?> changeMaxBudget(@RequestBody Map<String, Float> body) {
    Float newBudget = body.get("budget");
    itin.setBudget(newBudget);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/changeMaxDistance")
  public ResponseEntity<?> changeMaxDistance(@RequestBody Map<String, Float> body) {
    Float maxDist = body.get("maxDist");
    itin.setMaxDist(maxDist);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/addTransportation")
  public ResponseEntity<?> changeTransportation(@RequestBody Map<String, Object> body) {
    Object transportationArray = body.get("Transportation");
    System.out.println(transportationArray);
    for (String transportation : (ArrayList<String>) transportationArray) {
      itin.addMethodsOfTrans(transportation);
    }
    return ResponseEntity.ok().build();
  }

  @PutMapping("/login")
  public void login(@RequestBody User body) {
    this.user = mpd.readUser(body.getEmail());
  }

  @GetMapping("/checkPw")
  public boolean checkPw(@RequestBody User body) {
    User user = mpd.readUser(body.getEmail());
    if(user.getPassword().equals(body.getPassword())){
      return true;
    }
    else{
      return false;
    }
  }
  
  @PutMapping("/register")
  public void register(@RequestBody User body) { 
    this.user = new User();
    this.itin = this.user.getItinerary();
    this.user.setUsername(body.getUsername());
    this.user.setPassword(body.getPassword());
    this.user.setEmail(body.getEmail());
    mpd.createUser(this.user);
    // userService.addUser(user);
  }

  public UserService getUserService() {
    return userService;
  }

  @PutMapping("/post")
  public void addEvent(@RequestBody Map<String, Object> body) {
    // MongoDBUserDAO mpd = new MongoDBUserDAO(new MongoClient());

    Itinerary i = new Itinerary();
    Time start = new Time(2019, 11, 9, 5, 00, true);
    Time end = new Time(2019, 11, 9, 24, 00, true);
    double lat = 43.645474;
    double ltd = -79.380922;
    float budget = 150f;
    float distance = 20f;
    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");
    List<String> trans = new ArrayList<String>();
    trans.add("Drive");
    trans.add("Transit");
    Transportation t1 = new Transportation(10, "walk", 0, start, end,
        new Time(0, 0, 0, 0, 15, true), "flight-takeoff", "15 minutes");
    Event e1 = new Event("Eaton Centre", "Eaton Centre", 43.2, 43.2, "Mall", 4, 4,
        new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true),
        "https://www.dailydot.com/wp-content/uploads/2018/10/pikachu_surprised_meme-e1540570767482.png",
        "If Quebec is Canada's ass...", "4");
    List<ItineraryItem> itin = new ArrayList<>();
    itin.add(t1);
    itin.add(e1);
    itin.add(t1);
    itin.add(e1);

    i.setStartTime(start);
    i.setEndTime(end);
    i.setMaxDist(distance);
    i.setBudget(budget);
    i.setLocationLat(lat);
    i.setLocationLong(ltd);
    i.setHome("Scarborough");
    i.setHomeLat(43.7764);
    i.setHomeLong(-79.2318);
    i.setMethodsOfTrans(trans);
    i.setActivities(activities);
    i.setItin(itin);

    List<String> visitedEvents = new ArrayList<>();
    visitedEvents.add("1");
    visitedEvents.add("2");
    Map<String, Integer> eventRatings = new HashMap<>();
    eventRatings.put("1", 2);
    eventRatings.put("2", 5);
    //User u = new User("UN1", visitedEvents, eventRatings, i);
    //mpd.createUser(u);
  }

  @GetMapping("/post")
  public ResponseEntity<?> getEvent() {
    return ResponseEntity.ok().body(mpd.readAllUsers());
  }
  
  @GetMapping("/getExplorePage")
  public List<Event> getExplorePage() {
	  explorePage.generateExplorePage();
	  return explorePage.getEvents();
  }

  @GetMapping("/costPerPerson")
  public ResponseEntity<?> getCostPerPerson() {
	  Float cost = 0f;
	  Map<String, String> response = new HashMap<String, String>();
	  for (ItineraryItem item: itin.getItin()) {
		  if (item instanceof Event) {
			  cost += ((Event) item).getPrice();
		  }
	  }
	  response.put("costPerPerson", "$" + cost.toString());
	  return ResponseEntity.ok(response);
  }

  @GetMapping("/getInitializedDatabase")
  public ResponseEntity<?> getInitializedDatabase() {
    try {
      gm.initializeDatabase();
    } catch (ApiException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(null);
  }
}

/*
 * //3 Event query "startTime": "2019-10-03 9:00:00 AM", "endTime": "2019-11-09 8:00:00 PM",
 * "maxDist": 1.9, "budget": 300, "locationLat": 43.7764, "locationLong": -79.2318, "homeLat":
 * 43.7764, "homeLong": -79.231, "methodsOfTrans": [ "Drive", "Transit" ], "activities": [
 * "aquarium", "art gallery" ] { "type": "event", "title": "Scarborough Buffet", "price": 2.0,
 * "startTime": { "year": 2019, "month": 11, "day": 9, "hour": 17, "minute": 0, "positive": true },
 * "endTime": { "year": 2019, "month": 11, "day": 9, "hour": 22, "minute": 0, "positive": true },
 * "expectedLength": { "year": 0, "month": 0, "day": 0, "hour": 2, "minute": 0, "positive": true },
 * "location": "1221 Markham Rd #1a, Scarborough, ON M1H 3E2, Canada", "longitude": 47.2,
 * "latitude": 47.2, "activity":
 * "meal_delivery, restaurant, food, point_of_interest, establishment", "rating": 3, "image": "",
 * "description": "https://maps.google.com/?cid=12885707353534163555", "id":
 * "ChIJS4nFwffQ1IkRY-oKD5E607I" }, { "type": "event", "title":
 * "The Keg Steakhouse + Bar - Estate Drive", "price": 3.0, "startTime": { "year": 2019, "month":
 * 11, "day": 9, "hour": 16, "minute": 0, "positive": true }, "endTime": { "year": 2019, "month":
 * 11, "day": 9, "hour": 22, "minute": 0, "positive": true }, "expectedLength": { "year": 0,
 * "month": 0, "day": 0, "hour": 2, "minute": 0, "positive": true }, "location":
 * "60 Estate Dr, Scarborough, ON M1H 2Z1, Canada", "longitude": 47.2, "latitude": 47.2, "activity":
 * "bar, restaurant, food, point_of_interest, establishment", "rating": 4, "image": "",
 * "description": "https://maps.google.com/?cid=4855807317498539638", "id":
 * "ChIJNTJCxvvQ1IkRdi4-MQdLY0M" }
 * 
 * //6 event query { "startTime": "2019-10-03 2:47:41 PM", "endTime": "2019-10-03 2:48:41 PM",
 * "maxDist": 20, "budget": 590, "locationLat": 43.76768768758, "locationLong": -789.3586968758798,
 * "homeLat": -83.76768768758, "homeLong": 9.3586968758798, "methodsOfTrans": [ "Drive", "Transit",
 * "Ride Services" ], "activities": [ "museums", "nature and parks", "malls" ] }
 */
