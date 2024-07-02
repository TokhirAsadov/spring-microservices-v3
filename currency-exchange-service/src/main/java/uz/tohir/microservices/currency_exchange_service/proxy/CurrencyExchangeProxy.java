package uz.tohir.microservices.currency_exchange_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency-conversion-service",url = "localhost:8000")
public interface CurrencyExchangeProxy {



}
