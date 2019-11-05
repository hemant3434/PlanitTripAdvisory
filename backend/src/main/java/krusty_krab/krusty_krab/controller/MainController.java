package krusty_krab.krusty_krab.controller;

import java.util.*;

import krusty_krab.krusty_krab.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v1/")
public class MainController {
  
  String link = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjArePQmp"
      + "_lAhUmx4UKHSOLDy4QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.fanthatracks.com%2Fnews"
      + "%2Fmisc%2Fstar-wars-is-everywhere-021-hello-there%2F&psig=AOvVaw3rX7UoWqkc6toLU"
      + "iEGB24b&ust=1571261300777966";

  Itinerary itin = new Itinerary();
  //Instantiated by register, remove instantiation for final product
  User user = new User("amy", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  GoogleMaps gm = new GoogleMaps();

  @Autowired
  UserService userService;
  
  @GetMapping("/getDummy1")
  public ResponseEntity<?> getDummy1(@RequestBody Map<String, Object> body) {
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("Title", "Obi-Wan");
    map.put("Description", "Iconic Line");
    map.put("URL", link);

    return ResponseEntity.ok().body(map);
  }

  @GetMapping("/getDummy2")
  public ResponseEntity<?> getDummy2(@RequestBody Map<String, Object> body) throws Exception {

    Time start = new Time(2019, 11, 3, 9, 00, true);
    Time end = new Time(2019, 11, 3, 20, 00, true);
    double lat = 43.7764;
    double ltd = -79.2318;
    float budget = 4;
    float distance = 1f;

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
    itin.setHome(GoogleMaps.getHomeLocation(47.2, 47.2));
    itin.setHomeLat(47.2);
    itin.setHomeLong(47.2);
    itin.setMethodsOfTrans(trans);
    itin.setActivities(activities);

    itin.createItinerary(this.user);
    //gm.getEvents(start, end, lat, ltd, distance, activities, budget)
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
    itin.setMethodsOfTrans(body.getMethodsOfTrans());
    itin.setActivities(body.getActivities());
    
    itin.createItinerary(this.user);
    return ResponseEntity.ok().body(itin.getItin());
  }
  
  @GetMapping("/getItinerary2")
  public ResponseEntity<?> getItinerary2(@RequestBody Map<String, Object> body) throws Exception {

    // is this map needed?
    Map<String, Transportation> map = new HashMap<String, Transportation>();
    //map.put("hello", "there");
    //Sends dummy data for the user filters into the itinerary class
    
    GoogleMaps maps = new GoogleMaps();
    Time start = new Time(2019, 11, 5, 5, 00, true);
    Time end = new Time(2019, 11, 3, 20, 00, true);
    double lat = 43.7764;
    double ltd = -79.2318;
    float budget = 4;
    float distance = 1f;

    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");
    
    maps.getEvents(start, end, lat, ltd, distance, activities, budget);
    
    List<String> methods = new ArrayList<String>();
    methods.add("Transit");
    methods.add("Drive");
    map.put("trans", maps.getTransportation("ChIJcQxvld3e1IkR6mBYwLARjhQ", "ChIJtwVr559GK4gR22ZZ175sFAM", start, methods));
    return ResponseEntity.ok().body(map);
  }
  
  @GetMapping("/viewItinerary")
  public List<ItineraryItem> viewItinerary() {
      return itin.getItin();
  }
  
//  @GetMapping("/dummy1")
//  public void dummy1(@RequestBody )
  	

  @GetMapping("getExploreEvents")
  public List<Event> getExploreEvents() {
    return GoogleMaps.getExploreEvents();
  }
  
  @PostMapping("/addEvent")
  public ResponseEntity<?> addEvent(@RequestBody Event event) {
      itin.addEvent(event);
      return ResponseEntity.ok().build();
  }
  
  @PutMapping("/deleteEvent")
  public ResponseEntity<?> deleteEvent(@RequestBody Map<String, String> body) {
	  String eventId = new String(body.get("eventId"));
	  itin.deleteEvent(eventId);
	  return ResponseEntity.ok().build();
  }
  
  @PutMapping("/changeTime")
  public ResponseEntity<?> changeTime(@RequestBody Itinerary body) {
      //itin.setStartTime(body.getStartTime());
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
	  for (String transportation: (ArrayList<String>)transportationArray) {
		  itin.addMethodsOfTrans(transportation);
	  }
	  return ResponseEntity.ok().build();
  }

  @PutMapping("/login")
  public void login(@RequestBody User body) {
    this.user = userService.getUser(body.getUsername());
  }

  @PutMapping("/register")
  public void register(@RequestBody User body) {
    this.user = new User(body.getUsername(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    userService.addUser(user);
  }

  public UserService getUserService() {
    return userService;
  }
}
