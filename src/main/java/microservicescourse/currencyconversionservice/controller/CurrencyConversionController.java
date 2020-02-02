package microservicescourse.currencyconversionservice.controller;

import microservicescourse.currencyconversionservice.currencyconversion.CurrencyConversion;
import microservicescourse.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    private CurrencyConversion currencyConversion;

    @GetMapping("currency-converter-API/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversion convertCurrencyUsingAPIRestTemplate(@PathVariable String from,
                                                                  @PathVariable String to,
                                                                  @PathVariable String amount) throws IOException, JSONException {
        String URL = "http://localhost:8000/currency-exchange-API/from/{from}/to/{to}";
        return getCurrencyConversion(from, to, amount, URL);
    }

    @GetMapping("currency-converter/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversion convertCurrencyRestTemplate(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable String amount) throws IOException, JSONException {
        String URL = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        return getCurrencyConversion(from, to, amount, URL);

    }

    @GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable String amount) throws IOException, JSONException {
        currencyConversion = proxy.retrieveExchangeValue(from, to);
        setCurrencyConversionResult(amount, currencyConversion);
        return currencyConversion;
    }

    @GetMapping("currency-converter-feign-API/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversion convertCurrencyAPIFeign(@PathVariable String from,
                                                      @PathVariable String to,
                                                      @PathVariable String amount) throws IOException, JSONException {
        currencyConversion = proxy.retrieveExchangeValueUsingAPI(from, to);
        setCurrencyConversionResult(amount, currencyConversion);
        return currencyConversion;
    }

    private void setCurrencyConversionResult(@PathVariable String amount, CurrencyConversion currencyConversion) {
        currencyConversion.setQuantity(amount);
        currencyConversion.setConversionResult();
    }

    private ResponseEntity<CurrencyConversion> getCurrencyConversionResponseEntity(@PathVariable String from,
                                                                                   @PathVariable String to, String URL) {
        return new RestTemplate().getForEntity(URL, CurrencyConversion.class, createUriVariables(from, to));
    }

    private CurrencyConversion getCurrencyConversion(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @PathVariable String amount, String URL) {
        ResponseEntity<CurrencyConversion> responseEntity = getCurrencyConversionResponseEntity(from, to, URL);
        return getCurrencyConversionResponseObject(amount, responseEntity);
    }

    private CurrencyConversion getCurrencyConversionResponseObject(@PathVariable String amount,
                                                                   ResponseEntity<CurrencyConversion> responseEntity) {
        CurrencyConversion currencyConversion = responseEntity.getBody();
        setCurrencyConversionResult(amount, Objects.requireNonNull(currencyConversion));
        return currencyConversion;
    }

    private Map<String, String> createUriVariables(@PathVariable String from, @PathVariable String to) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        return uriVariables;
    }

}
