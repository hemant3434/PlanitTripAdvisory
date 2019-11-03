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
  public void getDummy2(@RequestBody Map<String, Object> body) {
    /*List<String> visitedEvents = new ArrayList<>();
    List<Float> eventRatings = new ArrayList<>();
    //visitedEvents.add("1");
    visitedEvents.add("2");
    visitedEvents.add("3");
    //eventRatings.add(5f);
    eventRatings.add(5f);
    eventRatings.add(5f);
    user.setVisitedEvents(visitedEvents);
    user.setEventRatings(eventRatings);
    userService.changeUser(user);*/
    //System.out.println(GoogleMaps.getEventByID("1").getScore(new Time(2019, 10, 25, 9, 00, true), "Eaton Centre", this.gm, 20, 200, user));
    System.out.println(user.getUsername());
    System.out.println(user.getVisitedEvents());
    System.out.println(user.getEventRatings());
    System.out.println(user.getItin());
  }

  @GetMapping("/getItinerary")
  public ResponseEntity<?> getItinerary(@RequestBody Map<String, Object> body) throws Exception {

	// is this map needed?
    //Map<String, Object> map = new HashMap<String, Object>();
    itin = new Itinerary();
    //Sends dummy data for the user filters into the itinerary class
    itin.setStartTime(new Time(2019, 10, 25, 9, 00, true));
    itin.setEndTime(new Time(2019, 10, 25, 23, 00, true));
    itin.setHome("union station");
    itin.setLocation("toronto");
    itin.setMaxDist(20);
    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");
    itin.setActivities(activities);
    itin.setBudget(200);

    itin.createItinerary(this.user);
    return ResponseEntity.ok().body(itin.getItin());
  }
  
  @GetMapping("/viewItinerary")
  public List<ItineraryItem> viewItinerary() {
      return itin.getItin();
  }

  @GetMapping("getExploreEvents")
  public List<Event> getExploreEvents() {
    return GoogleMaps.getExploreEvents();
  }
  
  @PostMapping("/addEvent")
  public void addEvent(@RequestBody Event event) {
      itin.addEvent(event);
  }
  
//  @PutMapping("/deleteEvent")
//  public void deleteEvent(@RequestBody Event event) {
//	  itin.deleteEvent(event);
//  }
  
  @PutMapping("/changeTime")
  public ResponseEntity<?> changeTime(@RequestBody Itinerary body) {
      itin.setStartTime(body.getStartTime());
      itin.setEndTime(body.getEndTime());
      return ResponseEntity.ok().build();
  }
  
  @PutMapping("/changeLocation")
  public ResponseEntity<?> changeLocation(@RequestBody Itinerary body) {
      itin.setLocation(body.getLocation());
      return ResponseEntity.ok().build();
  }
  
  @PutMapping("/changeMaxBudget")
  public ResponseEntity<?> changeMaxBudget(@RequestBody Itinerary body) {
	  itin.setBudget(body.getBudget());
	  return ResponseEntity.ok().build();
  }
  
  @PutMapping("/changeMaxDistance")
  public ResponseEntity<?> changeMaxDistance(@RequestBody Itinerary body) {
	  itin.setMaxDist(body.getMaxDist());
	  return ResponseEntity.ok().build();
  }
  
  @PutMapping("/addTransportation")
  public ResponseEntity<?> changeTransportation(@RequestBody Map<String, Object> body) {
	  Object canWalk = body.get("walk");
	  Object canBus = body.get("bus");
	  Object canDrive = body.get("drive");
	  if (body.size() != 3 || canWalk == null || canBus == null || canDrive == null) {
		  return ResponseEntity.badRequest().build();
	  } else {
		  if ((Boolean)canWalk) itin.addMethodsOfTrans("walk");
		  if ((Boolean)canBus) itin.addMethodsOfTrans("bus");
		  if ((Boolean)canDrive) itin.addMethodsOfTrans("drive");
		  return ResponseEntity.ok().build();
	  }
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
