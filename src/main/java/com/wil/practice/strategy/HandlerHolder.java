package com.wil.practice.strategy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.ws.rs.PathParam;
import java.util.Map;

public class HandlerHolder {

    public enum HandlerTypes {
        DEFAULT_TYPE("defaultHandlerType");

        private final String defaultHandlerType;

        HandlerTypes(String defaultHandlerType) {
            this.defaultHandlerType = defaultHandlerType;
        }
    }

    private static boolean supports(String obj, String type) {
        return obj.toLowerCase().startsWith(type.toLowerCase()) || obj.toLowerCase().endsWith(type.toLowerCase());
    }

    public static Handler getHandler(String type) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HandlerHolder.class.getPackage().getName());
        Map<String, Handler> map = context.getBeansOfType(Handler.class);
        System.out.println(map.keySet());
        for(String str : map.keySet()) {
            if(supports(str, type)) {
                return map.get(str);
            }
        }
        System.out.println(HandlerTypes.DEFAULT_TYPE.defaultHandlerType);
        return map.get(HandlerTypes.DEFAULT_TYPE.defaultHandlerType);
    }



}
