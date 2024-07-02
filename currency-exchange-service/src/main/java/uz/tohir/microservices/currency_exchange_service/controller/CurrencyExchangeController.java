package uz.tohir.microservices.currency_exchange_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tohir.microservices.currency_exchange_service.entity.CurrencyExchange;
import uz.tohir.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,
                                                  @PathVariable("to") String to){
//        CurrencyExchange currencyExchange = new CurrencyExchange(
//                1000L,
//                from,
//                to,
//                BigDecimal.valueOf(50)
//        );

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange==null){
            throw new RuntimeException("Unable to Find data for "+from+" to "+to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

}
