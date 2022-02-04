package com.msa.module3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SimpleRestController {

    private Logger logger = LoggerFactory.getLogger(SimpleRestController.class);

    @GetMapping
    public String get(){
        logger.info("GET METHOD");
        return "GET";
    }

    @PostMapping
    public String post(){
        logger.info("POST METHOD");
        return "POST";
    }

    @PutMapping
    public String put(){
        logger.info("PUT METHOD");
        return "PUT";
    }

    @DeleteMapping
    public String delete(){
        logger.info("DELETE METHOD");
        return "DELETE";
    }

}
