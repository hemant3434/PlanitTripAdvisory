package krusty_krab.krusty_krab.mongo;

import java.util.*;

import com.mongodb.*;
import krusty_krab.krusty_krab.domain.User;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBUserDAO {

    private DBCollection col;

    public MongoDBUserDAO(MongoClient mc) {
        this.col = mc.getDB("krustykrab").getCollection("users");
    }

    public void createUser(User p) {
        DBObject doc = UserConverter.toDBObject(p);
        this.col.insert(doc);
    }

    public User readUser(String username) {
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            User p = UserConverter.toUser(doc);
            if(p.getUsername().equals(username)){
                return p;
            }
        }
        return null;
    }

    public List<User> readAllUsers() {
        List<User> data = new ArrayList<User>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            User p = UserConverter.toUser(doc);
            data.add(p);
        }
        return data;
    }

    public void updateUser(User p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("username", p.getUsername()).get();
        this.col.update(query, UserConverter.toDBObject(p));
    }

}
