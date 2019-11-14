package krusty_krab.krusty_krab.mongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import krusty_krab.krusty_krab.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConverter {

    public static DBObject toDBObject(User p) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("username", p.getUsername())
                .append("visitedEvents", p.getVisitedEvents())
                .append("eventRatings", p.getEventRatings())
                .append("itinerary",UserConverter.toMapFromItinerary(p.getItinerary()));
        return builder.get();
    }

    public static Itinerary toItinerary(DBObject doc) {
        Itinerary p = new Itinerary();
        p.setStartTime(toTimeFromMap((Map<String, Object>)doc.get("startTime")));
        p.setEndTime(toTimeFromMap((Map<String, Object>)doc.get("endTime")));
        p.setLocation((String) doc.get("location"));
        p.setLocationLat((double) doc.get("locationLat"));
        p.setLocationLong((double) doc.get("locationLong"));
        p.setHome((String) doc.get("home"));
        p.setHomeLat((double) doc.get("homeLat"));
        p.setHomeLong((double) doc.get("homeLong"));
        p.setMaxDist(((Double) doc.get("maxDist")).floatValue());
        p.setBudget(((Double) doc.get("budget")).floatValue());
        p.setActivities((List<String>)doc.get("activities"));
        p.setMethodsOfTrans((List<String>)doc.get("methodsOfTrans"));
        p.setItin(toItinFromList((List<Map<String, Object>>)doc.get("itin")));
        return p;
    }

    public static User toUser(DBObject doc) {
        User p = new User();
        p.setUsername((String)doc.get("username"));
        p.setVisitedEvents((List<String>)doc.get("visitedEvents"));
        p.setEventRatings((Map<String, Integer>)doc.get("eventRatings"));
        p.setItinerary(toItineraryFromMap((Map<String, Object>)doc.get("itinerary")));
        return p;
    }

    public static Itinerary toItineraryFromMap(Map<String, Object> doc) {
        Itinerary p = new Itinerary();
        p.setStartTime(toTimeFromMap((Map<String, Object>)doc.get("startTime")));
        p.setEndTime(toTimeFromMap((Map<String, Object>)doc.get("endTime")));
        p.setLocation((String) doc.get("location"));
        p.setLocationLat((double) doc.get("locationLat"));
        p.setLocationLong((double) doc.get("locationLong"));
        p.setHome((String) doc.get("home"));
        p.setHomeLat((double) doc.get("homeLat"));
        p.setHomeLong((double) doc.get("homeLong"));
        p.setMaxDist(((Double) doc.get("maxDist")).floatValue());
        p.setBudget(((Double) doc.get("budget")).floatValue());
        p.setActivities((List<String>)doc.get("activities"));
        p.setMethodsOfTrans((List<String>)doc.get("methodsOfTrans"));
        p.setItin(toItinFromList((List<Map<String, Object>>)doc.get("itin")));
        return p;
    }

    public static List<ItineraryItem> toItinFromList(List<Map<String, Object>> doc) {
        List<ItineraryItem> itin = new ArrayList<>();
        for(Map<String, Object> item:doc){
            itin.add(toItinItemFromMap(item));
        }
        return itin;
    }
    public static ItineraryItem toItinItemFromMap(Map<String, Object> doc) {
        if(doc.containsKey("distance")){
            return toTransFromMap(doc);
        }
        else{
            return toEventFromMap(doc);
        }
    }

    public static Transportation toTransFromMap(Map<String, Object> doc) {
        Transportation p = new Transportation();
        p.setDistance(((Double) doc.get("distance")).floatValue());
        p.setIcon((String)doc.get("icon"));
        p.setSubtitle((String) doc.get("subtitle"));
        p.setType((String)doc.get("type"));
        p.setTitle((String)doc.get("title"));
        p.setPrice(((Double)doc.get("price")).floatValue());
        p.setStartTime(toTimeFromMap((Map<String, Object>)doc.get("startTime")));
        p.setEndTime(toTimeFromMap((Map<String, Object>)doc.get("endTime")));
        p.setExpectedLength(toTimeFromMap((Map<String, Object>)doc.get("expectedLength")));
        return p;
    }

    // convert DBObject Object to Post
    // take special note of converting ObjectId to String
    public static Event toEvent(DBObject doc) {
        Event p = new Event();
        p.setLocation((String) doc.get("location"));
        p.setLongitude((double) doc.get("longitude"));
        p.setLatitude((double) doc.get("latitude"));
        p.setActivity((String) doc.get("activity"));
        p.setRating((Integer) doc.get("rating"));
        p.setImage((String) doc.get("image"));
        p.setDescription((String) doc.get("description"));
        p.setId((String) doc.get("id"));
        p.setType((String)doc.get("type"));
        p.setTitle((String)doc.get("title"));
        p.setPrice(((Double)doc.get("price")).floatValue());
        p.setStartTime(toTimeFromMap((Map<String, Object>)doc.get("startTime")));
        p.setEndTime(toTimeFromMap((Map<String, Object>)doc.get("endTime")));
        p.setExpectedLength(toTimeFromMap((Map<String, Object>)doc.get("expectedLength")));
        return p;
    }

    public static Event toEventFromMap(Map<String, Object> body) {
        Event p = new Event();
        p.setLocation((String) body.get("location"));
        p.setLongitude((double) body.get("longitude"));
        p.setLatitude((double) body.get("latitude"));
        p.setActivity((String) body.get("activity"));
        p.setRating((Integer) body.get("rating"));
        p.setImage((String) body.get("image"));
        p.setDescription((String) body.get("description"));
        p.setId((String) body.get("id"));
        p.setType((String)body.get("type"));
        p.setTitle((String)body.get("title"));
        p.setPrice(((Double)body.get("price")).floatValue());
        p.setStartTime(toTimeFromMap((Map<String, Object>)body.get("startTime")));
        p.setEndTime(toTimeFromMap((Map<String, Object>)body.get("endTime")));
        p.setExpectedLength(toTimeFromMap((Map<String, Object>)body.get("expectedLength")));

        return p;
    }

    public static Time toTimeFromMap(Map<String, Object> body) {
        return new Time((int)body.get("year"),(int)body.get("month"),(int)body.get("day"),(int)body.get("hour"),(int)body.get("minute"),(boolean)body.get("positive"));
    }

    public static Map<String, Object> toMapFromTime(Time time) {
        Map<String, Object> body = new HashMap<>();
        body.put("year", time.getYear());
        body.put("month", time.getMonth());
        body.put("day", time.getDay());
        body.put("hour", time.getHour());
        body.put("minute", time.getMinute());
        body.put("positive", time.isPositive());
        return body;
    }

    public static List<Map<String, Object>> toListFromItin(List<ItineraryItem> itin) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(ItineraryItem i:itin){
            list.add(toMapFromItinItem(i));
        }
        return list;
    }
    public static Map<String, Object> toMapFromItinItem(ItineraryItem itinItem) {
        Map<String, Object> map;
        if(itinItem instanceof Event){
            map = toMapFromEvent((Event)itinItem);
        }
        else{
            map = toMapFromTrans((Transportation) itinItem);
        }
        return map;
    }

    public static Map<String, Object> toMapFromEvent(Event p) {
        Map<String, Object> map = new HashMap<>();
        map.put("location", p.getLocation());
        map.put("longitude", p.getLongitude());
        map.put("latitude", p.getLatitude());
        map.put("activity", p.getActivity());
        map.put("rating", p.getRating());
        map.put("image", p.getImage());
        map.put("description", p.getDescription());
        map.put("id", p.getId());
        map.put("type", p.getType());
        map.put("title", p.getTitle());
        map.put("price", p.getPrice());
        map.put("startTime", UserConverter.toMapFromTime(p.getStartTime()));
        map.put("endTime", UserConverter.toMapFromTime(p.getEndTime()));
        map.put("expectedLength", UserConverter.toMapFromTime(p.getExpectedLength()));
        return map;
    }
    public static Map<String, Object> toMapFromTrans(Transportation p) {
        Map<String, Object> map = new HashMap<>();
        map.put("distance", p.getDistance());
        map.put("icon", p.getIcon());
        map.put("subtitle", p.getSubtitle());
        map.put("type", p.getType());
        map.put("title", p.getTitle());
        map.put("price", p.getPrice());
        map.put("startTime", UserConverter.toMapFromTime(p.getStartTime()));
        map.put("endTime", UserConverter.toMapFromTime(p.getEndTime()));
        map.put("expectedLength", UserConverter.toMapFromTime(p.getExpectedLength()));
        return map;
    }

    public static Map<String, Object> toMapFromItinerary(Itinerary p) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", UserConverter.toMapFromTime(p.getStartTime()));
        map.put("endTime", UserConverter.toMapFromTime(p.getEndTime()));
        map.put("location", p.getLocation());
        map.put("locationLat", p.getLocationLat());
        map.put("locationLong", p.getLocationLong());
        map.put("home", p.getHome());
        map.put("homeLat", p.getHomeLat());
        map.put("homeLong", p.getHomeLong());
        map.put("maxDist", p.getMaxDist());
        map.put("budget", p.getBudget());
        map.put("activities", p.getActivities());
        map.put("methodsOfTrans", p.getMethodsOfTrans());
        map.put("itin",UserConverter.toListFromItin(p.getItin()));
        return map;
    }

}