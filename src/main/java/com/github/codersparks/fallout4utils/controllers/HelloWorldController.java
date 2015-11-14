package com.github.codersparks.fallout4utils.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by codersparks on 14/11/2015.
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value="/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<String>("Hello World!!!!", HttpStatus.OK);
    }
}
