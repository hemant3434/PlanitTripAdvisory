package krusty_krab.krusty_krab.domain;

import java.io.IOException;
import java.util.*;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import java.time.*;
import java.util.Date;
import java.lang.Math.*;

public class GoogleMaps {

    private final float WALK_DISTANCE = 3, BIKE_DISTANCE = 7, TRANSIT_DISTANCE = 15;
    
  private static GeoApiContext KEY = null;

  public GoogleMaps() {
    if (KEY == null) {
      //AIzaSyDxwdE3kLIG6GehK-6h4DnLENeiayH2FYc
      KEY = new GeoApiContext.Builder().apiKey("AIzaSyDxwdE3kLIG6GehK-6h4DnLENeiayH2FYc").build();
    }
  }
  
  public static Instant getInstant(Time start) {
    
    long seconds_start = start.toMinutes()*60;
    
    Date date = new Date();
    Time curr = new Time(date.getYear(), date.getMonth(), date.getDay(), date.getHours(), date.getMinutes(), true);
    long seconds_curr = curr.toMinutes()*60;
    
    Instant now = Instant.now();
    long offset = Math.abs(seconds_curr - seconds_start);
    now.plusSeconds(offset);
    
    return now;
  }
  
  public Transportation getTransportation(String loc1, String loc2, Time startTime, List<String> methods) {
    List<Transportation> obj = null;
    
    for(String i: methods) {
      if(i.equals("Bike")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1).destinationPlaceId(loc2).mode(TravelMode.BICYCLING);
      }
      if(i.equals("Drive")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1).destinationPlaceId(loc2).mode(TravelMode.DRIVING);
      }
      if(i.equals("Transit")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1).destinationPlaceId(loc2).mode(TravelMode.TRANSIT).departureTime(getInstant(startTime));
        try {
          DirectionsResult res = req.await();
        } catch (ApiException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }


      }
      if(i.equals("Walk")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1).destinationPlaceId(loc2).mode(TravelMode.WALKING);
      }
    }
    
    if ((loc1.equals("union station") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("union station") && loc1.equals("ripley's aquarium"))) {
      obj.add(new Transportation(10, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 15, true)), new Time(0, 0, 0, 0, 15, true),
          "flight-takeoff", "15 minutes"));
    }

    return chooseTransportation(obj);
  }

  public static int filterByPrice(float budget, float price) {

    if (budget < price) {
      return 100;
    }
    return 0;
  }

  public static int filterByTime(Time startTime, Time endTime, Time[] times) {

    return 0;
  }

  public static float getPriceLevel(PriceLevel obj) {
    float price = -1;

    if (obj == PriceLevel.FREE) {
      price = 0;
    } else if (obj == PriceLevel.INEXPENSIVE) {
      price = 1;
    } else if (obj == PriceLevel.MODERATE) {
      price = 2;
    } else if (obj == PriceLevel.EXPENSIVE) {
      price = 3;
    } else if (obj == PriceLevel.VERY_EXPENSIVE) {
      price = 4;
    } else if (obj == PriceLevel.UNKNOWN) {
      price = 0;
    }


    return price;
  }

  // index 0 - closing time
  // index 1 - start time
  public static Time[] getTime(OpeningHours hours, Time time) {
    Date date = new Date();
    date.setYear(time.getYear());
    date.setMonth(time.getMonth()-1);
    date.setDate(time.getDay());
    date.setHours(time.getHour());
    date.setMinutes(time.getMinute());
    date.setSeconds(0);
    int curDayIndex = -1;
    for (int i = 0; i < hours.periods.length; i++) {
      if (date.getDay() == 0 && hours.periods[i].open.day.getName() == "Sunday") {
        curDayIndex = i;
      } else if (date.getDay() == 1 && hours.periods[i].open.day.getName() == "Monday") {
        curDayIndex = i;
      } else if (date.getDay() == 2 && hours.periods[i].open.day.getName() == "Tuesday") {
        curDayIndex = i;
      } else if (date.getDay() == 3 && hours.periods[i].open.day.getName() == "Wednesday") {
        curDayIndex = i;
      } else if (date.getDay() == 4 && hours.periods[i].open.day.getName() == "Thursday") {
        curDayIndex = i;
      } else if (date.getDay() == 5 && hours.periods[i].open.day.getName() == "Friday") {
        curDayIndex = i;
      } else if (date.getDay() == 6 && hours.periods[i].open.day.getName() == "Saturday") {
        curDayIndex = i;
      }
    }
    if (curDayIndex != -1) {
      int str_year = time.getYear();
      int str_month = time.getMonth();
      int str_day = time.getDay();
      int str_hour = hours.periods[curDayIndex].open.time.getHour();
      int str_minute = hours.periods[curDayIndex].open.time.getMinute();
      boolean str_positive = true;

      int clo_year = time.getYear();
      int clo_month = time.getMonth();
      int clo_day = time.getDay();
      int clo_hour = hours.periods[curDayIndex].close.time.getHour();
      int clo_minute = hours.periods[curDayIndex].close.time.getMinute();
      boolean clo_positive = true;

      //if event closes after 12pm, just sets it to close at 12pm
      if(clo_hour < str_hour){
        clo_hour = 24;
        clo_minute = 0;
      }

      Time open = new Time(str_year, str_month, str_day, str_hour, str_minute, str_positive);
      Time close = new Time(clo_year, clo_month, clo_day, clo_hour, clo_minute, clo_positive);
      Time[] times = {open, close};

      times[0] = open;
      times[1] = close;

      return times;
    }
    else{
      return null;
    }

  }

  public static String getPhoto(Photo[] photos) {
    String result = "";

    if (photos == null) {
      return result;
    }
    if (photos.length == 0) {
      return result;
    }

    return result;
  }

  public List<Event> getEvents(Time startTime, Time endTime, double lat, double ltd, float maxDist,
      List<String> activities, float budget) {
    List<Event> events = new ArrayList<Event>();

    LatLng cur_loc = new LatLng((double) lat, (double) ltd);
    NearbySearchRequest all_events =
        PlacesApi.nearbySearchQuery(KEY, cur_loc).radius((int) (maxDist * 1000));

    ArrayList<String> place_ids = null;
    if (all_events != null) {
      PlacesSearchResponse obj = all_events.awaitIgnoreError();
      PlacesSearchResult results[] = obj.results;

      place_ids = new ArrayList<String>();
      for (PlacesSearchResult i : results) {
        place_ids.add(i.placeId);
      }
    }

    for (String i : place_ids) {
      PlaceDetailsRequest req = PlacesApi.placeDetails(KEY, i).fields();
      PlaceDetails r = null;
      try {
        r = req.await();
      } catch (ApiException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(r.name != null && r.formattedAddress != null && r.types != null && r.priceLevel != null && r.openingHours != null && r.photos != null && r.url != null) {
        Time[] times = getTime(r.openingHours, startTime);
        if(times != null){
          int first = filterByPrice(budget, getPriceLevel(r.priceLevel));
          int second = filterByTime(startTime, endTime, times);


          if ((first + second) == 0) {
            Event e = new Event(r.name, r.formattedAddress,
                    Arrays.toString(r.types).replace("[", "").replace("]", ""), (int) r.rating,
                    getPriceLevel(r.priceLevel), times[0], times[1],
                    new Time(0, 0, 0, 2, 0, true), getPhoto(r.photos), r.url.toString(), i);

            events.add(e);
          }
        }
      }
    }

    for (Event i : events) {
      System.out.println(i.getLocation());
    }
    return events;
  }

  public Transportation chooseTransportation(List<Transportation> trans){
      float distance = trans.get(0).getDistance();
      Transportation transportation;
      
      // Choose the transportation based on the distance you need to travel
      if (distance < WALK_DISTANCE) {
	  transportation = getTransportationWithTitle(trans, Transportation.WALK);
      } else if (WALK_DISTANCE <= distance && distance < BIKE_DISTANCE) {
	  transportation = getTransportationWithTitle(trans, Transportation.BIKE);
      } else if (BIKE_DISTANCE <= distance && distance < TRANSIT_DISTANCE) {
	  transportation = getTransportationWithTitle(trans, Transportation.TRANSIT);
      } else {
	  transportation = getTransportationWithTitle(trans, Transportation.DRIVE);
      }
      
    return transportation;
  }
  
  private Transportation getTransportationWithTitle(List<Transportation> trans, String title) {
      for (Transportation transportation : trans) {
	  if (transportation.getTitle().equals(title)) {
	      return transportation;
	  }
      }
      
      return null;
  }

  // Gets event from google maps api using name of event
  public Event getEvent(String name) {
    return new Event();
  }

  public GeoApiContext getKEY() {
    return KEY;
  }

  public void setKEY(GeoApiContext kEY) {
    KEY = kEY;
  }
}
