package krusty_krab.krusty_krab.mongo;

import krusty_krab.krusty_krab.domain.Event;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import krusty_krab.krusty_krab.domain.Time;

import java.util.HashMap;
import java.util.Map;

public class EventConverter {

    // convert Post Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Event p) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("location", p.getLocation())
                .append("longitude", p.getLongitude())
                .append("latitude", p.getLatitude())
                .append("activity", p.getActivity())
                .append("rating", p.getRating())
                .append("image", p.getImage())
                .append("description", p.getDescription())
                .append("id", p.getId())
                .append("type", p.getType())
                .append("title", p.getTitle())
                .append("price", p.getPrice())
                .append("startTime", toMapFromTime(p.getStartTime()))
                .append("endTime", toMapFromTime(p.getEndTime()))
                .append("expectedLength", toMapFromTime(p.getExpectedLength()));
        return builder.get();
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

}