package uz.tohir.microservices.currency_exchange_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

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

        //INFO [currency-exchange-service] [nio-8000-exec-1] [06fed4db10961d3d477d19834579019d-50fffeba275fc699] u.t.m.c.c.CurrencyExchangeController     : retrieveExchangeValue: from=USD, to=SUM
        logger.info("retrieveExchangeValue: from={}, to={}", from, to);

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange==null){
            throw new RuntimeException("Unable to Find data for "+from+" to "+to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

}
