package com.elyzar.play;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@EnableAutoConfiguration
public class BlockingOpResource {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/blocking/name")
    public ResponseEntity getByName(@PathParam("name") String name) {
        String result = potentiallyVerySlowSynchronousCall(name);
        return ResponseEntity.ok(result);
    }

    private String potentiallyVerySlowSynchronousCall(String name) {
        try {
            System.out.println("doing something about "+name);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "James";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlockingOpResource.class, args);
    }

}
