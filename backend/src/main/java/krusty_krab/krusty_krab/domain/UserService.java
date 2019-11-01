package krusty_krab.krusty_krab.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.getByUsername(username);
    }

    public void addUser(String username) {userRepository.addUser(username);}

    public void deleteUser(String username) {userRepository.deleteUser(username);}
}
