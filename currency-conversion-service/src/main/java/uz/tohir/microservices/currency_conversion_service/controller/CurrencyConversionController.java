package uz.tohir.microservices.currency_conversion_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tohir.microservices.currency_conversion_service.entity.CurrencyConversion;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity){


        return new CurrencyConversion(
                10001L,
                from,
                to,
                quantity,
                BigDecimal.ONE,
                BigDecimal.ONE,
                ""
        );
    }

}
