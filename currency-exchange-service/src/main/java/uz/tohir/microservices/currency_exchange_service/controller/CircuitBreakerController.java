package uz.tohir.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse"/* method name which should exist below*/) //resilience4j.retry.instances.[sample-api].maxAttempts=5
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse"/* method name which should exist below*/) //resilience4j.retry.instances.[sample-api].maxAttempts=5
    //@RateLimiter(name = "default") // 10s => 10000 calls to the sample-api
    //@Bulkhead(name = "default")
    public String sampleApi() {
        logger.info("Sample Api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/circuit-breaker", String.class);
        return forEntity.getBody(); //"Sample API";
    }

    public String hardcodedResponse(Exception ex) { // we can also override hardcodedResponse to be able to more flexible another errors sucha as Runtime, NOT_FOUND
        return "fallback-response by Takhir";
    }
}
