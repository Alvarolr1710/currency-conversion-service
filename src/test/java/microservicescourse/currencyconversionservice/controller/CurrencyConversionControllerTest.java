package microservicescourse.currencyconversionservice.controller;


import microservicescourse.currencyconversionservice.IntegrationTestBase;
import org.apache.http.HttpStatus;
import org.hamcrest.core.Is;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

public class CurrencyConversionControllerTest extends IntegrationTestBase {

    @Test
    public void shouldReturnCurrencyConversionUsingExchangeAPI() {
        response = given().log().all()
                .when().get("currency-converter-API/from/USD/to/GBP/quantity/20000").thenReturn();
        response.then().log().all();

        assertThat(response.statusCode(), Is.is(HttpStatus.SC_OK));
        assertThat(response.getBody().asString().contains("from"), Is.is(true));
        assertThat(response.getBody().asString().contains("to"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionValue"), Is.is(true));
        assertThat(response.getBody().asString().contains("quantity"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionResult"), Is.is(true));
    }

    @Test
    public void shouldReturnCurrencyConversionUsingExchange() {
        response = given().log().all()
                .when().get("currency-converter/from/USD/to/GBP/quantity/20000").thenReturn();
        response.then().log().all();

        assertThat(response.statusCode(), Is.is(HttpStatus.SC_OK));
        assertThat(response.getBody().asString().contains("from"), Is.is(true));
        assertThat(response.getBody().asString().contains("to"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionValue"), Is.is(true));
        assertThat(response.getBody().asString().contains("quantity"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionResult"), Is.is(true));
    }

    @Test
    public void shouldReturnCurrencyConversionUsingExchangeFeign() {
        response = given().log().all()
                .when().get("currency-converter-feign/from/USD/to/GBP/quantity/20000").thenReturn();
        response.then().log().all();

        assertThat(response.statusCode(), Is.is(HttpStatus.SC_OK));
        assertThat(response.getBody().asString().contains("from"), Is.is(true));
        assertThat(response.getBody().asString().contains("to"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionValue"), Is.is(true));
        assertThat(response.getBody().asString().contains("quantity"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionResult"), Is.is(true));
    }

    @Test
    public void shouldReturnCurrencyConversionUsingExchangeAPIFeign() {
        response = given().log().all()
                .when().get("currency-converter-feign-API/from/USD/to/GBP/quantity/20000").thenReturn();
        response.then().log().all();

        assertThat(response.statusCode(), Is.is(HttpStatus.SC_OK));
        assertThat(response.getBody().asString().contains("from"), Is.is(true));
        assertThat(response.getBody().asString().contains("to"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionValue"), Is.is(true));
        assertThat(response.getBody().asString().contains("quantity"), Is.is(true));
        assertThat(response.getBody().asString().contains("conversionResult"), Is.is(true));
    }
}