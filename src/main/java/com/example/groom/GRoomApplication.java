package com.example.groom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableCaching
@SpringBootApplication
@EnableAspectJAutoProxy
public class GRoomApplication {
    public static void main(String[] args) {
        SpringApplication.run(GRoomApplication.class, args);
    }
}
