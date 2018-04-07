package mk.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class Backend extends RouteBuilder {

    @Override
    public void configure() {
        // A first route generates some orders and queue them in DB
        from("timer:new-order?delay=1s&period={{example.generateOrderPeriod:30s}}")
            .routeId("generate-order")
            .bean("orderService", "generateOrder")
            .to("jpa:mk.Order")
            .log("Inserted new order ${body.id}");

        // A second route polls the DB for new orders and processes them
        from("jpa:mk.Order"
            + "?consumer.namedQuery=new-orders"
            + "&consumer.delay={{example.processOrderPeriod:30s}}"
            + "&consumeDelete=false")
            .routeId("process-order")
            .log("Processed order #id ${body.id} with ${body.amount} copies of the �${body.book.description}� book");
    }
}