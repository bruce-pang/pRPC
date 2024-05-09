package com.brucepang.prpc.consumer.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author BrucePang
 */
// 这里扫包要注意，不要扫到server端的包，不然会报错，这里是客户端
@ComponentScan(basePackages = {"com.brucepang.prpc.spring.reference","com.brucepang.prpc.consumer.example.controller","com.brucepang.prpc.annotation"})
@SpringBootApplication
public class PrpcConsumerApplication {
    public static void main(String[] args) {

        SpringApplication.run(PrpcConsumerApplication.class, args);
    }
}