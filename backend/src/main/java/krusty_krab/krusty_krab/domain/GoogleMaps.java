package krusty_krab.krusty_krab.domain;

import java.io.IOException;
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
    if (KEY == null) {
      KEY = new GeoApiContext.Builder().apiKey("AIzaSyDT2fV_yz3DWPcKbwiyxNZUxHdf373Yal8").build();
    }
  }

  public Transportation getTransportation(String loc1, String loc2, Time startTime) {

    if ((loc1.equals("union station") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("union station") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(10, "walk", 0, startTime,
          startTime.add(new Time(0, 0, 0, 0, 15, true)), new Time(0, 0, 0, 0, 15, true),
          "flight-takeoff", "15 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("cn tower"))
        || (loc2.equals("union station") && loc1.equals("cn tower"))) {
      return new Transportation(15, "bus", 3.5f, startTime,
          startTime.add(new Time(0, 0, 0, 0, 5, true)), new Time(0, 0, 0, 0, 5, true),
          "flight-takeoff", "5 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("Canadian National Exhibition"))
        || (loc2.equals("union station") && loc1.equals("Canadian National Exhibition"))) {
      return new Transportation(80, "bus", 20f, startTime,
          startTime.add(new Time(0, 0, 0, 0, 30, true)), new Time(0, 0, 0, 0, 30, true),
          "flight-takeoff", "30 minutes");
    }
    if ((loc1.equals("union station") && loc2.equals("Eaton Centre"))
        || (loc2.equals("union station") && loc1.equals("Eaton Centre"))) {
      return new Transportation(1, "bus", 20f, startTime,
          startTime.add(new Time(0, 0, 0, 0, 10, true)), new Time(0, 0, 0, 0, 10, true),
          "flight-takeoff", "10 minutes");
    }
    if ((loc1.equals("spadina") && loc2.equals("ripley's aquarium"))
        || (loc2.equals("spadina") && loc1.equals("ripley's aquarium"))) {
      return new Transportation(100, "bus", 3.5f, startTime,
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
      return new Transportation(2, "bus", 3.5f, startTime,
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
      return new Transportation(4, "bus", 3.5f, startTime,
          startTime.add(new Time(0, 0, 0, 0, 10, true)), new Time(0, 0, 0, 0, 10, true),
          "flight-takeoff", "10 minutes");
    }

    if ((loc1.equals("Canadian National Exhibition") && loc2.equals("Eaton Centre"))
        || (loc2.equals("Canadian National Exhibition") && loc1.equals("Eaton Centre"))) {
      return new Transportation(1, "bus", 3.5f, startTime,
          startTime.add(new Time(0, 0, 0, 0, 25, true)), new Time(0, 0, 0, 0, 25, true),
          "flight-takeoff", "25 minutes");
    }

    return new Transportation();
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
    }

    return price;
  }
  
  // index 0 - closing time
  // index 1 - start time
  public static Time[] getTime(OpeningHours hours) {
    int str_year = 0;
    int str_month = 0;
    int str_day = 0;
    int str_hour = 0;
    int str_minute = 0;
    boolean str_positive = true;
    
    int clo_year = 0;
    int clo_month = 0;
    int clo_day = 0;
    int clo_hour = 0;
    int clo_minute = 0;
    boolean clo_positive = false;
    
    Time open = new Time(str_year, str_month, str_day, str_hour, str_minute, str_positive);
    Time close = new Time(clo_year, clo_month, clo_day, clo_hour, clo_minute, clo_positive);
    Time[] times = {open, close};

    times[0] = open;
    times[1] = close;
    return times;
  }

  public List<Event> getEvents(Time startTime, Time endTime, double lat, double ltd, float maxDist,
      List<String> activities, float budget) throws Exception {
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
      PlaceDetailsRequest req = PlacesApi.placeDetails(KEY, i);
      PlaceDetails r = req.await();

      int first = filterByPrice(budget, getPriceLevel(r.priceLevel));
      int second = filterByTime(startTime, endTime, getTime(r.openingHours));

      if ((first + second) == 0) {

        Event e = new Event(r.name, r.formattedAddress, "", (int) r.rating,
            getPriceLevel(r.priceLevel), getTime(r.openingHours)[0], getTime(r.openingHours)[1],
            new Time(0, 0, 0, 2, 0, true), "img", r.url.toString(), i);
        
        events.add(e);
      }
    }


    Event e1 = new Event("ripley's aquarium", "ripley's aquarium", "aquarium", 5, 20,
        new Time(2019, 10, 25, 8, 0, true), new Time(2019, 10, 25, 22, 0, true),
        new Time(0, 0, 0, 2, 0, true), "toronto", "There be fish", "1");
    events.add(e1);

    return events;
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
