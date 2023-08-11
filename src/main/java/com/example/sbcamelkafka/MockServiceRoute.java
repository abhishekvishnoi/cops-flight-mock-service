package com.example.sbcamelkafka;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MockServiceRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /**
         * Rest configuration for the Rest API .
         */
        restConfiguration()
                .enableCORS(true)
                .component("jetty")
                .host("0.0.0.0")
                .port(8082);

        /**
         * A Mock Rest service to that return the flight Data.
         */
        rest("/mock")
                .get("/flightData")
                .to("direct:rerunFlightReportGeneration");

        from("direct:rerunFlightReportGeneration")
                .routeId("rerunFlightReportGeneration")
                .setBody(simple("{{body}}"))
                .log("Received an  request for Flight Data.");

    }
}
