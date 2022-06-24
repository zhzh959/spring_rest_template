package com.gunt;

import com.gunt.config.MyConfig;
import com.gunt.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;


/**
 * Hello world!
 */
public class App {
    private final static String URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        String result = response.getBody();
        List<String> cookies = response.getHeaders().get("Set-Cookie"); //.stream().forEach(System.out::println);
        System.out.println(result);
        System.out.println(cookies);


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        User user = communication.getUser();
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        communication.createUser(entity);


        User user1 = communication.getUser1();
        HttpEntity<User> entity1 = new HttpEntity<>(user1, headers);
        communication.updateUser(entity1);

        HttpEntity<User> entity2 = new HttpEntity<>(headers);
        communication.deleteUser(entity2);

    }

}




