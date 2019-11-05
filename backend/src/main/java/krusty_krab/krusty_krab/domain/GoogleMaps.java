package krusty_krab.krusty_krab.domain;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

public class GoogleMaps {
  
  private static GeoApiContext KEY = null;

  public GoogleMaps() {
    if(KEY == null) {
      KEY = new GeoApiContext.Builder().apiKey("AIzaSyCBL_WbHzOOXyq2mrs34KZIa7RglpealxQ").build();
    }
  }

  // Given 2 locations and a time, gets the transportation object from the Google Maps API between
  // these 2 locations at this time
  public Transportation getTransportation(String loc1, String loc2, Time startTime) {

    if ((loc1.equals("union station") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("union station") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(10, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 15, true)), new Time(0, 0, 0, 0, 15, true),
          "flight-takeoff", "15 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("cn tower"))
        || (loc2.equals("union station") && loc1.equals("cn tower"))) {
      return new Transportation(15, "bus", 1, startTime,
          startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true),
          "flight-takeoff", "5 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("Canadian National Exhibition"))
        || (loc2.equals("union station") && loc1.equals("Canadian National Exhibition"))) {
      return new Transportation(80, "bus", 2, startTime,
          startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true),
          "flight-takeoff", "30 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("Eaton Centre"))
        || (loc2.equals("union station") && loc1.equals("Eaton Centre"))) {
      return new Transportation(1, "bus", 2, startTime,
          startTime.add(new Time(0, 0, 0, 0, 10, true)), new Time(0, 0, 0, 0, 10, true),
          "flight-takeoff", "10 minutes");
    }
    if ((loc1.equals("spadina") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("spadina") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(100, "bus", 1, startTime,
          startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true),
          "flight-takeoff", "30 minutes");
    }
    if ((loc1.equals("spadina") && loc2.equals("cn tower"))
        || (loc2.equals("spadina") && loc1.equals("cn tower"))) {
      return new Transportation(5, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true),
          "flight-takeoff", "5 minutes");
    }

