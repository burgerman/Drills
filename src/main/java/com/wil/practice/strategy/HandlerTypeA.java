package com.wil.practice.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerTypeA implements Handler {

    @Bean
    public Handler handlerType() {
        return new HandlerTypeA();
    }

    @Override
    public void printType(String type) {
        System.out.println(this.getClass().getName()+" print type: "+type);
    }
}
