package uz.tohir.microservices.currency_exchange_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tohir.microservices.currency_exchange_service.entity.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    CurrencyExchange findByFromAndTo(String from, String to);

}
