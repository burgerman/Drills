package com.wil.practice.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DefaultHandlerType implements Handler{

    @Bean
    @Primary
    public Handler handlerType() {
        return new DefaultHandlerType();
    }

    @Override
    public void printType(String type) {
        System.out.println(this.getClass().getName()+" print type: "+type);
    }
}
