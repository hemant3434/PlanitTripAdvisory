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
  
  @GetMapping("/getDummy2")
  public ResponseEntity<?> getDummy2(@RequestBody Map<String, Object> body) {

    int maxDist = 20;
    int budget = 200;
    
    Map<String, Object> map = new HashMap<String, Object>();
    Event e1 = new Event("Ripley's Aquarium", "Ripley's Aquarium", "aquarium", 5, 20, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true));
    Event e2 = new Event("CN Tower", "CN Tower", "lookout", 4, 40, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true), new Time(0, 0, 0, 2, 0, true));

    //System.out.println(e1.getScore(new Time(2019, 10, 25, 9, 0, true), "Union Station", this.gm, maxDist, budget));
    //System.out.println(e2.getScore(new Time(2019, 10, 25, 9, 0, true), "Union Station", this.gm, maxDist, budget));
    //System.out.println(e1.getScore(new Time(2019, 10, 25, 9, 0, true), "Spadina", this.gm, maxDist, budget));
    //System.out.println(e2.getScore(new Time(2019, 10, 25, 9, 0, true), "Spadina", this.gm, maxDist, budget));

    Transportation t = gm.getTransportation("Union Station", "Ripley's Aquarium", new Time(2019, 10, 25, 8, 0, true));


    itin.setStartTime(new Time(2019, 10, 25, 9, 00, true));
    itin.setEndTime(new Time(2019, 10, 25, 20, 00, true));
    itin.setHome("University of Toronto Scarborough");
    itin.setLocation("Toronto");
    itin.setMaxDist(maxDist);

    List<String> activities = new ArrayList();
    activities.add("aquarium");
    activities.add("art gallery");

    itin.setActivities(activities);
    itin.setBudget(budget);
    Event e = this.itin.getNextBestEvent(new Time(2019, 10, 25, 10, 0, true), "Union Station");

    //itin.createItinerary();
    map.put("A", e);

    return ResponseEntity.ok().body(map);
    //return ResponseEntity.ok().body(itin.getItin());
  }

  @GetMapping("/getItinerary")
  public ResponseEntity<?> getItinerary(@RequestBody Map<String, Object> body) {

    itin.setStartTime(new Time(2019, 10, 25, 9, 00, true));
    itin.setEndTime(new Time(2019, 10, 25, 20, 00, true));
    itin.setHome("University of Toronto Scarborough");
    itin.setLocation("Toronto");
    itin.setMaxDist(20);

    List<String> activities = new ArrayList();
    activities.add("aquarium");
    activities.add("art gallery");

    itin.setActivities(activities);
    itin.setBudget(200);

    itin.createItinerary();

    return ResponseEntity.ok().body(itin.getItin());
  }
}
