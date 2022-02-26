package com.example;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.domain.PingEntity;
import com.example.repository.PingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Ping {

    @Autowired
    private PingRepository pingRepository;

    private String HOST_PONG = "https://pacific-oasis-42993.herokuapp.com";
    private static AtomicInteger retornoPing = new AtomicInteger(0);
    private static AtomicInteger envioPing = new AtomicInteger(0);

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public String ping(){ 
        Integer valorAtualRetornoPing = retornoPing.incrementAndGet();
        PingEntity pingEntity = new PingEntity("PING </br>" + new Date().toString() + "</br> Retorno Ping remoto: " + valorAtualRetornoPing);
        pingEntity.setContadorRetorno(valorAtualRetornoPing);
        pingEntity = pingRepository.insert(pingEntity); 
        String retorno = pingEntity.getName();
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
        retorno = restTemplate.postForObject(pongPath, entity, String.class); 
        retorno = retorno + "</br> Envio Ping: " + envioPing.getAndIncrement() + 
                            "</br> Retorno Ping: " + retornoPing.get();

        return retorno;
    }
}