    if ((loc1.equals("cn tower") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("cn tower") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(5, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 8, true)), new Time(0, 0, 0, 0, 8, true),
          "flight-takeoff", "8 minutes");
    }

    if ((loc1.equals("Canadian National Exhibition") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("Canadian National Exhibition") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(2, "bus", 1, startTime,
          startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true),
          "flight-takeoff", "5 minutes");
    }

    if ((loc1.equals("Eaton Centre") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("Eaton Centre") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(3, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 13, true)), new Time(0, 0, 0, 0, 13, true),
          "flight-takeoff", "13 minutes");
    }

    if ((loc1.equals("Canadian National Exhibition") && loc2.equals("cn tower"))
        || (loc2.equals("Canadian National Exhibition") && loc1.equals("cn tower"))) {
      return new Transportation(3, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true),
          "flight-takeoff", "30 minutes");
    }

    if ((loc1.equals("Eaton Centre") && loc2.equals("cn tower"))
        || (loc2.equals("Eaton Centre") && loc1.equals("cn tower"))) {
      return new Transportation(4, "bus", 1, startTime,
          startTime.add(new Time(0, 0, 0, 0, 10, true)), new Time(0, 0, 0, 0, 10, true),
          "flight-takeoff", "10 minutes");
    }

    if ((loc1.equals("Canadian National Exhibition") && loc2.equals("Eaton Centre"))
        || (loc2.equals("Canadian National Exhibition") && loc1.equals("Eaton Centre"))) {
      return new Transportation(1, "bus", 1, startTime,
          startTime.add(new Time(0, 0, 0, 0, 25, true)), new Time(0, 0, 0, 0, 25, true),
          "flight-takeoff", "25 minutes");
    }

    return new Transportation();
  }
  
  public static int filterByPrice(float budget, PriceLevel obj) {
    return 0;
  }
  
  public static int filterByTime(Time startTime, Time endTime, OpeningHours obj) {
    return 0;
  }
  
  public static int filterByType(List<String> activities, AddressComponent[] components) {
    return 0;
  }

  public static String getLocation(AddressComponent[] components) {
    return "";
  }
  
  public List<Event> getEvents(Time startTime, Time endTime, String curLoc, String location,
      float maxDist, List<String> activities, float budget) throws Exception {
    List<Event> events = new ArrayList();

//    double lat = 43.7764;
//    double ltd = -79.2318;
//    LatLng cur_loc = new LatLng((double)lat, (double)ltd);
//    NearbySearchRequest all_events = PlacesApi.nearbySearchQuery(KEY, cur_loc).radius((int)5000);
//
//    ArrayList<String> place_ids = null;
//    if(all_events != null) {
//      PlacesSearchResponse obj = all_events.awaitIgnoreError();
//      PlacesSearchResult results[] = obj.results;
//      
//      place_ids = new ArrayList<String>();
//      for (PlacesSearchResult i: results) {
//        place_ids.add(i.placeId);
//      }
//    }
//    
//    for (String i: place_ids) {
//      PlaceDetailsRequest req = PlacesApi.placeDetails(KEY, i);
//      PlaceDetails r = req.await();
//      
//      int first = filterByPrice(budget, r.priceLevel);
//      int second = filterByTime(startTime, endTime, r.openingHours);
//      int third = filterByType(activities, r.addressComponents);
//      
//      System.out.println(r.addressComponents);
//      if((first+second+third) == 0) {
//        Event e = new Event();
//        events.add(e);
//      }
//    }    
    
    Event e1 = new Event("ripley's aquarium", "ripley's aquarium", 43.2, 43.2, "aquarium", 5, 2,
        new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true), "toronto", "There be fish", "1");
    Event e2 = new Event("cn tower", "cn tower", 43.2, 43.2,"lookout", 4, 4,
        new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true), "../images/toronto.jpg", "If Quebec is Canada's ass...", "2");
    Event e3 = new Event("Canadian National Exhibition", "Canadian National Exhibition", 43.2, 43.2,"Festival",
        4, 4, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true),
        "https://www.dailydot.com/wp-content/uploads/2018/10/pikachu_surprised_meme-e1540570767482.png",
        "If Quebec is Canada's ass...", "3");
    /*Event e4 = new Event("Eaton Centre", "Eaton Centre", "Mall", 4, 4,
        new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true),
        "https://www.dailydot.com/wp-content/uploads/2018/10/pikachu_surprised_meme-e1540570767482.png",
        "If Quebec is Canada's ass...", "4");*/

    events.add(e1);
    events.add(e2);
    events.add(e3);
    //events.add(e4);

    return events;
  }

  // Gets event from google maps api using name of event
  public Event getEvent(String name) {
    return new Event();
  }

  // Gets event from google maps api using the event id
  public static Event getEventByID(String id) {
    if(id.equals("1")){
      return new Event("ripley's aquarium", "ripley's aquarium", 43.2, 43.2,"aquarium", 5, 2,
              new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
              new Time(0, 0, 0, 2, 0, true), "toronto", "There be fish", "1");
    }
    else if(id.equals("2")){
      return new Event("cn tower", "cn tower", 43.2, 43.2,"aquarium", 4, 4,
              new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
              new Time(0, 0, 0, 2, 0, true), "../images/toronto.jpg", "If Quebec is Canada's ass...", "2");
    }
    else if(id.equals("3")){
      return new Event("Canadian National Exhibition", "Canadian National Exhibition", 43.2, 43.2,"aquarium",
              4, 4, new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
              new Time(0, 0, 0, 2, 0, true),
              "https://www.dailydot.com/wp-content/uploads/2018/10/pikachu_surprised_meme-e1540570767482.png",
              "If Quebec is Canada's ass...", "3");
    }
    else if(id.equals("4")){
      return new Event("Eaton Centre", "Eaton Centre", 43.2, 43.2,"Mall", 4, 4,
              new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
              new Time(0, 0, 0, 2, 0, true),
              "https://www.dailydot.com/wp-content/uploads/2018/10/pikachu_surprised_meme-e1540570767482.png",
              "If Quebec is Canada's ass...", "4");
    }
     return new Event();
  }

  public static List<Event> getExploreEvents(){
    List<Event> events= new ArrayList<>();
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    events.add(getEventByID("4"));
    return events;
  }

  public static float budgetToRange(float budget){
    if(budget<10){
      return 0;
    }
    else if(budget < 20){
      return 1;
    }
    else if(budget < 30){
      return 2;
    }
    else if(budget < 40){
      return 3;
    }
    else{
      return 4;
    }
  }

  public static float rangeToBudget(float range){
    if(range == 0){
      return 0;
    }
    else if(range == 1){
      return 10;
    }
    else if(range == 2){
      return 20;
    }
    else if(range == 3){
      return 30;
    }
    else{
      return 40;
    }
  }
  
  public GeoApiContext getKEY() {
    return KEY;
  }

  public void setKEY(GeoApiContext kEY) {
    KEY = kEY;
  }
}
