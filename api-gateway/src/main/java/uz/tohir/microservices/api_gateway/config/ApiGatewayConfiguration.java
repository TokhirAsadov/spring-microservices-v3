package uz.tohir.microservices.api_gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {


//        Function<PredicateSpec, Buildable<Route>> routeFunction
//                = p -> p.path("/get")
//                .filters(f -> f
//                        .addRequestHeader("MyHeader","MyURI")
//                        .addRequestParameter("Param","MyParam")
//                )
//                .uri("http://httpbin.org:80");


        return builder.routes()
                .route(p -> p.path("/get") // first custom route
                        .filters(f -> f
                                .addRequestHeader("MyHeader","MyURI")
                                .addRequestParameter("Param","MyParam")
                        )
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")  // second custom route connected with currency-exchange-service
                        .uri("lb://currency-exchange-service")
                )
                .route(p -> p.path("/currency-conversion/**")  // third custom route connected with currency-conversion-service
                        .uri("lb://currency-conversion-service")
                )
                .route(p -> p.path("/currency-conversion-new/**")  // fourth custom route connected with currency-conversion-service and also rewrite path
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion/${segment}"
                        ))
                        .uri("lb://currency-conversion-service")
                )
                .build();
    }
}
