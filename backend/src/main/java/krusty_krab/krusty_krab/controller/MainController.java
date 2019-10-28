package krusty_krab.krusty_krab.controller;

import java.util.*;

import krusty_krab.krusty_krab.domain.*;
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
  GoogleMaps gm = new GoogleMaps();
  
  @GetMapping("/getDummy1")
  public ResponseEntity<?> getDummy1(@RequestBody Map<String, Object> body) {
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("Title", "Obi-Wan");
    map.put("Description", "Iconic Line");
    map.put("URL", link);

    return ResponseEntity.ok().body(map);
  }

  @GetMapping("/getItinerary")
  public ResponseEntity<?> getItinerary(@RequestBody Map<String, Object> body) throws Exception {

	// is this map needed?
    //Map<String, Object> map = new HashMap<String, Object>();

    //Sends dummy data for the user filters into the itinerary class
    itin.setStartTime(new Time(2019, 10, 25, 9, 00, true));
    itin.setEndTime(new Time(2019, 10, 25, 20, 00, true));
    itin.setHome("union station");
    itin.setLocation("toronto");
    itin.setMaxDist(20);
    List<String> activities = new ArrayList<String>();
    activities.add("aquarium");
    activities.add("art gallery");
    itin.setActivities(activities);
    itin.setBudget(200);

    itin.createItinerary();
    return ResponseEntity.ok().body(itin.getItin());
  }
  
  @GetMapping("/viewItinerary")
  public List<ItineraryItem> viewItinerary() {
      return itin.getItin();
  }
  
  @PostMapping("/addEvent")
  public void addEvent(@RequestBody Event event) {
      itin.addEvent();
  }
  
  @PutMapping("/changeTime")
  public void changeTime(@RequestBody Itinerary body) {
      itin.setStartTime(body.getStartTime());
      itin.setEndTime(body.getEndTime());
  }
  
  @PutMapping("/changeLocation")
  public void changeLocation(@RequestBody Itinerary body) {
      itin.setLocation(body.getLocation());
  }
  
  @PutMapping("/changeMaxBudget")
  public void changeMaxBudget(@RequestBody Itinerary body) {
	  itin.setBudget(body.getBudget());
  }
  
  @PutMapping("/changeMaxDistance")
  public void changeMaxDistance(@RequestBody Itinerary body) {
	  itin.setMaxDist(body.getMaxDist());
  }
}
