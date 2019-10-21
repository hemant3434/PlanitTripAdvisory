package krusty_krab.krusty_krab.controller;

import java.sql.Time;
import java.util.*;

import krusty_krab.krusty_krab.domain.Event;
import krusty_krab.krusty_krab.domain.ItineraryItem;
import krusty_krab.krusty_krab.domain.Transportation;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v1/")
public class MainController {
  
  String link = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjArePQmp"
      + "_lAhUmx4UKHSOLDy4QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.fanthatracks.com%2Fnews"
      + "%2Fmisc%2Fstar-wars-is-everywhere-021-hello-there%2F&psig=AOvVaw3rX7UoWqkc6toLU"
      + "iEGB24b&ust=1571261300777966";
  
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
    
    Map<String, Object> map = new HashMap<String, Object>();

    return ResponseEntity.ok().body(map);
  }

  @GetMapping("/getItinerary")
  public ResponseEntity<?> getItinerary(@RequestBody Map<String, Object> body) {

    Time startTime = new Time(0);
    Time endTime = new Time(10);



    //Time time, String distance, double cost, String title, float price, Time startTime, Time endTime) {
    Transportation t = new Transportation(new Time(2), 200f, "Car", 0f, new Time(3), new Time(6));

    List<ItineraryItem> itin = new ArrayList();
    itin.add(e);
    itin.add(t);

    return ResponseEntity.ok().body(itin);
  }
}
