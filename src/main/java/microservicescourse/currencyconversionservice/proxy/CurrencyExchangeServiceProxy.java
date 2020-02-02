package microservicescourse.currencyconversionservice.proxy;

import microservicescourse.currencyconversionservice.currencyconversion.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("currency-exchange-API/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValueUsingAPI(@PathVariable String from, @PathVariable String to);

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
