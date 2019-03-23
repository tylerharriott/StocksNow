import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) throws IOException {
        Stock stock = YahooFinance.get("dgaz");



        BigDecimal percentChange = stock.getQuote().getChangeInPercent();
        BigDecimal dayHigh = stock.getQuote().getDayHigh();
        BigDecimal dayLow = stock.getQuote().getDayLow();
        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();


        System.out.println(percentChange + "\n" + dayHigh + "\n" + dayLow + "\n" + dividend);





    }
}

