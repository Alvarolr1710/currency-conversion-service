package microservicescourse.currencyconversionservice.currencyconversion;

import java.math.BigDecimal;

public class CurrencyConversion {

    private Long id;
    private String from;
    private String to;
    private String conversionValue;
    private String quantity;
    private String conversionResult;
    private int port;

    public CurrencyConversion() {
    }

    public CurrencyConversion(Long id, String from, String to, String conversionValue, int port, String quantity) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.quantity = quantity;
        this.conversionValue = conversionValue;
        this.port = port;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult() {
        Double aDouble = new Double(quantity);
        Double aDouble1 = new Double(conversionValue);
        this.conversionResult = String.valueOf(aDouble * aDouble1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(String conversionValue) {
        this.conversionValue = conversionValue;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
