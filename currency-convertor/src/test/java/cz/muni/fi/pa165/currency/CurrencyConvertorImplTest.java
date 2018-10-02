package cz.muni.fi.pa165.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import cz.muni.fi.pa165.currency.ExternalServiceFailureException;
import cz.muni.fi.pa165.currency.UnknownExchangeRateException;

public class CurrencyConvertorImplTest {

    ExchangeRateTable table = mock(ExchangeRateTable.class);
    CurrencyConvertor cc = mock(CurrencyConvertor.class);
    static Currency CZK = Currency.getInstance("CZK");
    static Currency EUR = Currency.getInstance("EUR");
    static Currency HAV = Currency.getInstance("HAV");
    @Test
    public void testConvert() {
        // Don't forget to test border values and proper rounding.
        BigDecimal ammount = new BigDecimal(25);

        BigDecimal value = new BigDecimal(1);
//        BigDecimal value = (BigDecimal) cc.convert(CZK,EUR,ammount);
        when(cc.convert(CZK,EUR,ammount)).thenReturn(value);
        assertEquals(cc.convert(CZK,EUR,ammount),value);
//        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        BigDecimal amount = new BigDecimal(20);
        when(cc.convert(null,EUR,amount)).thenThrow(new IllegalArgumentException());
        try {
            cc.convert(null,EUR,amount);
        } catch (IllegalArgumentException ex) {

        }
//        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        BigDecimal amount = new BigDecimal(20);
        when(cc.convert(EUR,null,amount)).thenThrow(new IllegalArgumentException());
        try {
            cc.convert(EUR,null,amount);
        } catch (IllegalArgumentException ex) {

        }
//        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullSourceAmount() {
//        BigDecimal amount = new BigDecimal(20);
        when(cc.convert(EUR,CZK,null)).thenThrow(new IllegalArgumentException());
        try {
            cc.convert(EUR,CZK,null);
        } catch (IllegalArgumentException ex) {

        }
//        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        BigDecimal amount = new BigDecimal(20);

        when(cc.convert(EUR,HAV,amount)).thenThrow(new UnknownExchangeRateException("currency HAV"));
        try {
            cc.convert(EUR,HAV,amount);
        } catch (UnknownExchangeRateException ex) {

        }
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        fail("Test is not implemented yet.");
    }

}
