package krusty_krab.krusty_krab.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import java.time.*;
import java.util.Date;
import java.lang.Math.*;
import java.util.Currency;

public class GoogleMaps {

  private static GeoApiContext KEY = null;

  public GoogleMaps() {
    if (KEY == null) {
      KEY = new GeoApiContext.Builder().apiKey("AIzaSyDT2fV_yz3DWPcKbwiyxNZUxHdf373Yal8").build();
    }
  }

  public static Instant getInstant(Time start) {

    long seconds_start = start.toMinutes() * 60;

    Date date = new Date();
    Time curr = new Time(date.getYear(), date.getMonth(), date.getDay(), date.getHours(),
        date.getMinutes(), true);
    long seconds_curr = curr.toMinutes() * 60;

    Instant now = Instant.now();
    long offset = Math.abs(seconds_curr - seconds_start);
    now.plusSeconds(offset);

    return now;
  }

  public static Transportation getTransObject(DirectionsRoute[] rou, Time startTime, String i) {
    Transportation e = null;

    for (DirectionsRoute j : rou) {
      long tot_dis = 0;
      long tot_tim = 0;
      for (DirectionsLeg k : j.legs) {
        tot_dis += k.distance.inMeters;
        tot_tim += k.duration.inSeconds;
      }
      float min = tot_tim / 60;
      String subtitle = "";
      Time expected = null;
      Time endtime = null;
      if (min < 60) {
        subtitle += Integer.toString(Math.round(min)) + " minutes";
        expected = new Time(0, 0, 0, 0, Math.round(min), true);
      } else {
        float hours = min / 60;
        float minutes = min % 60;
        expected = new Time(0, 0, 0, Math.round(hours), Math.round(minutes), true);
        subtitle += Integer.toString(Math.round(hours)) + " hours "
            + Integer.toString(Math.round(minutes)) + " minutes";
      }

      if ((startTime.getMinute() + expected.getMinute()) < 60) {
        endtime = new Time(startTime.getYear(), startTime.getMonth(), startTime.getDay(),
            startTime.getHour() + expected.getHour(), startTime.getMinute() + expected.getMinute(),
            true);
      } else {
        endtime = new Time(startTime.getYear(), startTime.getMonth(), startTime.getDay(),
            startTime.getHour() + expected.getHour()
                + (startTime.getMinute() + expected.getMinute()) / 60,
            (startTime.getMinute() + expected.getMinute()) % 60, true);
      }
      e = new Transportation(((float) tot_dis / (float) 1000), i, 2.0f, startTime, endtime,
          expected, "icon", subtitle);
      break;
    }
    return e;
  }

  public List<Transportation> getTransportation(String loc1, String loc2, Time startTime,
      List<String> methods) {
    List<Transportation> obj = new ArrayList<Transportation>();

    for (String i : methods) {
      if (i.equals("Bike")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1)
            .destinationPlaceId(loc2).mode(TravelMode.BICYCLING);
        DirectionsResult res = null;
        try {
          res = req.await();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        DirectionsRoute[] rou = res.routes;
        obj.add(getTransObject(rou, startTime, i));
      }
      if (i.equals("Drive")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1)
            .destinationPlaceId(loc2).mode(TravelMode.DRIVING);
        DirectionsResult res = null;
        try {
          res = req.await();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        DirectionsRoute[] rou = res.routes;
        obj.add(getTransObject(rou, startTime, i));
      }
      if (i.equals("Transit")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1)
            .destinationPlaceId(loc2).mode(TravelMode.TRANSIT).departureTime(getInstant(startTime))
            .transitMode(TransitMode.BUS);
        DirectionsResult res = null;
        try {
          res = req.await();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        DirectionsRoute[] rou = res.routes;
        obj.add(getTransObject(rou, startTime, i));
      }
      if (i.equals("Walk")) {
        DirectionsApiRequest req = DirectionsApi.getDirections(KEY, "", "").originPlaceId(loc1)
            .destinationPlaceId(loc2).mode(TravelMode.WALKING);
        DirectionsResult res = null;
        try {
          res = req.await();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        DirectionsRoute[] rou = res.routes;
        obj.add(getTransObject(rou, startTime, i));
      }
    }
    return obj;
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
  public static Time[] getTime(OpeningHours hours) {
    int str_year = 2019;
    int str_month = 0;
    int str_day = 0;
    int str_hour = 0;
    int str_minute = 0;
    boolean str_positive = true;

    int clo_year = 2019;
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
      PlaceDetailsRequest req = PlacesApi.placeDetails(KEY, i).fields();
      PlaceDetails r = req.await();

      int first = filterByPrice(budget, getPriceLevel(r.priceLevel));
      int second = filterByTime(startTime, endTime, getTime(r.openingHours));

      if ((first + second) == 0) {
        Event e = new Event(r.name, r.formattedAddress,
            Arrays.toString(r.types).replace("[", "").replace("]", ""), (int) r.rating,
            getPriceLevel(r.priceLevel), getTime(r.openingHours)[0], getTime(r.openingHours)[1],
            new Time(0, 0, 0, 2, 0, true), getPhoto(r.photos), r.url.toString(), i);

        events.add(e);
      }
    }

    for (Event i : events) {
      System.out.println(i.getId());
      System.out.println(i.getLocation());
    }
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
