package krusty_krab.krusty_krab.domain;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (a:User {username: {username}}) RETURN a")
    User getByUsername(@Param("username") String username);

    @Query("CREATE (a:User {username: {username}, visitedEvents:{visitedEvents}, eventRatings:[], itin:[]})")
    void addUser(@Param("username") String username, @Param("visitedEvents") List<String> visitedEvents);

    @Query("MATCH (a:User {username: {username}}) DELETE a")
    void deleteUser(@Param("username") String username);
}