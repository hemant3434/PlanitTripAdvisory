package krusty_krab.krusty_krab.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.getByUsername(username);
    }

    public void addUser(User user) {userRepository.addUser(user.getUsername(), user.getVisitedEvents(), null, null);}

    public void deleteUser(String username) {userRepository.deleteUser(username);}

    public void changeUser(User user) {
        deleteUser(user.getUsername());
        addUser(user);
    }
}
