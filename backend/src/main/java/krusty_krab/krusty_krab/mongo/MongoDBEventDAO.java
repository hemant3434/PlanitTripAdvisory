package krusty_krab.krusty_krab.mongo;

import java.util.*;

import com.mongodb.*;
import krusty_krab.krusty_krab.domain.Event;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBEventDAO {

    private DBCollection col;

    public MongoDBEventDAO(MongoClient mc) {
        this.col = mc.getDB("krustykrab").getCollection("events");
    }

    public void createEvent(Event p) {
        DBObject doc = EventConverter.toDBObject(p);
        this.col.insert(doc);
    }

    public Event readEvent(String id) {
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Event p = EventConverter.toEvent(doc);
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public List<Event> readAllEvents() {
        List<Event> data = new ArrayList<Event>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Event p = EventConverter.toEvent(doc);
            data.add(p);
        }
        return data;
    }

}
