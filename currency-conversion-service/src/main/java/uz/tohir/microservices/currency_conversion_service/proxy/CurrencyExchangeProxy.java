package uz.tohir.microservices.currency_conversion_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.tohir.microservices.currency_conversion_service.entity.CurrencyConversion;

//@FeignClient(name = "currency-exchange-service",url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,
                                                    @PathVariable("to") String to);

}
