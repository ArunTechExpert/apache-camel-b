package com.arunsoft.democamelb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:first-timer") //queue
                .to("log:first-timer"); //database
    }
}
