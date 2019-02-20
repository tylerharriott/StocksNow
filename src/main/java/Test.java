import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) throws IOException {
        Stock stock = YahooFinance.get("ntdoy");



        BigDecimal percentChange = stock.getQuote().getChangeInPercent();
        BigDecimal dayHigh = stock.getQuote().getDayHigh();
        BigDecimal dayLow = stock.getQuote().getDayLow();
        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
        dividend = dividend.setScale(2, RoundingMode.CEILING);



    }
}
