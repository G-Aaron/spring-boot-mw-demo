package com.grp.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-12-29 13:24
 **/
@SpringBootApplication
public class RabbitMqDemoApp {
    public static void main(String[] args){
        SpringApplication.run(RabbitMqDemoApp.class, args);
    }
}
