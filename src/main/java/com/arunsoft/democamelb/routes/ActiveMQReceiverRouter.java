package com.arunsoft.democamelb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson,CurrencyExchange.class)
                .to("log:received-message-from-active-mq");
    }
}
