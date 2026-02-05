package com.arunsoft.democamelb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {
    @Autowired
    private MyCurrencyExchangeTransformer  myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
//        from("activemq:my-activemq-queue")
//                .log("${body}")
//                .unmarshal().json(JsonLibrary.Jackson,CurrencyExchange.class)
//                .bean(myCurrencyExchangeTransformer, "processMessage")
//                .to("log:received-message-from-active-mq");
//        
        from("activemq:split-queue")
        .log("${body}")
        .to("log:received-message-from-active-mq");
        
        
    }
}

@Component
class MyCurrencyExchangeTransformer{
    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);

    public CurrencyExchange processMessage(CurrencyExchange exchange) {
        exchange.setConversionMultiple(exchange.getConversionMultiple().add(exchange.getConversionMultiple().multiply(BigDecimal.TEN)));
        logger.info(exchange.toString());
        return exchange;
    }
}
