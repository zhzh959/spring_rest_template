package com.gunt;

import com.gunt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.stream.Collectors;


@RestController
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final static String URL = "http://94.198.50.185:7081/api/users";

    public User getUser() {
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 25);
        return user;
    }

    public User getUser1() {
        User user = new User();
        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte) 25);
        return user;
    }

    public void createUser(HttpEntity<User> entity) {

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

    }


    public void updateUser(HttpEntity<User> entity) {

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());

    }

    public void deleteUser(HttpEntity<User> entity) {

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    URL + "/3", HttpMethod.DELETE, entity, String.class);
        } catch (RuntimeException e) {
            System.out.println("Пользователя с таким id не существует");
        }
        try {
            System.out.println(response.getBody());
        } catch (NullPointerException e) {
            System.out.println("все равно печатай боди");
        } finally {
            System.out.println(response.getBody());
        }
    }
}


