package com.example;

import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Ping {

    private String HOST_PONG = "https://pacific-oasis-42993.herokuapp.com";
 
    @RequestMapping("/ping")
    public String ping(){
        String retorno = "PING </br>" + new Date().toString();
        return retorno;
    }

    @RequestMapping("/sendping")
    public String sendPing(){
        String retorno = callPing();

        return retorno;
    }

    private String callPing(){
        String retorno = "PING </br>" + new Date().toString();

        //define path for pong
        String pongPath = HOST_PONG + "/pong";
        //make a call
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        HttpEntity<String> entity = new HttpEntity<>(retorno, headers);
        restTemplate.postForObject(pongPath, entity, String.class); 
        retorno = entity.getBody();

        return retorno;
    }
}
