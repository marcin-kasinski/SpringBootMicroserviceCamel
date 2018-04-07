package mk.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import mk.Book;

import mk.Database;

@Component
class RestApi extends RouteBuilder {

    @Override
    public void configure() {
    	
    	JacksonDataFormat format = new JacksonDataFormat(); 
    	// or JacksonDataFormat format = new JacksonDataFormat(); 
    	format.setUnmarshalType(Book.class);

    	
    	
        restConfiguration()
            .contextPath("/camel-rest-jpa").apiContextPath("/api-doc")
                .apiProperty("api.title", "Camel REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
            .bindingMode(RestBindingMode.json);

        rest("/books").description("Books REST service")
            .get("/").description("The list of all the books")
                .route().routeId("books-api")
                .bean(Database.class, "findBooks")
                .endRest()
            
                .get("order/{id}").description("Details of an order by id")
                .route().routeId("order-api")
                .bean(Database.class, "findOrder(${header.id})")
                .endRest()
            
                .post("addbook").description("add book").outType(Book.class)
                .bindingMode(RestBindingMode.json)
                .type(Book.class)
                .route().routeId("add book")
//        .unmarshal(format).json(JsonLibrary.Jackson, Book.class)
//        .unmarshal(format)
        .to("jpa:mk.Book").process(new MKProcessor())
        ;
    }
}